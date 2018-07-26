package com.example.betterwine.dosth.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.betterwine.dosth.Model.Chat;
import com.example.betterwine.dosth.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.zip.Inflater;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context mContext;
    private ArrayList<Chat> mList=new ArrayList<>();

    public static enum ITEM_TYPE{
        ITEM_TYPE_LEFT,
        ITEM_TYPE_RIGHT;
    }

    public ChatAdapter(Context context, ArrayList<Chat> List)
    {
        this.mContext=context;
        mList=List;
    }

    class LeftViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.left_chat) TextView leftText;

        public LeftViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    class RightViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.right_chat) TextView rightText;

        public RightViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if(i==ITEM_TYPE.ITEM_TYPE_LEFT.ordinal()){
            return new LeftViewHolder(LayoutInflater.from(mContext).inflate(R.layout.chat_item_left,viewGroup,false));
        }
        else {
            return new RightViewHolder(LayoutInflater.from(mContext).inflate(R.layout.chat_item_right,viewGroup,false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
            if(viewHolder instanceof LeftViewHolder){
                ((LeftViewHolder)viewHolder).leftText.setText(mList.get(i).getText());
            }
            else if(viewHolder instanceof RightViewHolder){
                ((RightViewHolder)viewHolder).rightText.setText(mList.get(i).getText());
            }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(mList.get(position).getType()==0)
        {
            return ITEM_TYPE.ITEM_TYPE_LEFT.ordinal();
        }
        else if(mList.get(position).getType()==1)
        {
            return ITEM_TYPE.ITEM_TYPE_RIGHT.ordinal();
        }
        else {
            return ITEM_TYPE.ITEM_TYPE_RIGHT.ordinal();
        }
    }
}
