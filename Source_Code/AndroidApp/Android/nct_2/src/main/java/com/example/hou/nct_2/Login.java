package com.example.hou.nct_2;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


import com.example.hou.tools.NctTools;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;


public class Login extends Activity {

    private String userName;
    private String password;

    private EditText view_userName;
    private EditText view_password;
    private CheckBox view_rememberMe;
    private Button view_loginSubmit;
    private Button view_loginRegister;
    private ProgressDialog proDialog;
    private boolean isNetError;
    private Button loginRegister;

    String validateURL;

    String User_Id ="";
    String Type_Id ="";
    String Name ="";
    String LoginName ="";
    String Password ="";
    String Garage_Id ="";
    String json ="";

    /** 用来操作SharePreferences的标识 */
    private final String SHARE_LOGIN_TAG = "MAP_SHARE_LOGIN_TAG";

    /** 如果登录成功后,用于保存用户名到SharedPreferences,以便下次不再输入 */
    private String SHARE_LOGIN_USERNAME = "MAP_LOGIN_USERNAME";

    /** 如果登录成功后,用于保存PASSWORD到SharedPreferences,以便下次不再输入 */
    private String SHARE_LOGIN_PASSWORD = "MAP_LOGIN_PASSWORD";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViewsById();
        view_loginSubmit.setOnClickListener(submitListener);
        loginRegister = (Button)findViewById(R.id.loginRegister);
    }





    private void findViewsById() {
        view_userName = (EditText) findViewById(R.id.loginUserNameEdit);
        view_password = (EditText) findViewById(R.id.loginPasswordEdit);
        view_rememberMe = (CheckBox) findViewById(R.id.loginRememberMeCheckBox);
        view_loginSubmit = (Button) findViewById(R.id.loginSubmit);
        view_loginRegister = (Button) findViewById(R.id.loginRegister);
        view_loginRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Login.this, Register.class);
                startActivity(intent);
            }
        });
    }

    /** 登录后台通知更新UI线程,主要用于登录失败,通知UI线程更新界面 */
    Handler loginHandler = new Handler() {
        public void handleMessage(Message msg) {
            isNetError = msg.getData().getBoolean("isNetError");
            if (proDialog != null) {
                proDialog.dismiss();
            }
            if (isNetError) {
                Toast.makeText(Login.this, "Log in Fail:\n1.Please check network.\n2.Contact us.!",
                        Toast.LENGTH_SHORT).show();
            }
            // 用户名和密码错误
            else {
                Toast.makeText(Login.this, "Log in Fail,Please enter the correct user name and password!",
                        Toast.LENGTH_SHORT).show();
                // 清除以前的SharePreferences密码
                clearSharePassword();
            }
        }
    };

    private View.OnClickListener submitListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            proDialog = ProgressDialog.show(Login.this, "connecting..",
                    "connecting..please wait....", true, true);
            Thread loginThread = new Thread(new LoginFailureHandler());
            loginThread.start();
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private boolean validateLocalLogin(String userName, String password,
                                       String validateUrl) {
        // 用于标记登陆状态
        Boolean loginState = false;
        try {
            List list = new NctTools().getData(validateUrl);
            isNetError = (Boolean)list.get(0);
            json = (String)list.get(1);
            JSONArray arr = new JSONArray(json);
            for (int i = 0; i < arr.length(); i++) {
                JSONObject temp = (JSONObject) arr.get(i);
                User_Id = temp.getString("User_Id");
                Type_Id = temp.getString("Type_Id");
                Name = temp.getString("Name");
                LoginName = temp.getString("LoginName");
                Password = temp.getString("Password");
                Garage_Id=temp.getString("Garage_Id");
            }
            // 读取服务器的登录状态码
            //int loginStateInt = dis.readInt(); //标记1
            if ( arr.length() > 0) {
                loginState = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(this.toString(), e.getMessage() + "  127 line");
        }
        // 登陆成功
        if (loginState) {
            if (isRememberMe()) {
                saveSharePreferences(true, true);
            } else {
                saveSharePreferences(true, false);
            }
        } else {
            // 如果不是网络错误
            if (!isNetError) {
                clearSharePassword();
            }
        }
        if (!view_rememberMe.isChecked()) {
            clearSharePassword();
        }
        return loginState;
    }

    /** 记住我的选项是否勾选 */
    private boolean isRememberMe() {
        if (view_rememberMe.isChecked()) {
            return true;
        }
        return false;
    }

    /** 清除密码 */
    private void clearSharePassword() {
        SharedPreferences share = getSharedPreferences(SHARE_LOGIN_TAG, 0);
        share.edit().putString(SHARE_LOGIN_PASSWORD, "").commit();
        share = null;
    }

    /**
     * 如果登录成功过,则将登陆用户名和密码记录在SharePreferences
     *
     *
     * */
    private void saveSharePreferences(boolean saveUserName, boolean savePassword) {
        SharedPreferences share = getSharedPreferences(SHARE_LOGIN_TAG, 0);
        if (saveUserName) {
            Log.d(this.toString(), "saveUserName="
                    + view_userName.getText().toString());
            share.edit().putString(SHARE_LOGIN_USERNAME,
                    view_userName.getText().toString()).commit();
        }
        if (savePassword) {
            share.edit().putString(SHARE_LOGIN_PASSWORD,
                    view_password.getText().toString()).commit();
        }
        share = null;
    }


    class LoginFailureHandler implements Runnable {
        @Override
        public void run() {
            //userName = "vitae.aliquet@neque.org";
            //password = "MU2340071935994288237984478867";
            userName = view_userName.getText().toString().trim();
            password = view_password.getText().toString().trim();

            validateURL=getResources().getString(R.string.IP)+"Login?userName="
                    + userName + "&password=" + password;

            boolean loginState = validateLocalLogin(userName, password,
                    validateURL);
            Log.d(this.toString(), "validateLogin");

            // 登陆成功
            if (loginState) {

                Intent intent = new Intent();
                intent.setClass(Login.this, BookingLing.class);
                Bundle bundle = new Bundle();
                //bundle.putString("MAP_USERNAME", userName);

                bundle.putString("User_Id", User_Id);
                //bundle.putString("Type_Id", Type_Id);
                bundle.putString("Name", Name);
               // bundle.putString("LoginName", LoginName);
               // bundle.putString("Password", Password);
                bundle.putString("Garage_Id", Garage_Id);
                intent.putExtras(bundle);
                // 转向登陆后的页面
                startActivity(intent);
                proDialog.dismiss();
            } else {
                // 通过调用handler来通知UI主线程更新UI,
                Message message = new Message();
                Bundle bundle = new Bundle();
                bundle.putBoolean("isNetError", isNetError);
                message.setData(bundle);
                loginHandler.sendMessage(message);
            }
        }

    }


}