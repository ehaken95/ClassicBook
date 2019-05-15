package com.seosj.classicbook;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Menu1Fragment extends Fragment{

    //메인화면
    //
    //
    //
    //
    public static Context mContext;
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
        mContext= v.getContext();

        getData();
        return v;
    }



    private void getData(){

        //기본 SharedPreference를 가져옴. (LoginActivity에서 설정한 pref)
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getContext());
        String t1 = sharedPref.getString("info_date","00");
        String t2 = sharedPref.getString("info_name","00");

        List<String> listTitle = new ArrayList<>();
        List<String> listContent = new ArrayList<>();
        if(t1.equals("00")) {
            listTitle = Arrays.asList("예약한 시험이 없습니다.");
            listContent = Arrays.asList("-");
        }else {
            listTitle = Arrays.asList(t1);
            listContent = Arrays.asList(t2);
        }
        List<Integer> listResId = Arrays.asList(R.drawable.ic_button_clickarrow);

        Data data = new Data();
        data.setTitle(listTitle.get(0));
        data.setContent(listContent.get(0));
        data.setResId(listResId.get(0));
        adapter.addItem(data);

        adapter.notifyDataSetChanged();

    }


}
