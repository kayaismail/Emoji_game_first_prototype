package com.example.emoji;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
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

public class MainActivity extends AppCompatActivity {
    Button b1,b2,b3,b4;
    TextView t1_question,timerTxt,t1_answer;
    int total=0;
    int correct=0;
    int wrong=0;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1=(Button)findViewById(R.id.button1);
        b2=(Button)findViewById(R.id.button2);
        b3=(Button)findViewById(R.id.button3);
        b4=(Button)findViewById(R.id.button4);
        t1_question=(TextView)findViewById(R.id.questionTxt);
        t1_answer=(TextView)findViewById(R.id.answerTxt);
        timerTxt=(TextView)findViewById(R.id.timerTxt);
        updateQuestion(); //soruları getiren metod
        reverseTimer(30,timerTxt);
        hide(t1_answer);  // cevabı gösterip gizleyen metod
        }

    public void hide(View view) {  // cevabı gösterip gizleyen metod
        TextView txtView = (TextView)findViewById(R.id.answerTxt);

        //Toggle
        if (txtView.getVisibility() == View.VISIBLE)
            txtView.setVisibility(View.INVISIBLE);
        else
            txtView.setVisibility(View.VISIBLE);

        //If you want it only one time
        //txtView.setVisibility(View.VISIBLE);

    }

    public void updateQuestion() {
        total++;
        if(total>6)
        {
            Intent i=new Intent(MainActivity.this,ResultActivity.class);
            i.putExtra("total",String.valueOf(total));
            i.putExtra("correct",String.valueOf(correct));
            i.putExtra("incorrect",String.valueOf(wrong));
            startActivity(i);

        }
        else{
            reference= FirebaseDatabase.getInstance().getReference().child("Questions").child(String.valueOf(total));
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    final Question question=dataSnapshot.getValue(Question.class);

                    TextView txtView = (TextView)findViewById(R.id.answerTxt);

                    //Toggle
                    if (txtView.getVisibility() == View.VISIBLE)
                        txtView.setVisibility(View.INVISIBLE);
                    else
                        txtView.setVisibility(View.INVISIBLE);

                   t1_question.setText(question.getQuestion());
                    b1.setText(question.getOption1());
                    b2.setText(question.getOption2());
                    b3.setText(question.getOption3());
                    b4.setText(question.getOption4());
                    t1_answer.setText(question.getAnswer());


                    b1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                          if(b1.getText().toString().equals(question.getAnswer()))
                              {
                              b1.setBackgroundColor(Color.GREEN);
                              Handler handler=new Handler();
                              handler.postDelayed(new Runnable(){
                                  @Override
                                  public void run() {
                                      correct++;
                                      b1.setBackgroundColor(Color.parseColor("#E040FB"));
                                      updateQuestion();
                                  }
                              },500);
                          }
                          else
                          {
                              //answer is wrong ...... we will find correct ans and make green
                              Toast.makeText(getApplicationContext(),"Incorrect",Toast.LENGTH_SHORT).show();
                              wrong=wrong+1;
                              b1.setBackgroundColor(Color.RED);
                              if(b2.getText().toString().equals(question.getAnswer()))
                              {
                                  b2.setBackgroundColor(Color.GREEN);
                              }
                              else if(b3.getText().toString().equals(question.getAnswer()))
                              {
                                  b3.setBackgroundColor(Color.GREEN);

                              }
                              else if(b4.getText().toString().equals(question.getAnswer()))
                              {
                                  b4.setBackgroundColor(Color.GREEN);
                              }
                              Handler handler=new Handler();
                              handler.postDelayed(new Runnable(){
                                  @Override
                                  public void run() {
                                      b1.setBackgroundColor(Color.parseColor("#E040FB"));
                                      b2.setBackgroundColor(Color.parseColor("#E040FB"));
                                      b3.setBackgroundColor(Color.parseColor("#E040FB"));
                                      b4.setBackgroundColor(Color.parseColor("#E040FB"));
                                      updateQuestion();
                                  }
                              },500);
                          }
                        }

                    });

                    b2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(b2.getText().toString().equals(question.getAnswer()))
                            {
                                b2.setBackgroundColor(Color.GREEN);
                                Handler handler=new Handler();
                                handler.postDelayed(new Runnable(){
                                    @Override
                                    public void run() {
                                        correct++;
                                        b2.setBackgroundColor(Color.parseColor("#E040FB"));
                                        updateQuestion();
                                    }
                                },500);
                            }
                            else
                            {
                                //answer is wrong ...... we will find correct ans and make green
                                Toast.makeText(getApplicationContext(),"Incorrect",Toast.LENGTH_SHORT).show();
                                wrong=wrong+1;
                                b2.setBackgroundColor(Color.RED);
                                if(b1.getText().toString().equals(question.getAnswer()))
                                {
                                    b1.setBackgroundColor(Color.GREEN);
                                }
                                else if(b3.getText().toString().equals(question.getAnswer()))
                                {
                                    b3.setBackgroundColor(Color.GREEN);

                                }
                                else if(b4.getText().toString().equals(question.getAnswer()))
                                {
                                    b4.setBackgroundColor(Color.GREEN);
                                }
                                Handler handler=new Handler();
                                handler.postDelayed(new Runnable(){
                                    @Override
                                    public void run() {
                                        b1.setBackgroundColor(Color.parseColor("#E040FB"));
                                        b2.setBackgroundColor(Color.parseColor("#E040FB"));
                                        b3.setBackgroundColor(Color.parseColor("#E040FB"));
                                        b4.setBackgroundColor(Color.parseColor("#E040FB"));
                                        updateQuestion();
                                    }
                                },500);
                            }
                        }


                    });

                    b3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(b3.getText().toString().equals(question.getAnswer()))
                            {
                                b3.setBackgroundColor(Color.GREEN);
                                Handler handler=new Handler();
                                handler.postDelayed(new Runnable(){
                                    @Override
                                    public void run() {
                                        correct++;
                                        b3.setBackgroundColor(Color.parseColor("#E040FB"));
                                        updateQuestion();
                                    }
                                },500);
                            }
                            else
                            {
                                //answer is wrong ...... we will find correct ans and make green
                                Toast.makeText(getApplicationContext(),"Incorrect",Toast.LENGTH_SHORT).show();
                                wrong=wrong+1;
                                b3.setBackgroundColor(Color.RED);
                                if(b1.getText().toString().equals(question.getAnswer()))
                                {
                                    b1.setBackgroundColor(Color.GREEN);
                                }
                                else if(b2.getText().toString().equals(question.getAnswer()))
                                {
                                    b2.setBackgroundColor(Color.GREEN);

                                }
                                else if(b4.getText().toString().equals(question.getAnswer()))
                                {
                                    b4.setBackgroundColor(Color.GREEN);
                                }
                                Handler handler=new Handler();
                                handler.postDelayed(new Runnable(){
                                    @Override
                                    public void run() {
                                        b1.setBackgroundColor(Color.parseColor("#E040FB"));
                                        b2.setBackgroundColor(Color.parseColor("#E040FB"));
                                        b3.setBackgroundColor(Color.parseColor("#E040FB"));
                                        b4.setBackgroundColor(Color.parseColor("#E040FB"));
                                        updateQuestion();
                                    }
                                },500);
                            }
                        }


                    });
                    b4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(b4.getText().toString().equals(question.getAnswer()))
                            {
                                b4.setBackgroundColor(Color.GREEN);
                                Handler handler=new Handler();
                                handler.postDelayed(new Runnable(){
                                    @Override
                                    public void run() {
                                        correct++;
                                        b4.setBackgroundColor(Color.parseColor("#E040FB"));
                                        updateQuestion();
                                    }
                                },500);
                            }
                            else
                            {
                                //answer is wrong ...... we will find correct ans and make green
                                Toast.makeText(getApplicationContext(),"Incorrect",Toast.LENGTH_SHORT).show();
                                wrong=wrong+1;
                                b4.setBackgroundColor(Color.RED);
                                if(b1.getText().toString().equals(question.getAnswer()))
                                {
                                    b1.setBackgroundColor(Color.GREEN);
                                }
                                else if(b2.getText().toString().equals(question.getAnswer()))
                                {
                                    b2.setBackgroundColor(Color.GREEN);

                                }
                                else if(b3.getText().toString().equals(question.getAnswer()))
                                {
                                    b3.setBackgroundColor(Color.GREEN);
                                }
                                Handler handler=new Handler();
                                handler.postDelayed(new Runnable(){
                                    @Override
                                    public void run() {
                                        b1.setBackgroundColor(Color.parseColor("#E040FB"));
                                        b2.setBackgroundColor(Color.parseColor("#E040FB"));
                                        b3.setBackgroundColor(Color.parseColor("#E040FB"));
                                        b4.setBackgroundColor(Color.parseColor("#E040FB"));
                                        updateQuestion();
                                    }
                                },500);
                            }
                        }


                    });



                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            }) ;
        }
    }

    public void reverseTimer(int Seconds,final TextView tv)
    {
        new CountDownTimer(Seconds* 1000+1000,1000)
        {
            public void onTick(long millisUnitFinished){
                int seconds =(int)(millisUnitFinished / 1000);
                int minutes= seconds / 60;
                seconds= seconds % 60;
                tv.setText(String.format("%02d", minutes)
                        +":"+String.format("%02d", seconds));
            }
            public void onFinish()
            {
                tv.setText("Completed");
                Intent myIntent=new Intent(MainActivity.this, ResultActivity.class);
                myIntent.putExtra("total",String.valueOf(total));
                myIntent.putExtra("correct",String.valueOf(correct));
                myIntent.putExtra("incorrect",String.valueOf(wrong));
                startActivity(myIntent);
            }
        }.start();


    }


}