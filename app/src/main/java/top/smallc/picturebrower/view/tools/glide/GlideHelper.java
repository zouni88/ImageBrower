package top.smallc.picturebrower.view.tools.glide;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.request.RequestOptions;

import java.util.HashMap;
import java.util.Map;

/**
 * @author small.cao
 * @date 2018/5/15
 */
public class GlideHelper {

    public static void show(ImageView view,String url){
        GlideUrl gliderUrl = new GlideUrl(url, () -> {
            Map<String, String> header = new HashMap<>();
            //不一定都要添加，具体看原站的请求信息
            header.put("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)");
            header.put("Referer", "http://i.meizitu.net");
            header.put("Referer","http://www.mzitu.com");
            return header;
        });

        RequestOptions myOptions = new RequestOptions()
                .centerCrop();

        //显示图片
        Glide.with(view.getContext()).load(gliderUrl).apply(myOptions).into(view);

    }
}
