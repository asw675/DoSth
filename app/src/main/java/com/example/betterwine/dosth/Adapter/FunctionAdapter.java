package com.example.betterwine.dosth.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.betterwine.dosth.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FunctionAdapter extends RecyclerView.Adapter<FunctionAdapter.ViewHolder> {

    private ArrayList<String> mList=new ArrayList<>();
    private Context mContext;

    public FunctionAdapter(Context context,ArrayList<String> List)
    {
        this.mContext=context;
        this.mList=List;
    }

    @NonNull
    @Override
    public FunctionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(View.inflate(mContext,R.layout.function_item,null));
    }

    @Override
    public void onBindViewHolder(@NonNull FunctionAdapter.ViewHolder viewHolder, int i) {
        String r,g,b;
        Random random = new Random();
        r = Integer.toHexString(random.nextInt(256)).toUpperCase();
        g = Integer.toHexString(random.nextInt(256)).toUpperCase();
        b = Integer.toHexString(random.nextInt(256)).toUpperCase();

        r = r.length()==1 ? "0" + r : r ;
        g = g.length()==1 ? "0" + g : g ;
        b = b.length()==1 ? "0" + b : b ;
            viewHolder.mButton.setBackgroundColor(Color.parseColor("#"+ r + g + b));
            viewHolder.mButton.getBackground().setAlpha(120);
            viewHolder.mButton.setText(mList.get(i).trim());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.function_button)
        public Button mButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
