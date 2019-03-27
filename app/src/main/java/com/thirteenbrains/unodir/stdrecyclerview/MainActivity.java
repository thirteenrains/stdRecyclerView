package com.thirteenbrains.unodir.stdrecyclerview;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

        List<Book> dataSet = new ArrayList<Book>();

        for(int i = 0 ; i <5; i++ ) {
            dataSet.add(new Book("Java 언어로 배우는 디자인 패턴 입문", "YUKI HIROSHI", "영진닷컴"
                    , BitmapFactory.decodeResource(getResources(), R.drawable.book1)));
            dataSet.add(new Book("서른과 마흔사이 나를 되돌아볼 시간", "미리암 프리스", "비즈니스북스"
                    , BitmapFactory.decodeResource(getResources(), R.drawable.book2)));
            dataSet.add(new Book("안드로이드 애플리케이션 UI디자인 & 프로그래밍", "도카시키 마모루", "정보문화사"
                    , BitmapFactory.decodeResource(getResources(), R.drawable.book3)));
            dataSet.add(new Book("괜찮아 괜찮아 다 괜찮아", "조명연", "정음"
                    , BitmapFactory.decodeResource(getResources(), R.drawable.book4)));
        }

        final StdRecyclerAdapter mAdapter = new StdRecyclerAdapter(this, recyclerView, this,this);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setData(dataSet);

         // 기본 구분선 추가
//        DividerItemDecoration dividerItemDecoration =
//                new DividerItemDecoration(recyclerView.getContext(),new LinearLayoutManager(this).getOrientation());
//        recyclerView.addItemDecoration(dividerItemDecoration);

        // 아이템간 공백 추가
        RecyclerDecoration spaceDecoration = new RecyclerDecoration(10);
        recyclerView.addItemDecoration(spaceDecoration);

        Button btnClear = (Button) findViewById(R.id.btnClear);
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.clearSelectedItem();
            }
        });
        Button btnNormal = (Button) findViewById(R.id.btnNormal);
        btnNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.setItemViewType(StdRecyclerAdapter.VIEWTYPE_NORMAL);
            }
        });
        Button btnDetail = (Button) findViewById(R.id.btnDetail);
        btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.setItemViewType(StdRecyclerAdapter.VIEWTYPE_DETAIL);
            }
        });
    }

    @Override
    public void onItemSelected(View v, int position) {
        StdRecyclerAdapter.StdViewHolder viewHolder = (StdRecyclerAdapter.StdViewHolder)recyclerView.findViewHolderForAdapterPosition(position);
//        Toast.makeText(this, position + " clicked", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemLongSelected(View v, int position) {
        Toast.makeText(this, position + " long clicked", Toast.LENGTH_SHORT).show();
    }
}
