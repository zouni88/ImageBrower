package top.smallc.picturebrower.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import top.smallc.picturebrower.R;
import top.smallc.picturebrower.model.Item;
import top.smallc.picturebrower.view.tools.AutoHeightTools;


/**
 * Created by cao_c on 2018/3/26.
 */

public class MediaDetailAdapter extends RecyclerView.Adapter<MediaDetailAdapter.RecyclerHolder> {
    private Context mContext;
    private ArrayList<Item> dataList = new ArrayList<>();

    private AutoHeightTools autoHeightTools;
    public MediaDetailAdapter(RecyclerView recyclerView) {
        this.mContext = recyclerView.getContext();
        autoHeightTools = new AutoHeightTools(mContext);
    }

    public void setData(List<Item> list) {
        if (null != dataList) {
            this.dataList.clear();
            this.dataList.addAll(list);
            notifyDataSetChanged();
        }
    }

    @Override
    public RecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_media, parent, false);
        return new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerHolder holder, int position) {
        autoHeightTools.setAutoHeight(holder.iv_media,4);
        Item refs = dataList.get(position);

        GlideUrl gliderUrl = new GlideUrl(refs.url, new com.bumptech.glide.load.model.Headers() {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> header = new HashMap<>();
                //不一定都要添加，具体看原站的请求信息
                header.put("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)");
                header.put("Referer", "http://i.meizitu.net");
                header.put("Referer","http://www.mzitu.com");
                return header;
            }
        });

        //显示图片
        Glide.with(holder.iv_media.getContext()).load(gliderUrl).into(holder.iv_media);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class RecyclerHolder extends RecyclerView.ViewHolder {
        private ImageView iv_media;


        private RecyclerHolder(View itemView) {
            super(itemView);
            iv_media = itemView.findViewById(R.id.iv_media);

        }
    }


}
