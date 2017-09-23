package seu.android.seuexamples.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import seu.android.seuexamples.model.Student;

/**
 * Created by Lenovo on 9/23/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "seulict.db";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        TableAttributes obj = new TableAttributes();
        String query = obj.tableCreation();
        try {
            db.execSQL(query);
            Log.i("Table Create", "Hoise");
        } catch (SQLException e) {
            Log.e("SQL Error", e.toString());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insertStudent(Student std) {
        ContentValues values = new ContentValues();
        values.put(TableAttributes.STUDENT_USERNAME, std.getUserName());
        values.put(TableAttributes.STUDENT_PASSWORD, std.getPassword());
        values.put(TableAttributes.STUDENT_PHONENO, std.getPhoneNo());
        values.put(TableAttributes.STUDENT_CGPA, std.getCgpa());

        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.insert(TableAttributes.TABLE_NAME, null, values);
            Log.i("Insert", "Hoise");
        } catch (SQLException e) {
            Log.e("Insert Error", e.toString());
        }
    }

    public ArrayList<Student> getAllStudentData() {
        ArrayList<Student> studentList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TableAttributes.TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        try {
            if (cursor.getCount() > 0) {
                while (!cursor.isAfterLast()) {
                    Student std = new Student();
                    std.setUserName(cursor.getString(cursor.getColumnIndex(TableAttributes.STUDENT_USERNAME)));
                    std.setPassword(cursor.getString(cursor.getColumnIndex(TableAttributes.STUDENT_PASSWORD)));
                    std.setCgpa(cursor.getFloat(cursor.getColumnIndex(TableAttributes.STUDENT_CGPA)));
                    std.setPhoneNo(cursor.getString(cursor.getColumnIndex(TableAttributes.STUDENT_PHONENO)));

                    studentList.add(std);
                    cursor.moveToNext();
                }
            }
        }catch (SQLException e){

        }finally {
            db.close();
        }
        return studentList;
    }
}
