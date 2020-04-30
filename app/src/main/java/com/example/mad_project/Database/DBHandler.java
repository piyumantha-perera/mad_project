package com.example.mad_project.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.BaseColumns;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.mad_project.Customer_Details.Profile;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.mad_project.Database.ProjectTables.EMPWorks.TABLE_EMPWORKS;
import static com.example.mad_project.Database.ProjectTables.EmployeeAdd.COLUMN_ADDRESS;
import static com.example.mad_project.Database.ProjectTables.EmployeeAdd.TABLE_EMPLOYEEADD;

public class DBHandler extends SQLiteOpenHelper {

    Context context;

    public static final String DATABASE_NAME = "Test.db" ;

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.context = context;
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
            ProjectTables.Employee.COLUMN_NETSALARY + " TEXT,"+
            ProjectTables.Employee.COLUMN_DATE + " TEXT)";
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


    private static final String CREATE_TABLE_WORK = "CREATE TABLE " + TABLE_EMPWORKS + " (" +
            ProjectTables.EMPWorks._ID + " INTEGER PRIMARY KEY," +
            ProjectTables.EMPWorks.COLUMN_NIC + " TEXT," +
            ProjectTables.EMPWorks.COLUMN_EMPName + " TEXT," +
            ProjectTables.EMPWorks.COLUMN_Work_Description + " TEXT," +
            ProjectTables.EMPWorks.COLUMN_Location + " TEXT," +
            ProjectTables.EMPWorks.COLUMN_Date + " TEXT)";

    private static final String SQL_DELETE_WORK = "DROP TABLE IF EXISTS " + TABLE_EMPWORKS;




    private static String CREATE_TABLE_PROFILE = "create table imageInfo (id INTEGER PRIMARY KEY"+", imageName TEXT"+
            ", image BLOB)";
    private static final String SQL_DELETE_PROFILE = "DROP TABLE IF EXISTS imageInfo";


