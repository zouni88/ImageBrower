package top.smallc.picturebrower.view.manager;

import android.content.Context;

import java.util.List;

import top.smallc.picturebrower.db.ParentDb;
import top.smallc.picturebrower.model.Parent;

/**
 * @author small.cao
 * @date 2018/5/16
 */
public class ParentManager {

    private static ParentManager parentManager;

    public static ParentManager getInstance(){
        if(parentManager == null){
            parentManager = new ParentManager();
        }
        return parentManager;
    }

    public interface OnParentListener{
        void delete(int id);
    }

    private OnParentListener onParentListener;
    public void setOnParentListener(OnParentListener onParentListener){
        this.onParentListener = onParentListener;
    }

    public List<Parent> getList(Context context){
        ParentDb parentDb = new ParentDb(context);
        List<Parent> list = parentDb.getParents();
        return list;
    }

    public void  deleteById(Context context,int id){
        ParentDb parentDb = new ParentDb(context);
        if(parentDb.deleteById(id) > 0){
            onParentListener.delete(id);
        }
    }

    public void star(Context context,int id,int star){

    }

    public void read(Context context,int id,int status){
        ParentDb parentDb = new ParentDb(context);
        if(parentDb.isRead(id,status) > 0){
            onParentListener.delete(id);
        }
    }

}
