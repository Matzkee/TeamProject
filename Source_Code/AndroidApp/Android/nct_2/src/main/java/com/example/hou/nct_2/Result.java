package com.example.hou.nct_2;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.example.hou.tools.NctTools;
import com.example.hou.tools.TestingResultShow;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Result extends Activity {

    ImageView title_bar_menu_btn;
    LinearLayout Detection;
    Button isDetection;
    Button resultSubmit;

    TextView Alignment;
    TextView Suspension ;
    TextView Brakes ;
    TextView Exhaust_Emission ;
    TextView Head_Lights;

    TextView Car_Registration_ID;
    TextView BookingTime;
    TextView OwnerName;
    TextView Make;
    TextView Model;
    TextView MakeDate;

    private String IP;
    String Car_Reg;
    String User_Id;
    String Owner_Id;
    String Garage_Id;
    private ProgressDialog proDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        IP = getResources().getString(R.string.IP);

        Intent intent=getIntent();
        Car_Reg=intent.getStringExtra("Car_Reg");
        User_Id=intent.getStringExtra("User_Id");
        Garage_Id = intent.getStringExtra("Garage_Id");

        /**
         * TestResultShow
         */
        proDialog = ProgressDialog.show(Result.this, "Connecting..",
                "Connecting..please wait....", true, true);
        GetNCTValueTask task = new GetNCTValueTask();
        task.execute(IP+"TestResultShow?Car_Reg="+Car_Reg,"TestResultShow");

        title_bar_menu_btn = (ImageView)findViewById(R.id.title_bar_menu_btn);
        Detection = (LinearLayout)findViewById(R.id.Detection);
        Detection.setVisibility(View.GONE);
        isDetection = (Button)findViewById(R.id.isDetection);
        isDetection.setVisibility(View.VISIBLE) ;
        isDetection.setOnClickListener(new View.OnClickListener() { //设置是否显示检测过程
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(Result.this);
                builder.setMessage("Are you sure you want to start detection?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //Result.this.finish();
                                Detection.setVisibility(View.VISIBLE);
                                isDetection.setVisibility(View.INVISIBLE);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        }).show();
                AlertDialog alert = builder.create();
            }
        });
        //后退按钮
        title_bar_menu_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //根据ID找到该文本控件
        Alignment = (TextView)this.findViewById(R.id.alignment);
        Suspension = (TextView)this.findViewById(R.id.suspension);
        Brakes = (TextView)this.findViewById(R.id.brakes);
        Exhaust_Emission = (TextView)this.findViewById(R.id.exhaust_Emission);
        Head_Lights = (TextView)this.findViewById(R.id.head_Lights);

        /**
         * 显示show
         */
        Car_Registration_ID = (TextView)this.findViewById(R.id.Car_Registration_ID);
        BookingTime = (TextView)this.findViewById(R.id.BookingTime);
        OwnerName = (TextView)this.findViewById(R.id.OwnerName);
        Make = (TextView)this.findViewById(R.id.Make);
        Model = (TextView)this.findViewById(R.id.Model);
        MakeDate = (TextView)this.findViewById(R.id.MakeDate);

        //根据ID找到RadioGroup实例
        RadioGroup group = (RadioGroup)this.findViewById(R.id.radioGroup);
        RadioGroup group1 = (RadioGroup)this.findViewById(R.id.radioGroup1);
        RadioGroup group2 = (RadioGroup)this.findViewById(R.id.radioGroup2);
        RadioGroup group3 = (RadioGroup)this.findViewById(R.id.radioGroup3);
        RadioGroup group4 = (RadioGroup)this.findViewById(R.id.radioGroup4);
        //绑定一个匿名监听器
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup arg0, int arg1) {
                int radioButtonId = arg0.getCheckedRadioButtonId();
                //根据ID获取RadioButton的实例
                RadioButton rb = (RadioButton)Result.this.findViewById(radioButtonId);
                //更新文本内容，以符合选中项
                switch (rb.getText().toString()) {
                    case "PASS" : Alignment.setTextColor(getResources().getColor(R.color.color_8));
                        break;
                    case  "FAIL" :Alignment.setTextColor(getResources().getColor(R.color.color_9));
                        break;
                }
                Alignment.setText(rb.getText());
            }
        });
        group1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup arg0, int arg1) {
                int radioButtonId = arg0.getCheckedRadioButtonId();
                //根据ID获取RadioButton的实例
                RadioButton rb1 = (RadioButton)Result.this.findViewById(radioButtonId);
                //更新文本内容，以符合选中项
                switch (rb1.getText().toString()) {
                    case "PASS" : Suspension.setTextColor(getResources().getColor(R.color.color_8));
                        break;
                    case  "FAIL" :Suspension.setTextColor(getResources().getColor(R.color.color_9));
                        break;
                }
                Suspension.setText(rb1.getText());
            }
        });
        group2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup arg0, int arg1) {
                int radioButtonId = arg0.getCheckedRadioButtonId();
                //根据ID获取RadioButton的实例
                RadioButton rb2 = (RadioButton)Result.this.findViewById(radioButtonId);
                //更新文本内容，以符合选中项
                switch (rb2.getText().toString()) {
                    case "PASS" : Brakes.setTextColor(getResources().getColor(R.color.color_8));
                        break;
                    case  "FAIL" :Brakes.setTextColor(getResources().getColor(R.color.color_9));
                        break;
                }
                Brakes.setText( rb2.getText());
            }
        });
        group3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup arg0, int arg1) {
                int radioButtonId = arg0.getCheckedRadioButtonId();
                //根据ID获取RadioButton的实例
                RadioButton rb3 = (RadioButton)Result.this.findViewById(radioButtonId);
                //更新文本内容，以符合选中项
                switch (rb3.getText().toString()) {
                    case "PASS" : Exhaust_Emission.setTextColor(getResources().getColor(R.color.color_8));
                        break;
                    case  "FAIL" :Exhaust_Emission.setTextColor(getResources().getColor(R.color.color_9));
                        break;
                }
                Exhaust_Emission.setText( rb3.getText());
            }
        });
        group4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup arg0, int arg1) {
                int radioButtonId = arg0.getCheckedRadioButtonId();
                //根据ID获取RadioButton的实例
                RadioButton rb4 = (RadioButton)Result.this.findViewById(radioButtonId);
                //更新文本内容，以符合选中项
                switch (rb4.getText().toString()) {
                    case "PASS" : Head_Lights.setTextColor(getResources().getColor(R.color.color_8));
                        break;
                    case  "FAIL" :Head_Lights.setTextColor(getResources().getColor(R.color.color_9));
                        break;
                }
                Head_Lights.setText(rb4.getText());
            }
        });

        //返回DetectionResult
        resultSubmit = (Button)findViewById(R.id.resultSubmit);
        resultSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(Result.this);
                builder.setMessage("Are you sure you want to start detection?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                String validateURL = getDetectionResult();
                                proDialog = ProgressDialog.show(Result.this, "Connecting..",
                                        "Connecting..please wait....", true, true);
                                GetNCTValueTask task = new GetNCTValueTask();
                                task.execute(validateURL, "TestResultFeedback");
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        }).show();
                AlertDialog alert = builder.create();
            }
        });

    }

    public String getDetectionResult(){
        String alignment = Alignment.getText().toString();
        String suspension = Suspension.getText().toString();
        String brakes = Brakes.getText().toString();
        String exhaust_Emission = Exhaust_Emission.getText().toString();
        String head_Lights = Head_Lights.getText().toString();

        String validateURL = IP+"TestResultFeedback?alignment="+alignment+"&suspension="+suspension
                +"&brakes="+brakes+"&exhaust_Emission="+exhaust_Emission+"&head_Lights="+head_Lights+"&Car_Reg="+Car_Reg+"&User_Id="+User_Id+"&Owner_Id="+Owner_Id;
        return validateURL;
    }

    class GetNCTValueTask extends AsyncTask<String,Integer,List<Object>> {//继承AsyncTask

        @Override
        protected List<Object> doInBackground(String... params) {//处理后台执行的任务，在后台线程执行
            String validateURL = params[0];
            String Task = params[1];
            List list1 = new ArrayList<Object>();

            if (Task == "TestResultShow") {
                try {
                    //http://10.0.0.11:8080/NCT/servlet/TestResultShow?Car_Reg=9TFR4AHS
                    JSONArray list = new NctTools().getJSONArray(validateURL);
                    for (int a = 0; a < list.length(); a++) {

                        JSONObject temp = (JSONObject) list.get(a);
                        TestingResultShow testingResultShow = new TestingResultShow();
                        testingResultShow.setBook_Id(temp.getString("Book_Id"));
                        testingResultShow.setCar_Reg(temp.getString("Car_Reg"));
                        testingResultShow.setBookingTime(temp.getString("BookingTime"));
                        testingResultShow.setName(temp.getString("Name"));
                        testingResultShow.setMake(temp.getString("Make"));
                        testingResultShow.setModel(temp.getString("Model"));
                        testingResultShow.setMakeDate(temp.getString("MakeDate"));
                        testingResultShow.setOwner_Id(temp.getString("Owner_Id"));
                        list1.add(testingResultShow);
                    }
                    list1.add("TestResultShow");
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(Task == "TestResultFeedback") {
                try {
                    JSONArray list = new NctTools().getJSONArray(validateURL);
                    for(int a=0;a<list.length();a++){
                        JSONObject temp = (JSONObject) list.get(a);
                        String isOk = temp.getString("isOk");
                        list1.add(isOk);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                list1.add("TestResultFeedback");
            }
            return list1;
        }

        protected void onPostExecute(List<Object> result) {//后台任务执行完之后被调用，在ui线程执行
            if (result != null) {

                String Task = (String)result.get(result.size()-1);
                if(Task == "TestResultShow"){
                    TestingResultShow testingResultShow = new TestingResultShow();
                    testingResultShow = (TestingResultShow)result.get(0);

                    Car_Registration_ID.setText("  "+testingResultShow.getCar_Reg());
                    BookingTime.setText("  "+testingResultShow.getBookingTime());
                    OwnerName.setText("  "+testingResultShow.getName());
                    Make.setText("  "+testingResultShow.getMake());
                    Model.setText("  "+testingResultShow.getModel());
                    MakeDate.setText("  "+testingResultShow.getMakeDate());
                    Owner_Id = testingResultShow.getOwner_Id();

                    proDialog.dismiss();
                }

                if(Task == "TestResultFeedback"){
                    String isOk = (String)result.get(0);
                    proDialog.dismiss();

                        new AlertDialog.Builder(Result.this)
                                .setTitle("Message")
                                .setMessage("Detection succeed")
                                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        /**
                                         *Intent intent = new Intent();
                                         intent.setClass(Result.this, BookingLing.class);
                                         intent.putExtra("User_Id", User_Id);
                                         intent.putExtra("Car_Reg", Car_Reg);
                                         startActivity(intent);
                                         */

                                        finish();
                                    }
                                }).show();



                }
            }
        }
    }
}
