package com.brillicaservices.orderfood;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.brillicaservices.orderfood.Common.Common;
import com.brillicaservices.orderfood.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignIn extends AppCompatActivity {
    EditText editPhone,editPassword;
    Button SignIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        editPhone=(EditText)findViewById(R.id.edtPhone);
        editPassword=(EditText) findViewById(R.id.edtPassword);
        SignIn=(Button)findViewById(R.id.btnSignIn1);
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        final DatabaseReference table_user=database.getReference("User");

        //mDialog.show();
        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog mDialog=new ProgressDialog(SignIn.this);
                mDialog.setMessage("Wait....");
                mDialog.show();
                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.child(editPhone.getText().toString()).exists()) {
                                mDialog.dismiss();
                                User user = dataSnapshot.child(editPhone.getText().toString()).getValue(User.class);
                                user.setPhone(editPhone.getText().toString());
                                if (user.getPassword().equals(editPassword.getText().toString())) {
                                    Intent basicHome =new Intent(SignIn.this,BasicHome.class);
                                    Common.currentuser=user;
                                    startActivity(basicHome);
                                    finish();
                                    //Toast.makeText(SignIn.this, "Sign in Success", Toast.LENGTH_LONG).show();

                                } else {
                                    Toast.makeText(SignIn.this, "Sign in Failed", Toast.LENGTH_LONG).show();
                                }
                            }
                            else{
                                mDialog.dismiss();
                            Toast.makeText(SignIn.this, "User Not Exist", Toast.LENGTH_LONG).show();
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