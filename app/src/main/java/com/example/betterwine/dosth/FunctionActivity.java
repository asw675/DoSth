package com.example.betterwine.dosth;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.example.betterwine.dosth.Adapter.ChatAdapter;
import com.example.betterwine.dosth.Adapter.FunctionAdapter;
import com.example.betterwine.dosth.Model.FunctionType;

import org.w3c.dom.Text;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FunctionActivity extends AppCompatActivity {

    private ArrayList<String> mList=new ArrayList<>();
    private GridLayoutManager manager;
    private FunctionAdapter mAdapter;
    private ArrayList<FunctionType> mTypeList=new ArrayList<>();

    @BindView(R.id.func_tabLayout)
    public TabLayout mTabLayout;
    @BindView(R.id.func_recy)
    public RecyclerView mRecy;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_function);
        ButterKnife.bind(this);
        initData();
        initView();

    }

    void initData() {
        String[] text = {"生活", "娱乐", "金融", "新闻", "医疗", "文字", "其他"};

        final String[] all = {
                "身份证查询", "银行卡查询", "股票查询", "汇率转换", "白银实时价格", "天气预报", "笑话大全", "星座运势", "手机号码归属地", "彩票查询", "周公解梦", "全国邮编查询",
                "IP地址查询", "号码吉凶", "生肖配对", "二次元语言", "今日油价", "国内新闻", "微信精选", "时事新闻", "国际新闻", "体育新闻", "农业新闻", "奇闻异事", "旅游新闻",
                "健康菜谱", "健康知识", "药品查询", "食疗大全", "疾病信息", "身体部位",
                "名人名言", "歇后语", "唐诗宋词", "辞海", "成语词典", "新华字典",
                "热点热词", "测距"
        };

        Integer[] img = {
                R.drawable.ic_local_dining_black_24dp,
                R.drawable.ic_pool_black_24dp,
                R.drawable.ic_business_center_black_24dp,
                R.drawable.ic_message_black_24dp,
                R.drawable.ic_local_hospital_black_24dp,
                R.drawable.ic_g_translate_black_24dp,
                R.drawable.ic_apps_black_24dp
        };

        for (int i = 0; i < 7; i++) {
            FunctionType functionType = new FunctionType();
            functionType.setFunctionImg(img[i]);
            functionType.setFunctionName(text[i]);
            mTypeList.add(functionType);
        }
        for (int i = 0; i < all.length; i++)
        {
            mList.add(all[i]);
        }
    }
    void initView()
    {
        for(int i=0;i<7;i++)
        {
            TabLayout.Tab tab=mTabLayout.newTab();
            View v= LayoutInflater.from(this).inflate(R.layout.tab_item,null);
            TextView tv=v.findViewById(R.id.tab_text);
            ImageView img=v.findViewById(R.id.tab_img);
            tv.setText(mTypeList.get(i).getFunctionName());
            img.setImageResource(mTypeList.get(i).getFunctionImg());
            tab.setCustomView(v);
            mTabLayout.addTab(tab);
        }
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                TextView tv= tab.getCustomView().findViewById(R.id.tab_text);
                String type=tv.getText().toString().trim();
                Refresh(type);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        manager = new GridLayoutManager(FunctionActivity.this,3);
        //设置
        mRecy.setLayoutManager(manager);
        //实例化适配器
        mAdapter=new FunctionAdapter(FunctionActivity.this,mList);
        //设置适配器
        mRecy.setAdapter(mAdapter);
    }

    void Refresh(String Type)
    {
        final String[] financial = {"身份证查询", "银行卡查询", "股票查询", "汇率转换", "白银实时价格"};
        final String[] life = {"天气预报", "笑话大全", "星座运势", "手机号码归属地", "彩票查询", "周公解梦", "全国邮编查询",
                "IP地址查询", "号码吉凶", "生肖配对", "二次元语言", "今日油价"};
        final String[] news = {"国内新闻", "微信精选", "时事新闻", "国际新闻", "体育新闻", "农业新闻", "奇闻异事", "旅游新闻"};
        final String[] medical = {"健康菜谱", "健康知识", "药品查询", "食疗大全", "疾病信息", "身体部位"};
        final String[] writing = {"名人名言", "歇后语", "唐诗宋词", "辞海", "成语词典", "新华字典"};
        final String[] other = {"热点热词", "测距"};
        final String[] entertainment={};

        switch (Type)
        {
            case "娱乐":
                mList.clear();
                for(int i=0;i<entertainment.length;i++)
                {
                    mList.add(entertainment[i]);
                }
                mAdapter.notifyDataSetChanged();
                break;
            case "金融":
                mList.clear();
                for(int i=0;i<financial.length;i++)
                {
                    mList.add(financial[i]);
                }
                mAdapter.notifyDataSetChanged();
                break;

            case "生活":
                mList.clear();
                for(int i=0;i<life.length;i++)
                {
                    mList.add(life[i]);
                }
                mAdapter.notifyDataSetChanged();
                break;
            case "新闻":
                mList.clear();
                for(int i=0;i<news.length;i++)
                {
                    mList.add(news[i]);
                }
                mAdapter.notifyDataSetChanged();
                break;
            case "医疗":
                mList.clear();
                for(int i=0;i<medical.length;i++)
                {
                    mList.add(medical[i]);
                }
                mAdapter.notifyDataSetChanged();
                break;
            case "文字":
                mList.clear();
                for(int i=0;i<writing.length;i++)
                {
                    mList.add(writing[i]);
                }
                mAdapter.notifyDataSetChanged();
                break;
            case "其他":
                mList.clear();
                for(int i=0;i<other.length;i++)
                {
                    mList.add(other[i]);
                }
                mAdapter.notifyDataSetChanged();
                break;

        }
    }


}
