package com.thirteenbrains.unodir.stdrecyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class StdRecyclerAdapter extends RecyclerView.Adapter<StdRecyclerAdapter.StdViewHolder> {

    Context mContext;
    List<String> mdata;

    public StdRecyclerAdapter(Context context, List<String> mdata) {
        this.mContext = context;
        this.mdata = mdata;
    }

    public void setData(List<String> data) {
        mdata = data;
        notifyDataSetChanged();
    }
    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public StdViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        LayoutInflater inflate = LayoutInflater.from(mContext);
        View view = inflate.inflate(R.layout.list_item, parent, false);
        StdViewHolder vh = new StdViewHolder(view);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull StdViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.textView.setText(mdata.get(position));
    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class StdViewHolder extends RecyclerView.ViewHolder{
        // each data item is just a string in this case
        public View view;
        public TextView textView;

        public StdViewHolder(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;
            this.textView = view.findViewById(R.id.textView);
        }
    }
}
