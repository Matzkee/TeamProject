package com.example.hou.nct_2;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hou.tools.Booking;
import com.example.hou.tools.BookingResult;
import com.example.hou.tools.NctTools;
import com.example.hou.tools.SlideMenu;
import com.example.hou.tools.TestResultDate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class BookingLing extends Activity implements View.OnClickListener{

    public static final String DB_NAME="Note.db";
    SQLiteDatabase db;
    private SlideMenu slideMenu;
    public ListView listView;
    private ProgressDialog proDialog;
    private TextView name;
    private EditText showDate = null;
    private Button pickDate = null;
    private TextView title_bar_name;
    private TextView xianshi1;
    private TextView TimeStart;
    private TextView xianshi2;
    private TextView TimeFinish;
    private TextView LogOut;
    private  ImageView Myphoto;
    private TextView note;
    private LinearLayout TimeLinearLayout;
    private EditText FIndNote;
    private Button addNote;

    private static final int SHOW_DATAPICK = 0;
    private static final int DATE_DIALOG_ID = 1;
    private static final int SHOW_TIMEPICK = 2;
    private static final int TIME_DIALOG_ID = 3;

    private int mYear;
    private int mMonth;
    private int mDay;

    private String User_Id;
    private String Name;
    private String Garage_Id;
    private String IP;
    private String StartDate;
    private String FinishDate;
    private String str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_ling);

        TimeLinearLayout = (LinearLayout)findViewById(R.id.TimeLinearLayout);
        note = (TextView)findViewById(R.id.note);
        FIndNote = (EditText)findViewById(R.id.FIndNote);
        FIndNote.setVisibility(View.GONE);
        FIndNote.addTextChangedListener(FIndNoteWatcher);

        addNote = (Button)findViewById(R.id.addNote);
        addNote.setVisibility(View.GONE);

        db = openOrCreateDatabase(DB_NAME, this.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS NoteBook (id INTEGER PRIMARY KEY AUTOINCREMENT, NoteTitle VARCHAR,  NoteContent VARCHAR)");

        IP = getResources().getString(R.string.IP);
        title_bar_name = (TextView)findViewById(R.id.title_bar_name);
        Intent intent=getIntent();
        User_Id=intent.getStringExtra("User_Id");
        Name=intent.getStringExtra("Name");
        Garage_Id=intent.getStringExtra("Garage_Id");


        initializeViews();
        if(!(Name ==null)){
            name.setText("Hi, "+Name);
        }

        //时间控件
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        //setDateTime();
        slideMenu = (SlideMenu) findViewById(R.id.slide_menu);
        ImageView menuImg = (ImageView) findViewById(R.id.title_bar_menu_btn);
        pickDate = (Button)findViewById(R.id.pickdate);
        showDate = (EditText)findViewById(R.id.showdate);
        xianshi1 = (TextView)findViewById(R.id.xianshi1);
        TimeStart = (TextView)findViewById(R.id.TimeStart);
        xianshi2 = (TextView)findViewById(R.id.xianshi2);
        TimeFinish = (TextView)findViewById(R.id.TimeFinish);

        //LogOut
        LogOut = (TextView)findViewById(R.id.LogOut);
        LogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(BookingLing.this);
                builder.setMessage("Are you sure you want to log out?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //Result.this.finish();
                                finish();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                slideMenu.closeMenu();
                                dialog.cancel();
                            }
                        }).show();
            }
        });


        menuImg.setOnClickListener(this);

        /**
         * Bookinglist
         */

        TextView bookingList = (TextView)findViewById(R.id.book);
        bookingList.setClickable(true);
        bookingList.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                showDate.setVisibility(View.GONE);
                pickDate.setVisibility(View.GONE);
                listView.setVisibility(View.VISIBLE);
                title_bar_name.setText("Booking List");
                pickDate.setText("Select Date");
                FIndNote.setVisibility(View.GONE);
                addNote.setVisibility(View.GONE);

                xianshi1.setVisibility(View.GONE);
                TimeStart.setVisibility(View.GONE);
                xianshi2.setVisibility(View.GONE);
                TimeFinish.setVisibility(View.GONE);

                StartDate = "";
                FinishDate = "";
                GetNCTValueTask task = new GetNCTValueTask();
                task.execute(IP+"BookingList?Garage_Id=" + Garage_Id+"&User_Id="+User_Id, "BookingLing");
            }
        });

        /**
         * testResult
         */

        TextView testingResult = (TextView)findViewById(R.id.testingResult);

        testingResult.setClickable(true);
        testingResult.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                showDate.setVisibility(View.GONE);
                pickDate.setVisibility(View.VISIBLE);
                StartDate = null;
                FinishDate = null;
                pickDate.setText("Select Date");
                slideMenu.closeMenu();
                title_bar_name.setText("Test Result");
                FIndNote.setVisibility(View.GONE);
                addNote.setVisibility(View.GONE);

                String Data = showDate.getText().toString();
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(BookingLing.this, android.R.layout.simple_list_item_1);
                listView.setAdapter(adapter);
                pickDate.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        Message msg = new Message();
                        if (pickDate.equals((TextView) v)) {
                            msg.what = BookingLing.SHOW_DATAPICK;
                            if(StartDate == null){
                                BookingLing.this.dateandtimeHandler.sendMessage(msg);

                            }else if((!(StartDate == null)) && (FinishDate == null)){
                                BookingLing.this.dateandtimeHandler.sendMessage(msg);
                            }else{
                                if(java.sql.Date.valueOf(StartDate).after(java.sql.Date.valueOf(FinishDate))){//起始日期大于结束日期
                                    new AlertDialog.Builder(BookingLing.this)
                                            .setTitle("Message")
                                            .setMessage("Start Date must before Due Date")
                                            .setPositiveButton("ok", new DialogInterface.OnClickListener() {

                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    StartDate = null;
                                                    FinishDate = null;
                                                    pickDate.setText("Select Date");
                                                }
                                            }).show();
                                }else{

                                    GetNCTValueTask task = new GetNCTValueTask();
                                    task.execute(IP + "TestResultDateList?Garage_Id=" + Garage_Id + "&User_Id=" + User_Id + "&StartData=" + StartDate+"&FinishDate="+FinishDate, "TestingResult");
                                }
                            }
                        }

                    }
                });

            }
        });

        /**
         * Note
         */

        note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slideMenu.closeMenu();
                FIndNote.setVisibility(View.VISIBLE);
                showDate.setVisibility(View.GONE);
                pickDate.setVisibility(View.GONE);
                TimeLinearLayout.setVisibility(View.GONE);

                addNote.setVisibility(View.VISIBLE);
                listView.setVisibility(View.VISIBLE);

                title_bar_name.setText("Note List");
                addNote.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.setClass(BookingLing.this, AddNote.class);
                        startActivity(intent);
                    }
                });
                final ArrayList<String> mArrayList = new ArrayList<String>();
                Cursor c = db.rawQuery("SELECT * FROM NoteBook ", null);
                while (c.moveToNext()) {
                    String NoteTitle = c.getString(c.getColumnIndex("NoteTitle"));
                    mArrayList.add(NoteTitle);
                }
                c.close();
                ArrayAdapter<String> adapter1=new ArrayAdapter<String>(BookingLing.this, android.R.layout.simple_expandable_list_item_1,mArrayList);
                listView.setAdapter(adapter1);

                listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

                    @Override
                    public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                                   int arg2, long arg3) {
                        final String Title = mArrayList.get(arg2);
                        // TODO Auto-generated method stub

                        AlertDialog.Builder builder = new AlertDialog.Builder(BookingLing.this);
                        builder.setMessage("Are you sure you want to delete this note?")
                                .setCancelable(false)
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        db.delete("NoteBook", "NoteTitle = ?", new String[]{Title});
                                    }
                                })
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                }).show();


                        return true;
                    }
                });
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

                    @Override
                    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                            long arg3) {
                        // TODO Auto-generated method stub

                         Intent intent = new Intent();
                         intent.setClass(BookingLing.this, AddNote.class);
                         Bundle bundle = new Bundle();
                         bundle.putString("Title", mArrayList.get(arg2));
                         intent.putExtras(bundle);
                         startActivity(intent);


                    }

                });

            }
        });



        String bar_name = (String) title_bar_name.getText();

        if("Booking List".equals(bar_name) ) {
            showDate.setVisibility(View.GONE);
            pickDate.setVisibility(View.GONE);
            listView = (ListView) findViewById(R.id.bookList);

            xianshi1.setVisibility(View.GONE);
            TimeStart.setVisibility(View.GONE);
            xianshi2.setVisibility(View.GONE);
            TimeFinish.setVisibility(View.GONE);

            StartDate = null;
            FinishDate = null;

            GetNCTValueTask task = new GetNCTValueTask();
            task.execute(IP + "BookingList?Garage_Id=" + Garage_Id + "&User_Id=" + User_Id, "BookingLing");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        String bar_name = (String) title_bar_name.getText();

        if("Booking List".equals(bar_name) ) {
            showDate.setVisibility(View.GONE);
            pickDate.setVisibility(View.GONE);
            listView = (ListView) findViewById(R.id.bookList);

            xianshi1.setVisibility(View.GONE);
            TimeStart.setVisibility(View.GONE);
            xianshi2.setVisibility(View.GONE);
            TimeFinish.setVisibility(View.GONE);

            StartDate = null;
            FinishDate = null;

            GetNCTValueTask task = new GetNCTValueTask();
            task.execute(IP + "BookingList?Garage_Id=" + Garage_Id + "&User_Id=" + User_Id, "BookingLing");
        }

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_bar_menu_btn:
                if (slideMenu.isMainScreenShowing()) {
                    slideMenu.openMenu();
                } else {
                    slideMenu.closeMenu();
                }
                break;
        }

    }

    /**
     * dateInit
     */
    private void initializeViews(){
        name = (TextView)findViewById(R.id.NAME);
        showDate = (EditText) findViewById(R.id.showdate);
        pickDate = (Button) findViewById(R.id.pickdate);

        showDate.setCursorVisible(false);
        showDate.setFocusable(false);
        showDate.setFocusableInTouchMode(false);

        ImageView Myphoto = (ImageView)findViewById(R.id.MyPhoto);
        Bitmap bitmap = getLoacalBitmap("/sdcard/myImage/111.jpg");
        Myphoto .setImageBitmap(bitmap);
    }

    /**
    * initPhoto
    */
    public static Bitmap getLoacalBitmap(String url) {
        try {
            FileInputStream fis = new FileInputStream(url);
            return BitmapFactory.decodeStream(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 设置日期

    private void setDateTime(){
        final Calendar c = Calendar.getInstance();

        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        updateDateDisplay();
    }
     */

    /**
     * 更新日期显示
     */
    private void updateDateDisplay(){
        showDate.setText(new StringBuilder().append(mYear).append("-")
                .append((mMonth + 1) < 10 ? "0" + (mMonth + 1) : (mMonth + 1)).append("-")
                .append((mDay < 10) ? "0" + mDay : mDay));

        if(StartDate == null){
            StartDate = showDate.getText().toString();
            xianshi1.setVisibility(View.VISIBLE);
            TimeStart.setText(StartDate);
            TimeStart.setVisibility(View.VISIBLE);
            xianshi2.setVisibility(View.VISIBLE);

            pickDate.setText("Select FinishDate");


        }else if(!(StartDate == null) && (FinishDate == null)){
            FinishDate = showDate.getText().toString();
            TimeFinish.setText(FinishDate);
            TimeFinish.setVisibility(View.VISIBLE);
            pickDate.setText("Submit");
        }

    }

    /**
     * 日期控件的事件
     */
    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;

            updateDateDisplay();
        }
    };

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this, mDateSetListener, mYear, mMonth,
                        mDay);

        }

        return null;
    }

    /**
     * DataHandler
     */
    Handler dateandtimeHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case BookingLing.SHOW_DATAPICK:
                    showDialog(DATE_DIALOG_ID);
                    break;
                case BookingLing.SHOW_TIMEPICK:
                    showDialog(TIME_DIALOG_ID);
                    break;
            }
        }

    };


    private  TextWatcher FIndNoteWatcher = new TextWatcher() {

        @Override
        public void afterTextChanged(Editable s) {
            // TODO Auto-generated method stub
            Log.d("TAG", "afterTextChanged--------------->");
            str = FIndNote.getText().toString();
            ArrayList<String> mArrayList = new ArrayList<String>();


            Cursor c = db.rawQuery("SELECT * FROM NoteBook WHERE NoteTitle like '%"+str+"%'", null);
            while (c.moveToNext()) {
                String NoteTitle = c.getString(c.getColumnIndex("NoteTitle"));
                String NoteContent = c.getString(c.getColumnIndex("NoteContent"));
                mArrayList.add(NoteTitle);
            }
            c.close();
            ArrayAdapter<String> adapter1=new ArrayAdapter<String>(BookingLing.this, android.R.layout.simple_expandable_list_item_1,mArrayList);
            listView.setAdapter(adapter1);
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
            // TODO Auto-generated method stub
            Log.d("TAG","beforeTextChanged--------------->");

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {
            Log.d("TAG","onTextChanged--------------->");
            str = FIndNote.getText().toString();
            try {
                //if ((heighText.getText().toString())!=null)
                Integer.parseInt(str);

            } catch (Exception e) {
                // TODO: handle exception

            }

        }
    };




    class GetNCTValueTask extends AsyncTask<String,Integer,List<Object>> {//继承AsyncTask

        protected void onPreExecute() {
           //先执行关列表
            slideMenu.closeMenu();
        }

        @Override
        protected List<Object> doInBackground(String... params) {//处理后台执行的任务，在后台线程执行
            String validateURL = params[0];
            String Task = params[1];
            List<Object> list1 = new ArrayList<Object>();
            try {
                if(Task == "BookingLing"){
                    JSONArray list = new NctTools().getJSONArray(validateURL);
                    for(int a =0;a<list.length();a++){
                        JSONObject temp = (JSONObject) list.get(a);
                        BookingResult bookingResult = new BookingResult();
                        bookingResult.setBook_Id(temp.getString("Book_Id"));
                        bookingResult.setModel(temp.getString("Model"));
                        bookingResult.setCar_Reg(temp.getString("Car_Reg"));
                        bookingResult.setBDate(temp.getString("BDate"));
                        bookingResult.setUser_Id(temp.getString("User_Id"));
                        bookingResult.setGarage_Id(Garage_Id);
                        list1.add(bookingResult);
                    }
                    list1.add("BookingLing");

                }

                if(Task =="TestingResult"){
                    System.out.println("查看URL===="+validateURL);
                    JSONArray list = new NctTools().getJSONArray(validateURL);

                      for(int a =0;a<list.length();a++){
                         JSONObject temp = (JSONObject) list.get(a);
                          TestResultDate testResultDate = new TestResultDate();
                          testResultDate.setModle(temp.getString("Modle"));
                          testResultDate.setCar_Reg(temp.getString("Car_Reg"));
                          testResultDate.setTestDate(temp.getString("TestDate"));
                          testResultDate.setOwner_Id(temp.getString("Owner_Id"));

                         list1.add(testResultDate);
                     }
                    list1.add("TestingResult");
                }

                if(Task =="Note"){
                    list1.add("");
                    list1.add("TestingResult");
                }


            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return list1;
        }

        protected void onPostExecute(final List<Object> result) {//后台任务执行完之后被调用，在ui线程执行
            if(result != null) {
                ArrayList<String> mArrayList = new ArrayList<String>();
                String Task ="";
                if(result.size()>=2) {
                    Task = (String) result.get(result.size() - 1);
                }else{
                    Task = (String) result.get(result.size());
                }
                if(Task == "BookingLing") {
                    for (int a = 0; a < (result.size()-1); a++) {
                        BookingResult bookingResult = (BookingResult) result.get(a);
                        String Show = bookingResult.getModel() + " [ " + bookingResult.getBDate() + " ]";
                        mArrayList.add(Show);
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(BookingLing.this, android.R.layout.simple_list_item_1, mArrayList);
                    listView.setAdapter(adapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position,
                                                long id) {
                            BookingResult bookingResult = (BookingResult) result.get(position);
                            String Car_Reg = bookingResult.getCar_Reg();
                            String User_Id = bookingResult.getUser_Id();
                            String Garage_Id = bookingResult.getGarage_Id();
                            Intent intent = new Intent();
                            intent.setClass(BookingLing.this, Result.class);
                            intent.putExtra("User_Id", User_Id);
                            intent.putExtra("Car_Reg", Car_Reg);
                            intent.putExtra("Garage_Id", Garage_Id);
                            startActivity(intent);
                        }
                    });
                }

                if(Task == "TestingResult"){

                    for (int a = 0; a < (result.size()-1); a++) {
                        TestResultDate testResultDate = (TestResultDate) result.get(a);
                        String Show = testResultDate.getModle() + " [ " + testResultDate.getTestDate() + " ]";
                        mArrayList.add(Show);
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(BookingLing.this, android.R.layout.simple_list_item_1, mArrayList);
                    listView.setAdapter(adapter);
                    pickDate.setText("Select Date");
                    StartDate = null;
                    FinishDate = null;
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position,
                                                long id) {
                            TestResultDate testResultDate = (TestResultDate) result.get(position);
                            String Car_Reg = testResultDate.getCar_Reg();
                            String Owner_Id = testResultDate.getOwner_Id();

                             Intent intent = new Intent();
                             intent.setClass(BookingLing.this,TestingResultDate.class);
                             intent.putExtra("Car_Reg",Car_Reg);
                             intent.putExtra("Owner_Id",Owner_Id);
                             startActivity(intent);


                        }
                    });
                }

                if(Task =="Note"){
                    Toast.makeText(BookingLing.this, "111111111111111111111111111111", Toast.LENGTH_LONG).show();
                }


            }else {
                Toast.makeText(BookingLing.this, "GetDateError", Toast.LENGTH_LONG).show();
            }
        }
    }


}
