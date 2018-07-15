package com.brillicaservices.orderfood;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.brillicaservices.orderfood.Common.Common;
import com.brillicaservices.orderfood.Interface.ItemClickListener;
import com.brillicaservices.orderfood.Model.Category;
import com.brillicaservices.orderfood.ViewHolder.MenuViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class Home2 extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference category;
    TextView name;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);
        //Toast.makeText(Home2.this, "WE are inside home 2 activity", Toast.LENGTH_LONG).show();
        firebaseDatabase=FirebaseDatabase.getInstance();
        category=firebaseDatabase.getReference("Category");
        name=(TextView)findViewById(R.id.namex);
        name.setText("Your Special Discount MR."+Common.currentuser.getName());
        recyclerView=(RecyclerView)findViewById(R.id.recyclerview2);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        Toast.makeText(Home2.this, "Its Working", Toast.LENGTH_LONG).show();
        loadMenu();
        FloatingActionButton btn;
        btn=(FloatingActionButton)findViewById(R.id.btnCartoriginal);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cartintent=new Intent(Home2.this,Cart.class);
                startActivity(cartintent);
            }
        });
    }

    private void loadMenu() {
        final FirebaseRecyclerAdapter<Category,MenuViewHolder> adapter=new
                FirebaseRecyclerAdapter<Category, MenuViewHolder>
                        (Category.class,R.layout.menu_item,MenuViewHolder.class,category) {
                    @Override
                    protected void populateViewHolder(final MenuViewHolder viewHolder, Category model, int position) {
                        viewHolder.txtmenuName.setText(model.getName());
                        viewHolder.Price.setText(model.getPrice());
                        Picasso.with(getBaseContext()).load(model.getImage()).into(viewHolder.image);
                        final Category clickitem=model;
                        viewHolder.setItemClickListener(new ItemClickListener() {
                            @Override
                            public void onClick(View view, int position, boolean inLongClick) {
                                //Toast.makeText(Home2.this,""+clickitem.getName(),Toast.LENGTH_LONG).show();
                                Intent intent=new Intent(Home2.this,FoodDetail.class);
                                intent.putExtra("FoodId",clickitem.getID());
                                //String name=clickitem.getName();
                                startActivity(intent);

                            }
                        });

                    }
                };
        recyclerView.setAdapter(adapter);
    }
}