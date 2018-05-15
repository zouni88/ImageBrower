package top.smallc.picturebrower.view.tools;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import top.smallc.picturebrower.R;
import top.smallc.picturebrower.view.BaseActivity;


public class HeaderTools {

    public static void setTitle(BaseActivity baseActivity, int title){
        if(baseActivity == null){
            return;
        }
        ImageView back = baseActivity.findViewById(R.id.iv_back);
        if(back != null){
            back.setOnClickListener(view ->{
                baseActivity.finish();
            });
        }

        TextView titleTv = baseActivity.findViewById(R.id.tv_title);
        if(titleTv != null){
            String titles = baseActivity.getResources().getString(title);
            titleTv.setText(titles);
        }
    }

    public static void setTitle(BaseActivity baseActivity,View root, int title){
        if(baseActivity == null){
            return;
        }
        ImageView back = root.findViewById(R.id.iv_back);
        if(back != null){
            back.setOnClickListener(view ->{
                baseActivity.finish();
            });
        }
        TextView titleTv = root.findViewById(R.id.tv_title);
        if(titleTv != null){
            String titles = baseActivity.getResources().getString(title);
            titleTv.setText(titles);
        }
    }
}
