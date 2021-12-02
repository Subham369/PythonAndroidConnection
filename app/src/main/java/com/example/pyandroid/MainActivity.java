package com.example.pyandroid;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import com.example.prediction.pyPrediction;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private static final int MY_PERMISSION_REQUEST_STORAGE=1;
    private EditText ed1,ed2,ed3,ed4;
    private TextView textView,textfile;
    private Button predict,reset;
    int requestcode=1;
    String file_name="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},MY_PERMISSION_REQUEST_STORAGE);
            }
            else{
                ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},MY_PERMISSION_REQUEST_STORAGE);
            }
        }
        else{

        }

        ed1=findViewById(R.id.editTextNumberDecimal);
        ed2=(EditText)findViewById(R.id.editTextNumberDecimal2);
        ed3=(EditText)findViewById(R.id.editTextNumberDecimal3);
        ed4=(EditText)findViewById(R.id.editTextNumberDecimal4);
        Spinner spinner = findViewById(R.id.spinner);
        textfile=findViewById(R.id.textfile);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.numbers, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        textView= findViewById(R.id.textView);
        predict=(Button)findViewById(R.id.button);
        reset=(Button)findViewById(R.id.button2);

        if (! Python.isStarted()) {
            Python.start(new AndroidPlatform(this));
        }

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed1.getText().clear();
                ed2.getText().clear();
                ed3.getText().clear();
                ed4.getText().clear();
                ed4.clearFocus();
                textView.setText("");
            }
        });

        predict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ed1.getText().length()!=0&&ed2.getText().length()!=0&&ed3.getText().length()!=0&&ed4.getText().length()!=0)
                {
                    float v1= Float.parseFloat(ed1.getText().toString());
                    float v2= Float.parseFloat(ed2.getText().toString());
                    float v3= Float.parseFloat(ed3.getText().toString());
                    float v4= Float.parseFloat(ed4.getText().toString());

                    switch (spinner.getSelectedItem().toString())
                    {
                        case  "Random Forest Model":
                            Toast.makeText(getApplicationContext(),"Prediction Using Random Forest Model",Toast.LENGTH_SHORT).show();
                            String ans1= pyPrediction.random_forest(MainActivity.this,v1,v2,v3,v4);
                            textView.setText(ans1);
                            break;
                        case "Decision Tree Model":
                            Toast.makeText(getApplicationContext(),"Prediction Using Decision Tree Model",Toast.LENGTH_SHORT).show();
                            //decision_tree(v1,v2,v3,v4);
                            String ans2= pyPrediction.decision_tree(MainActivity.this,v1,v2,v3,v4);
                            textView.setText(ans2);
                            break;
//                        case "XG Boost Model":
//                            Toast.makeText(getApplicationContext(),"Prediction Using XG Boost Model",Toast.LENGTH_SHORT).show();
//                            xgboostcls(v1,v2,v3,v4);
//                            break;
                        case "Extra Tree Model":
                            Toast.makeText(getApplicationContext(),"Prediction Using Extra Tree Model",Toast.LENGTH_SHORT).show();
                            //predict();
                            String ans3= pyPrediction.extratree(MainActivity.this,v1,v2,v3,v4);
                            textView.setText(ans3);
                            break;
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Please Fill Data For Prediction of Flower", Toast.LENGTH_SHORT).show();
                }
            }
        });





    }



    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (i)
        {
            case  0:
                break;
            case 1:
                break;
            case 2:
                break;
//            case 3:
//                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

        Toast.makeText(this,"Nothing is Selected",Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Context context=getApplicationContext();

        if(requestCode==requestcode && resultCode== Activity.RESULT_OK){
            if(data==null){
                return;
            }
            //ArrayList<MediaFile> mediafile=
            Uri uri=data.getData();
            file_name=uri.getPath();
            textfile.setText(file_name);
            copyAssets(file_name);
            Toast.makeText(this, file_name, Toast.LENGTH_SHORT).show();
        }
    }

    public void chooseFile(View view) {
        Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        startActivityForResult(intent,requestcode);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSION_REQUEST_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if(ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED){

                    }
                    else{
                        Toast.makeText(this, "No permission granted!!!", Toast.LENGTH_SHORT).show();
                    }
                }
        }
    }


    private void copyAssets(String f_name){
        String dirPath= Environment.getExternalStorageDirectory().getAbsolutePath()+"/MyFiles";
        File dir=new File(dirPath);
        if(!dir.exists()){
            dir.mkdirs();
        }

        AssetManager assetManager=getAssets();
        InputStream in=null;
        OutputStream out=null;

        try{
            in=assetManager.open(f_name);
            File outFile= new File(dirPath,f_name);
            out= new FileOutputStream(outFile);
            copyFile(in,out);
            Toast.makeText(this, "Saved !!!", Toast.LENGTH_SHORT).show();
        }

        catch(Exception e){
            Toast.makeText(this, "Failed !!!", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

        finally {
            if(in != null){
                try{
                    in.close();
                }
                catch(IOException e){
                    e.printStackTrace();
                }
            }

            if(out != null){
                try{
                    out.close();
                }
                catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    private void copyFile(InputStream io, OutputStream out) throws IOException {
        byte[] buffer= new byte[1024];
        int read;

        while((read=io.read(buffer)) != -1){
            out.write(buffer,0,read);
        }
    }

}