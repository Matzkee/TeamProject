package com.example.hou.nct_2;

import android.app.Activity;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;



public class AddNote extends Activity {

    private EditText addNoteTitle;
    private EditText addNoteContent;
    private Button noteSubmit;
    private ImageView title_bar_menu_btn;
    public static final String DB_NAME="Note.db";
    SQLiteDatabase db;
    private TextView ID;

    public AddNote() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        final String  TitleValue;

        Intent intent=getIntent();
        TitleValue = intent.getStringExtra("Title");
        db = openOrCreateDatabase(DB_NAME, this.MODE_PRIVATE, null);

        addNoteTitle = (EditText)findViewById(R.id.addNoteTitle);
        addNoteContent=(EditText)findViewById(R.id.addNoteContent);
        ID = (TextView)findViewById(R.id.ID);
        title_bar_menu_btn = (ImageView)findViewById(R.id.title_bar_menu_btn);
        title_bar_menu_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if(!(TitleValue ==null)) {
            String NoteTitle = "";
            String NoteContent = "";
            int id = 0;
            Cursor c = db.rawQuery("SELECT * FROM NoteBook where NoteTitle = ?", new String[]{TitleValue});
            while (c.moveToNext()) {
                NoteTitle = c.getString(c.getColumnIndex("NoteTitle"));
                NoteContent = c.getString(c.getColumnIndex("NoteContent"));
                id = c.getInt(c.getColumnIndex("id"));
            }
            c.close();
            addNoteTitle.setText(NoteTitle);
            addNoteContent.setText(NoteContent);
            ID.setText(Integer.toString(id));
        }
        noteSubmit = (Button)findViewById(R.id.noteSubmit);
        noteSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String NoteTitle = addNoteTitle.getText().toString().isEmpty()?"":addNoteTitle.getText().toString();
                String NoteContent = addNoteContent.getText().toString().isEmpty()?"":addNoteContent.getText().toString();
                String NoteId = ID.getText().toString().isEmpty()?"":ID.getText().toString();

                GetNCTValueTask task = new GetNCTValueTask();
                if(!(TitleValue ==null)) {

                    task.execute(NoteTitle, NoteContent,NoteId,"update");
                }else {
                    task.execute(NoteTitle, NoteContent,"", "add");
                }
            }
        });



    }

    class GetNCTValueTask extends AsyncTask<String,Integer,String> {//继承AsyncTask

        @Override
        protected String doInBackground(String... params) {//处理后台执行的任务，在后台线程执行
            String NoteTitle = params[0];
            String NoteContent = params[1];
            String id = params[2];
            String Task = params[3];
            String isOk ="";



           if(Task == "update"){
                ContentValues cv = new ContentValues();
                cv.put("NoteTitle", NoteTitle);
                cv.put("NoteContent", NoteContent);
                db.update("NoteBook", cv, "id = ?", new String[]{id});
            }else {
               ContentValues notebook = new ContentValues();
               notebook.put("NoteTitle", NoteTitle);
               notebook.put("NoteContent", NoteContent);
               db.insert("NoteBook", null, notebook);
            }
            return isOk;
        }

        protected void onPostExecute(String isOk) {//后台任务执行完之后被调用，在ui线程执行

            Toast.makeText(getApplicationContext(), "Note Save succeed!",
                    Toast.LENGTH_SHORT).show();

        }
    }

}
