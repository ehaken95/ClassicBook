package com.seosj.classicbook;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
    private RecyclerAdapter adapter;
    private Calendar pickedDate = Calendar.getInstance();
    private Calendar minDate = Calendar.getInstance();//다음날
    private Calendar maxDate = Calendar.getInstance();//
    int mYear;
    int mMonth;
    int mDay;
    TextView DatePickerText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_2, container, false);
        DatePickerText = v.findViewById(R.id.text_menu2_datepicker);

        minDate.add(Calendar.DATE, 1);
        maxDate.add(Calendar.DATE, 30);

        DatePickerText.setOnClickListener(v1 -> setDate());


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        RecyclerView recyclerView = v.findViewById(R.id.recyclerView_testdate);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new RecyclerAdapter();
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
