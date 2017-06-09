package com.jinwoo.android.sharedpreference;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Switch;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class MainActivity extends AppCompatActivity {


    EditText editName;
    Switch switchShuffle;
    RelativeLayout layout2;

    PropertyUtil propertyUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        propertyUtil = PropertyUtil.getInstance(this);

        editName = (EditText) findViewById(R.id.editName);
        switchShuffle = (Switch) findViewById(R.id.switch1);
        layout2 = (RelativeLayout)findViewById(R.id.layout2);

        // firstOpen 체크가 되어 있으면 도움말 레이아웃을 닫아준다.
        if("false".equals(propertyUtil.getProperty("firstOpen"))){
            Log.i("PINGPONG","============================================GONE?");
            layout2.setVisibility(View.GONE);
        }
        Log.i("PINGPONG","============================================" + propertyUtil.getProperty("firstOpen"));
        // 세팅된 값을 가져와서 화면에 뿌린다.
        loadSetting();

    }

    public void closeHelp(View view){
        layout2.setVisibility(View.GONE);
        propertyUtil.saveProperty("firstOpen", "false");
    }

    public void saveSetting(View view){

        // 1. Preference 생성하기
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE); // getSharedPreferences 사용할 수 있다.

        // 2. SharedPreference에 값을 입력하기 위해서는 에디터를 통해서만 가능
        SharedPreferences.Editor editor = sharedPref.edit();
        // editor.putInt(키, 값);
        editor.putString("email", editName.getText().toString());
        editor.putBoolean("shuffle", switchShuffle.isChecked());

        // 3. 입력된 값을 반영
        editor.commit();

    }

    public void loadSetting(){
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        // 1. 프로퍼티 가져오기
        String email = sharedPref.getString("email", null);
        boolean shuffle = sharedPref.getBoolean("shuffle", false);
        // 2. 화면에 세팅.
        editName.setText(email);
        switchShuffle.setChecked(shuffle);

    }


}
