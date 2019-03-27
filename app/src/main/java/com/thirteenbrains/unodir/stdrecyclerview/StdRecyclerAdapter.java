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

    public static final int VIEWTYPE_NORMAL = 0;
    public static final int VIEWTYPE_DETAIL = 1;

    public interface OnListItemLongSelectedInterface {
        void onItemLongSelected(View v, int position);
    }

    public interface OnListItemSelectedInterface {
        void onItemSelected(View v, int position);
    }

//    public enum ViewType {
//        NORMAL(0),
//        DETAIL(1);
//
//        private final int value;
//        ViewType(int value){
//            this.value = value;
//        }
//        public int getValue(){
//            return this.value;
//        }
//    };

    private OnListItemSelectedInterface mListener;
    private OnListItemLongSelectedInterface mLongListener;


    private SparseBooleanArray mSelectedItems = new SparseBooleanArray(0);

    Context mContext;
    List<Book> mdata;
    RecyclerView recyclerView;
    int     mItemViewType;

    public StdRecyclerAdapter(Context context
            , RecyclerView recyclerView
            , OnListItemSelectedInterface listener
            , OnListItemLongSelectedInterface longListener) {
        this.mContext = context;
        this.mListener = listener;
        this.mLongListener = longListener;
        this.recyclerView = recyclerView;
        this.mItemViewType = VIEWTYPE_NORMAL;
    }

    public void setData(List<Book> data) {
        mdata = data;
        notifyDataSetChanged();
    }

    public void setItemViewType(int viewType){
        mItemViewType = viewType;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        //return super.getItemViewType(position);
        //return mItemViewType.getValue();
        Log.d("TEST", "getItemViewType =" + mItemViewType);
        return mItemViewType;

    }

    @NonNull
    @Override
    public StdViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemView view = null;
//        if ( ViewType.values()[viewType] == ViewType.NORMAL ){
        if ( viewType == VIEWTYPE_NORMAL ){
            view = new ItemView(mContext, R.layout.list_layout_normal);
            Log.d("TEST", "normal view created");
        } else   {
            view = new ItemView(mContext, R.layout.list_layout_detail);
            Log.d("TEST", "detail view created");
        }
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
