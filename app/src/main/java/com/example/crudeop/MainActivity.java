package com.example.crudeop;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText ed1=(EditText) findViewById(R.id.edittext2);
        EditText ed2=(EditText) findViewById(R.id.edittext3);
        EditText ed3=(EditText) findViewById(R.id.edittext4);
        Button insert=(Button) findViewById(R.id.button);
        Button update=(Button) findViewById(R.id.button2);
        Button delete=(Button) findViewById(R.id.button3);
        Button view=(Button) findViewById(R.id.button4);

        DBhelper DB=new DBhelper(this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String namentext=ed1.getText().toString();
                String contacttext=ed2.getText().toString();
                String agetext = ed3.getText().toString();

                Boolean checkinsertdata = DB.insertuserdata(namentext,contacttext,agetext);
                if(checkinsertdata==true){
                    Toast.makeText(MainActivity.this, "Entry inserted", Toast.LENGTH_SHORT).show();

                }
                else
                    Toast.makeText(MainActivity.this, "entry not inserted", Toast.LENGTH_SHORT).show();


            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String namentext=ed1.getText().toString();
                String contacttext=ed2.getText().toString();
                String agetext = ed3.getText().toString();
                Boolean checkupdatedata = DB.updateuserdata(namentext,contacttext,agetext);
                if(checkupdatedata==true){
                    Toast.makeText(MainActivity.this, "Entry updated", Toast.LENGTH_SHORT).show();

                }
                else
                    Toast.makeText(MainActivity.this, "not updated", Toast.LENGTH_SHORT).show();

            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String namentext=ed1.getText().toString();
                Boolean checkdeletedata = DB.deleteuserdata(namentext);
                if(checkdeletedata==true){
                    Toast.makeText(MainActivity.this, "Entry deleted", Toast.LENGTH_SHORT).show();

                }
                else
                    Toast.makeText(MainActivity.this, "not deleted", Toast.LENGTH_SHORT).show();

            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res=DB.getdata();
                if(res.getCount()==0) {
                    Toast.makeText(MainActivity.this, "No entry exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Name:"+res.getString(0)+"\n");
                    buffer.append("Contact"+res.getString(1)+"\n");
                    buffer.append("dob"+res.getString(2)+"\n");


                }
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });




    }
}