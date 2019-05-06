package com.seosj.classicbook;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Arrays;
import java.util.List;

public class Menu1Fragment extends Fragment{

    //메인화면
    //
    //
    //
    //
    private RecyclerAdapter_testinfo adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_1, container, false);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        RecyclerView recyclerView = v.findViewById(R.id.recyclerView1);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new RecyclerAdapter_testinfo();
        recyclerView.setAdapter(adapter);


        getData();

        return v;
    }


    private void getData(){

        //추후 자동으로 불러와야 함
        List<String> listTitle = Arrays.asList("2019-03-19\n10:00~10:20");
        List<String> listContent = Arrays.asList("광108B\n도서명: 실락원");
        List<Integer> listResId = Arrays.asList(R.drawable.ic_button_clickarrow);

        Data data = new Data();
        data.setTitle(listTitle.get(0));
        data.setContent(listContent.get(0));
        data.setResId(listResId.get(0));
        adapter.addItem(data);

        adapter.notifyDataSetChanged();

    }


}
