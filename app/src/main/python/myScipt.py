import pandas as pd
import numpy as np
from sklearn import datasets
from xgboost import XGBClassifier # Import XGBoost Classifier

from sklearn.preprocessing import LabelEncoder
def main(v1,v2,v3,v4):
    
    #from sklearn import datasets
    
    #from tensorflow.keras.utils import to_categorical
    #df = pd.read_csv("iris.csv")
    df = datasets.load_iris()

    #df = datasets.load_iris()
    # X = iris.data[:, :2]  # we only take the first two features.
    # y = iris.target

    #Extracting input and output variables from cvv file and putting it in X and Y 
    X = df.data[:, :4]
    Y = df.target


    #Extracting input and output variables from cvv file and putting it in X and Y 
    #X = df.iloc[:, :4].values
    #Y = df.iloc[:,4].values

    #Encoding Y Variable into numerical values from string
    #le= LabelEncoder()
    #Y=le.fit_transform(Y)
    #Y=to_categorical(Y)

    from sklearn.tree import DecisionTreeClassifier # Import Decision Tree Classifier
    from sklearn.model_selection import train_test_split # Import train_test_split function
    X_train, X_test, y_train, y_test = train_test_split(X, Y, test_size=0.3, random_state=1)


    # Create Decision Tree classifer object
    clf = DecisionTreeClassifier()

    # Train Decision Tree Classifer
    clf.fit(X_train,y_train)

    result=clf.predict([[v1,v2,v3,v4]])
    #val1=str(result[0][0])
    #val2=str((result[0][1]))
    #val3=str((result[0][2]))
    ans=result[0]
    return ans


def randomforest(v1,v2,v3,v4):
    
    #from sklearn import datasets
    
    #from tensorflow.keras.utils import to_categorical
    #df = pd.read_csv("iris.csv")
    df = datasets.load_iris()

    #df = datasets.load_iris()
    # X = iris.data[:, :2]  # we only take the first two features.
    # y = iris.target

    #Extracting input and output variables from cvv file and putting it in X and Y 
    X = df.data[:, :4]
    Y = df.target


    #Extracting input and output variables from cvv file and putting it in X and Y 
    #X = df.iloc[:, :4].values
    #Y = df.iloc[:,4].values

    #Encoding Y Variable into numerical values from string
    #le= LabelEncoder()
    #Y=le.fit_transform(Y)
    #Y=to_categorical(Y)

    from sklearn.ensemble import RandomForestClassifier # Import Random Forest Classifier
    from sklearn.model_selection import train_test_split # Import train_test_split function
    X_train, X_test, y_train, y_test = train_test_split(X, Y, test_size=0.3, random_state=1)


    # Create Random Forest classifer object
    clf = RandomForestClassifier(random_state=42, n_jobs=-1, max_depth=5,n_estimators=100, oob_score=True)

    # Train Random Forest Classifer
    clf.fit(X_train,y_train)

    result=clf.predict([[v1,v2,v3,v4]])
    #val1=str(result[0][0])
    #val2=str((result[0][1]))
    #val3=str((result[0][2]))
    ans=result[0]
    return ans
    

def extratree(v1,v2,v3,v4):

    #from sklearn import datasets
    
    #from tensorflow.keras.utils import to_categorical
    #df = pd.read_csv("iris.csv")
    df = datasets.load_iris()

    #df = datasets.load_iris()
    # X = iris.data[:, :2]  # we only take the first two features.
    # y = iris.target

    #Extracting input and output variables from cvv file and putting it in X and Y 
    X = df.data[:, :4]
    Y = df.target


    #Extracting input and output variables from cvv file and putting it in X and Y 
    #X = df.iloc[:, :4].values
    #Y = df.iloc[:,4].values

    #Encoding Y Variable into numerical values from string
    #le= LabelEncoder()
    #Y=le.fit_transform(Y)
    #Y=to_categorical(Y)

    from sklearn.ensemble import ExtraTreesClassifier  #Import Extra Tree Model
    from sklearn.model_selection import train_test_split # Import train_test_split function
    X_train, X_test, y_train, y_test = train_test_split(X, Y, test_size=0.3, random_state=1)


    # Create Extra Tree Model object
    clf = ExtraTreesClassifier(n_estimators=100, random_state=0)

    # Train Extra Tree Model
    clf.fit(X_train,y_train)

    result=clf.predict([[v1,v2,v3,v4]])
    #val1=str(result[0][0])
    #val2=str((result[0][1]))
    #val3=str((result[0][2]))
    ans=result[0]
    return ans


def xgboostcls(v1,v2,v3,v4):

    #This code converts iris dataset using sequential model in TFLite format
    #So that we can use it in android
    
    #from sklearn import datasets
    
    #from tensorflow.keras.utils import to_categorical
    #df = pd.read_csv("iris.csv")
    df = datasets.load_iris()

    #df = datasets.load_iris()
    # X = iris.data[:, :2]  # we only take the first two features.
    # y = iris.target

    #Extracting input and output variables from cvv file and putting it in X and Y 
    X = df.data[:, :4]
    Y = df.target


    #Extracting input and output variables from cvv file and putting it in X and Y 
    #X = df.iloc[:, :4].values
    #Y = df.iloc[:,4].values

    #Encoding Y Variable into numerical values from string
    #le= LabelEncoder()
    #Y=le.fit_transform(Y)
    #Y=to_categorical(Y)

    from sklearn.ensemble import ExtraTreesClassifier
    from sklearn.model_selection import train_test_split # Import train_test_split function
    X_train, X_test, y_train, y_test = train_test_split(X, Y, test_size=0.3, random_state=1)


    # Create XGBoost classifer object
    clf =  XGBClassifier()

    # Train XGBoost classifer object
    clf.fit(X_train,y_train)

    result=clf.predict([[v1,v2,v3,v4]])
    #val1=str(result[0][0])
    #val2=str((result[0][1]))
    #val3=str((result[0][2]))
    ans=result[0]
    return ans
    
    

