package top.smallc.picturebrower.view.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import top.smallc.picturebrower.R;
import top.smallc.picturebrower.model.Parent;
import top.smallc.picturebrower.view.DetailActivity;
import top.smallc.picturebrower.view.manager.ParentManager;


/**
 * Created by cao_c on 2018/3/26.
 */

public class TitleAdapter extends RecyclerView.Adapter<TitleAdapter.RecyclerHolder> {
    private Context mContext;
    private ArrayList<Parent> dataList = new ArrayList<>();

    public TitleAdapter(RecyclerView recyclerView) {
        this.mContext = recyclerView.getContext();
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
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_title, parent, false);
        return new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerHolder holder, int position) {
        Parent refs = dataList.get(position);
        if(refs.status == 1){
            int color = ContextCompat.getColor(holder.linearLayout.getContext(),R.color.red_1);
            holder.linearLayout.setBackgroundColor(color);
        } else {
            int color = ContextCompat.getColor(holder.linearLayout.getContext(),R.color.colorAccent);
            holder.linearLayout.setBackgroundColor(color);
        }
        holder.iv_title.setTag(refs);
        holder.iv_title.setText(refs.title);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class RecyclerHolder extends RecyclerView.ViewHolder {
        private TextView iv_title;
        private LinearLayout linearLayout;
        private RecyclerHolder(View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.ll_root);
            iv_title = itemView.findViewById(R.id.iv_title);

            iv_title.setOnClickListener(v -> {
                Parent parent = (Parent) v.getTag();

                parent.status = 1;
                ParentManager.getInstance().read(v.getContext(),parent.id,1);

                DetailActivity.start(v.getContext(),parent);
            });
        }
    }

}
