package top.smallc.picturebrower.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import top.smallc.picturebrower.R;
import top.smallc.picturebrower.model.Parent;
import top.smallc.picturebrower.view.adapter.TitleGridAdapter;
import top.smallc.picturebrower.view.manager.ParentManager;
import top.smallc.picturebrower.view.tools.GridSpacingItemDecoration;
import top.smallc.picturebrower.view.tools.HeaderTools;
import top.smallc.picturebrower.view.tools.PreferenceUtils;

/**
 * @author small.cao
 * @date 2018/5/14
 */
public class HomeActivity extends BaseActivity implements ParentManager.OnParentListener{

    private RecyclerView rcList;

    TitleGridAdapter titleAdapter;

    private List<Parent> list;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_home);
        ParentManager.getInstance().setOnParentListener(this);
        initView();
        initData();
    }

    private void initView(){
        HeaderTools.setTitle(this,R.string.home);
        super.findViewById(R.id.iv_point_record).setOnClickListener(v->{
            SetActivity.start(v.getContext());
        });
        rcList = super.findViewById(R.id.rv_list);
        //列表
//        rcList.setLayoutManager(new LinearLayoutManager(this));
        //网格
        GridLayoutManager layoutManager = new GridLayoutManager(context, 2);
        int space = context.getResources().getDimensionPixelSize(R.dimen.space1);
        rcList.addItemDecoration(new GridSpacingItemDecoration(2,space,false));
        rcList.setLayoutManager(layoutManager);
    }

    private void initData(){
        titleAdapter = new TitleGridAdapter(rcList);
        rcList.setAdapter(titleAdapter);
        setData();
    }

    private void setData(){
        boolean isS = PreferenceUtils.getBoolean(context,"isStar",false);
        if(isS){
            list = ParentManager.getInstance().getListNoRead(this);
        } else {
            list = ParentManager.getInstance().getList(this);
        }


        titleAdapter.setData(list);
    }

    @Override
    public void delete(int id) {
        setData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setData();
    }
}
