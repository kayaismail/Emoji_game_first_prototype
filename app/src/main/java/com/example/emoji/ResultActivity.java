package com.example.emoji;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.emoji.Model.Question;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ResultActivity extends AppCompatActivity {
    Button b5;
    TextView t1,t2,t3;
    Button b1,b2,b3,b4;
    TextView t1_question,timerTxt,t1_answer;
    int total=0;
    int correct=0;
    int wrong=0;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
       t1=(TextView)findViewById(R.id.textView13);
        t2=(TextView)findViewById(R.id.textView14);
        t3=(TextView) findViewById(R.id.textView15);
       Intent i=getIntent();
        String questions= i.getStringExtra("total");
        String correct= i.getStringExtra("correct");
        String wrong= i.getStringExtra("incorrect");
        t1.setText(questions);
        t2.setText(correct);
        t3.setText(wrong);



}


}