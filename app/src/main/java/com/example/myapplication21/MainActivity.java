package com.example.myapplication21;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.net.ConnectivityManagerCompat;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private MyGuangBo wdgb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wdgb = new MyGuangBo();
        IntentFilter xuantai = new IntentFilter();
        xuantai.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(wdgb, xuantai);//注册（广播对象，选台）
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(wdgb);
    }

    class MyGuangBo extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager wlgl = (ConnectivityManager) getSystemService(context.CONNECTIVITY_SERVICE);
            NetworkInfo wlxx = wlgl.getActiveNetworkInfo();
            if (wlxx != null && wlxx.isConnected()) {
                Toast.makeText(context, "老八，有网了，可以看你制作秘制小汉堡了！", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "老八，没网了，不能直播看你做秘制小汉堡了！", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
