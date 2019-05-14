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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.gun0912.tedpermission.PermissionListener;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    public String loginURL = "http://15.164.113.118:3000/?status=1&";
    public String loginID = "id=";
    public String loginPW = "&password=";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);



        EditText text_id = findViewById(R.id.textID);
        EditText text_pw = findViewById(R.id.textPW);
        Button button_login=findViewById(R.id.buttonLogin);
        ScrollView scrollView = findViewById(R.id.sv_root);
        CheckBox autoLogin = findViewById(R.id.autologin);

        //자동로그인 정보 저장
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
                //getSharedPreferences("pref",MODE_PRIVATE);

        //자동로그인일 경우 메인으로 바로 가야한다!
        //자동로그인->로그인창에서 로그인 버튼 안눌러도 자동 로그인 시도
        //

        Gson gson = new Gson();
        //Json
        //pref.getString("ID","");
       // pref.getString("PW","");

        String auID = pref.getString("ID","null");
        String auPW = pref.getString("PW","null");

        if(pref.getString("auto_login","0").equals("1")){
            autoLogin.setChecked(true);

            System.out.println("login info : " + auID + auPW);

            text_id.setText(auID);
            text_pw.setText(auPW);

            String url = loginURL + loginID + auID + loginPW + auPW;

            LoginTask loginTask = new LoginTask(url,null);
            loginTask.execute();

            //Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            //startActivity(intent);
            //finish();
        }


        /*권한 얻기
        TedPermission.with(this)
                .setPermissionListener(permissionlistener)
                .setRationaleMessage("권한 설정")
                .setDeniedMessage("거부하셨습니다.\n어플리케이션을 원활히 이용하기 위해서는\n" +
                        "[설정]->[어플리케이션]->[권한]에서 권한을 허용해 주시기 바랍니다.")
                .setPermissions()
        */





        //editText.getText.toString();을 하면 String객체로 Text를 리턴하게 된다.
        //또한 이 String이 공백인지 아닌지를 체크하기 위해서는 다음과 같이 처리를 해줘도 된다.


        //으로 공백체크도 해줄 수 있다.






        //다음화면 넘기기
        button_login.setOnClickListener((View v) -> {

            if ( text_id.getText().toString().length() == 0 ) {
                //공백일 때 처리할 내용
                //alertDialog띄우
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder
                        .setMessage("ID를 입력해 주세요")
                        .setPositiveButton("확인", (dialog, which)-> {} );
                builder.create().show();
            } else if(text_pw.getText().toString().length() == 0){
                //공백일 때 처리할 내용
                //alertDialog띄우
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder
                        .setMessage("PW를 입력해 주세요")
                        .setPositiveButton("확인", (dialog, which)-> {} );
                builder.create().show();
            }else {
                String uID;
                String uPW;
                uID = text_id.getText().toString();
                uPW = text_pw.getText().toString();
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("ID",uID);
                editor.putString("PW",uPW);
                editor.commit();

                //자동로그인 선택 상태 체크
                //후에 바로 넘어가게 설정할 것
                //저장만 했음. 로드는 추후에
                if(autoLogin.isChecked()){
                    //TODO
                    System.out.println("autoautoauto true");
                    editor.putString("auto_login","1");
                    editor.commit();
                }else{
                    //TODO
                    System.out.println("autoautoauto false");
                    editor.putString("auto_login","0");
                    editor.commit();
                }




                String url = loginURL + loginID + uID + loginPW + uPW;

                LoginTask loginTask = new LoginTask(url,null);
                loginTask.execute();


                //Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                //startActivity(intent);
                //finish();
            }

        });






    }
    //{"status":"0"}
    public class LoginTask extends AsyncTask<String, Void, String>{

        ProgressDialog asyncDialog = new ProgressDialog(LoginActivity.this);

        //로그인 실패 동작 아직 안했으
        private String url;
        private ContentValues values;

        private LoginTask(String url, ContentValues values) {

            this.url = url;
            this.values = values;
        }

        @Override
        protected void onPreExecute(){
            asyncDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            asyncDialog.setMessage("잠시만 기다려 주세요...");

            //show dialog
            asyncDialog.show();

            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            //전송하기 위한 스트링 변수
            String serversendletter;
            String turl = url;


            String result;

            RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();
            result = requestHttpURLConnection.request(turl, values);

            System.out.println(result);

            try{
                for(int i=0; i<2;i++){
                    Thread.sleep(1000);
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }

            return result;
        }

        @Override
        protected void onPostExecute(String result){
            asyncDialog.dismiss();
            super.onPostExecute(result);
            if(result.equals("{\"status\":\"0\"}")){
                Toast.makeText(LoginActivity.this,"ID와 PW를 확인해 주세요",Toast.LENGTH_SHORT).show();
            }else {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }



    }



    PermissionListener permissionlistener = new PermissionListener() {
        @Override
        public void onPermissionGranted() {
            Toast.makeText(LoginActivity.this, "권한 허가", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPermissionDenied(ArrayList<String> deniedPermissions) {
            Toast.makeText(LoginActivity.this, "권한 거부\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
        }


    };


}
