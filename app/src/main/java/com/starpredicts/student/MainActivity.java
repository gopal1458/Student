package com.starpredicts.student;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.starpredicts.student.activity.NewActivity;
import com.starpredicts.student.activity.NewAddActivity;
import com.starpredicts.student.database.StudentRepo;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends ListActivity {

    Button btnAdd,btnAddNew;
    TextView student_Id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.btnAdd);
        btnAddNew = findViewById(R.id.btnAddNew);
        /*Start*/
        StudentRepo repo = new StudentRepo(this);

        ArrayList<HashMap<String, String>> studentList =  repo.getStudentList();
        if(studentList.size()!=0) {
            ListView lv = getListView();
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                    student_Id = (TextView) view.findViewById(R.id.student_Id);
                    String studentId = student_Id.getText().toString();
                    Intent objIndent = new Intent(getApplicationContext(),NewActivity.class);
                    objIndent.putExtra("student_Id", Integer.parseInt( studentId));
                    startActivity(objIndent);
                }
            });
            ListAdapter adapter = new SimpleAdapter( MainActivity.this,studentList, R.layout.view_student_entry, new String[] { "id","name"}, new int[] {R.id.student_Id, R.id.student_name});
            setListAdapter(adapter);
        }else{
            Toast.makeText(this,"No student!",Toast.LENGTH_SHORT).show();
        }
        /*End*/

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NewActivity.class);
                intent.putExtra("student_Id",0);
                startActivity(intent);
            }
        });

        btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NewAddActivity.class);
                startActivity(intent);
            }
        });
    }
}
