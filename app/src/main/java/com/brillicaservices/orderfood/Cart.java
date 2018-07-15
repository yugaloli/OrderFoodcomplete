package com.brillicaservices.orderfood;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.brillicaservices.orderfood.Common.Common;
import com.brillicaservices.orderfood.Database.Database;
import com.brillicaservices.orderfood.Model.Order;
import com.brillicaservices.orderfood.Model.Request;
import com.brillicaservices.orderfood.ViewHolder.CartAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import info.hoang8f.widget.FButton;

public class Cart extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    FirebaseDatabase database;
    DatabaseReference requests;
    TextView textTotalPrice;
    FButton btnPlace;

    List<Order> cart=new ArrayList<>();
    CartAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        //Firebase
        database=FirebaseDatabase.getInstance();
        requests=database.getReference("Requests");
        //Init
        recyclerView=(RecyclerView) findViewById(R.id.listCart);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        textTotalPrice=(TextView)findViewById(R.id.total);
        btnPlace=(FButton)findViewById(R.id.btnPlaceOrder);
        loadListFood();
        btnPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog();
            }
        });
    }
    private void showAlertDialog()
    {
        AlertDialog.Builder alertdialog=new AlertDialog.Builder(Cart.this);
        alertdialog.setTitle("One More Step");
        alertdialog.setMessage("Enter Your Address : ");
        final EditText editAddress=new EditText(Cart.this);
        LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );
        editAddress.setLayoutParams(lp);
        alertdialog.setView(editAddress);
        alertdialog.setIcon(R.drawable.ic_shopping_cart_black_24dp);
        alertdialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Request request=new Request(Common.currentuser.getPhone(),
                        Common.currentuser.getName(),
                        editAddress.getText().toString(),
                        textTotalPrice.getText().toString(),
                        cart);
                //Submit to firebase
                requests.child(String.valueOf(System.currentTimeMillis())).setValue(request);
                new Database(getBaseContext()).cleanCart();
                Toast.makeText(Cart.this,"Thank You Order Placed Success",Toast.LENGTH_LONG).show();
                finish();
            }
        });
        alertdialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertdialog.show();
    }

    private void loadListFood() {
        cart=new Database(this).getCart();
        adapter=new CartAdapter(cart,this);
        recyclerView.setAdapter(adapter);
        int total =0;
        for(Order order:cart)
        {
            total+=(Integer.parseInt(order.getPrice()))*(Integer.parseInt(order.getQuantity()));
            String numberAsString = Integer.toString(total);
            //Locale locale=new Locale("en","US");
            //NumberFormat fmt=NumberFormat.getCurrencyInstance(locale);
            textTotalPrice.setText(""+numberAsString);
        }
    }
}