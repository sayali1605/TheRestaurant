package com.example.therestaurant.ViewHolder;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.therestaurant.Interface.ItemClickListner;
import com.example.therestaurant.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class ReceivedOrdersViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public TextView txtProductName, txtquantity;
    public ItemClickListner listner;

    public ReceivedOrdersViewHolder(@NonNull View itemView)
    {
        super(itemView);

        txtProductName = (TextView) itemView.findViewById(R.id.order_product_name);
        txtquantity = (TextView) itemView.findViewById(R.id.order_product_quantity);
    }

    public void setItemClickListner(ItemClickListner listner)
    {
        this.listner = listner;
    }
    @Override
    public void onClick(View v)
    {
        listner.onClick(v,getAdapterPosition(),false);
    }
}
