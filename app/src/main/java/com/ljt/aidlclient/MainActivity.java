package com.ljt.aidlclient;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ljt.aidlserver.IPerson;

public class MainActivity extends AppCompatActivity {

    private IPerson iPerson;
    Button btn;

    private ServiceConnection conn=new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
            // TODO Auto-generated method stub
            iPerson=null;
        }

        @Override
        synchronized public void onServiceConnected(ComponentName name, IBinder service) {
            // TODO Auto-generated method stub
            iPerson=IPerson.Stub.asInterface(service);
            System.out.println("iperson---------"+iPerson);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn=(Button) findViewById(R.id.btn_main);
        Intent intent = new Intent("com.ljt.aidlserver.MY_SERVICE");
        bindService(intent, conn, Service.BIND_AUTO_CREATE);

        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                try{
                    iPerson.setAge(20);
                    iPerson.setName("码上飞");
                    String msg = iPerson.display();
                    // 显示方法调用返回值
                    Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        unbindService(conn);
        super.onDestroy();
    }
}
