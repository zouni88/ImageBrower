package top.smallc.picturebrower.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import me.drakeet.multitype.ItemViewBinder;
import top.smallc.picturebrower.R;
import top.smallc.picturebrower.model.Item;
import top.smallc.picturebrower.model.ItemList;
import top.smallc.picturebrower.view.tools.GridSpacingItemDecoration;

/**
 * Created by cao_c on 2018/3/25.
 */

public class SitMediaItemDetailViewBinder extends ItemViewBinder<ItemList, SitMediaItemDetailViewBinder.ViewHolder> {

    @Override
    protected @NonNull
    ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new ViewHolder(inflater.inflate(R.layout.item_discover_detail, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull ItemList detail) {
        holder.setPosts(detail.list);
        //holder.tv_content.setText(detail.content);
        //holder.tv_isfree.setVisibility(detail.paynumber>0?View.GONE:View.VISIBLE);
        //holder.ll_header_root.setTag(detail.user);

    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final static int SPAN_COUNT = 4;
        private RecyclerView recyclerView;
        private DetailMediaAdapter adapter;


        ViewHolder(@NonNull View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.rv_medias);
            GridLayoutManager layoutManager = new GridLayoutManager(itemView.getContext(), SPAN_COUNT, GridLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(layoutManager);
            int space = recyclerView.getContext().getResources().getDimensionPixelSize(R.dimen.space1);
            recyclerView.addItemDecoration(new GridSpacingItemDecoration(SPAN_COUNT,space,true));
            /* adapter 只负责灌输、适配数据，布局交给 LayoutManager，可复用 */
            adapter = new DetailMediaAdapter(recyclerView);
            recyclerView.setAdapter(adapter);

        }

        private void setPosts(List<Item> list) {
            adapter.setData(list);
            adapter.notifyDataSetChanged();
        }
    }
}
