package com.example.mad_project.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

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



    private static final String CREATE_TABLE_SALARY = "CREATE TABLE " + ProjectTables.Employee.TABLE_NAME + " (" +
            ProjectTables.Users._ID + " INTEGER PRIMARY KEY," +
            ProjectTables.Employee.COLUMN_NAME + " TEXT," +
            ProjectTables.Employee.COLUMN_BASICSALARY + " TEXT," +
            ProjectTables.Employee.COLUMN_ALLOWANCE + " TEXT," +
            ProjectTables.Employee.COLUMN_OT + " TEXT," +
            ProjectTables.Employee.COLUMN_SALARYADVANCE + " TEXT," +
            ProjectTables.Employee.COLUMN_NETSALARY + " TEXT)";

    private static final String SQL_DELETE_SALARY = "DROP TABLE IF EXISTS " + ProjectTables.Employee.TABLE_NAME;


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_USERS);
        db.execSQL(CREATE_TABLE_SALARY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(SQL_DELETE_USERS);
        onCreate(db);

        db.execSQL(SQL_DELETE_SALARY);
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


    public long addEmployeeDetails(String userName, String BasicSalary, String TravellingAllowance, String OverTime, String SalaryAdvance, String NetSalary){

        // Gets the data repository in write mode
        SQLiteDatabase db = getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(ProjectTables.Employee.COLUMN_NAME, userName);
        values.put(ProjectTables.Employee.COLUMN_BASICSALARY, BasicSalary);
        values.put(ProjectTables.Employee.COLUMN_ALLOWANCE, TravellingAllowance);
        values.put(ProjectTables.Employee.COLUMN_OT, OverTime);
        values.put(ProjectTables.Employee.COLUMN_SALARYADVANCE, SalaryAdvance);
        values.put(ProjectTables.Employee.COLUMN_NETSALARY, NetSalary);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(ProjectTables.Employee.TABLE_NAME, null, values);

        db.close();
        return newRowId;
    }

    /*public void readEmployeeDetails(){
        String UserName="piyumantha";

        SQLiteDatabase db = getReadableDatabase();

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                ProjectTables.Employee.COLUMN_NAME,
                ProjectTables.Employee.COLUMN_BASICSALARY,
                ProjectTables.Employee.COLUMN_ALLOWANCE,
                ProjectTables.Employee.COLUMN_OT,
                ProjectTables.Employee.COLUMN_SALARYADVANCE,
                ProjectTables.Employee.COLUMN_NETSALARY,

        };

// Filter results WHERE "title" = 'My Title'
        String selection = ProjectTables.Employee.COLUMN_NAME + " = ?";
        String[] selectionArgs = { "UserName " };

// How you want the results sorted in the resulting Cursor
        String sortOrder =
               ProjectTables.Employee.COLUMN_NAME + " DESC";

        Cursor cursor = db.query(
                ProjectTables.Employee.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );
    }*/

}
