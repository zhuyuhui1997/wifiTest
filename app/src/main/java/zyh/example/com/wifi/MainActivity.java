package zyh.example.com.wifi;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
 public  Boolean isWifi;
    public TextView mTextView;
    public Button mButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView=(TextView)findViewById(R.id.text);
        mButton=(Button)findViewById(R.id.button);
      final  ConnectivityManager cm=(ConnectivityManager) MainActivity.this.getSystemService(Context.CONNECTIVITY_SERVICE);
        final WifiManager wifiManager=(WifiManager) MainActivity.this.getSystemService(Context.WIFI_SERVICE);
              wifidisplay(cm);
         mButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 String a="建议开启wifi";
                 String b="要不要关闭wifi";
                 if (mButton.getText().toString()==a)
                 wifiManager.setWifiEnabled(true);
                 if(mButton.getText().toString()==b)
                     wifiManager.setWifiEnabled(false);
                 SystemClock.sleep(4000);
                wifidisplay(cm);


             }
         });
        }
             public void    wifidisplay(ConnectivityManager cm)
    {
        NetworkInfo networkInfos =cm.getActiveNetworkInfo();
        if (networkInfos!=null) {
            if (networkInfos.getState() == NetworkInfo.State.CONNECTED) {
                if (networkInfos.getType() == cm.TYPE_MOBILE) {
                    isWifi = false;
                    mTextView.setText("现在是数据流量");
                    mButton.setText("开启wifi");

                }
                if (networkInfos.getType() == cm.TYPE_WIFI) {
                    isWifi = true;
                    mTextView.setText("现在是wifi");
                    mButton.setText("要不要关闭wifi");

                }
            }
        }
        else

        {
            mTextView.setText("没网啊,少年");
            mButton.setText("建议开启wifi");

        }
    }

}
