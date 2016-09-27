package com.liquorice.app.android.ui.sections.menu;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liquorice.app.android.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by eyablonskaya on 9/27/2016.
 */

public class NavigationDrawerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private List<String> menuItems =  new ArrayList<>();

    public NavigationDrawerAdapter(Context context) {
        this.context =  context;
        inflater =  LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.nav_drawer_menu_row, parent, false);
        return new DrawerMenuHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        String currentItemMenu = menuItems.get(position);
        ((DrawerMenuHolder) holder).menuTitle.setText(currentItemMenu);
    }

    @Override
    public int getItemCount() {
        return menuItems == null? 0: menuItems.size();
    }

    public void setMenuItems(List<String> menuItems) {
        this.menuItems = menuItems;
    }

    public class DrawerMenuHolder extends RecyclerView.ViewHolder{

        @Bind(R.id.tv_menu_title)
        TextView menuTitle;

        @Bind(R.id.iv_arrow_white)
        ImageView menuArrow;

        @Bind(R.id.layout_menu_item)
        LinearLayout rootMenuView;

        public DrawerMenuHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
