package top.smallc.picturebrower.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import top.smallc.picturebrower.R;
import top.smallc.picturebrower.model.Parent;
import top.smallc.picturebrower.view.adapter.TitleAdapter;
import top.smallc.picturebrower.view.manager.ParentManager;
import top.smallc.picturebrower.view.tools.HeaderTools;
import top.smallc.picturebrower.view.tools.PreferenceUtils;

/**
 * @author small.cao
 * @date 2018/5/14
 */
public class HomeActivity extends BaseActivity implements ParentManager.OnParentListener{

    private RecyclerView rcList;

    TitleAdapter titleAdapter;

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
            startActivity(new Intent(context,SetActivity.class));
        });
        rcList = super.findViewById(R.id.rv_list);
        rcList.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initData(){
        boolean isS = PreferenceUtils.getBoolean(context,"isStar",false);

        titleAdapter = new TitleAdapter(rcList);
        rcList.setAdapter(titleAdapter);
        if(isS){
            list = ParentManager.getInstance().getListNoRead(this);
        } else {
            list = ParentManager.getInstance().getList(this);
        }
        setData();
    }

    private void setData(){
        titleAdapter.setData(list);
    }

    @Override
    public void delete(int id) {
        setData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }
}