    private ByteArrayOutputStream objectByteArrayOutputStream;
    private byte[] imageInBytes;


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_USERS);
        db.execSQL(CREATE_TABLE_SALARY);
        db.execSQL(CREATE_TABLE_CREATIONS);
        db.execSQL(CREATE_TABLE_THOUGHT);
        db.execSQL(CREATE_TABLE_EMPLOYEEADD);
        db.execSQL(CREATE_TABLE_WORK);

        db.execSQL(CREATE_TABLE_PROFILE);

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

        db.execSQL(SQL_DELETE_PROFILE);
        onCreate(db);

        db.execSQL(SQL_DELETE_WORK);
        onCreate(db);
    }

    public long addUserDetails(String userName, String contactNo, String email, String address, String password, String cnfPassword){


        SQLiteDatabase db = getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put(ProjectTables.Users.COULMN_USERNAME, userName);
        values.put(ProjectTables.Users.COULMN_CONTACTNO, contactNo);
        values.put(ProjectTables.Users.COULMN_EMAIL, email);
        values.put(ProjectTables.Users.COULMN_ADDRESS, address);
        values.put(ProjectTables.Users.COULMN_PASSWORD, password);
        values.put(ProjectTables.Users.COULMN_CNFPASSWORD, cnfPassword);


        long newRowId = db.insert(ProjectTables.Users.TABLE_USERS, null, values);

        db.close();
        return newRowId;
    }

    public long addCreationDetails(String name, String creation, String length, String width, String imagesURL, String description, String qty, String total, String type, String dDate){

        SQLiteDatabase db = getWritableDatabase();

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

        long newRowId = db.insert(ProjectTables.Creations.TABLE_CREATION, null, values);

        db.close();
        return newRowId;

    }

    public long addThoughtDetails(String email, String rating, String feedback){


        SQLiteDatabase db = getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put(ProjectTables.Thoughts.COULMN_EMAIL, email);
        values.put(ProjectTables.Thoughts.COULMN_RATING, rating);
        values.put(ProjectTables.Thoughts.COULMN_FEEDBACK, feedback);



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


        if (count>0){
            return true;
        }
        else {
            return false;
        }

    }

    public long addEmployeeDetails(String userName, String BasicSalary, String TravellingAllowance, String OverTime, String SalaryAdvance, String NetSalary, String Date){


        SQLiteDatabase db = getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put(ProjectTables.Employee.COLUMN_NAME, userName);
        values.put(ProjectTables.Employee.COLUMN_BASICSALARY, BasicSalary);
        values.put(ProjectTables.Employee.COLUMN_ALLOWANCE, TravellingAllowance);
        values.put(ProjectTables.Employee.COLUMN_OT, OverTime);
        values.put(ProjectTables.Employee.COLUMN_SALARYADVANCE, SalaryAdvance);
        values.put(ProjectTables.Employee.COLUMN_NETSALARY, NetSalary);
        values.put(ProjectTables.Employee.COLUMN_DATE, Date);


        long newRowId = db.insert(ProjectTables.Employee.TABLE_NAME, null, values);

        db.close();
        return newRowId;
    }

    public List readEmployeeDetails(String id){

        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {
                BaseColumns._ID,
                ProjectTables.Employee.COLUMN_NAME,
                ProjectTables.Employee.COLUMN_BASICSALARY,
                ProjectTables.Employee.COLUMN_ALLOWANCE,
                ProjectTables.Employee.COLUMN_OT,
                ProjectTables.Employee.COLUMN_SALARYADVANCE,
                ProjectTables.Employee.COLUMN_NETSALARY,
                ProjectTables.Employee.COLUMN_DATE,

        };


        String selection = ProjectTables.Employee._ID + " LIKE ?";
        String[] selectionArgs = { id };


        String sortOrder = ProjectTables.Employee._ID + " ASC";

        Cursor cursor = db.query(
                ProjectTables.Employee.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
        );

        List employeeInfo = new ArrayList<>();
        while(cursor.moveToNext()) {

            String user = cursor.getString(cursor.getColumnIndexOrThrow(ProjectTables.Employee.COLUMN_NAME));
            String BASICSALARY = cursor.getString(cursor.getColumnIndexOrThrow(ProjectTables.Employee.COLUMN_BASICSALARY));
            String ALLOWANCE = cursor.getString(cursor.getColumnIndexOrThrow(ProjectTables.Employee.COLUMN_ALLOWANCE));
            String OT = cursor.getString(cursor.getColumnIndexOrThrow(ProjectTables.Employee.COLUMN_OT));
            String SALARYADVANCE = cursor.getString(cursor.getColumnIndexOrThrow(ProjectTables.Employee.COLUMN_SALARYADVANCE));
            String NETSALARY = cursor.getString(cursor.getColumnIndexOrThrow(ProjectTables.Employee.COLUMN_NETSALARY));
            String DATE = cursor.getString(cursor.getColumnIndexOrThrow(ProjectTables.Employee.COLUMN_DATE));

            employeeInfo.add(user);
            employeeInfo.add(BASICSALARY);
            employeeInfo.add(ALLOWANCE);
            employeeInfo.add(OT);
            employeeInfo.add(SALARYADVANCE);
            employeeInfo.add(NETSALARY);
            employeeInfo.add(DATE);
        }
        cursor.close();
        return employeeInfo;
    }

    public boolean deleteEmployeeInfo(String salaryID){
        SQLiteDatabase db = getWritableDatabase();

        String selection = ProjectTables.Employee._ID + " LIKE ?";

         String[] selectionArgs = {salaryID};

         int deletedRows = db.delete(ProjectTables.Employee.TABLE_NAME, selection, selectionArgs);

        if (deletedRows >= 1){
            return true;
        }
        else {
            return false;
        }
    }

    public long addEmployeeAddDetails(String employeefname, String employeelname, String email, String address, String contactNo, String nic, String empType){

        SQLiteDatabase db = getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put(ProjectTables.EmployeeAdd.COLUMN_EMPLOYEEFNAME, employeefname);
        values.put(ProjectTables.EmployeeAdd.COLUMN_EMPLOYEELNAME, employeelname);
        values.put(ProjectTables.EmployeeAdd.COLUMN_EMAIL, email);
        values.put(ProjectTables.EmployeeAdd.COLUMN_ADDRESS, address);
        values.put(ProjectTables.EmployeeAdd.COLUMN_CONTACT, contactNo);
        values.put(ProjectTables.EmployeeAdd.COLUMN_NIC, nic);
        values.put(ProjectTables.EmployeeAdd.COLUMN_EMPLOYEETYPE, empType);

        long newRowId = db.insert(ProjectTables.EmployeeAdd.TABLE_EMPLOYEEADD, null, values);

        db.close();
        return newRowId;
    }

    public Boolean updateemployeeaddInfo(String employeefname, String employeelname, String email, String address, String contactNo, String nic, String empType) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ProjectTables.EmployeeAdd.COLUMN_EMAIL, email);
        values.put(ProjectTables.EmployeeAdd.COLUMN_ADDRESS, address);
        values.put(ProjectTables.EmployeeAdd.COLUMN_CONTACT, contactNo);
        values.put(ProjectTables.EmployeeAdd.COLUMN_NIC, nic);
        values.put(ProjectTables.EmployeeAdd.COLUMN_EMPLOYEETYPE, empType);
        values.put(ProjectTables.EmployeeAdd.COLUMN_EMPLOYEELNAME, employeelname);

        String selection = ProjectTables.EmployeeAdd.COLUMN_EMPLOYEEFNAME + " LIKE ?";
        String[] selectionArgs = { employeefname };

        int count = db.update(
                TABLE_EMPLOYEEADD,
                values,
                selection,
                selectionArgs);

        if (count >= 1){
            return true;
        } else
        {
            return false;
        }

    }
     public boolean deleteemployeeaddInfo(String employeefname) {
         SQLiteDatabase db = getWritableDatabase();

         String selection = ProjectTables.EmployeeAdd.COLUMN_EMPLOYEEFNAME + " LIKE ?";

         String[] selectionArgs = {employeefname};

         int deletedRows = db.delete(TABLE_EMPLOYEEADD, selection, selectionArgs);

         if (deletedRows >= 1){
             return true;
         }
         else {
             return false;
         }
     }

    public Cursor readallemployeeaddInfo(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+ ProjectTables.EmployeeAdd.TABLE_EMPLOYEEADD,null);
        return res;
    }

    public Cursor readEmpspinNme(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+ ProjectTables.Employee.TABLE_NAME,null);
        return res;

    }
    public Cursor readEmployeeSalary(){
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

    public boolean updateEmpSalary(String id, String userName, String BasicSalary, String TravellingAllowance, String OverTime, String SalaryAdvance, String NetSalary,String Date){

        SQLiteDatabase db = getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put(ProjectTables.Employee.COLUMN_NAME,userName);
        values.put(ProjectTables.Employee.COLUMN_BASICSALARY,BasicSalary);
        values.put(ProjectTables.Employee.COLUMN_ALLOWANCE,TravellingAllowance);
        values.put(ProjectTables.Employee.COLUMN_OT,OverTime);
        values.put(ProjectTables.Employee.COLUMN_SALARYADVANCE,SalaryAdvance);
        values.put(ProjectTables.Employee.COLUMN_NETSALARY,NetSalary);
        values.put(ProjectTables.Employee.COLUMN_DATE,Date);


        String selection = ProjectTables.Employee._ID+ " LIKE ?";
        String[] selectionArgs = { id };

        int count = db.update(
                ProjectTables.Employee.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        if (count >= 1){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean updateCreationInfo(String id, String userName, String creationName, String length, String width, String imagesURL, String description, String qty, String total, String type, String dDate){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ProjectTables.Creations.COLUMN_USERNAME, userName);
        values.put(ProjectTables.Creations.COLUMN_CREATION, creationName);
        values.put(ProjectTables.Creations.COLUMN_LENGTH, length);
        values.put(ProjectTables.Creations.COLUMN_WIDTH, width);
        values.put(ProjectTables.Creations.COLUMN_URL, imagesURL);
        values.put(ProjectTables.Creations.COLUMN_DESCRIPTION, description);
        values.put(ProjectTables.Creations.COLUMN_QUANTITY, qty);
        values.put(ProjectTables.Creations.COLUMN_AMOUNT, total);
        values.put(ProjectTables.Creations.COLUMN_TYPE, type);
        values.put(ProjectTables.Creations.COLUMN_DATE, dDate);

        String selection = ProjectTables.Creations._ID + " LIKE ?";
        String[] selectionArgs = { id };

        int count = db.update(
                ProjectTables.Creations.TABLE_CREATION,
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

    public boolean checkEmployee(String user, String nic){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        String[] columns = { ProjectTables.EmployeeAdd._ID };
        String selection = "First_Name =? and NIC =?";
        String selectionArgs[] = { user, nic };
        Cursor cursor = sqLiteDatabase.query(ProjectTables.EmployeeAdd.TABLE_EMPLOYEEADD,columns,selection,selectionArgs,null,null,null);

        int count = cursor.getCount();
        cursor.close();

        if (count>0){
            return true;
        }
        else {
            return false;
        }

    }

    public Cursor getListConents(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + ProjectTables.Creations.TABLE_CREATION , null);
        return data;
    }

    public boolean deleteCreationInfo(String creationID){

        SQLiteDatabase db = this.getWritableDatabase();

        String selection = ProjectTables.Creations._ID + " LIKE ?";
        String[] selectionArgs = { creationID };

        int deleteRow = db.delete(ProjectTables.Creations.TABLE_CREATION, selection, selectionArgs);

        if (deleteRow >= 1){
            return true;
        }
        else {
            return false;
        }

    }

    public void storeImage(Profile profile){
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            Bitmap imageToStoreBitmap = profile.getImage();

            objectByteArrayOutputStream = new ByteArrayOutputStream();
            imageToStoreBitmap.compress(Bitmap.CompressFormat.JPEG,100,objectByteArrayOutputStream);

            imageInBytes = objectByteArrayOutputStream.toByteArray();
            ContentValues objectContentValues = new ContentValues();

            objectContentValues.put("imageName",profile.getImageName());
            objectContentValues.put("image",imageInBytes);

            long checkIfQueryRuns = db.insert("imageInfo",null,objectContentValues);
            if (checkIfQueryRuns != 0){
                Toast.makeText(context, "Image add successfull.", Toast.LENGTH_SHORT).show();
                db.close();
            }
            else {
                Toast.makeText(context, "Image not Added.", Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception e){
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    public Bitmap getImage(String name){

        SQLiteDatabase db = this.getWritableDatabase();
        Bitmap bt = null;
        Cursor res = db.rawQuery("select * from imageInfo where imageName=?", new String[]{name});
        if (res.moveToNext()){
            byte[] imag = res.getBlob(2);
            bt = BitmapFactory.decodeByteArray(imag, 0,imag.length);
        }
        return bt;
    }

    public boolean deleteUserInfo(String userName){

        SQLiteDatabase db = this.getWritableDatabase();

        String selection = ProjectTables.Users.COULMN_USERNAME + " LIKE ?";
        String[] selectionArgs = { userName };

        int deleteRow = db.delete(ProjectTables.Users.TABLE_USERS, selection, selectionArgs);

        if (deleteRow >= 1){
            return true;
        }
        else {
            return false;
        }

    }

    public long ADDEmployeeWorksDetails(String nic, String employeename, String work_description, String location, String date){

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ProjectTables.EMPWorks.COLUMN_NIC, nic);
        values.put(ProjectTables.EMPWorks.COLUMN_EMPName, employeename);
        values.put(ProjectTables.EMPWorks.COLUMN_Work_Description, work_description);
        values.put(ProjectTables.EMPWorks.COLUMN_Location, location);
        values.put(ProjectTables.EMPWorks.COLUMN_Date, date);


        long newRowId = db.insert(TABLE_EMPWORKS, null, values);

        db.close();
        return newRowId;
    }

    /*public Boolean UpdateEmpWorks(String nic, String employeename, String work_description, String location, String date) {
        SQLiteDatabase db = getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put(ProjectTables.EMPWorks.COLUMN_EMPName, employeename);
        values.put(ProjectTables.EMPWorks.COLUMN_Work_Description, work_description);
        values.put(ProjectTables.EMPWorks.COLUMN_Location, location);
        values.put(ProjectTables.EMPWorks.COLUMN_Date, date);

        String selection = ProjectTables.EMPWorks.COLUMN_NIC + " LIKE ?";
        String[] selectionArgs = { nic };

        int count = db.update(
                TABLE_EMPWORKS,
                values,
                selection,
                selectionArgs);

        if (count >= 1){
            return true;
        } else
        {
            return false;
        }

    }
    public boolean DeleteEmpWorks(String nic) {
        SQLiteDatabase db = getWritableDatabase();

        String selection = ProjectTables.EMPWorks.COLUMN_NIC + " LIKE ?";

        String[] selectionArgs = {nic};

        int deletedRows = db.delete(TABLE_EMPWORKS, selection, selectionArgs);

        if (deletedRows >= 1){
            return true;
        }
        else {
            return false;
        }
    }*/

    public Cursor readEmployeeAddDetails(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+ TABLE_EMPLOYEEADD,null);
        return res;

    }
}
