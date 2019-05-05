package com.seosj.classicbook;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Menu3_searchLibrary extends AppCompatActivity{

    TextView txbookname;
    TextView txbookloc;
    TextView txbooknum;
    TextView txbookdate;
    TextView txbookrent;
    String URL;
    String URL1 = "http://library.sejong.ac.kr/search/Search.Result.ax?sid=1&q=ISBN%3A";
    String URL2 = "&eq=&mf=true&qt=ISBN%3D";
    String URL3 = "&qf=";
    String URL4 = "&f=&br=&cl=1+2+3+4+5+7+8+9+18+19+20+21+22+23+24+25+26+27+28+29+30+31+32+33+" +
            "34+35+36+37+61+62+34+35+38+39+11+12+13+63+44+45+51+52+42+43+49+50+40+41+57+58+48" +
            "+59+61+62&gr=1+2+3+4+5+6+7+8+9+10+12+13+20&rl=&page=&pageSize=10&s=&st=&h=&cr=&p" +
            "y=&subj=&facet=Y&nd=&vid=0&tabID=";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu3_search_library);

        txbookname = findViewById(R.id.text_menu_bookname);
        txbookloc = findViewById(R.id.textView_menu3_search2);
        txbooknum = findViewById(R.id.textView_menu3_search4);
        txbookdate = findViewById(R.id.textView_menu3_search5);
        txbookrent = findViewById(R.id.textView_menu3_search6);
        Intent intent = getIntent();
        String bookname=intent.getExtras().getString("Book");
        String ISBN=intent.getExtras().getString("ISBN");
        txbookname.setText(bookname);
        Log.d("searchsearch",ISBN);
        URL = URL1 + ISBN + URL2 + ISBN + URL3 + ISBN + URL4;
        SearchTask sc = new SearchTask();
        sc.execute();
    }


    private class SearchTask extends AsyncTask<Void,Void,Void>{
        private Elements element;

        @Override
        protected void onPostExecute(Void result){
            //doinbackground작업끝나고 할작업
            super.onPostExecute(result);


        }
        @Override
        protected Void doInBackground(Void... params){
            try{
                Document doc = Jsoup.connect(URL).get();
                element = doc.select(".tbRecord1");
                Log.d("searchsearch",element.toString());
                System.out.println("searchsearch" + element + "1searchsearch");

            }catch (IOException e){
                e.printStackTrace();
            }
            return null;
        }

    }





}
