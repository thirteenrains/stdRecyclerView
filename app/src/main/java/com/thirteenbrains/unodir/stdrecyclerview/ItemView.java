package com.thirteenbrains.unodir.stdrecyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ItemView extends LinearLayout {
    public TextView textView;
    public ItemView(Context context) {
        super(context);

        LayoutInflater inflate = LayoutInflater.from(context);

        // inflate itemLayout & attach to this LinearLayout
        View v = inflate.inflate(R.layout.list_item, this, true);

        // bind widget
        textView = v.findViewById(R.id.textView);

        v.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
    }

    void setContents(String contents) {
        textView.setText(contents);
    }
}
