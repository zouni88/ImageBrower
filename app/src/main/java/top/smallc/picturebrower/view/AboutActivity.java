package top.smallc.picturebrower.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import top.smallc.picturebrower.R;
import top.smallc.picturebrower.view.tools.HeaderTools;

/**
 * @author small.cao
 * @date 2018/5/19
 */
public class AboutActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.setContentView(R.layout.activity_about);
        HeaderTools.setTitle(context,R.string.about);

        super.findViewById(R.id.tv_content);
    }


    public static void start(Context mContext){
        Intent intent = new Intent(mContext, AboutActivity.class);
        mContext.startActivity(intent);
    }
}
