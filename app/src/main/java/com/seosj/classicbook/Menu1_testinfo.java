package com.seosj.classicbook;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Menu1_testinfo extends AppCompatActivity {

    TextView txname;
    TextView tx1;
    TextView tx2;
    TextView tx3;
    TextView txAlarm;
    Button bt_cancel;
    Button bt_alarm;
    //테스트 예약 정보부분
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu1_testinfo);

        txname= findViewById(R.id.text_menu_test_bookname_tt);
        tx1 = findViewById(R.id.text_menu_test_1_detail);
        tx2 = findViewById(R.id.text_menu_test_2_detail);
        tx3 = findViewById(R.id.text_menu_test_3_detail);
        bt_cancel = findViewById(R.id.btn_cancel);

        //bt_alarm.findViewById

        //기본 SharedPreference를 가져옴. (LoginActivity에서 설정한 pref)
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String tname =sharedPref.getString("Book_Name","null");
        String t1 = sharedPref.getString("Book_Course","null");
        String t2 = sharedPref.getString("Book_Date","null");
        String t3 = sharedPref.getString("Book_Time","null");

        txname.setText(tname);
        tx1.setText(t1);
        tx2.setText(t2);
        tx3.setText(t3);


        bt_cancel.setOnClickListener((View v) -> {
            //시험 정보 삭제
            //alertDialog띄우
            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            builder
                    .setMessage("예약을 취소하시겠습니까?")
                    .setPositiveButton("확인", (dialog, which)-> {
                        SharedPreferences.Editor ed = sharedPref.edit();
                        ed.remove("info_date");
                        ed.remove("info_name");
                        ed.commit();
                        Toast.makeText(v.getContext(), "예약을 취소하였습니다",Toast.LENGTH_SHORT).show();

                        Intent inte = new Intent(v.getContext(),MainActivity.class);
                        inte.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(inte);
                        finish();
                    } )
                    .setNegativeButton("취소", (dialog, which)-> {} );
            builder.create().show();


        });
    }
}
