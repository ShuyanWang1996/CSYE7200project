import pymongo
import pandas
import matplotlib
import numpy
import json
from pandas import Series,DataFrame
from matplotlib import pyplot as plt
from sklearn.linear_model import LinearRegression
from sklearn.preprocessing import PolynomialFeatures

client = pymongo.MongoClient('49.235.244.219',27017)
dbname = client['csye7200']
collection = dbname['test_data_US']
#collection = dbname['train_data_China']
data = DataFrame(list(collection.find({'total_confirmed':{'$gt':10000}})))
data = data.reset_index()
data['index'] = range(len(data))
print(data)

x = data[["index"]]
y = data[["total_confirmed"]]
poly_reg =PolynomialFeatures(degree=2)
X_ploy =poly_reg.fit_transform(x)
lin_reg_2=LinearRegression()
lin_reg_2.fit(X_ploy,y)

## print result
# print('Cofficients:',lin_reg_2.coef_)
# print('intercept',lin_reg_2.intercept_)
# plt.scatter(x,y,color='red')
# plt.plot(x,lin_reg_2.predict(poly_reg.fit_transform(x)),color='blue')
# plt.xlabel('day')
# plt.ylabel('case')
# plt.show()

#regression China
# print("test")
# x1=pandas.DataFrame({'index':list(range(0,100))})
# result = lin_reg_2.predict(poly_reg.fit_transform(x1))
# #x2=pandas.DataFrame({'index':list(range(0,100)), 'Confirmed': lin_reg_2.predict(poly_reg.fit_transform(x1))})
# collection = dbname['regression_China']
# j=0
# for i in result:
#     collection.insert_one({"index":j,"Confirmed":i[0]})
#     j=j+1

#predict american
#y=-20.3807x^2+22219x-385852
def caculate(x):
    return int(-20.3807*x*x+22219*x-385852)
## print result
mx = list(range(0,1000))
#my= [caculate(i) for i in mx]
# plt.scatter(x,y,color='red')
# plt.plot(mx,my,color='blue')
# plt.xlabel('day')
# plt.ylabel('case')
# plt.show()

# collection = dbname['regression_US']
# for i in mx:
#     collection.insert_one({"index":i,"Confirmed":caculate(i)})