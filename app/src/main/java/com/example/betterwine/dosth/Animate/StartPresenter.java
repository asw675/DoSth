package com.example.betterwine.dosth.Animate;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.betterwine.dosth.MainActivity;

public class StartPresenter extends AppCompatActivity{
    protected void setAnimateTrans(View view,final Context context){
        ObjectAnimator animator=ObjectAnimator.ofFloat(view, "translationY", 800, 300);
        animator.setDuration(2000);
        animator.start();
        new Handler().postDelayed(new Runnable(){
            public void run() {
                outThis(context);
            }
        }, 3000);
    }
    protected void outThis(Context context){
        Intent i=new Intent(context,MainActivity.class);
        startActivity(i);
    }
}
