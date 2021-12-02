package com.example.prediction;

import android.content.Context;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;

public class pyPrediction {

    public static String decision_tree(Context context, float v1, float v2, float v3, float v4) {
        Python py= Python.getInstance();
        PyObject pyObject=py.getModule("myScipt");
        PyObject obj=pyObject.callAttr("main",v1,v2,v3,v4);
        String str="";
        if(obj.toInt()==0){
            str+="Iris Setosa :1"+"\nIris Versicolour :0 "+"\nIris Virginica :0";
        }

        else if(obj.toInt()==1){
            str+="Iris Setosa :0"+"\nIris Versicolour :1 "+"\nIris Virginica :0";
        }

        if(obj.toInt()==2){
            str+="Iris Setosa :0"+"\nIris Versicolour :0 "+"\nIris Virginica :1";
        }
        return str;
    }

    public static String extratree(Context context,float v1, float v2, float v3, float v4) {
        Python py= Python.getInstance();
        PyObject pyObject=py.getModule("myScipt");
        PyObject obj=pyObject.callAttr("extratree",v1,v2,v3,v4);
        String str="";
        if(obj.toInt()==0){
            str+="Iris Setosa :1"+"\nIris Versicolour :0 "+"\nIris Virginica :0";
        }

        else if(obj.toInt()==1){
            str+="Iris Setosa :0"+"\nIris Versicolour :1 "+"\nIris Virginica :0";
        }

        if(obj.toInt()==2){
            str+="Iris Setosa :0"+"\nIris Versicolour :0 "+"\nIris Virginica :1";
        }
        return str;
    }

    public static String random_forest(Context context,float v1, float v2, float v3, float v4) {
        Python py= Python.getInstance();
        PyObject pyObject=py.getModule("myScipt");
        PyObject obj=pyObject.callAttr("randomforest",v1,v2,v3,v4);
        String str="";
        if(obj.toInt()==0){
            str+="Iris Setosa :1"+"\nIris Versicolour :0 "+"\nIris Virginica :0";
        }

        else if(obj.toInt()==1){
            str+="Iris Setosa :0"+"\nIris Versicolour :1 "+"\nIris Virginica :0";
        }

        if(obj.toInt()==2){
            str+="Iris Setosa :0"+"\nIris Versicolour :0 "+"\nIris Virginica :1";
        }

        return str;
    }
}
