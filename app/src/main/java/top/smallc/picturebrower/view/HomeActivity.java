package top.smallc.picturebrower.view;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.header.bezierlayout.BezierLayout;

import java.util.List;

import top.smallc.picturebrower.R;
import top.smallc.picturebrower.model.Parent;
import top.smallc.picturebrower.net.HttpRequestController;
import top.smallc.picturebrower.net.utils.BaseResponse;
import top.smallc.picturebrower.view.adapter.TitleGridAdapter;
import top.smallc.picturebrower.view.manager.ParentManager;
import top.smallc.picturebrower.view.tools.GridSpacingItemDecoration;
import top.smallc.picturebrower.view.tools.HeaderTools;
import top.smallc.picturebrower.view.tools.PreferenceUtils;
import top.smallc.picturebrower.view.tools.Utils;

/**
 * @author small.cao
 * @date 2018/5/14
 */
public class HomeActivity extends BaseActivity implements ParentManager.OnParentListener{
    private final int MAX_COUNT = 20;
    private RecyclerView rcList;

    TitleGridAdapter titleAdapter;
    TwinklingRefreshLayout refreshLayout;
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

        refreshLayout = super.findViewById(R.id.refresh);
        BezierLayout headerView = new BezierLayout(context);
        refreshLayout.setHeaderView(headerView);
        refreshLayout.setMaxHeadHeight(140);
        refreshLayout.setOverScrollBottomShow(false);
        refreshLayout.setEnableLoadmore(false);
        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
                new Handler().postDelayed(() ->{
                    getList(true);
                }, 500);
            }

            @Override
            public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
                new Handler().postDelayed(() ->{
                    getList(false);
                }, 500);
            }
        });
    }

    private void initData(){
        titleAdapter = new TitleGridAdapter(rcList);
        rcList.setAdapter(titleAdapter);
        setData();
    }

    private void setData(){
        boolean isS = PreferenceUtils.getBoolean(context,"isStar",false);
        List<Parent> list;
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

    private int pageNum = 1;
    private void getList(boolean isRefresh){
        if(isRefresh){
            this.pageNum = 1;
        }
        HttpRequestController.getList(context, pageNum, MAX_COUNT, response -> {
            stopLoading(isRefresh);
            if(response.getRetCode() == BaseResponse.RET_HTTP_STATUS_OK && response.categories!=null){
                if(response.categories.size() < MAX_COUNT){
                    refreshLayout.setEnableLoadmore(false);
                }
                pageNum++;
                titleAdapter.setData(response.categories);
            } else {
                Utils.toast(context,response.getRetInfo());
            }

        });
    }

    public void stopLoading(boolean isRefresh){
        context.dismissCustomDialog();
        if(!isRefresh){
            refreshLayout.finishLoadmore();
        } else {
            refreshLayout.finishRefreshing();
        }
    }
}
