package top.smallc.picturebrower.view.tools.preview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import top.smallc.picturebrower.R;
import top.smallc.picturebrower.model.Item;
import top.smallc.picturebrower.view.BaseActivity;
import top.smallc.picturebrower.view.tools.HackyViewPager;
import top.smallc.picturebrower.view.tools.preview.adapter.PreviewAdapter;


/**
 * 预览大图
 * Created by smallc on 2018/04/01.
 */
public class PreviewActivity extends BaseActivity {

    private final String TAG = "PreviewActivity";
    private PreviewActivity mContext = PreviewActivity.this;

    public HackyViewPager viewpager;
    public TextView tv_step;
    private PreviewAdapter mImgPreviewAdapter;
    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.setContentView(R.layout.img_preview_details_preview_image);

        initView();
        initData();
    }

    private void initView(){
        viewpager = super.findViewById(R.id.viewpager);
        tv_step = super.findViewById(R.id.tv_step);
    }

    @SuppressWarnings("unchecked")
    private void initData(){
        int position = getIntent().getIntExtra("position",-1);
        Serializable serializable = getIntent().getSerializableExtra("items");
        List<Item> list = null;
        if(serializable instanceof List<?>){
            list = (List<Item>) serializable;
        }
        if(list == null){
            return;
        }
        mImgPreviewAdapter = new PreviewAdapter(getSupportFragmentManager(),list);
        viewpager.setAdapter(mImgPreviewAdapter);
        viewpager.setCurrentItem(position);
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                position++;
                updateStep(position,mImgPreviewAdapter.getCount());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        position++;
        updateStep(position,list.size());
    }

    public void updateStep(int position,int count){
        tv_step.setText(position+"/"+count);
    }

    public static void preview(Context mContext,int position,ArrayList<Item> dataList){
        Intent intent = new Intent(mContext, PreviewActivity.class);
        intent.putExtra("position", position);
        intent.putExtra("items", dataList);
        mContext.startActivity(intent);
    }

}

