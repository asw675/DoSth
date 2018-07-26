package com.example.betterwine.dosth.Animate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.betterwine.dosth.R;

public class StartActivity extends StartPresenter{

    private ImageView imageView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.start_layout);
        imageView=findViewById(R.id.logo);
        Glide.with(this).load(R.drawable.logo).asBitmap().into(imageView);
        setAnimateTrans(imageView,this);

    }
}
