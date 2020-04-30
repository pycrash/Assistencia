package com.t9.assistencia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.sql.Time;
import java.util.ArrayList;

public class SubjectActivity extends AppCompatActivity {
    ListView simpleList;
    FloatingActionButton floatingActionButton;
    CardView add;
    RelativeLayout rootLayout;
    boolean isSelectedText;
    String[] fruits = {"English","Hindi","Mathematics","Software Engineering"};
    ArrayList<String> specialitiesSearch;
    AutoCompleteTextView actv;


    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyChoice" ;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);

        rootLayout=(RelativeLayout)findViewById(R.id.root_layout_subjects);
        //Creating the instance of ArrayAdapter containing list of fruit names
        final ArrayAdapter<String> suggestionAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_dropdown_item, fruits);
        //Getting the instance of AutoCompleteTextView
        actv = (AutoCompleteTextView) findViewById(R.id.auto_complete_subjects);
        actv.setThreshold(1);//will start working from first character
        actv.setAdapter(suggestionAdapter);//setting the adapter data into the AutoCompleteTextView
        actv.setTextColor(ContextCompat.getColor(SubjectActivity.this, android.R.color.white));
        actv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actv.showDropDown();
            }
        });


        simpleList=(ListView)findViewById(R.id.list_subjects);
        specialitiesSearch=new ArrayList<>();
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(SubjectActivity.this,R.layout.list_item_subject,R.id.textView, specialitiesSearch);

        View.OnClickListener onClickListener=new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s = actv.getEditableText().toString();
                if (s.equals("")) {
                    Snackbar.make(rootLayout, "Enter a Subject", Snackbar.LENGTH_LONG).show();

                } else {
                    if (!specialitiesSearch.contains(s)) {
                        specialitiesSearch.add(s);
                        actv.setText("");
                        arrayAdapter.notifyDataSetChanged();
                    } else {
                        Snackbar.make(rootLayout, "Subject Already exists", Snackbar.LENGTH_LONG).show();
                    }
                }
            }
        };
        actv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = actv.getEditableText().toString();

                if(!specialitiesSearch.contains(s)){
                    specialitiesSearch.add(s);
                    actv.setText("");
                    arrayAdapter.notifyDataSetChanged();
                }
                else{
                    Snackbar.make(rootLayout,"Subject Already exists",Snackbar.LENGTH_LONG).show();
                }
                }

        });
        add=(CardView)findViewById(R.id.add_subjects);
        add.setOnClickListener(onClickListener);
        simpleList.setAdapter(arrayAdapter);
        AdapterView.OnItemClickListener itemClickListener=new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, final int position, long id) {
                ImageView delete=(ImageView)findViewById(R.id.delete_subjects);
                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        arrayAdapter.remove(specialitiesSearch.remove(position));
                        simpleList.requestLayout();
                        arrayAdapter.notifyDataSetChanged();

                    }
                });
            }
        };
        simpleList.setOnItemClickListener(itemClickListener);



        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        floatingActionButton=(FloatingActionButton)findViewById(R.id.floating_action_button);
        floatingActionButton.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {

                String selected = "";
                int cntChoice = simpleList.getCount();

                ArrayList<String> sparseBooleanArray = specialitiesSearch;
                for(int i = 0; i < cntChoice; i++){
                    selected += simpleList.getItemAtPosition(i).toString() + "\n";

                }

                Toast.makeText(SubjectActivity.this, selected, Toast.LENGTH_LONG).show();
                Intent intent=new Intent(SubjectActivity.this, TimetableActivity.class);
                TinyDB tinydb = new TinyDB(SubjectActivity.this);
                tinydb.putListString("Subjects", specialitiesSearch);
                startActivity(intent);

            }});
    }


}


