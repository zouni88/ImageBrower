package top.smallc.picturebrower.model;

import java.io.Serializable;

/**
 * @author small.cao
 * @date 2018/5/14
 */
public class Parent implements Serializable{

    public int id;
    public String title;

    @Override
    public String toString() {
        return "Parent{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
