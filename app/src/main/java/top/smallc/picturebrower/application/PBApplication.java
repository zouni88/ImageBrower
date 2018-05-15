package top.smallc.picturebrower.application;

import android.app.Activity;
import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.crashreport.CrashReport;
import com.umeng.commonsdk.UMConfigure;

import java.util.HashSet;
import java.util.Set;

import top.smallc.picturebrower.view.BaseActivity;
import top.smallc.picturebrower.view.tools.fresco.ImagePipelineConfigFactory;

/**
 * @author small.cao
 * @date 2018/4/2
 */
public class PBApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Bugly.init(getApplicationContext(), "b31be7b51c", true);
        CrashReport.setUserId(getApplicationContext(), "android");

        /**
         * 初始化common库
         * 参数1:上下文，不能为空
         * 参数2:友盟 app key
         * 参数3:友盟 channel
         * 参数4:设备类型，UMConfigure.DEVICE_TYPE_PHONE为手机、UMConfigure.DEVICE_TYPE_BOX为盒子，默认为手机
         * 参数5:Push推送业务的secret
         */
        UMConfigure.init(this, "5afa86deb27b0a54a9000130", "Umeng", UMConfigure.DEVICE_TYPE_PHONE,null);

        Fresco.initialize(this, ImagePipelineConfigFactory.getImagePipelineConfig(this));
    }

    private Set<BaseActivity> list = new HashSet<>();

    public void addActivity(BaseActivity baseActivity){
        list.add(baseActivity);
    }

    public void removeActivity(BaseActivity baseActivity){
        if(baseActivity != null){
            list.remove(baseActivity);
        }
    }

    public void exits(){
        for (Activity activity : list) {
            activity.finish();
        }
        System.exit(0);
    }
}
