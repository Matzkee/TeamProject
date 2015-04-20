package com.example.hou.nct_2;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import android.widget.Switch;
import android.widget.TextView;
import com.example.hou.tools.NctTools;
import com.example.hou.tools.ResultDateInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class TestingResultDate extends Activity {

    ImageView title_bar_menu_btn;
    Button resultSubmit;

    TextView Alignment;
    TextView Suspension ;
    TextView Brakes ;
    TextView Exhaust_Emission ;
    TextView Head_Lights;

    TextView Name;
    TextView Address;
    TextView Email;
    TextView Make;
    TextView Model;
    TextView MakeDate;
    TextView TestDate;
    private String IP;
    String Car_Reg;
    String Owner_Id;
    private ProgressDialog proDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing_result_date);
        IP = getResources().getString(R.string.IP);

        Intent intent=getIntent();
        Car_Reg=intent.getStringExtra("Car_Reg");
        Owner_Id=intent.getStringExtra("Owner_Id");

        /**
         * TestResultShow
         */
        proDialog = ProgressDialog.show(TestingResultDate.this, "Connecting..",
                "Connecting..please wait....", true, true);
        GetCSDNLogoTask task = new GetCSDNLogoTask();
        task.execute(IP+"TestResultDateShow?Car_Reg="+Car_Reg+"&Owner_Id="+Owner_Id,"TestResultDateShow");

        title_bar_menu_btn = (ImageView)findViewById(R.id.title_bar_menu_btn);


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
        Name = (TextView)this.findViewById(R.id.Name);
        Address = (TextView)this.findViewById(R.id.Address);
        Email = (TextView)this.findViewById(R.id.Email);
        Make = (TextView)this.findViewById(R.id.Make);
        Model = (TextView)this.findViewById(R.id.Model);
        MakeDate = (TextView)this.findViewById(R.id.MakeDate);
        TestDate = (TextView)this.findViewById(R.id.TestDate);



        //返回DetectionResult
        resultSubmit = (Button)findViewById(R.id.resultSubmit);

    }

    class GetCSDNLogoTask extends AsyncTask<String,Integer,List<Object>> {//继承AsyncTask

        @Override
        protected List<Object> doInBackground(String... params) {//处理后台执行的任务，在后台线程执行
            String validateURL = params[0];
            String Task = params[1];
            List list1 = new ArrayList<Object>();

            if (Task == "TestResultDateShow") {
                try {
                    System.out.println("validateURL==="+validateURL);
                    //http://10.0.0.11:8080/NCT/servlet/TestResultShow?Car_Reg=9TFR4AHS
                    JSONArray list = new NctTools().getJSONArray(validateURL);
                    for (int a = 0; a < list.length(); a++) {
                        JSONObject temp = (JSONObject) list.get(a);
                        ResultDateInfo resultDateInfo = new ResultDateInfo();
                        resultDateInfo.setName(temp.getString("Name"));
                        resultDateInfo.setAddress(temp.getString("Address"));
                        resultDateInfo.setEmail(temp.getString("Email"));
                        resultDateInfo.setMake(temp.getString("Make"));
                        resultDateInfo.setModel(temp.getString("Model"));
                        resultDateInfo.setMadeDate(temp.getString("MadeDate"));
                        resultDateInfo.setTestDate(temp.getString("TestDate"));
                        resultDateInfo.setAlignment(temp.getString("Alignment"));
                        resultDateInfo.setSuspension(temp.getString("Suspension"));
                        resultDateInfo.setBrakes(temp.getString("Brakes"));
                        resultDateInfo.setExhaust_Emission(temp.getString("Exhaust_Emission"));
                        resultDateInfo.setHead_Lights(temp.getString("Head_Lights"));


                        list1.add(resultDateInfo);
                    }
                    list1.add("TestResultDateShow");
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


            return list1;
        }

        protected void onPostExecute(List<Object> result) {//后台任务执行完之后被调用，在ui线程执行
            if (result != null) {

                String Task = (String)result.get(result.size()-1);
                if(Task == "TestResultDateShow"){
                    ResultDateInfo resultDateInfo = new ResultDateInfo();

                    resultDateInfo = (ResultDateInfo)result.get(0);

                    Name.setText("  "+resultDateInfo.getName());
                    Address.setText("  "+resultDateInfo.getAddress());
                    Email.setText("  "+resultDateInfo.getName());
                    Make.setText("  "+resultDateInfo.getMake());
                    Model.setText("  "+resultDateInfo.getModel());
                    MakeDate.setText("  "+resultDateInfo.getMadeDate());
                    TestDate.setText("  "+resultDateInfo.getTestDate());

                    Alignment.setText("  "+resultDateInfo.getAlignment()== "1"?"Pass":"Fail");
                    switch (Alignment.getText().toString()) {
                        case "  Pass" : Alignment.setTextColor(getResources().getColor(R.color.color_8));
                            break;
                        case  "  Fail" :Alignment.setTextColor(getResources().getColor(R.color.color_9));
                            break;
                    }
                    Suspension.setText("  "+resultDateInfo.getSuspension()== "1"?"Pass":"Fail");
                    Brakes.setText("  "+resultDateInfo.getBrakes()== "1"?"Pass":"Fail");
                    Exhaust_Emission.setText("  "+resultDateInfo.getExhaust_Emission()== "1"?"Pass":"Fail");
                    Head_Lights.setText("  "+resultDateInfo.getHead_Lights()== "1"?"Pass":"Fail");

                    proDialog.dismiss();
                }

                if(Task == "TestResultFeedback"){
                    String isOk = (String)result.get(0);
                    proDialog.dismiss();
                }
            }
        }
    }
}