package com.example.mad_project.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

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



    private static final String CREATE_TABLE_CREATIONS = "CREATE TABLE " + ProjectTables.Creations.TABLE_CREATION + " (" +
            ProjectTables.Creations._ID + " INTEGER PRIMARY KEY," +
            ProjectTables.Creations.COLUMN_USERNAME + " TEXT," +
            ProjectTables.Creations.COLUMN_CREATION + " TEXT," +
            ProjectTables.Creations.COLUMN_LENGTH + " TEXT," +
            ProjectTables.Creations.COLUMN_WIDTH + " TEXT," +
            ProjectTables.Creations.COLUMN_URL + " TEXT," +
            ProjectTables.Creations.COLUMN_DESCRIPTION + " TEXT," +
            ProjectTables.Creations.COLUMN_QUANTITY + " TEXT," +
            ProjectTables.Creations.COLUMN_AMOUNT + " TEXT," +
            ProjectTables.Creations.COLUMN_TYPE + " TEXT," +
            ProjectTables.Creations.COLUMN_DATE + " TEXT)";

    private static final String SQL_DELETE_CREATIONS = "DROP TABLE IF EXISTS " + ProjectTables.Users.TABLE_USERS;



    private static final String CREATE_TABLE_SALARY = "CREATE TABLE " + ProjectTables.Employee.TABLE_NAME + " (" +
            ProjectTables.Users._ID + " INTEGER PRIMARY KEY," +
            ProjectTables.Employee.COLUMN_NAME + " TEXT," +
            ProjectTables.Employee.COLUMN_BASICSALARY + " TEXT," +
            ProjectTables.Employee.COLUMN_ALLOWANCE + " TEXT," +
            ProjectTables.Employee.COLUMN_OT + " TEXT," +
            ProjectTables.Employee.COLUMN_SALARYADVANCE + " TEXT," +
            ProjectTables.Employee.COLUMN_NETSALARY + " TEXT)";

    private static final String SQL_DELETE_SALARY = "DROP TABLE IF EXISTS " + ProjectTables.Employee.TABLE_NAME;


    private static final String CREATE_TABLE_EMPLOYEEADD = "CREATE TABLE " + ProjectTables.EmployeeAdd.TABLE_EMPLOYEEADD + "(" +
            ProjectTables.EmployeeAdd._ID + " INTEGER PRIMARY KEY," +
            ProjectTables.EmployeeAdd.COLUMN_EMPLOYEEFNAME + " TEXT," +
            ProjectTables.EmployeeAdd.COLUMN_EMPLOYEELNAME + " TEXT," +
            ProjectTables.EmployeeAdd.COLUMN_EMAIL + " TEXT," +
            ProjectTables.EmployeeAdd.COLUMN_ADDRESS + " TEXT," +
            ProjectTables.EmployeeAdd.COLUMN_CONTACT + " TEXT," +
            ProjectTables.EmployeeAdd.COLUMN_NIC + " TEXT," +
            ProjectTables.EmployeeAdd.COLUMN_EMPLOYEETYPE + " TEXT)" ;

    private static final String SQL_DELETE_EMPLOYEEADD = "DROP TABLE IF EXISTS " + ProjectTables.EmployeeAdd.TABLE_EMPLOYEEADD;


    private static final String CREATE_TABLE_THOUGHT = "CREATE TABLE " + ProjectTables.Thoughts.TABLE_Thought + " (" +
            ProjectTables.Thoughts._ID + " INTEGER PRIMARY KEY," +
            ProjectTables.Thoughts.COULMN_EMAIL + " TEXT," +
            ProjectTables.Thoughts.COULMN_RATING + " TEXT," +
            ProjectTables.Thoughts.COULMN_FEEDBACK + " TEXT)";

    private static final String SQL_DELETE_THOUGHT = "DROP TABLE IF EXISTS " + ProjectTables.Thoughts.TABLE_Thought;


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_USERS);
        db.execSQL(CREATE_TABLE_SALARY);
        db.execSQL(CREATE_TABLE_CREATIONS);
        db.execSQL(CREATE_TABLE_THOUGHT);
        db.execSQL(CREATE_TABLE_EMPLOYEEADD);
        db.execSQL(CREATE_TABLE_THOUGHT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(SQL_DELETE_USERS);
        onCreate(db);

        db.execSQL(SQL_DELETE_SALARY);
        onCreate(db);

        db.execSQL(SQL_DELETE_CREATIONS);
        onCreate(db);

        db.execSQL(SQL_DELETE_THOUGHT);
        onCreate(db);

        db.execSQL(SQL_DELETE_EMPLOYEEADD);
        onCreate(db);

        db.execSQL(SQL_DELETE_THOUGHT);
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


    public long addCreationDetails(String name, String creation, String length, String width, String imagesURL, String description, String qty, String total, String type, String dDate){

        // Gets the data repository in write mode
        SQLiteDatabase db = getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(ProjectTables.Creations.COLUMN_USERNAME, name);
        values.put(ProjectTables.Creations.COLUMN_CREATION, creation);
        values.put(ProjectTables.Creations.COLUMN_LENGTH, length);
        values.put(ProjectTables.Creations.COLUMN_WIDTH, width);
        values.put(ProjectTables.Creations.COLUMN_URL, imagesURL);
        values.put(ProjectTables.Creations.COLUMN_DESCRIPTION, description);
        values.put(ProjectTables.Creations.COLUMN_QUANTITY, qty);
        values.put(ProjectTables.Creations.COLUMN_AMOUNT, total);
        values.put(ProjectTables.Creations.COLUMN_TYPE, type);
        values.put(ProjectTables.Creations.COLUMN_DATE, dDate);


        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(ProjectTables.Creations.TABLE_CREATION, null, values);

        db.close();
        return newRowId;

    }

    public long addThoughtDetails(String email, String rating, String feedback){

        // Gets the data repository in write mode
        SQLiteDatabase db = getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(ProjectTables.Thoughts.COULMN_EMAIL, email);
        values.put(ProjectTables.Thoughts.COULMN_RATING, rating);
        values.put(ProjectTables.Thoughts.COULMN_FEEDBACK, feedback);


        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(ProjectTables.Thoughts.TABLE_Thought, null, values);

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

    public List readEmployeeDetails(String userName){


        SQLiteDatabase db = getReadableDatabase();

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
        String selection = ProjectTables.Employee.COLUMN_NAME + " LIKE ?";
        String[] selectionArgs = { userName };

        // How you want the results sorted in the resulting Cursor
        String sortOrder = ProjectTables.Employee.COLUMN_NAME + " ASC";

        Cursor cursor = db.query(
                ProjectTables.Employee.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        List employeeInfo = new ArrayList<>();
        while(cursor.moveToNext()) {
            String user = cursor.getString(cursor.getColumnIndexOrThrow(ProjectTables.Employee.COLUMN_NAME));
            String BASICSALARY = cursor.getString(cursor.getColumnIndexOrThrow(ProjectTables.Employee.COLUMN_BASICSALARY));
            String ALLOWANCE = cursor.getString(cursor.getColumnIndexOrThrow(ProjectTables.Employee.COLUMN_ALLOWANCE));
            String OT = cursor.getString(cursor.getColumnIndexOrThrow(ProjectTables.Employee.COLUMN_OT));
            String SALARYADVANCE = cursor.getString(cursor.getColumnIndexOrThrow(ProjectTables.Employee.COLUMN_SALARYADVANCE));
            String NETSALARY = cursor.getString(cursor.getColumnIndexOrThrow(ProjectTables.Employee.COLUMN_NETSALARY));

            employeeInfo.add(user);
            employeeInfo.add(BASICSALARY);
            employeeInfo.add(ALLOWANCE);
            employeeInfo.add(OT);
            employeeInfo.add(SALARYADVANCE);
            employeeInfo.add(NETSALARY);
        }
        cursor.close();
        return employeeInfo;
    }

    public int deleteEmployeeInfo(String userName){
        SQLiteDatabase db = getWritableDatabase();

        // Define 'where' part of query.
        String selection = ProjectTables.Employee.COLUMN_NAME + " LIKE ?";
       // Specify arguments in placeholder order.
         String[] selectionArgs = { userName };
        // Issue SQL statement.
        int deletedRows = db.delete(ProjectTables.Employee.TABLE_NAME, selection, selectionArgs);
        return  deletedRows;
    }
  //addemployee
    public long addEmployeeAddDetails(String employeefname, String employeelname, String email, String address, String contactNo, String nic, String empType){

        // Gets the data repository in write mode
        SQLiteDatabase db = getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(ProjectTables.EmployeeAdd.COLUMN_EMPLOYEEFNAME, employeefname);
        values.put(ProjectTables.EmployeeAdd.COLUMN_EMPLOYEELNAME, employeelname);
        values.put(ProjectTables.EmployeeAdd.COLUMN_EMAIL, email);
        values.put(ProjectTables.EmployeeAdd.COLUMN_ADDRESS, address);
        values.put(ProjectTables.EmployeeAdd.COLUMN_CONTACT, contactNo);
        values.put(ProjectTables.EmployeeAdd.COLUMN_NIC, nic);
        values.put(ProjectTables.EmployeeAdd.COLUMN_EMPLOYEETYPE, empType);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(ProjectTables.EmployeeAdd.TABLE_EMPLOYEEADD, null, values);

        db.close();
        return newRowId;
    }


    public Cursor readEmpspinNme(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+ ProjectTables.Employee.TABLE_NAME,null);
        return res;

    }

    public Cursor readCreationDetails(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+ ProjectTables.Creations.TABLE_CREATION,null);
        return res;

    }

    public Cursor readThoughtDetails(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+ ProjectTables.Thoughts.TABLE_Thought,null);
        return res;
    }

    public boolean updateUserInfo(String name, String contact, String email, String address, String password, String cnfPassword){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ProjectTables.Users.COULMN_CONTACTNO, contact);
        values.put(ProjectTables.Users.COULMN_EMAIL, email);
        values.put(ProjectTables.Users.COULMN_ADDRESS, address);
        values.put(ProjectTables.Users.COULMN_PASSWORD, password);
        values.put(ProjectTables.Users.COULMN_CNFPASSWORD, cnfPassword);

        String selection = ProjectTables.Users.COULMN_USERNAME + " LIKE ?";
        String[] selectionArgs = { name };

        int count = db.update(
                ProjectTables.Users.TABLE_USERS,
                values,
                selection,
                selectionArgs
        );

        if (count >= 1){
            return true;
        }
        else {
            return false;
        }
    }


}
