package com.brillicaservices.orderfood;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.brillicaservices.orderfood.Database.Database;
import com.brillicaservices.orderfood.Model.Category;
import com.brillicaservices.orderfood.Model.Order;
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class FoodDetail extends AppCompatActivity {
    TextView food_name,food_price,food_desc,food_discount;
    ImageView food_image;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton btncart;
    ElegantNumberButton numberButton;
    String foodId="";
    FirebaseDatabase database;
    DatabaseReference foods;
    Category currentFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);
        //Firebase
        database=FirebaseDatabase.getInstance();
        foods=database.getReference("Category");
        numberButton=(ElegantNumberButton)findViewById(R.id.number_button);
        btncart=(FloatingActionButton)findViewById(R.id.btnCart);
        food_desc=(TextView)findViewById(R.id.item_description);
        food_name=(TextView)findViewById(R.id.food_name);
        food_price=(TextView)findViewById(R.id.food_price);
        food_image=(ImageView)findViewById(R.id.img_food);
        collapsingToolbarLayout=(CollapsingToolbarLayout)findViewById(R.id.collapsing);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);

        if(getIntent()!=null){
            foodId=getIntent().getStringExtra("FoodId");
            //Toast.makeText(FoodDetail.this,""+foodId,Toast.LENGTH_LONG).show();
        }
        if(!foodId.isEmpty()){
            getDetailFood(foodId);
        }
        btncart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Database(getBaseContext()).addToCart(new Order(foodId,currentFood.getName(),
                        numberButton.getNumber(),currentFood.getPrice()));

                Toast.makeText(FoodDetail.this,"Added To Cart",Toast.LENGTH_LONG).show();
                //Toast.makeText(FoodDetail.this,""+numberButton.getNumber(),Toast.LENGTH_LONG).show();

            }

        });

    }

    private void getDetailFood(String foodId) {
        foods.child(foodId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                currentFood=dataSnapshot.getValue(Category.class);
                Picasso.with(getBaseContext()).load(currentFood.getImage()).into(food_image);
                collapsingToolbarLayout.setTitle(currentFood.getName());
                food_price.setText(currentFood.getPrice());
                food_name.setText(currentFood.getName());
                food_desc.setText(currentFood.getDescription());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
