package com.thirteenbrains.unodir.stdrecyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;

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
        ItemView view = new ItemView(mContext);

        StdViewHolder vh = new StdViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull StdViewHolder holder, int position) {

        holder.mItemView.setSelected(isItemSelected(position));
        holder.mItemView.setContents(mdata.get(position));
    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public class StdViewHolder extends RecyclerView.ViewHolder {
        public ItemView mItemView;

        public StdViewHolder(@NonNull View itemView) {
            super(itemView);
            this.mItemView = (ItemView)itemView;

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
}
