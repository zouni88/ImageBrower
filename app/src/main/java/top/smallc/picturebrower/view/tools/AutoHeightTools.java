package top.smallc.picturebrower.view.tools;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;

import top.smallc.picturebrower.R;


public class AutoHeightTools {

    private Context context;
    private int width;
    public AutoHeightTools(Context context){
        this.context = context;
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        width = dm.widthPixels;
    }

    public void setAutoHeight(View view,int spanCount){
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        int space = context.getResources().getDimensionPixelSize(R.dimen.space1);
        layoutParams.width = (width - space*(spanCount*2-1))/spanCount;
        layoutParams.height = (width - space*(spanCount*2-1))/spanCount;
        view.setLayoutParams(layoutParams);
    }

    public void setAutoHeight(View view,int spanCount,int spaceS){
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        int space = context.getResources().getDimensionPixelSize(spaceS);
        layoutParams.width = (width - space*(spanCount-1))/spanCount;
        layoutParams.height = (width - space*(spanCount-1))/spanCount;
        view.setLayoutParams(layoutParams);
    }
}
