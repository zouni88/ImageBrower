package top.smallc.picturebrower.view;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.tencent.bugly.beta.Beta;

import top.smallc.picturebrower.R;
import top.smallc.picturebrower.view.tools.HeaderTools;

/**
 * @author small.cao
 * @date 2018/5/16
 */
public class SetActivity extends BaseActivity {
    private LinearLayout root;
    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.setContentView(R.layout.activity_set);
        initView();
        initData();
    }

    private void initView(){
        root = super.findViewById(R.id.ll_root);
        HeaderTools.setTitle(this,R.string.set);
        super.findViewById(R.id.rl_check_update).setOnClickListener(view -> {
            Beta.checkUpgrade(true,false);
        });
    }

    private void initData(){

    }
}
