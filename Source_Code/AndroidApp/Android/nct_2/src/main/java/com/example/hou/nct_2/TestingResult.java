package com.example.hou.nct_2;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hou.tools.Booking;
import com.example.hou.tools.SlideMenu;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class TestingResult extends Activity implements View.OnClickListener{

    private SlideMenu slideMenu;
    private TextView testingResult;

    private EditText showDate = null;
    private TextView pickDate = null;

    private static final int SHOW_DATAPICK = 0;
    private static final int DATE_DIALOG_ID = 1;
    private static final int SHOW_TIMEPICK = 2;
    private static final int TIME_DIALOG_ID = 3;

    private int mYear;
    private int mMonth;
    private int mDay;

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing_result);

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        setDateTime();
        initializeViews();
        slideMenu = (SlideMenu)findViewById(R.id.slide_menu);

        ImageView menuImg = (ImageView) findViewById(R.id.title_bar_menu_btn);
        menuImg.setOnClickListener(this);

            testingResult = (TextView)findViewById(R.id.testingResult);
            testingResult.setClickable(true);
            testingResult.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v) {

                    slideMenu.closeMenu();
                }
            });
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
     * 初始化控件和UI视图
     */
    private void initializeViews(){

        showDate = (EditText) findViewById(R.id.showdate);
        pickDate = (TextView) findViewById(R.id.pickdate);


        showDate.setCursorVisible(false);
        showDate.setFocusable(false);
        showDate.setFocusableInTouchMode(false);

        pickDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Message msg = new Message();
                if (pickDate.equals((TextView) v)) {
                    msg.what = TestingResult.SHOW_DATAPICK;
                }
                TestingResult.this.dateandtimeHandler.sendMessage(msg);
            }
        });



    }

    /**
     * 设置日期
     */
    private void setDateTime(){
        final Calendar c = Calendar.getInstance();

        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        updateDateDisplay();
    }

    /**
     * 更新日期显示
     */
    private void updateDateDisplay(){
        showDate.setText(new StringBuilder().append(mYear).append("-")
                .append((mMonth + 1) < 10 ? "0" + (mMonth + 1) : (mMonth + 1)).append("-")
                .append((mDay < 10) ? "0" + mDay : mDay));
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
 * 处理日期和时间控件的Handler
 */
Handler dateandtimeHandler = new Handler() {

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case TestingResult.SHOW_DATAPICK:
                showDialog(DATE_DIALOG_ID);
                break;
            case TestingResult.SHOW_TIMEPICK:
                showDialog(TIME_DIALOG_ID);
                break;
        }
    }

};
}
