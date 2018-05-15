package top.smallc.picturebrower.view.tools.preview.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import top.smallc.picturebrower.model.Item;
import top.smallc.picturebrower.view.tools.preview.BaseFragment;
import top.smallc.picturebrower.view.tools.preview.PreviewImageFragment;

/**
 * Created by cao on 2016/7/22.
 */
public class PreviewAdapter extends FragmentStatePagerAdapter {
    private List<Item> list;
    private List<BaseFragment> fragments = new ArrayList<>();
    public PreviewAdapter(FragmentManager fm, List<Item> list) {
        super(fm);
        if(list == null){
            this.list = new ArrayList<>();
        } else {
            this.list = list;
        }
    }

    public void setData(List<Item> list){
        if(list == null){
            this.list = new ArrayList<>();
        } else {
            this.list = list;
        }
    }

    public List<BaseFragment> getFragments(){
        return fragments;
    }

    @Override
    public Fragment getItem(int position) {
        BaseFragment bf = PreviewImageFragment.newInstance(list.get(position));
        fragments.add(bf);
        return bf;
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
    }
}
