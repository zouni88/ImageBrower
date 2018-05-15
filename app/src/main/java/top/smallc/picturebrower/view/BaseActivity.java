package top.smallc.picturebrower.view;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;

import com.umeng.analytics.MobclickAgent;

import top.smallc.picturebrower.R;


public class BaseActivity extends FragmentActivity {
	public static final String TAG = "BaseActivity";

    public BaseActivity context = BaseActivity.this;
    private final int ROTATE_ANIM_DURATION = 1000;

    private Dialog mDialog;
    private Dialog mNotifyDialog;//dialog2

    private boolean mIsVisiable;
    
    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        mDialog = new Dialog(this, R.style.CustomLoadingDialog);
        mDialog.setContentView(R.layout.custom_loading_dialog2);
        mDialog.setCancelable(true);
        mNotifyDialog = new Dialog(this, R.style.SettleDialog1);
        mNotifyDialog.setCancelable(false);
        mNotifyDialog.setContentView(R.layout.custom_dialog_layout3);

    }
    
    /**
	 * 显示Dialog
	 */
	public void showCustomDialog() {
	    if(isVisiable()){
            mDialog.show();
        }
	}

	/**
	 * 取消Dialog
	 */
	public void dismissCustomDialog() {
		if (mDialog != null && mIsVisiable) {
			mDialog.dismiss();
		}
	}

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dismissCustomDialog();
    }

    public boolean isVisiable() {
        return mIsVisiable;
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
        mIsVisiable = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
        mIsVisiable = false;
    }

    /**
     * 显示错误提示Dialog
     * <p>
     * Dialog中要显示的内容
     */
    public void showCustomNoticeDialog(String content) {
        TextView tv = (TextView) mNotifyDialog.findViewById(R.id.tv_content);
        tv.setText(content);
        mNotifyDialog.show();
    }

    public void showCustomNoticeDialogCanCancle(String content) {
        TextView tv = (TextView) mNotifyDialog.findViewById(R.id.tv_content);
        tv.setText(content);
        mNotifyDialog.setCancelable(true);
        mNotifyDialog.show();
    }

    public void showCustomNoticeDialog(int content) {
        TextView tv = (TextView) mNotifyDialog.findViewById(R.id.tv_content);
        tv.setText(content);
        mNotifyDialog.show();
    }

    /**
     * 取消Dialog
     */
    public void dismissCustomNoticeDialog() {
        if (mNotifyDialog != null) {
            mNotifyDialog.dismiss();
        }
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
