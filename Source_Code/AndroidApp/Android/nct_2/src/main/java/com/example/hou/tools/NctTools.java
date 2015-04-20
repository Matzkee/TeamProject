package com.example.hou.tools;

import org.json.JSONArray;
import org.json.JSONException;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by yuan on 2015/3/25.
 */
public class NctTools{

    public List getData(String validateUrl) {
        boolean loginState = false;
        HttpURLConnection conn = null;
        DataInputStream dis = null;
        List list =new ArrayList();
        Boolean isNetError =false;
        String json ="";
        try {
            URL url = new URL(validateUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setRequestMethod("GET");
            conn.connect();
            //dis = new DataInputStream(conn.getInputStream());
            InputStream is = conn.getInputStream(); // 获取输入流
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                isNetError = true;
                list.add(isNetError);
                list.add(json);
                return list;
            }
            //------处理信息-----
            byte[] data = readStream(is);   // 把输入流转换成字符数组
            json = new String(data); // 把字符数组转换成字符串
            list.add(isNetError);
            list.add(json);


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        return list;
    }


    public static JSONArray getJSONArray(String server_url) throws IOException {

        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        Map<String, String> map = null;
        JSONArray arr = null;

        HttpURLConnection conn = null;// 利用HttpURLConnection对象,我们可以从网络中获取网页数据.

        try {
            URL url = new URL(server_url);
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5 * 1000);   // 单位是毫秒，设置超时时间为5秒
            conn.setRequestMethod("GET");

            // HttpURLConnection是通过HTTP协议请求path路径的，所以需要设置请求方式,可以不设置，因为默认为GET


            if (conn.getResponseCode() == 200) {// 判断请求码是否是200码，否则失败
                InputStream is = conn.getInputStream(); // 获取输入流
                byte[] data =readStream(is);   // 把输入流转换成字符数组
                String json = new String(data); // 把字符数组转换成字符串


                arr = new JSONArray(json);

                // {"name":"Bob Qin","sex":1,"age":10,"email":"bob@hotmail.com"}
                //数据形式：{"total":2,"success":true,"arrayData":[{"id":1,"name":"小猪"},{"id":2,"name":"小猫"}]}
                // JSONObject jsonObject=new JSONObject(json);     //返回的数据形式是一个Object类型，所以可以直接转换成一个Object
                // String User_Id = jsonObject.getString("User_Id");
                // String Type_Id = jsonObject.getString("Type_Id");
                //String LoginName = jsonObject.getString("LoginName");
                //String Password = jsonObject.getString("Password");
                // Garage_Id=jsonObject.getString("Garage_Id");
                //String sexString = Integer.toString(sex);
                //Boolean success=jsonObject.getBoolean("success");

                //  Log.i("abc", "name:" + name + " | password:" +password);   //测试数据
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }



        return arr;
    }



    public static byte[] readStream(InputStream inputStream) throws Exception
    {
        byte[] buffer=new byte[1024];
        int len=-1;
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();

        while((len=inputStream.read(buffer))!=-1)
        {
            byteArrayOutputStream.write(buffer,0,len);
        }

        inputStream.close();
        byteArrayOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }


}
