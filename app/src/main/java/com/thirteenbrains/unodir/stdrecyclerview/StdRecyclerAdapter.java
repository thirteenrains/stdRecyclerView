package com.thirteenbrains.unodir.stdrecyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class StdRecyclerAdapter extends RecyclerView.Adapter<StdRecyclerAdapter.StdViewHolder> {

    Context mContext;
    List<String> mdata;
    RecyclerView recyclerView;

    public StdRecyclerAdapter(Context context) {
        this.mContext = context;
    }

    public void setData(List<String> data) {
        mdata = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public StdViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflate = LayoutInflater.from(mContext);
        View view = inflate.inflate(R.layout.list_item, parent, false);

        StdViewHolder vh = new StdViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull StdViewHolder holder, int position) {
        holder.textView.setText(mdata.get(position));
    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public static class StdViewHolder extends RecyclerView.ViewHolder
            implements View.OnCreateContextMenuListener{
        public TextView textView;

        public StdViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textView = itemView.findViewById(R.id.textView);

            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            MenuItem Edit = menu.add(Menu.NONE, R.id.add_menu, 1, "add");
            MenuItem Delete = menu.add(Menu.NONE, R.id.delete_menu, 2, "delete");
            Edit.setOnMenuItemClickListener(onMenuItemClickListener);
            Delete.setOnMenuItemClickListener(onMenuItemClickListener);
        }

        private final MenuItem.OnMenuItemClickListener onMenuItemClickListener = new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                case R.id.add_menu: // add
                    return true;

                case R.id.delete_menu: // delete
                    return true;
            }
            return false;
            }
        };
    }
}
