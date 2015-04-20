package com.example.hou.nct_2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hou.tools.Garage;
import com.example.hou.tools.BookingResult;
import com.example.hou.tools.NctTools;
import com.example.hou.tools.TestResultDate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Register extends Activity {
    private ImageView imageView;
    private Button CameraButton;
    private ImageView Photo;
    private ImageView title_bar_menu_btn;

    private EditText RegisterName;
    private EditText RegisterLoginName;
    private EditText RegisterPassword;
    private Spinner GarageInfo;
    private TextView GarageInfomation;
    private ArrayAdapter<String> adapter;
    private Button RegisterSubmit;
    public ArrayList<String> mArrayList1;
    private String IP;
    private  RadioGroup group;
    private TextView Role;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        IP = getResources().getString(R.string.IP);
        findViewsById();
        initValue();
        setOnClickListener();
        ArrayList<String> mArrayList1 = new ArrayList<String>();
    }

    private void findViewsById(){
        imageView = (ImageView)findViewById(R.id.title_bar_menu_btn);
        CameraButton = (Button)findViewById(R.id.CameraButton);
        Photo = (ImageView)findViewById(R.id.Photo);

        RegisterName = (EditText)findViewById(R.id.RegisterName1);
        RegisterLoginName = (EditText)findViewById(R.id.RegisterLoginName1);
        RegisterPassword = (EditText)findViewById(R.id.RegisterPassword1);
        GarageInfo = (Spinner)findViewById(R.id.Garage);
        GarageInfomation = (TextView)findViewById(R.id.GarageInfomation);
        RegisterSubmit = (Button)findViewById(R.id.RegisterSubmit);
        group = (RadioGroup)findViewById(R.id.RegisterRadioGroup);
        Role = (TextView)findViewById(R.id.Role);
        title_bar_menu_btn = (ImageView)findViewById(R.id.title_bar_menu_btn);

    };

    private void initValue() {
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup arg0, int arg1) {
                int radioButtonId = arg0.getCheckedRadioButtonId();
                //根据ID获取RadioButton的实例
                RadioButton rb = (RadioButton)Register.this.findViewById(radioButtonId);
                //更新文本内容，以符合选中项

                Role.setText(rb.getText());
            }
        });
        title_bar_menu_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        GetNCTDateTask task = new GetNCTDateTask();
          task.execute(IP+"Register?Type=Garage","Garage");


    }

    private void setOnClickListener(){
        CameraButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 1);
            }
        });

        RegisterSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Name =  RegisterName.getText().toString().trim();
                String LoginName = RegisterLoginName.getText().toString().trim();
                String PasseWord = RegisterPassword.getText().toString().trim();
                int GarageId = Integer.parseInt(GarageInfomation.getText().toString().trim())+1;
                String TypeId = Role.getText().toString().equals("Administrator")?"1":"2";

                GetNCTDateTask task = new GetNCTDateTask();
                task.execute(IP+"Register?Type=Register&Name="+Name+"&LoginName="+LoginName+"&PasseWord="+PasseWord+"&GarageId="+GarageId
                        +"&TypeId="+TypeId,"Register");
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {

            String sdStatus = Environment.getExternalStorageState();
            if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
                Log.v("TestFile",
                        "SD card is not avaiable/writeable right now.");
                return;
            }

            Bundle bundle = data.getExtras();
            Bitmap bitmap = (Bitmap) bundle.get("data");// 获取相机返回的数据，并转换为Bitmap图片格式
            FileOutputStream b = null;
            File file = new File("/sdcard/myImage/");
            file.mkdirs();// 创建文件夹
            String fileName = "/sdcard/myImage/111.jpg";

            try {
                b = new FileOutputStream(fileName);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    b.flush();
                    b.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            ((ImageView) findViewById(R.id.Photo)).setImageBitmap(bitmap);// 将图片显示在ImageView里
        }
    }

    //使用数组形式操作
    class SpinnerSelectedListener implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                                   long arg3) {
            GarageInfomation.setText(" "+arg2);
        }

        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }

     class GetNCTDateTask extends AsyncTask<String,Integer,List<Object>> {//继承AsyncTask

     @Override
     protected List<Object> doInBackground(String... params) {//处理后台执行的任务，在后台线程执行
         String validateURL = params[0];
         String Task = params[1];
         List<Object> list1 = new ArrayList<Object>();
         JSONArray list = null;

         if (Task == "Garage") {
             try {
                 list = new NctTools().getJSONArray(validateURL);
             } catch (IOException e) {
                 e.printStackTrace();
             }
             for (int a = 0; a < list.length()-1; a++) {
                 try {
                     JSONObject temp = (JSONObject) list.get(a);
                     Garage garage = new Garage();
                     garage.setAddress(temp.getString("Address"));
                     garage.setGarageId(temp.getString("GarageId"));
                     list1.add(garage);
                 } catch (JSONException e) {
                     e.printStackTrace();
                 }
             }
             System.out.println("进入Garage===================");

             list1.add("Garage");
             }

             if (Task == "Register") {
                 try {
                     System.out.println("Register===validateURL===="+validateURL);
                     list = new NctTools().getJSONArray(validateURL);
                 } catch (IOException e) {
                     e.printStackTrace();
                 }

                 for (int a = 0; a < list.length(); a++) {
                     try {
                         JSONObject temp = (JSONObject) list.get(a);

                         String State = temp.getString("State");
                         list1.add(State);
                     } catch (JSONException e) {
                         e.printStackTrace();
                     }
                 }
                 list1.add("Register");
             }
             return list1;

     }

     protected void onPostExecute(final List<Object> result) {//后台任务执行完之后被调用，在ui线程执行
         ArrayList<String> mArrayList = new ArrayList<String>();
         ArrayList<String> GarageIdList = new ArrayList<String>();
         if(result != null) {

              String Task = (String)result.get(result.size()-1);

                if(Task == "Garage") {
                     for (int a = 0; a <(result.size() - 1); a++) {
                         Garage garage = (Garage) result.get(a);
                         String address = garage.getAddress();
                         String GarageId = garage.getGarageId();
                         mArrayList.add(address);
                         GarageIdList.add(GarageId);
                       //  listGarageIds.add(address);
                     }
                     adapter = new ArrayAdapter<String>(Register.this, android.R.layout.simple_spinner_item, mArrayList);
                     //设置下拉列表的风格
                     adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                     //将adapter 添加到spinner中
                     GarageInfo.setAdapter(adapter);
                         //添加事件Spinner事件监听
                     GarageInfo.setOnItemSelectedListener(new SpinnerSelectedListener());
                     GarageInfo.setVisibility(View.VISIBLE);
                    mArrayList1 = GarageIdList;
                }

                if(Task == "Register"){
                    System.out.println("Register完成");
                   String State =(String)result.get(result.size() - 2);
                    new AlertDialog.Builder(Register.this)
                            .setTitle("Message")
                            .setMessage("Register succeed")
                            .setPositiveButton("ok", new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            }).show();

                }
             }
         }
     }
}



