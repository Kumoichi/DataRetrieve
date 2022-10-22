package com.example.dataretrieve;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class DatabaseAdapter extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "MY CLASS";
    private static final String TABLE_CONTACTS = "tablename";

    private Context context;
    private static final String KEY_ID = "id";
    private static final String KEY_ID_PHY = "PHY";
    private static final String KEY_ID_CHE = "CHE";
    private static final String KEY_ID_MATHS = "MATHS";
    private static final String KEY_ID_ENG = "ENG";

    public DatabaseAdapter(Context context) {
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase arg0) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + "INTEGER PRIMARY KEY," + KEY_ID_PHY + " TEXT,"
                + KEY_ID_CHE + " TEXT," + KEY_ID_MATHS + " TEXT,"
                + KEY_ID_ENG + " TEXT" + ")";
        arg0.execSQL(CREATE_CONTACTS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        arg0.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        onCreate(arg0);
    }

    public void insert123(String phy, String che, String maths, String english) {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ID_PHY,phy);
        values.put(KEY_ID_CHE, che);
        values.put(KEY_ID_MATHS, maths);
        values.put(KEY_ID_ENG, english);
        db.insert(TABLE_CONTACTS, null, values);
        db.close();
    }

    public Cursor getInsertedData()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor x =  db.query(TABLE_CONTACTS, new String[] {KEY_ID,KEY_ID_PHY,KEY_ID_CHE,KEY_ID_MATHS,KEY_ID_ENG },null,null,null,null,null);
        return x;
    }

    public void clearData(){
        context.deleteDatabase(DATABASE_NAME);
    }

}
