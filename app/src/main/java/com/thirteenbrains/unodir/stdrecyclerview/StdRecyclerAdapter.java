package com.thirteenbrains.unodir.stdrecyclerview;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class StdRecyclerAdapter extends RecyclerView.Adapter<StdRecyclerAdapter.StdViewHolder> {

    public interface OnListItemLongSelectedInterface {
        void onItemLongSelected(View v, int position);
    }

    public interface OnListItemSelectedInterface {
        void onItemSelected(View v, int position);
    }

    private OnListItemSelectedInterface mListener;
    private OnListItemLongSelectedInterface mLongListener;


    private SparseBooleanArray mSelectedItems = new SparseBooleanArray(0);

    Context mContext;
    List<String> mdata;
    RecyclerView recyclerView;

    public StdRecyclerAdapter(Context context
            , RecyclerView recyclerView
            , OnListItemSelectedInterface listener
            , OnListItemLongSelectedInterface longListener) {
        this.mContext = context;
        this.mListener = listener;
        this.mLongListener = longListener;
        this.recyclerView = recyclerView;
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

        if (isItemSelected(position)) {
            holder.itemView.setBackgroundColor(Color.BLUE);
        } else {
            holder.itemView.setBackgroundColor(Color.WHITE);
        }
    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public class StdViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public StdViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textView = itemView.findViewById(R.id.textView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    //mListener.onItemSelected(v, getAdapterPosition());
                    //view = recyclerView.findViewHolderForAdapterPosition(getAdapterPosition()).itemView;
                    toggleItemSelected(position);

                    Log.d("test", "position = " + position);
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mLongListener.onItemLongSelected(v, getAdapterPosition());
                    return false;
                }
            });
        }
    }

    private void toggleItemSelected(int position) {

        if (mSelectedItems.get(position, false) == true) {
            mSelectedItems.delete(position);
            notifyItemChanged(position);
        } else {
            mSelectedItems.put(position, true);
            notifyItemChanged(position);
        }
    }

    private boolean isItemSelected(int position) {
        return mSelectedItems.get(position, false);
    }

    public void clearSelectedItem() {
        int position;

        for (int i = 0; i < mSelectedItems.size(); i++) {
            position = mSelectedItems.keyAt(i);
            mSelectedItems.put(position, false);
            notifyItemChanged(position);
        }

        mSelectedItems.clear();
    }
//-------------------------------------------------------------------------
//    public static class StdViewHolder extends RecyclerView.ViewHolder
//            implements View.OnCreateContextMenuListener{
//        public TextView textView;
//
//        public StdViewHolder(@NonNull View itemView) {
//            super(itemView);
//            this.textView = itemView.findViewById(R.id.textView);
//
//            itemView.setOnCreateContextMenuListener(this);
//        }
//
//        @Override
//        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//            MenuItem Edit = menu.add(Menu.NONE, R.id.add_menu, 1, "add");
//            MenuItem Delete = menu.add(Menu.NONE, R.id.delete_menu, 2, "delete");
//            Edit.setOnMenuItemClickListener(onMenuItemClickListener);
//            Delete.setOnMenuItemClickListener(onMenuItemClickListener);
//        }
//
//        private final MenuItem.OnMenuItemClickListener onMenuItemClickListener = new MenuItem.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                switch (item.getItemId()) {
//                    case R.id.add_menu: // add
//                        return true;
//
//                    case R.id.delete_menu: // delete
//                        return true;
//                }
//                return false;
//            }
//        };
//    }
}
