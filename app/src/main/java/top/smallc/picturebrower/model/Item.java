package top.smallc.picturebrower.model;

import java.io.Serializable;

/**
 * @author small.cao
 * @date 2018/5/14
 */
public class Item implements Serializable{

    public int id;
    public int parentId;
    public String url;

    @Override
    public String toString() {
        return "ItemDb{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", url='" + url + '\'' +
                '}';
    }
}
