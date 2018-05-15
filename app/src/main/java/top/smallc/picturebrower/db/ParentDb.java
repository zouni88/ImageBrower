package top.smallc.picturebrower.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import top.smallc.picturebrower.model.Parent;

/**
 * @author small.cao
 * @date 2018/5/14
 */
public class ParentDb {

    private DBOpenHelper dbHelper;

    public ParentDb(Context context) {
        dbHelper = new DBOpenHelper(context);
    }

    public List<Parent> getParents() {
        List<Parent> parents = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.openDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from parent", null);
        while(cursor.moveToNext()){
            //获取你的数据
            Parent parent = new Parent();
            parent.id = cursor.getInt(cursor.getColumnIndex("id"));
            parent.title = cursor.getString(cursor.getColumnIndex("title"));
            parents.add(parent);
        }
        return parents;
    }

}
