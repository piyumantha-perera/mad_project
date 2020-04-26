package com.example.mad_project.Customer_Details;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mad_project.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {

    private static final String TAG = "creationViewrecycleAdapter";
    private ArrayList<String> userNames = new ArrayList<>();
    private ArrayList<String> userID = new ArrayList<>();
    private ArrayList<String> userCreations = new ArrayList<>();
    private ArrayList<String> deleteImage = new ArrayList<>();
    private ArrayList<String> creationID = new ArrayList<>();
    private Context mContext;

    public RecycleViewAdapter(ArrayList<String> userNames, ArrayList<String>
            userID, ArrayList<String> userCreations, ArrayList<String> deleteImage, ArrayList<String> creatinID, Context mContext) {
        this.userNames = userNames;
        this.userID = userID;
        this.userCreations = userCreations;
        this.deleteImage = deleteImage;
        this.creationID = creatinID;
        this.mContext = mContext;
    }
    @SuppressLint("LongLogTag")

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @SuppressLint("LongLogTag")
    @Override
    public void onBindViewHolder(@NonNull final RecycleViewAdapter.ViewHolder holder, final int position) {

        Log.d(TAG, "onBindViewHolder: called");
        Glide.with(mContext)
                .asBitmap().load(deleteImage.get(position))
                .into(holder.image);
        holder.userId.setText(userID.get(position));
        holder.userName.setText(userNames.get(position));
        holder.userCreation.setText(userCreations.get(position));
        holder.userCreationID.setText(creationID.get(position));
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on"+userNames.get(position));
                //Toast.makeText(mContext,userNames.get(position),Toast.LENGTH_SHORT).show();
                holder.image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d(TAG, "onClick: clicked on"+userNames.get(position));
                        ////Toast.makeText(mContext,userNames.get(position),Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(mContext, Delete_Creation.class);
                        intent.putExtra("creation_id", creationID.get(position));
                        mContext.startActivity(intent);
                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return userNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        CircleImageView image;
        TextView userId;
        TextView userName;
        TextView userCreation;
        TextView userCreationID;
        RelativeLayout parentLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userId = itemView.findViewById(R.id.userId);
            userName = itemView.findViewById(R.id.user_name);
            userCreation = itemView.findViewById(R.id.user_choice);
            image = itemView.findViewById(R.id.delete_image);
            userCreationID = itemView.findViewById(R.id.creationId);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }

}
