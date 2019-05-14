package com.seosj.classicbook;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class Menu2Fragment extends Fragment{
    private RecyclerAdapter_Testreserv adapter;
    private Calendar pickedDate = Calendar.getInstance();
    private Calendar minDate = Calendar.getInstance();//다음날
    private Calendar maxDate = Calendar.getInstance();//
    int mYear;
    int mMonth;
    int mDay;
    int isweedendint;
    public int isweekend=0;
    TextView DatePickerText;
    //시험 예약화면

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_2, container, false);
        DatePickerText = v.findViewById(R.id.text_menu2_datepicker);

        //최소/최대 날짜 선택 가능 지정
        minDate.add(Calendar.DATE, 1);
        maxDate.add(Calendar.DATE, 30);

        //주말 아직 미구현됨
        DatePickerText.setOnClickListener(v1 -> setDate());


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        RecyclerView recyclerView = v.findViewById(R.id.recyclerView_testdate);
        recyclerView.setLayoutManager(linearLayoutManager);
        //리사이클러뷰 선
        recyclerView.addItemDecoration(new DividerItemDecoration(v.getContext(),1));
        adapter = new RecyclerAdapter_Testreserv();
        recyclerView.setAdapter(adapter);


        getData();



        return v;
    }


    private void setDate(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getContext(),
                (view, year, month, dayOfMonth) -> updateDisplay(year,month,dayOfMonth),
                mYear = pickedDate.get(Calendar.YEAR),
                mMonth = pickedDate.get(Calendar.MONTH),
                mDay = pickedDate.get(Calendar.DAY_OF_MONTH)


        );
        isweedendint = pickedDate.get(Calendar.DAY_OF_WEEK);
        switch(isweedendint){
            case 1: case 2:
                isweekend = 1;
                break;
        }
        datePickerDialog.getDatePicker().setMinDate(minDate.getTime().getTime());
        datePickerDialog.getDatePicker().setMaxDate(maxDate.getTimeInMillis());


        datePickerDialog.show();




    }

    private void updateDisplay(int year, int month, int day) {
        DatePickerText.setText(new StringBuilder()
                        .append(year).append("년 ")
                        .append(month+1).append("월 ")
                        .append(day).append("일")
            );
        //기본 SharedPreference를 가져옴. (LoginActivity에서 설정한 pref)
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getContext());
        //Preference 자료 수정을 위하여 editor 생성
        SharedPreferences.Editor edit = sharedPref.edit();
        //"set_term" 키의 값을 원하는 string으로 변경
        edit.putString("Test_Date_Year", Integer.toString(year));
        edit.putString("Test_Date_Month", Integer.toString(month+1));
        edit.putString("Test_Date_Day", Integer.toString(day));
        //변경된 값을 저장한다.
        edit.commit();
    }


    private void getData(){

        List<String> listTitle = Arrays.asList(
                getString(R.string.text_time1000),
                getString(R.string.text_time1020),
                getString(R.string.text_time1040),
                getString(R.string.text_time1100),
                getString(R.string.text_time1120),
                getString(R.string.text_time1140),
                getString(R.string.text_time1400),
                getString(R.string.text_time1420),
                getString(R.string.text_time1440),
                getString(R.string.text_time1500),
                getString(R.string.text_time1520),
                getString(R.string.text_time1540),
                getString(R.string.text_time1600),
                getString(R.string.text_time1620),
                getString(R.string.text_time1640)
        );
       List<String> listContent = Arrays.asList(
               getString(R.string.text_test_locaion)
       );
        List<Integer> listResId = Arrays.asList(R.drawable.ic_button_clickarrow);

        for(int i=0;i<listTitle.size();i++) {
            Data data = new Data();
            data.setTitle(listTitle.get(i));
            data.setContent(listContent.get(0));
            data.setResId(listResId.get(0));
            adapter.addItem(data);
        }
        adapter.notifyDataSetChanged();

    }


}
