package com.jinwoo.android.sharedpreference;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

/**
 * Created by JINWOO on 2017-03-21.
 */

public class PropertyUtil {

    // 컨텍스트
    private static Context context;

    // 싱글턴
    private static PropertyUtil instance = null;

    // 내부저장소 절대경로 가져오기 ( /data/data/패키지명/files 경로가 이미 포함되어있다.)
    private String internalStoragePath;
    private String propertyFile = "test.properties";


    // 생성자가 호출될때 internalStorage를 세팅해주어야 됩니다.
    private PropertyUtil(){
        internalStoragePath = context.getFilesDir().getAbsolutePath();
    }

    public static PropertyUtil getInstance(Context ctx){
        context = ctx;
        if(instance== null){
            instance = new PropertyUtil();
        }
        return instance;
    }

    public void saveProperty(String key, String value){
        Properties prop = new Properties();
        prop.put(key, value);

        try {
            // 앱의 내부저장소/files/test.properties 파일을 저장
            FileOutputStream fos = new FileOutputStream(internalStoragePath + "/" + propertyFile);
            prop.store(fos, "comment"); // key = value

           // 저장 후 파일을 닫아준다.
            fos.close();
        } catch ( Exception e){
            e.printStackTrace();
        }
    }

    public String getProperty(String key){
        String value = "";

        Properties prop = new Properties();
        try {
            FileInputStream fis = new FileInputStream(internalStoragePath + "/" + propertyFile);
            prop.load(fis);
            fis.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        //prop.list(System.out); // 프로퍼티 목록 전체 나열하기
        value = prop.getProperty(key);

        return value;
    }
}
