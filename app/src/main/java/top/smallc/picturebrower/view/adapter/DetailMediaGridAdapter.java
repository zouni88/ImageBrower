package top.smallc.picturebrower.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import top.smallc.picturebrower.R;
import top.smallc.picturebrower.model.Item;
import top.smallc.picturebrower.view.tools.AutoHeightTools;
import top.smallc.picturebrower.view.tools.glide.GlideHelper;
import top.smallc.picturebrower.view.tools.preview.PreviewActivity;

/**
 * Created by cao_c on 2018/3/26.
 */

public class DetailMediaGridAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<Item> dataList = new ArrayList<>();
    private AutoHeightTools autoHeightTools;

    public DetailMediaGridAdapter(RecyclerView recyclerView) {
        this.mContext = recyclerView.getContext();
        autoHeightTools = new AutoHeightTools(mContext);
    }

    @Override
    public void setData(List list) {
        if (null != dataList && list != null) {
            this.dataList.clear();
            this.dataList.addAll(list);
            this.notifyDataSetChanged();
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_media, parent, false);
        return new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RecyclerHolder recyclerHolder = (RecyclerHolder) holder;
        autoHeightTools.setAutoHeight(recyclerHolder.frameLayout,3);
        Item refs = dataList.get(position);
        GlideHelper.show(recyclerHolder.iv_media,refs.url);
        recyclerHolder.frameLayout.setTag(position);
    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class RecyclerHolder extends RecyclerView.ViewHolder {
        private FrameLayout frameLayout;
        private ImageView iv_media;
        private RecyclerHolder(View itemView) {
            super(itemView);
            frameLayout = itemView.findViewById(R.id.fl_root);
            iv_media = itemView.findViewById(R.id.iv_media);

            frameLayout.setOnClickListener(view ->{
                int position = (int) view.getTag();
                PreviewActivity.preview(mContext,position,dataList);
            });
        }
    }
}
