package com.brillicaservices.orderfood;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

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
        //final ProgressDialog mDialog=new ProgressDialog(SignIn.this);
        //mDialog.setMessage("Loading....");
        //mDialog.show();
        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                table_user.setValue(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //Get User Information
                        //mDialog.dismiss();
                        //if (dataSnapshot.child(editPhone.getText().toString()).exists()) {

                            User user = dataSnapshot.child(editPhone.getText().toString()).getValue(User.class);
                            if (user.getPassword().equals(editPassword.getText().toString())) {
                                Toast.makeText(SignIn.this, "Signed in Success", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(SignIn.this, "Sign in Failed", Toast.LENGTH_LONG).show();
                            }
                        //}
                        //else{
                            //mDialog.dismiss();
                          //  Toast.makeText(SignIn.this, "User Not Exist", Toast.LENGTH_LONG).show();
                        //}
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}