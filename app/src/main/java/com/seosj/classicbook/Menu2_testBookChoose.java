package com.seosj.classicbook;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Menu2_testBookChoose extends AppCompatActivity {

    ArrayAdapter<CharSequence> book1,book2;
    TextView stu_name;
    TextView stu_id;
    TextView stu_major;
    TextView stu_date;
    TextView stu_time;
    String choice_select="도서명";
    Spinner spin1;
    Spinner spin2;
    Button gobutton;
    String ISBN = "0";
    String ISBN_sel="1";

    String sid;
    String name;
    String tdate;
    String ttime;

    View header;
    TextView textdate;
    TextView textname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu2_test_book_choose);

        stu_name=findViewById(R.id.text_stu_name);
        stu_id=findViewById(R.id.text_stu_id);
        stu_major=findViewById(R.id.text_stu_major);
        stu_date=findViewById(R.id.text_stu_bookdate);
        stu_time=findViewById(R.id.text_stu_booktime);

        spin1=findViewById(R.id.spinner_menu2_1);
        spin2=findViewById(R.id.spinner_menu2_2);

        gobutton = findViewById(R.id.btn_gobook);

        //기본 SharedPreference를 가져옴. (LoginActivity에서 설정한 pref)
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        tdate = sharedPref.getString("Test_Date_Year","null") + "-"+
                sharedPref.getString("Test_Date_Month","null") + "-"+
                sharedPref.getString("Test_Date_Day","null");

        Intent intent = getIntent();
        ttime = intent.getExtras().getString("time");

        //자동으로 불러와야함
        stu_name.setText("서성준");
        stu_id.setText("14011125");
        stu_major.setText("컴퓨터공학과");
        //
        stu_date.setText(tdate);
        stu_time.setText(ttime);
        //


        //btn
        book1 = ArrayAdapter.createFromResource(this, R.array.book_cat,R.layout.spinner_settings);

        book1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin1.setAdapter(book1);
        spin1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(book1.getItem(i).equals("서양의역사와사상")){
                    choice_select ="도서명";
                    //ISBN 초기화
                    ISBN = "0";
                    //서양도서목록으로 2번스피너 갱신
                    book2= ArrayAdapter.createFromResource(view.getContext(),R.array.book_name_seoyang,R.layout.spinner_settings);
                    book2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(book2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            //저장될 책이름
                            choice_select = book2.getItem(i).toString();
                            //ISBN 저장
                            if(i>0)
                            {
                                ISBN = ISBN_sel;
                            }else{
                                ISBN="0";
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                            //아무것도 선택안할시

                        }
                    });

                }else if(book1.getItem(i).equals("동양의역사와사상")){
                    choice_select ="도서명";
                    //ISBN 초기화
                    ISBN = "0";
                    //동양도서목록으로 2번스피너 갱신
                    book2= ArrayAdapter.createFromResource(view.getContext(),R.array.book_name_dongyang,R.layout.spinner_settings);
                    book2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(book2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            //저장될 책이름
                            choice_select = book2.getItem(i).toString();
                            //ISBN 저장
                            if(i>0){
                                ISBN =ISBN_sel;
                            }
                            else{
                                ISBN="0";
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                            //아무것도 선택안할시

                        }
                    });
                }else if(book1.getItem(i).equals("동서양의문학")){
                    choice_select ="도서명";
                    //ISBN 초기화
                    ISBN = "0";
                    //동양도서목록으로 2번스피너 갱신
                    book2= ArrayAdapter.createFromResource(view.getContext(),R.array.book_name_dong_seo,R.layout.spinner_settings);
                    book2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(book2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            //저장될 책이름
                            choice_select = book2.getItem(i).toString();
                            //ISBN 저장
                            if(i>0){
                                ISBN =ISBN_sel;
                            }else{
                                ISBN="0";
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                            //아무것도 선택안할시

                        }
                    });
                }else if(book1.getItem(i).equals("과학사상")){
                    choice_select ="도서명";
                    //ISBN 초기화
                    ISBN = "0";
                    //동양도서목록으로 2번스피너 갱신
                    book2= ArrayAdapter.createFromResource(view.getContext(),R.array.book_name_science,R.layout.spinner_settings);
                    book2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(book2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            //저장될 책이름
                            choice_select = book2.getItem(i).toString();
                            //ISBN 저장
                            if(i>0){
                                ISBN =ISBN_sel;
                            }else{
                                ISBN="0";
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                            //아무것도 선택안할시

                        }
                    });
                }
                else if(book1.getItem(i).equals("영역명")){
                    choice_select ="도서명";
                    //ISBN 초기화
                    ISBN = "0";
                    //아무것도없을시
                    book2= ArrayAdapter.createFromResource(view.getContext(),R.array.book_name_default,R.layout.spinner_settings);
                    book2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(book2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            //선택할게없음
                            //ISBN 초기화
                            ISBN = "0";
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                            //아무것도 선택안할시

                        }
                    });
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        gobutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(ISBN.equals("0")){
                    //alertDialog띄우
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    builder
                            .setMessage("책을 선택해 주세요")
                            .setPositiveButton("확인", (dialog, which)-> {} );
                    builder.create().show();
                }else {
                    String infodate = tdate+"\n"+ttime;
                    String infoname = "광108B\n" + "도서명: " + choice_select;

                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("Book_Date",tdate);
                    editor.putString("Book_Time",ttime);
                    editor.putString("Book_Name",choice_select);

                    editor.putString("info_date",infodate);
                    editor.putString("info_name",infoname);
                    //개설년도,학기 설정
                    editor.putString("Book_Course","2019년도 1학기");
                    editor.commit();


                    header = getLayoutInflater().inflate(R.layout.recycle_thisweektest,null,false);
                    textdate = header.findViewById(R.id.textView1);
                    textname = header.findViewById(R.id.textView2);

                    textdate.setText(infodate);
                    textname.setText(infoname);

                    Toast.makeText(v.getContext(), "예약을 성공하였습니다",Toast.LENGTH_SHORT).show();
                    Intent inte = new Intent(v.getContext(),MainActivity.class);
                    inte.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(inte);
                    finish();

                }
            }
        });

    }
}
