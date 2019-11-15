package com.example.sdktest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.lywj.android.LywjSdkManger;
import com.lywj.android.entity.OrderDetail;
import com.lywj.android.entity.UserInfo;
import com.lywj.android.net.listener.OnBindUserIdCallback;
import com.lywj.android.net.listener.OnLoginListener;
import com.lywj.android.net.listener.OnPayListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LywjSdkManger.getInstance().registerLoginEvent(this, new OnLoginListener() {
            @Override
            public void onLoginSuccess(UserInfo userInfo) {
                //TODO 登录成功
            }

            @Override
            public void onFirstLoginSuccess(UserInfo userInfo) {
                //TODO 初次登陆 需要绑定游戏放生成的userid 到链游玩家服务器
                LywjSdkManger.getInstance().bindUserId("xxx", new OnBindUserIdCallback() {
                    @Override
                    public void onBindSuccess() {
                        //TODO 绑定成功
                    }

                    @Override
                    public void onBindFailed(String s) {
                        //TODO 绑定失败
                    }
                });
            }

            @Override
            public void onLoginFailed(String message) {
                //TODO 登陆失败
            }

            @Override
            public void onLoginOut(String message) {
                //TODO  退出登录
            }
        });
        LywjSdkManger.getInstance().registerPayEvent(this, new OnPayListener() {
            @Override
            public void onPaySuccess(OrderDetail orderDetail) {
                Log.d("--->", "onPaySuccess");
            }

            @Override
            public void onPayFailed(String message) {
                Log.d("--->", "onPayFailed:" + message);
            }
        });
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.registerButton:
                LywjSdkManger.getInstance().starRegister(this);
                break;
            case R.id.loginButton:
                LywjSdkManger.getInstance().starLogin(this);
                break;
            case R.id.payButton:
                LywjSdkManger.getInstance().starPay(this, 0.01, "100元商品", "用户标识", "游戏放自己需要的额外参数");
                break;
            case R.id.loginOut:
                LywjSdkManger.getInstance().loginOut();
                break;
        }
    }
}
