package com.seosj.classicbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        //자동로그인일 경우 메인으로 바로 가야한다!

        /*권한 얻기
        TedPermission.with(this)
                .setPermissionListener(permissionlistener)
                .setRationaleMessage("권한 설정")
                .setDeniedMessage("거부하셨습니다.\n어플리케이션을 원활히 이용하기 위해서는\n" +
                        "[설정]->[어플리케이션]->[권한]에서 권한을 허용해 주시기 바랍니다.")
                .setPermissions()
        */


        EditText text_id = findViewById(R.id.textID);
        EditText text_pw = findViewById(R.id.textPW);
        Button button_login=findViewById(R.id.buttonLogin);
        ScrollView scrollView = findViewById(R.id.sv_root);
        CheckBox autoLogin = findViewById(R.id.autologin);
        /*
        editText.getText.toString();을 하면 String객체로 Text를 리턴하게 된다.
        또한 이 String이 공백인지 아닌지를 체크하기 위해서는 다음과 같이 처리를 해줘도 된다.
        if ( editText.getText.toString().length() == 0 ) {
            //공백일 때 처리할 내용

        } else {
            //공백이 아닐 때 처리할 내용
        }
        으로 공백체크도 해줄 수 있다.
        */


        //자동로그인 선택 상태 체크
        if(autoLogin.isChecked()){
            //TODO
        }else{
            //TODO
        }


        button_login.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        });






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
