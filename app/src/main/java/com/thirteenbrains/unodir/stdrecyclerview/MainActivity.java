package com.thirteenbrains.unodir.stdrecyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init(){
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        List<String> dataSet = new ArrayList<String>();
        dataSet.add("C/C++");
        dataSet.add("Java");
        dataSet.add("Kotlin");
        dataSet.add("Python");

        int i = dataSet.size();
        // specify an adapter (see also next example)
        StdRecyclerAdapter mAdapter = new StdRecyclerAdapter(this, dataSet);
        recyclerView.setAdapter(mAdapter);
//        mAdapter.setData(dataSet);
    }
}
