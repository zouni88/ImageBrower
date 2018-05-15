package top.smallc.picturebrower.view.tools.preview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.chrisbanes.photoview.PhotoView;

import top.smallc.picturebrower.R;
import top.smallc.picturebrower.model.Item;
import top.smallc.picturebrower.view.tools.glide.GlideHelper;


/**
 * 预览大图 fragment
 */
public class PreviewImageFragment extends BaseFragment {
    private final String TAG = "NotifyFragment";
    //解锁查看
    private View root;
    private PhotoView imgView;
    public static PreviewImageFragment newInstance(Item item) {

        Bundle bundle = new Bundle();
        bundle.putSerializable("item",item);
        PreviewImageFragment fragment = new PreviewImageFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.img_preview_item, null);
        initView();
        initData();
        return root;
    }

    private void initView(){
        imgView = root.findViewById(R.id.image_zoom);

        imgView.setOnPhotoTapListener((view, x, y) -> {
            getActivity().finish();
        });
    }

    private void initData(){
        Item item = (Item) getArguments().getSerializable("item");
        isOpen(true,item);
    }

    private void isOpen(boolean isCan,Item item){
        GlideHelper.show(imgView,item.url);
//        FrescoControllerUtils.showOriginUrlByPhotoView(imgView,item.url);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}