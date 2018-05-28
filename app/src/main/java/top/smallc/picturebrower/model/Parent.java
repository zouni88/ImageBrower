package top.smallc.picturebrower.model;

import java.io.Serializable;

/**
 * @author small.cao
 * @date 2018/5/14
 */
public class Parent implements Serializable{

    public int id;
    public String title;
    public String url;
    /**
     * 状态 0未读，1已读
     */
    public int status;
    /**
     * 1 收藏
     */
    public int star;
    @Override
    public String toString() {
        return "Parent{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
