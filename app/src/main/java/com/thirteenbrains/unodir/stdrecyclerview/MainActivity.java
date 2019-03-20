package com.thirteenbrains.unodir.stdrecyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements StdRecyclerAdapter.OnListItemLongSelectedInterface
        , StdRecyclerAdapter.OnListItemSelectedInterface{

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init(){
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        List<String> dataSet = new ArrayList<String>();

        int row = 1;
        for ( int i = 0; i < 10 ; i++) {
            dataSet.add("<" + row++ + ">" + "C/C++");
            dataSet.add("<" + row++ + ">" + "Java");
            dataSet.add("<" + row++ + ">" + "Kotlin");
            dataSet.add("<" + row++ + ">" + "Python");
            dataSet.add("<" + row++ + ">" + "Ruby");
        }

        final StdRecyclerAdapter mAdapter = new StdRecyclerAdapter(this, recyclerView, this,this);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setData(dataSet);

         // 기본 구분선 추가
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(recyclerView.getContext(),new LinearLayoutManager(this).getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        // 아이템간 공백 추가
        RecyclerDecoration spaceDecoration = new RecyclerDecoration(20);
        recyclerView.addItemDecoration(spaceDecoration);

        Button btnClear = (Button) findViewById(R.id.btnClear);
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.clearSelectedItem();
            }
        });
    }

    @Override
    public void onItemSelected(View v, int position) {
        StdRecyclerAdapter.StdViewHolder viewHolder = (StdRecyclerAdapter.StdViewHolder)recyclerView.findViewHolderForAdapterPosition(position);
        Toast.makeText(this, viewHolder.textView.getText().toString(), Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, position + " clicked", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemLongSelected(View v, int position) {
        Toast.makeText(this, position + " long clicked", Toast.LENGTH_SHORT).show();
    }


}
