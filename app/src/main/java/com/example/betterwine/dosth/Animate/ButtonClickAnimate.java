package com.example.betterwine.dosth.Animate;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;

public class ButtonClickAnimate {

    public static void ScaleAnimation(View view){
        ObjectAnimator anim1=ObjectAnimator.ofFloat(view,"scaleX",0.0f,2.0f);
        ObjectAnimator anim2=ObjectAnimator.ofFloat(view,"scaleX",0.0f,2.0f);
        AnimatorSet set=new AnimatorSet();
        set.playSequentially(anim1,anim2);
        set.setDuration(1000);
        set.start();
    }

}
