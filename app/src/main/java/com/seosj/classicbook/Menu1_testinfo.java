package com.seosj.classicbook;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Menu1_testinfo extends AppCompatActivity {

    TextView txname;
    TextView tx1;
    TextView tx2;
    TextView tx3;
    TextView txAlarm;
    Button bt_cancel;
    Button bt_alarm;

    public String adate;
    public String atime;
    public String[] ldate;
    public String[] ltime;
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
        txAlarm = findViewById(R.id.text_alarmlist);

        bt_cancel = findViewById(R.id.btn_cancel);

        bt_alarm = findViewById(R.id.btn_setalarm);

        JSONParser js = (JSONParser) this.getApplicationContext();

        furl = url1 + js.getStu_num();
        curl = crul2 + js.getStu_num();
        LoadInfo loadInfo = new LoadInfo(furl, null);
        loadInfo.execute();

        tx1.setText("2019년도 1학기");

        AlarmUtil au = new AlarmUtil();
        AlarmHATT ah = new AlarmHATT(getApplicationContext());
        bt_alarm.setOnClickListener((View v) -> {
            Toast.makeText(getApplicationContext(), "예약알림을 설정하였습니다.",
                    Toast.LENGTH_SHORT).show();

            ah.Alarm();
            Calendar calendar = Calendar.getInstance();
            au.setAlarm(this,calendar,0);
            txAlarm.setText(adate +" "+atime+" 1시간 전 알림 설정");
        });

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

        txAlarm.setOnClickListener((View v) -> {
            //시험 정보 삭제
            //alertDialog띄우
            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            builder
                    .setMessage("알림을 취소하시겠습니까?")
                    .setPositiveButton("확인", (dialog, which) -> {
                        txAlarm.setText("현재 설정된 알림이 없습니다.");
                        au.releaseAlarm(this,0);
                    })
                    .setNegativeButton("취소", (dialog, which) -> {
                    });
            builder.create().show();

        });
    }

    public class AlarmUtil {
        private static final long FIVE_TO_HOUR = 1000 * 60 * 1; // 1분

        // 알람 추가 메소드
        public void setAlarm(Context context, Calendar cal, int requestCode){
            AlarmManager fiveToHourAlarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);

            Intent fiveIntent = new Intent(context, BroadcastD.class);

            // FLAG_CANCEL_CURRENT : 이전에 생성한 PendingIntent 는 취소하고 새롭게 만든다
            PendingIntent fiveSender = PendingIntent.getBroadcast(context, requestCode, fiveIntent, PendingIntent.FLAG_CANCEL_CURRENT);

            long localTime = System.currentTimeMillis();
            long targetFiveTime = cal.getTimeInMillis();

            // AlarmManager.INTERVAL_DAY : 24시간
            if(localTime > targetFiveTime){
                targetFiveTime += AlarmManager.INTERVAL_DAY;
            }

            if((targetFiveTime - localTime) < FIVE_TO_HOUR){
                targetFiveTime += AlarmManager.INTERVAL_DAY;
            }

            fiveToHourAlarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, (targetFiveTime+FIVE_TO_HOUR), AlarmManager.INTERVAL_DAY, fiveSender);

            SimpleDateFormat format = new SimpleDateFormat("MM/dd kk:mm:ss");
            String setTargetFiveTime = format.format(new Date(targetFiveTime+FIVE_TO_HOUR));

            Log.d("NotiTEST", "Onemin : " + setTargetFiveTime);
        }

        //알람 해제 메소드
        public void releaseAlarm(Context context, int requestCode){
            AlarmManager fiveToHourAlarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);

            Intent fiveIntent = new Intent(context, BroadcastD.class);

            PendingIntent fiveSender = PendingIntent.getBroadcast(context, requestCode, fiveIntent, 0);

            fiveToHourAlarmManager.cancel(fiveSender);

            Log.d("NotiTEST", "AlarmUtil Canel");
        }
    }




    public class AlarmHATT {
        private Context context;

        public AlarmHATT(Context context) {
            this.context = context;
        }

        public void Alarm() {
            AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
            Intent intent = new Intent(Menu1_testinfo.this, BroadcastD.class);
            intent.setAction(BroadcastD.ACTION_RESTART_SERVICE);
            PendingIntent sender = PendingIntent.getBroadcast(
                    getApplicationContext(), 0, intent, 0);

            Calendar calendar = Calendar.getInstance();
            //알람시간 calendar에 set해주기
            ldate = adate.split("-");
            ltime = atime.split(":");
            int year=Integer.parseInt(ldate[0]);
            int month=Integer.parseInt(ldate[1]);
            int day=Integer.parseInt(ldate[2]);
            int ho = Integer.parseInt(ltime[0]);
            int min = Integer.parseInt(ltime[1]);

            calendar.set(year, month-1, day, ho, min, 0);

            AlarmManager.AlarmClockInfo ac = new AlarmManager.AlarmClockInfo(calendar.getTimeInMillis(),sender);
            am.setAlarmClock(ac, sender);
            am.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender);

        }

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

            adate = tbdate;
            atime = tbtime;

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