package com.example.betterwine.dosth;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AndroidException;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.betterwine.dosth.Animate.ButtonClickAnimate;
import com.example.betterwine.dosth.Interface.RetrofitInterface;
import com.example.betterwine.dosth.Model.TodayThing;

import java.sql.Time;
import java.util.Calendar;
import java.util.Random;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.example.betterwine.dosth.Animate.ButtonClickAnimate.ScaleAnimation;

public class MainActivity extends AppCompatActivity {

    private TextView mSay;
    private TextView mTodayThing;
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private Button mChat;
    private Button mFunc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        initTime();
        initView();
        RequestTodayThing();
    }

    public void initView()
    {
        mFunc=findViewById(R.id.to_func);
        mChat=findViewById(R.id.to_chat);
        mSay=findViewById(R.id.say);
        mTodayThing=findViewById(R.id.TodayThing);
        if(hour>=0&&hour<12)
        {
            mSay.setText("上午好");
            getWindow().getDecorView().setBackgroundResource(R.drawable.morning);
        }
        if (hour>=12&&hour<18)
        {
            mSay.setText("下午好");
            getWindow().getDecorView().setBackgroundResource(R.drawable.afternoon);
        }
        if (hour>=18)
        {
            mSay.setText("晚上好");
            getWindow().getDecorView().setBackgroundResource(R.drawable.night);
        }
        mChat.getBackground().setAlpha(200);
        mFunc.getBackground().setAlpha(200);
        mChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,ChatActivity.class),
                        ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, mChat, "shareNames").toBundle());
            }
        });
        mFunc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,FunctionActivity.class),
                        ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, mFunc, "shareNames").toBundle());
            }
        });
    }

    public void RequestTodayThing()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.avatardata.cn/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        RetrofitInterface retrofitInterface=retrofit.create(RetrofitInterface.class);

        retrofitInterface.getTodayThing("70aadc0694cf4a3ba07ea7162b01974a",month,day,1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<TodayThing>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(TodayThing todayThing) {
                            System.out.print("1asdw");
                            StringBuilder text=new StringBuilder();
                            Random random=new Random();
                            int position=random.nextInt(todayThing.getResult().size());
                            text.append("你知道吗？在"+todayThing.getResult().get(position).getYear()+"的今天\n"
                                    +todayThing.getResult().get(position).getTitle());
                            mTodayThing.setText(text);

                    }
                });
    }

    public void initTime()
    {
        Calendar calendar=Calendar.getInstance();
        //年
        year = calendar.get(Calendar.YEAR);
        //月
        month = calendar.get(Calendar.MONTH)+1;
        //日
        day = calendar.get(Calendar.DAY_OF_MONTH);
        //小时
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        //分钟
        minute = calendar.get(Calendar.MINUTE);
    }

}
