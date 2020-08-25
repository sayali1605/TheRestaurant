package com.example.therestaurant.Kitchen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.therestaurant.Admin.AdminNavigationActivity;
import com.example.therestaurant.Admin.AdminRemoveProductActivity;
import com.example.therestaurant.Model.Products;
import com.example.therestaurant.Model.ReceivedOrders;
import com.example.therestaurant.R;
import com.example.therestaurant.ViewHolder.ProductViewHolder;
import com.example.therestaurant.ViewHolder.ReceivedOrdersViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class KitchenReceivedOrderActivity extends AppCompatActivity
{
    private DatabaseReference Productref;
    private RecyclerView myProductsList;
    RecyclerView.LayoutManager layoutManager;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitchen_received_order);

        Productref = FirebaseDatabase.getInstance().getReference().child("kitchen orders");
        myProductsList = findViewById(R.id.kitchen_products_order);
        myProductsList.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        myProductsList.setLayoutManager(layoutManager);

        toolbar = findViewById(R.id.kitchen_toolbar_order);
        toolbar.setTitle("Received Orders");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        if(item.getItemId() ==android.R.id.home)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart()
    {
        super.onStart();

        FirebaseRecyclerOptions<ReceivedOrders> options =
                new FirebaseRecyclerOptions.Builder<ReceivedOrders>()
                        .setQuery(Productref, ReceivedOrders.class)
                        .build();
        FirebaseRecyclerAdapter<ReceivedOrders, ReceivedOrdersViewHolder> adapter =
                new FirebaseRecyclerAdapter<ReceivedOrders, ReceivedOrdersViewHolder>(options)
                {
                    @Override
                    protected void onBindViewHolder(@NonNull ReceivedOrdersViewHolder holder, int position, @NonNull final ReceivedOrders model)
                    {
                        holder.txtProductName.setText(model.getPname());
                        holder.txtquantity.setText("Quantity " + model.getQuantity());
                        Picasso.get().load(model.getImage()).into(holder.imageView);
                    }

                    @NonNull
                    @Override
                    public ReceivedOrdersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
                    {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.receivedorders,parent,false);
                        ReceivedOrdersViewHolder holder = new ReceivedOrdersViewHolder(view);
                        return holder;
                    }
                };
        myProductsList.setAdapter(adapter);
        adapter.startListening();
    }
}