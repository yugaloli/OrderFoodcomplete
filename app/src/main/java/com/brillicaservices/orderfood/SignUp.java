package com.brillicaservices.orderfood;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.brillicaservices.orderfood.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class SignUp extends AppCompatActivity {
    MaterialEditText editPhone,editName,editPassword;
    Button btnSignUp;
    FirebaseDatabase database=FirebaseDatabase.getInstance();
    final DatabaseReference table_user=database.getReference("User");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        editPhone=(MaterialEditText) findViewById(R.id.edtPhone);
        editName=(MaterialEditText) findViewById(R.id.edtName);
        editPassword=(MaterialEditText) findViewById(R.id.edtPassword);
        btnSignUp=(Button)findViewById(R.id.btnSignUp1);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog mDialog=new ProgressDialog(SignUp.this);
                mDialog.setMessage("Please Wait....");
                mDialog.show();
                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child(editPhone.getText().toString()).exists())
                        {
                            mDialog.dismiss();
                            Toast.makeText(SignUp.this, "User Already Registered", Toast.LENGTH_LONG).show();
                        }
                        else{
                            mDialog.dismiss();
                            User user=new User(editName.getText().toString(),editPassword.getText().toString());
                            table_user.child(editPhone.getText().toString()).setValue(user);
                            Toast.makeText(SignUp.this, "Registered", Toast.LENGTH_LONG).show();
                            Intent intent=new Intent(SignUp.this,SignIn.class);
                            startActivity(intent);
                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}