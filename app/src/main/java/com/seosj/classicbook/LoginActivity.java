package com.seosj.classicbook;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);


        EditText text_id = findViewById(R.id.textID);
        EditText text_pw = findViewById(R.id.textPW);
        Button button_login=findViewById(R.id.buttonLogin);
        ScrollView scrollView = findViewById(R.id.sv_root);
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







    }
}
