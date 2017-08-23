package com.lxj.maildemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements OnClickListener{

    private Button btn_send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_send = (Button)findViewById(R.id.btn_send);
        btn_send.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_send:
                //发送的操作必须要在非主线程中才能必须
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            //当你从github上下载下来代码后，你需要在这里设置自己的邮箱和密码，这样才能发送信息
                            GMailSender sender = new GMailSender("sender@163.com", "password");
                            //设置你的邮箱和接收者的邮箱，我这里填写的是个例子
                            sender.sendMail("This is Subject",
                                    "This is Body",
                                    "sender@163.com",
                                    "reciver@qq.com");
                        } catch (Exception e) {
                            Log.e("SendMail", e.getMessage(), e);
                        }
                    }
                }).start();

                break;
        }
    }


}
