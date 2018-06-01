package top.smallc.picturebrower.db;

import android.content.ContentValues;
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
    private Context context;
    public ParentDb(Context context) {
        this.context = context;
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
            parent.status = cursor.getInt(cursor.getColumnIndex("status"));
            parent.star = cursor.getInt(cursor.getColumnIndex("star"));
            parent.url = cursor.getString(cursor.getColumnIndex("url"));
            parents.add(parent);
        }
        cursor.close();
        return parents;
    }

    public List<Parent> getParentsWithUnRead() {
        List<Parent> parents = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.openDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from parent where status=0",null);
        while(cursor.moveToNext()){
            //获取你的数据
            Parent parent = new Parent();
            parent.id = cursor.getInt(cursor.getColumnIndex("id"));
            parent.title = cursor.getString(cursor.getColumnIndex("title"));
            parent.status = cursor.getInt(cursor.getColumnIndex("status"));
            parent.star = cursor.getInt(cursor.getColumnIndex("star"));
            parent.url = cursor.getString(cursor.getColumnIndex("url"));
            parents.add(parent);
        }
        return parents;
    }


    public int deleteById(int id){
        SQLiteDatabase sqLiteDatabase = dbHelper.openDatabase();
        int count = sqLiteDatabase.delete("parent" ,"id=?",new String[]{id+""});
        return count;
    }

    public int star(int id,int star){
        ContentValues values = new ContentValues();
        values.put("star", star);
        int count = update(id,values);
        return count;
    }

    public int isRead(int id,int status){
        ContentValues values = new ContentValues();
        values.put("status", status);
        int count = update(id,values);
        return count;
    }

    private int update(int id,ContentValues values){
        SQLiteDatabase sqLiteDatabase = dbHelper.openDatabase();
        return sqLiteDatabase.update("parent",values,"id=?",new String[]{id+""});
    }

}
