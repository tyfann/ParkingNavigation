package com.example.park.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.example.park.R;
import com.example.park.entity.Menu;

import java.util.List;

public class MainMenuAdapter extends RecyclerView.Adapter<MainMenuViewholder> {
    protected Context context;
    protected List<com.example.park.entity.Menu> menus;
    public MainMenuAdapter(Context context, List<com.example.park.entity.Menu> menus){
        this.context=context;
        this.menus=menus;
    }
    @NonNull
    @Override
    public MainMenuViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MainMenuViewholder(LayoutInflater.from(context).inflate(R.layout.item_main_menu,null));
    }

    @Override
    public void onBindViewHolder(@NonNull MainMenuViewholder holder, int position) {
        Menu menu=menus.get(position);
        holder.mImageMenuIcon.setImageResource(menu.icon);
        holder.mTxtMenuName.setText(menu.menuName);

    }

    @Override
    public int getItemCount() {
        return null!=menus?menus.size():0;
    }
}
class MainMenuViewholder extends ViewHolder {
    public ImageView mImageMenuIcon;
    public TextView mTxtMenuName;

    public MainMenuViewholder(@NonNull View itemView) {
        super(itemView);
        this.mImageMenuIcon = (ImageView)itemView.findViewById(R.id.img_menu_icon);
        this.mTxtMenuName = (TextView)itemView.findViewById(R.id.txt_menu_name);
    }
}