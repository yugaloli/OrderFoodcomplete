package com.brillicaservices.orderfood.ViewHolder;


import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.amulyakhare.textdrawable.TextDrawable;
import com.brillicaservices.orderfood.Interface.ItemClickListener;
import com.brillicaservices.orderfood.Model.Order;
import com.brillicaservices.orderfood.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

class CardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public TextView txt_card_name,txt_price;
    public ImageView img_cart_count;

    private ItemClickListener itemClickListener;

    public void setTxt_card_name(TextView txt_card_name) {
        this.txt_card_name = txt_card_name;
    }

    public CardViewHolder(View itemView) {
        super(itemView);
        txt_card_name=(TextView)itemView.findViewById(R.id.cart_item_name);
        txt_price=(TextView)itemView.findViewById(R.id.cart_item_price);
        img_cart_count=(ImageView)itemView.findViewById(R.id.card_item_count);
    }

    @Override
    public void onClick(View v) {

    }
}
public class CartAdapter extends RecyclerView.Adapter<CardViewHolder>{

    private List<Order> listData=new ArrayList<>();
    private Context context;

    public CartAdapter(List<Order> listData, Context context) {
        this.listData = listData;
        this.context = context;
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View itemView=inflater.inflate(R.layout.card_layout,parent,false);
        return new CardViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
        TextDrawable drawable=TextDrawable.builder()
                .buildRound(""+listData.get(position).getQuantity(), Color.RED);
        holder.img_cart_count.setImageDrawable(drawable);
        //Locale locale=new Locale("en","US");
        //NumberFormat fmt=NumberFormat.getCurrencyInstance(locale);
        //Log.e("myTag", "This is my message"+listData.get(position).getPrice());
        int price =1;
        price=(Integer.parseInt(listData.get(position).getPrice()))*(Integer.parseInt(listData.get(position).getQuantity()));
        String numberAsString = Integer.toString(price);
        holder.txt_price.setText(""+numberAsString);
        holder.txt_card_name.setText(listData.get(position).getProductName());
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }
}
