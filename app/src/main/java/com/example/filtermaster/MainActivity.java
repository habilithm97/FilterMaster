package com.example.filtermaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Item> items, filteredList; // 기존 아이템 어레이 리스트와 필터링된 아이템 어레이 리스트
    RecyclerViewAdapter adapter;

    EditText searchEdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        items = new ArrayList<>(); // 기존 아이템 어레이 리스트 생성
        filteredList = new ArrayList<>(); // 필터링된 아이템 어레이 리스트

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        searchEdt = (EditText)findViewById(R.id.searchEdt);

        adapter = new RecyclerViewAdapter(items, this); // 어댑터의 아이템 아이템 리스트와 연결
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        items.add(new Item("히어로"));
        items.add(new Item("다크나이트"));
        items.add(new Item("호영"));
        items.add(new Item("일리움"));
        items.add(new Item("패스파인더"));
        items.add(new Item("와일드헌터"));
        items.add(new Item("듀얼블레이더"));
        items.add(new Item("캐논슈터"));
        items.add(new Item("카인"));
        items.add(new Item("팔라딘"));
        items.add(new Item("라딘딘"));
        items.add(new Item("히어로1111"));
        adapter.notifyDataSetChanged();

        searchEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { // 입력하기 전에 처리
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { // 입력과 동시에 처리
            }

            @Override
            public void afterTextChanged(Editable editable) { // 입력 후에 처리
                String str = searchEdt.getText().toString();
                searchFilter(str);
            }
        });
    }

    public void searchFilter(String str) {
        filteredList.clear(); // 필터링된 어레이 리스트를 비우고

        for(int i = 0; i < items.size(); i++) {
            if(items.get(i).getName().toLowerCase().contains(str.toLowerCase())) { // 입력한 문자열과 일치하는 아이템이면
                filteredList.add(items.get(i)); // 필터링된 아이템 어레이 리스트에 추가하기
            }
        }
        adapter.filterList(filteredList); // 어댑터의 filterList 호출해서 필터링된 아이템 어레이 리스트를 파라미터로 전달함
    }
}