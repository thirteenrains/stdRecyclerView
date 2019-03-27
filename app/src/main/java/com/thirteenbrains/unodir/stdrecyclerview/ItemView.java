package com.thirteenbrains.unodir.stdrecyclerview;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ItemView extends LinearLayout {
    public TextView title;
    public TextView author;
    public TextView publisher;
    public ImageView image;
    public ItemView(Context context, @LayoutRes int resource) {
        super(context);

        LayoutInflater inflate = LayoutInflater.from(context);

        // inflate itemLayout & attach to this LinearLayout
        View v = inflate.inflate(resource, this, true);

        // bind widget
        title = v.findViewById(R.id.title);
        author = v.findViewById(R.id.author);
        publisher = v.findViewById(R.id.publisher);
        image = v.findViewById(R.id.image);

        v.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
    }

    void setContents(Book book) {
        title.setText(book.getTitle());
        author.setText(book.getAuthor());
        publisher.setText(book.getPublisher());
        if ( book.getBitmap() != null)
            image.setImageBitmap(book.getBitmap());
    }
}
