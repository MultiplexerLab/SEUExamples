package seu.android.seuexamples.database;

/**
 * Created by Lenovo on 9/23/2017.
 */

public class TableAttributes {

    public static final String TABLE_NAME = "student";
    public static final String STUDENT_ID = "id";
    public static final String STUDENT_USERNAME = "name";
    public static final String STUDENT_PHONENO = "phoneno";
    public static final String STUDENT_PASSWORD = "password";
    public static final String STUDENT_CGPA = "cgpa";

    public String tableCreation(){
        String query = "CREATE TABLE " + TABLE_NAME + "(" + STUDENT_ID + " INTEGER AUTO INCREMENT PRIMARY KEY," +
                STUDENT_USERNAME + " TEXT, " + STUDENT_PASSWORD + " TEXT, " + STUDENT_PHONENO + " TEXT, " + STUDENT_CGPA
                + " TEXT)";
        return query;
    }

}
