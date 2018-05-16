package top.smallc.picturebrower.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import java.util.List;

import top.smallc.picturebrower.R;
import top.smallc.picturebrower.db.ItemDb;
import top.smallc.picturebrower.model.Item;
import top.smallc.picturebrower.model.Parent;
import top.smallc.picturebrower.view.adapter.DetailMediaGridAdapter;
import top.smallc.picturebrower.view.adapter.DetailMediaListAdapter;
import top.smallc.picturebrower.view.tools.GridSpacingItemDecoration;
import top.smallc.picturebrower.view.tools.HeaderTools;
import top.smallc.picturebrower.view.tools.PreferenceUtils;


/**
 * @author small.cao
 * @date 2018/4/3
 */
public class DetailActivity extends BaseActivity {
    private Parent parent;
    private RecyclerView recyclerView;

    private ImageView iv_point_record;
    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.setContentView(R.layout.activity_discover_detail);
        parent = (Parent) getIntent().getSerializableExtra("parent");
        if(parent == null){
            return;
        }
        initView();
        initData();
    }

    private void initView(){
        HeaderTools.setTitle(this,R.string.detail);
        recyclerView = super.findViewById(R.id.rv_media);
        iv_point_record = super.findViewById(R.id.iv_point_record);
        iv_point_record.setImageResource(R.mipmap.show_grid);
        iv_point_record.setOnClickListener(v -> {
            initShowType();
            initData();
        });
    }

    private void initShowType(){
        if(PreferenceUtils.getBoolean(context,"isGrid",false)){
            PreferenceUtils.putBoolean(context,"isGrid",false);
            iv_point_record.setImageResource(R.mipmap.show_list);
        } else {
            PreferenceUtils.putBoolean(context,"isGrid",true);
            iv_point_record.setImageResource(R.mipmap.show_grid);
        }
    }

    private void initData(){
        if(PreferenceUtils.getBoolean(context,"isGrid",false)){
            initList();
        } else {
            initGrid();
        }
    }

    private void initList(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        /* adapter 只负责灌输、适配数据，布局交给 LayoutManager，可复用 */
        DetailMediaListAdapter adapter = new DetailMediaListAdapter(recyclerView);
        recyclerView.setAdapter(adapter);

        ItemDb itemDb = new ItemDb(this);
        List<Item> list = itemDb.getItemsByParentId(parent.id);
        adapter.setData(list);
    }

    private final static int SPAN_COUNT = 3;
    private void initGrid(){
        GridLayoutManager layoutManager = new GridLayoutManager(this, SPAN_COUNT, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        int space = recyclerView.getContext().getResources().getDimensionPixelSize(R.dimen.space1);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(SPAN_COUNT,space,true));
        /* adapter 只负责灌输、适配数据，布局交给 LayoutManager，可复用 */
        DetailMediaGridAdapter adapter = new DetailMediaGridAdapter(recyclerView);
        recyclerView.setAdapter(adapter);

        ItemDb itemDb = new ItemDb(this);
        List<Item> list = itemDb.getItemsByParentId(parent.id);
        adapter.setData(list);
    }

    public static void start(Context mContext,Parent parent){
        Intent intent = new Intent(mContext, DetailActivity.class);
        intent.putExtra("parent", parent);
        mContext.startActivity(intent);
    }
}
