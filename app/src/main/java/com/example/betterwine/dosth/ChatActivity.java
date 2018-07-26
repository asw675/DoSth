package com.example.betterwine.dosth;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.betterwine.dosth.Adapter.ChatAdapter;
import com.example.betterwine.dosth.Interface.RetrofitInterface;
import com.example.betterwine.dosth.Model.Chat;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ChatActivity extends AppCompatActivity{

    private ArrayList<Chat> mList=new ArrayList<>();
    private LinearLayoutManager manager;
    private ChatAdapter chatAdapter;
    private ConstraintLayout mConstraintLayout;

   @BindView(R.id.chat_recy)
   public RecyclerView mRecy;
   @BindView(R.id.chat_edit)
   public EditText mEdit;
   @BindView(R.id.send)
   public Button mButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);
        mConstraintLayout=findViewById(R.id.chat_layout);
        mConstraintLayout.setBackgroundResource(R.drawable.chatback);

        Chat chat=new Chat();
        chat.setType(0);
        chat.setCode(100000);
        chat.setText("您好，很高兴为您服务");
        mList.add(chat);

        initView();
    }

    void initView()
    {
        //创建LinearLayoutManager
        manager = new LinearLayoutManager(ChatActivity.this);
        //设置
        mRecy.setLayoutManager(manager);
        //实例化适配器
        final ChatAdapter chatAdapter = new ChatAdapter(ChatActivity.this,mList);
        //设置适配器
        mRecy.setAdapter(chatAdapter);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mEdit.getText().toString().trim()!=null)
                {
                    Chat chat = new Chat();
                    chat.setText(mEdit.getText().toString().trim());
                    chat.setType(1);
                    chat.setCode(100000);
                    mList.add(chat);
                    RequestChat(mEdit.getText().toString().trim());
                    mEdit.getText().clear();
                }
                else {
                    Toast.makeText(ChatActivity.this,"发送不能为空",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    void RequestChat(String text)
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.tuling123.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        RetrofitInterface retrofitInterface=retrofit.create(RetrofitInterface.class);

        retrofitInterface.getChat("82ad17be448a4168be3227d391c63f4f",text)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Chat>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Chat chat) {
                        chat.setType(0);
                        mList.add(chat);
                        Refresh();
                    }
                });
    }

    void Refresh()
    {
        if (chatAdapter == null) {
            chatAdapter = new ChatAdapter(ChatActivity.this,mList);
            mRecy.setAdapter(chatAdapter);
        } else {
            chatAdapter.notifyDataSetChanged();
            mRecy.scrollToPosition(chatAdapter.getItemCount()-1);
        }
    }

}
