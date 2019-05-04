package com.seosj.classicbook;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

public class Menu3Fragment extends Fragment{
    ArrayAdapter<CharSequence> book1,book2;
    String choice_do="";
    String choice_select="도서명";//도서명 판별
    Spinner spin1;
    Spinner spin2;
    ImageView searchbutton;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_3, container, false);

        spin1=v.findViewById(R.id.spinner_menu3_1);
        spin2=v.findViewById(R.id.spinner_menu3_2);
        searchbutton=v.findViewById(R.id.button_menu3_search);

        //btn
        book1 = ArrayAdapter.createFromResource(v.getContext(), R.array.book_cat,R.layout.spinner_settings);

        book1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin1.setAdapter(book1);
        spin1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(book1.getItem(i).equals("서양의역사와사상")){
                    choice_select ="도서명";
                    //서양도서목록으로 2번스피너 갱신
                    book2= ArrayAdapter.createFromResource(v.getContext(),R.array.book_name_seoyang,R.layout.spinner_settings);
                    book2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(book2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            //저장될 책이름
                            choice_select = book2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                            //아무것도 선택안할시

                        }
                    });

                }else if(book1.getItem(i).equals("동양의역사와사상")){
                    choice_select ="도서명";
                    //동양도서목록으로 2번스피너 갱신
                    book2= ArrayAdapter.createFromResource(v.getContext(),R.array.book_name_dongyang,R.layout.spinner_settings);
                    book2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(book2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            //저장될 책이름
                            choice_select = book2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                            //아무것도 선택안할시

                        }
                    });
                }else if(book1.getItem(i).equals("동서양의문학")){
                    choice_select ="도서명";
                    //동양도서목록으로 2번스피너 갱신
                    book2= ArrayAdapter.createFromResource(v.getContext(),R.array.book_name_dong_seo,R.layout.spinner_settings);
                    book2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(book2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            //저장될 책이름
                            choice_select = book2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                            //아무것도 선택안할시

                        }
                    });
                }else if(book1.getItem(i).equals("과학사상")){
                    choice_select ="도서명";
                    //동양도서목록으로 2번스피너 갱신
                    book2= ArrayAdapter.createFromResource(v.getContext(),R.array.book_name_science,R.layout.spinner_settings);
                    book2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(book2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            //저장될 책이름
                            choice_select = book2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                            //아무것도 선택안할시

                        }
                    });
                }
                else if(book1.getItem(i).equals("영역명")){
                    choice_select ="도서명";
                    //아무것도없을시
                    book2= ArrayAdapter.createFromResource(v.getContext(),R.array.book_name_default,R.layout.spinner_settings);
                    book2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(book2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            //선택할게없음
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

        //btn_onclick


        return v;
    }

    /*
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        book1 = ArrayAdapter.createFromResource(getActivity(), R.array.book_cat,android.R.layout.simple_spinner_item);
    }
*/
}
