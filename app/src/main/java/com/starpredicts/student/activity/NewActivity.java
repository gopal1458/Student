package com.starpredicts.student.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.starpredicts.student.R;
import com.starpredicts.student.database.StudentRepo;
import com.starpredicts.student.model.Student;

public class NewActivity extends AppCompatActivity {

    Button btnSave ,  btnDelete;
    Button btnClose;
    EditText editTextName;
    EditText editTextEmail;
    EditText editTextAge;
    private int _Student_Id=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnClose = (Button) findViewById(R.id.btnClose);

        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextAge = (EditText) findViewById(R.id.editTextAge);

        _Student_Id =0;
        Intent intent = getIntent();
        _Student_Id =intent.getIntExtra("student_Id", 0);
        StudentRepo repo = new StudentRepo(this);
        Student student = new Student();
        student = repo.getStudentById(_Student_Id);

        editTextAge.setText(String.valueOf(student.age));
        editTextName.setText(student.name);
        editTextEmail.setText(student.email);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StudentRepo repo = new StudentRepo(getApplicationContext());
                Student student = new Student();
                student.age= Integer.parseInt(editTextAge.getText().toString());
                student.email=editTextEmail.getText().toString();
                student.name=editTextName.getText().toString();
                student.student_ID=_Student_Id;

                if (_Student_Id==0){
                    _Student_Id = repo.insert(student);

                    Toast.makeText(getApplicationContext(),"New Student Insert",Toast.LENGTH_SHORT).show();
                }else{

                    repo.update(student);
                    Toast.makeText(getApplicationContext(),"Student Record updated",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StudentRepo repo = new StudentRepo(getApplicationContext());
                repo.delete(_Student_Id);
                Toast.makeText(getApplicationContext(),"Student Record Deleted", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
