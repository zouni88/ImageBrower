package top.smallc.picturebrower.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import top.smallc.picturebrower.model.Item;

/**
 * @author small.cao
 * @date 2018/5/14
 */
public class ItemDb {

    private DBOpenHelper dbHelper;

    public ItemDb(Context context) {
        dbHelper = new DBOpenHelper(context);
    }

    public List<Item> getItemsByParentId(int parentId) {
        List<Item> items = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.openDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from item where parentId = ?", new String[]{parentId+""});
        while(cursor.moveToNext()){
            //获取你的数据
            Item item = new Item();
            item.id = cursor.getInt(cursor.getColumnIndex("id"));
            item.url = cursor.getString(cursor.getColumnIndex("path"));
            item.parentId = cursor.getInt(cursor.getColumnIndex("parentId"));

            items.add(item);
        }
        return items;
    }

    public Item getItemByParentIdLimit1(int parentId) {
        SQLiteDatabase sqLiteDatabase = dbHelper.openDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from item where parentId = ? limit 1", new String[]{parentId+""});

        Item item = new Item();
        if(cursor.moveToFirst()){
            //获取你的数据
            item.id = cursor.getInt(cursor.getColumnIndex("id"));
            item.url = cursor.getString(cursor.getColumnIndex("path"));
            item.parentId = cursor.getInt(cursor.getColumnIndex("parentId"));
        }
        cursor.close();
        return item;
    }
}
