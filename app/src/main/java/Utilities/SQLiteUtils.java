package Utilities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import Constants.SQLiteConstant;
import Models.LessonModel;

/**
 * Created by Hano on 20/12/2015.
 */
public class SQLiteUtils extends SQLiteOpenHelper {
    SQLiteDatabase db;
    long rs;

    public SQLiteUtils(Context context) {
        super(context, SQLiteConstant.DB_NAME, null, SQLiteConstant.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQLiteConstant.LESSON_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQLiteConstant.LESSON_DROP_TABLE);
        onCreate(db);
    }

    public void initialDb(boolean isNew){
        db = this.getWritableDatabase();

        if (isNew){
            db.execSQL(SQLiteConstant.LESSON_DROP_TABLE);
        }
        db.execSQL(SQLiteConstant.LESSON_CREATE_TABLE);
    }

    public ArrayList<LessonModel> getAllLessons() {
        db = this.getWritableDatabase();

        ArrayList<LessonModel> list = new ArrayList<LessonModel>();
        Cursor cursor = db.rawQuery(SQLiteConstant.LESSON_LIST, null);

        if (cursor.moveToFirst()) {
            do {
                LessonModel les = new LessonModel();
                les.setID(Integer.parseInt(cursor.getString(0)));
                les.setLesID(Integer.parseInt(cursor.getString(1)));
                les.setName(cursor.getString(2));
                les.setOwner(cursor.getString(3));
                les.setState(Integer.parseInt(cursor.getString(4)));
                list.add(les);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return list;
    }

    public LessonModel getLesson(int ID){
        db = this.getWritableDatabase();

        Cursor cursor = db.query(SQLiteConstant.LESSON_TABLE,
                SQLiteConstant.LESSON_COLS,
                SQLiteConstant.LESSON_COL_ID + " = ?",
                new String[] { String.valueOf(ID) },
                null, null, null, null);

        LessonModel les = null;
        if (cursor != null && cursor.moveToFirst()){
            les = new LessonModel();
            les.setID(Integer.parseInt(cursor.getString(0)));
            les.setLesID(Integer.parseInt(cursor.getString(1)));
            les.setName(cursor.getString(2));
            les.setOwner(cursor.getString(3));
            les.setState(Integer.parseInt(cursor.getString(4)));
            cursor.close();
        }
        db.close();

        return les;
    }

    public long insertLesson(LessonModel les){
        db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(SQLiteConstant.LESSON_COL_LESID, les.getLesID());
        values.put(SQLiteConstant.LESSON_COL_NAME, les.getName());
        values.put(SQLiteConstant.LESSON_COL_OWNER, les.getOwner());
        values.put(SQLiteConstant.LESSON_COL_STATE, les.getState());

        rs = db.insert(SQLiteConstant.LESSON_TABLE, null, values);
        db.close();

        return rs;
    }

    public int updateLesson(LessonModel les){
        db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(SQLiteConstant.LESSON_COL_LESID, les.getLesID());
        values.put(SQLiteConstant.LESSON_COL_NAME, les.getName());
        values.put(SQLiteConstant.LESSON_COL_OWNER, les.getOwner());
        values.put(SQLiteConstant.LESSON_COL_STATE, les.getState());

        rs = db.update(SQLiteConstant.LESSON_TABLE, values,
                SQLiteConstant.LESSON_COL_ID + " = ?",
                new String[]{String.valueOf(les.getID())});
        db.close();

        return (int) rs;
    }

    public int deleteLesson(long id) {
        db = this.getWritableDatabase();

        rs = db.delete(SQLiteConstant.LESSON_TABLE,
                SQLiteConstant.LESSON_COL_ID + " = ?",
                new String[]{String.valueOf(id)});
        db.close();

        return (int) rs;
    }

    public int deleteLesson(ArrayList<String> ids) {
        String[] idList = new String[ids.size()];
        idList = ids.toArray(idList);

        db = this.getWritableDatabase();

        rs = db.delete(SQLiteConstant.LESSON_TABLE,
                SQLiteConstant.LESSON_COL_ID + " IN (" + makePlaceholders(idList.length) + ")",
                idList);
        db.close();

        return (int) rs;
    }

    String makePlaceholders(int len) {
        if (len < 1) {
            throw new RuntimeException("No placeholders");
        } else {
            StringBuilder sb = new StringBuilder(len * 2 - 1);
            sb.append("?");
            for (int i = 1; i < len; i++) {
                sb.append(",?");
            }
            return sb.toString();
        }
    }
}
