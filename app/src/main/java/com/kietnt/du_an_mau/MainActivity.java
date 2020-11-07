package com.kietnt.du_an_mau;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );
        setContentView(R.layout.activity_main);
        Thread bamgio= new Thread(){
          public void run(){
              try {
                  sleep(2000);
              } catch (Exception e) {

              }
              finally {
                  Intent newactivity = new Intent(MainActivity.this, LoginActivity.class);
                  startActivity(newactivity);
              }
          }
        };
        bamgio.start();
    }
    //Kết thúc màn hình chào

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}