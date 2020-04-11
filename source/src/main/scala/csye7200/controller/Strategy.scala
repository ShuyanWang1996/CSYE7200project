package csye7200.controller

import com.mongodb.spark.MongoSpark
import csye7200.data.CsvParser
import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, Row, SparkSession}
import com.mongodb.spark.config._
import org.apache.spark.mllib.tree.DecisionTree
import org.apache.spark.mllib.tree.model.DecisionTreeModel
import org.apache.spark.mllib.util.MLUtils
import org.apache.spark.sql.types.{IntegerType, StructField, StructType}
import org.apache.spark.mllib.linalg.{Vector, Vectors}
import org.apache.spark.mllib.regression.LabeledPoint

class Strategy {

}

case class DTStrategy(df:DataFrame,sparks:SparkSession){
  def start(): Unit = {
    //add sequence
    val schema = df.schema.add(StructField("index", IntegerType))
    val dfRDD = df.rdd.zipWithIndex()
    val rowRDD = dfRDD.map(f => Row.merge(f._1, Row(f._2)))
    val df2 = sparks.createDataFrame(rowRDD, schema)
    val targetInd = df2.columns.indexOf("index")
    val featInd = List("total_confirmed").map(df.columns.indexOf(_))
    val mrdd = df2.rdd.map(r => LabeledPoint(
      r.getDouble(targetInd), // Get target value
      // Map feature indices to values
      Vectors.dense(featInd.map(r.getDouble(_)).toArray)
    ))
    // Split the data into training and test sets (30% held out for testing)
    val splits = mrdd.randomSplit(Array(0.7, 0.3))
    val (trainingData, testData) = (splits(0), splits(1))

    // Train a DecisionTree model.
    //  Empty categoricalFeaturesInfo indicates all features are continuous.
    val categoricalFeaturesInfo = Map[Int, Int]()
    val impurity = "variance"
    val maxDepth = 5
    val maxBins = 32

    val model = DecisionTree.trainRegressor(mrdd, categoricalFeaturesInfo, impurity, maxDepth, maxBins)

    // Evaluate model on test instances and compute test error
    val labelsAndPredictions = testData.map { point =>
      val prediction = model.predict(point.features)
      (point.label, prediction)
    }
    val testMSE = labelsAndPredictions.map{ case (v, p) => math.pow(v - p, 2) }.mean()
    println(s"Test Mean Squared Error = $testMSE")
    println(s"Learned regression tree model:\n ${model.toDebugString}")
  }
}