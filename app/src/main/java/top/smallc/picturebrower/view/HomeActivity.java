package top.smallc.picturebrower.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import top.smallc.picturebrower.R;
import top.smallc.picturebrower.view.adapter.TitleAdapter;
import top.smallc.picturebrower.view.manager.ParentManager;
import top.smallc.picturebrower.view.tools.HeaderTools;

/**
 * @author small.cao
 * @date 2018/5/14
 */
public class HomeActivity extends BaseActivity implements ParentManager.OnParentListener{

    private RecyclerView rcList;

    TitleAdapter titleAdapter;
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

        rcList = super.findViewById(R.id.rv_list);
        rcList.setLayoutManager(new LinearLayoutManager(this));

    }

    private void initData(){
        titleAdapter = new TitleAdapter(rcList);
        rcList.setAdapter(titleAdapter);

        titleAdapter.setData(ParentManager.getInstance().getList(this));
    }

    @Override
    public void delete(int id) {
        initData();
    }
}
