package com.brillicaservices.orderfood.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.brillicaservices.orderfood.Interface.ItemClickListener;
import com.brillicaservices.orderfood.R;

/**
 * Created by user on 7/5/2018.
 */

public class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public ImageView image;
    public TextView txtmenuName;
    public TextView Price;
    private ItemClickListener itemClickListener;

    public MenuViewHolder(View itemView) {
        super(itemView);
        image=(ImageView)itemView.findViewById(R.id.image_item);
        txtmenuName=(TextView)itemView.findViewById(R.id.menu_name);
        Price=(TextView)itemView.findViewById(R.id.Price);
        itemView.setOnClickListener(this);
    }


    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v)
    {
        itemClickListener.onClick(v,getAdapterPosition(),false);
    }
}
