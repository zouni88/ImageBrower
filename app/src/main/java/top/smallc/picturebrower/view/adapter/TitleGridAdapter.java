package top.smallc.picturebrower.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import top.smallc.picturebrower.R;
import top.smallc.picturebrower.model.Parent;
import top.smallc.picturebrower.view.DetailActivity;
import top.smallc.picturebrower.view.manager.ParentManager;
import top.smallc.picturebrower.view.tools.AutoHeightTools;
import top.smallc.picturebrower.view.tools.glide.GlideHelper;

/**
 * Created by cao_c on 2018/3/26.
 */

public class TitleGridAdapter extends RecyclerView.Adapter<TitleGridAdapter.RecyclerHolder> {
    private Context mContext;
    private ArrayList<Parent> dataList = new ArrayList<>();

    private AutoHeightTools autoHeightTools;
    public TitleGridAdapter(RecyclerView recyclerView) {
        this.mContext = recyclerView.getContext();
        autoHeightTools = new AutoHeightTools(mContext);
    }

    public void setData(List<Parent> list) {
        if (null != dataList) {
            this.dataList.clear();
            this.dataList.addAll(list);
            notifyDataSetChanged();
        }
    }

    @Override
    public RecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_title_grid, parent, false);
        return new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerHolder holder, int position) {
        Parent category = dataList.get(position);
        autoHeightTools.setAutoHeight(holder.frameLayout,2,R.dimen.space1);
//        FrescoControllerUtils.showOriginUrl(holder.iv_media,category.url);
        GlideHelper.show(holder.iv_media,category.url);
        holder.tv_title.setText(category.title);
        holder.frameLayout.setTag(category);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class RecyclerHolder extends RecyclerView.ViewHolder {
        private FrameLayout frameLayout;
        private ImageView iv_media;
        private TextView tv_title;


        private RecyclerHolder(View itemView) {
            super(itemView);
            frameLayout = itemView.findViewById(R.id.fl_root);
            frameLayout.setOnClickListener(v -> {
                Parent parent = (Parent) v.getTag();

                parent.status = 1;
                ParentManager.getInstance().read(v.getContext(),parent.id,1);

                DetailActivity.start(v.getContext(),parent);
            });
            iv_media = itemView.findViewById(R.id.iv_media);
            tv_title = itemView.findViewById(R.id.tv_title);

        }
    }
}
