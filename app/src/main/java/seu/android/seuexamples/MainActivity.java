package seu.android.seuexamples;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import seu.android.seuexamples.model.Student;

public class MainActivity extends AppCompatActivity {

    EditText eTUserName, eTPassword, eTCgpa, eTPhone;
    ListView listViewStd;
    ArrayList<Student> studentObjList;
    ArrayAdapter<Student> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eTUserName = (EditText) findViewById(R.id.editTextUserName);
        eTPassword = (EditText) findViewById(R.id.editTextPassword);
        eTCgpa = (EditText) findViewById(R.id.editTextUserCGPA);
        eTPhone = (EditText) findViewById(R.id.editTextPhone);

        listViewStd = (ListView) findViewById(R.id.listViewStudents);
        studentObjList = new ArrayList<>();
        adapter = new ArrayAdapter<Student>(MainActivity.this, R.layout.listview_item_layout,
                studentObjList);
        listViewStd.setAdapter(adapter);

        listViewStd.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("Student Deatils");
                dialog.setCancelable(true);
                dialog.setMessage(studentObjList.get(position).toStringForDialog());
                dialog.setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.cancel();
                    }
                });
                dialog.show();

            }
        });
    }

    public void saveData(View view) {
        boolean error = false;
        Student std = new Student();
        String userName = eTUserName.getText().toString().trim();
        if(userName.isEmpty()){
            error = true;
            eTUserName.setError("UserName is missing!");
        }else if(userName.length()<6){
            error = true;
            eTUserName.setError("UserName is too short");
        }else{
            std.setUserName(userName);
        }
        std.setPassword(eTPassword.getText().toString());
        if(eTCgpa.getText().toString().isEmpty()){
            error = true;
            eTCgpa.setError("CGPA is missing!");
        }else{
            Float cgpa = Float.parseFloat(eTCgpa.getText().toString());
            if(cgpa<=4.0 && cgpa>=0.0){
                std.setCgpa(cgpa);
            }else{
                error = true;
                eTCgpa.setError("Invalid CGPA!");
            }
        }

        String phoneNo = eTPhone.getText().toString();
        if(phoneNo.isEmpty()){
            error = true;
            eTPhone.setError("PhoneNo is missing!");
        }else if(phoneNo.length()==11 || phoneNo.length()==14){
            if(phoneNo.startsWith("017")||phoneNo.startsWith("019")|| phoneNo.startsWith("016")){
                std.setPhoneNo(phoneNo);
            }else{
                error = true;
                eTPhone.setError("PhoneNo is not valid!");
            }
        }else{
            error = true;
            eTPhone.setError("Phone No should be 11 or 14 digit");
        }

        if(error){
            Toast.makeText(MainActivity.this, "Data is not saved!", Toast.LENGTH_LONG).show();
        }else{
            studentObjList.add(std);
            adapter.notifyDataSetChanged();
            Toast.makeText(MainActivity.this, "Data is saved!", Toast.LENGTH_LONG).show();
            clearEditFields();
        }
    }

    public void clearData(View view) {
        if(view.getId()==R.id.buttonClear) {
            clearEditFields();
        }else if(view.getId()== R.id.buttonCancel){
            clearEditFields();
            MainActivity.this.finish();
        }

    }

    private void clearEditFields() {
        eTUserName.setText(null);
        eTPassword.setText(null);
        eTCgpa.setText(null);
        eTPhone.setText(null);
    }
}
