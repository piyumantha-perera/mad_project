package com.example.mad_project.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHandler extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Test.db" ;

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    private static final String CREATE_TABLE_USERS = "CREATE TABLE " + ProjectTables.Users.TABLE_USERS + " (" +
            ProjectTables.Users._ID + " INTEGER PRIMARY KEY," +
            ProjectTables.Users.COULMN_USERNAME + " TEXT," +
            ProjectTables.Users.COULMN_CONTACTNO + " TEXT," +
            ProjectTables.Users.COULMN_EMAIL + " TEXT," +
            ProjectTables.Users.COULMN_ADDRESS + " TEXT," +
            ProjectTables.Users.COULMN_PASSWORD + " TEXT," +
            ProjectTables.Users.COULMN_CNFPASSWORD + " TEXT)";

    private static final String SQL_DELETE_USERS = "DROP TABLE IF EXISTS " + ProjectTables.Users.TABLE_USERS;

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_USERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(SQL_DELETE_USERS);
        onCreate(db);
    }

    public long addUserDetails(String userName, String contactNo, String email, String address, String password, String cnfPassword){

        // Gets the data repository in write mode
        SQLiteDatabase db = getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(ProjectTables.Users.COULMN_USERNAME, userName);
        values.put(ProjectTables.Users.COULMN_CONTACTNO, contactNo);
        values.put(ProjectTables.Users.COULMN_EMAIL, email);
        values.put(ProjectTables.Users.COULMN_ADDRESS, address);
        values.put(ProjectTables.Users.COULMN_PASSWORD, password);
        values.put(ProjectTables.Users.COULMN_CNFPASSWORD, cnfPassword);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(ProjectTables.Users.TABLE_USERS, null, values);

        db.close();
        return newRowId;
    }

    public Cursor readUserDetails(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+ ProjectTables.Users.TABLE_USERS,null);
        return res;
    }

    public boolean checkUser(String user, String pwd){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        String[] columns = { ProjectTables.Users._ID };
        String selection = "Name =? and Password =?";
        String selectionArgs[] = { user, pwd };
        Cursor cursor = sqLiteDatabase.query(ProjectTables.Users.TABLE_USERS,columns,selection,selectionArgs,null,null,null);

        int count = cursor.getCount();
        cursor.close();
        //sqLiteDatabase.close();

        if (count>0){
            return true;
        }
        else {
            return false;
        }

    }
}
