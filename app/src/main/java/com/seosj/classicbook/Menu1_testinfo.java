package com.seosj.classicbook;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Menu1_testinfo extends AppCompatActivity {

    TextView txname;
    TextView tx1;
    TextView tx2;
    TextView tx3;
    TextView txAlarm;
    Button bt_cancel;
    Button bt_alarm;

    public String furl;
    public String url1 = "http://15.164.113.118:3000/?status=4&id=";

    public String curl;
    public String crul2 = "http://15.164.113.118:3000/?status=3&id=";
    /*
    //status = 4 (예약 확인)
    //url
    http://15.164.113.118:3000/?status=4&id=14011110
    //변수
    status : 4
    id : 학번
    //리턴값
    예약 정보가 있으면(예시)
    [{"id":"14011110","title":"총균쇠","hakgi":"2017-2학기","date":"2018-09-18","time":"04:00","day":"월"}]
    예약 정보가 없으면
    [{"id":"1","title":"","hakgi":"","date":"","time":"","day":""}]

    id가 1인지 아닌지 판별해야함
     */

    //테스트 예약 정보부분
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu1_testinfo);

        txname = findViewById(R.id.text_menu_test_bookname_tt);
        tx1 = findViewById(R.id.text_menu_test_1_detail);
        tx2 = findViewById(R.id.text_menu_test_2_detail);
        tx3 = findViewById(R.id.text_menu_test_3_detail);
        bt_cancel = findViewById(R.id.btn_cancel);

        //bt_alarm.findViewById

        JSONParser js = (JSONParser) this.getApplicationContext();

        furl = url1 + js.getStu_num();
        curl = crul2 + js.getStu_num();
        LoadInfo loadInfo = new LoadInfo(furl, null);
        loadInfo.execute();

        tx1.setText("2019년도 1학기");

        bt_cancel.setOnClickListener((View v) -> {
            //시험 정보 삭제
            //alertDialog띄우
            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            builder
                    .setMessage("예약을 취소하시겠습니까?")
                    .setPositiveButton("확인", (dialog, which) -> {

                        Cancelinfo cancelinfo = new Cancelinfo(curl, null);
                        cancelinfo.execute();

                    })
                    .setNegativeButton("취소", (dialog, which) -> {
                    });
            builder.create().show();

        });
    }

    public class LoadInfo extends AsyncTask<String, Void, String> {

        ProgressDialog asyncDialog = new ProgressDialog(Menu1_testinfo.this);

        private String url;
        private ContentValues values;

        private LoadInfo(String url, ContentValues values) {

            this.url = url;
            this.values = values;
        }

        @Override
        protected void onPreExecute() {
            asyncDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            asyncDialog.setMessage("잠시만 기다려 주세요...");

            //show dialog
            asyncDialog.show();

            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            //전송하기 위한 스트링 변수
            String turl = url;


            String result;

            RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();
            result = requestHttpURLConnection.request(turl, values);

            System.out.println(result);

            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            asyncDialog.dismiss();
            super.onPostExecute(result);

            //파싱
            JsonParser jsonParser = new JsonParser();
            JsonArray jsonArray = (JsonArray) jsonParser.parse(result);
            JsonObject object = (JsonObject) jsonArray.get(0);
            String tbname = object.get("title").getAsString();
            String tbdate = object.get("date").getAsString();
            String tbtime = object.get("time").getAsString();

            if (tbname.equals("1")) {

            } else {
                txname.setText(tbname);
                tx2.setText(tbdate);
                tx3.setText(tbtime + "부터 20분동안");
            }
        }

    }

    public class Cancelinfo extends AsyncTask<String, Void, String> {

        ProgressDialog asyncDialog = new ProgressDialog(Menu1_testinfo.this);

        private String url;
        private ContentValues values;

        private Cancelinfo(String url, ContentValues values) {

            this.url = url;
            this.values = values;
        }

        @Override
        protected void onPreExecute() {
            asyncDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            asyncDialog.setMessage("잠시만 기다려 주세요...");

            //show dialog
            asyncDialog.show();

            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            //전송하기 위한 스트링 변수
            String turl = url;

            String result;

            RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();
            result = requestHttpURLConnection.request(turl, values);

            System.out.println(result);

            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            asyncDialog.dismiss();
            super.onPostExecute(result);

            //기본 SharedPreference를 가져옴. (LoginActivity에서 설정한 pref)
            SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor ed = sharedPref.edit();
            ed.remove("info_date");
            ed.remove("info_name");
            ed.commit();
            Toast.makeText(getApplicationContext(), "예약을 취소하였습니다", Toast.LENGTH_SHORT).show();

            Intent inte = new Intent(getApplicationContext(), MainActivity.class);
            inte.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(inte);
            finish();

        }
    }
}