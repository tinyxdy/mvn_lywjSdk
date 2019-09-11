## 链游玩家SDK
## 介绍
链游玩家SDK提供登录注册，以及支付功能。
## 用法
使用 Gradle 下载
我们的aar包 目前托管在阿里云私人仓库，你需要将以下代码添加到工程的 `build.gradle` 中。
```
allprojects {
    repositories {
        maven {
            credentials {
                username 'kfg6xa'
                password 'cxGy1ckrB0'
            }
            url 'https://repo.rdc.aliyun.com/repository/104143-release-OqJNQ8/'
        }
    }
}
```
然后，在应用 build.gradle 中添加依赖。
```
implementation 'com.lywj.sdk.android:lywj-sdk:1.0.0'
 ```
 
## 混淆规则
混淆配置已经在链游玩家的aar包里生成了
## 代码使用
初始化SDK 在应用的 `Application` `onCreate` 方法中 初始化下方代码 `init` 方法第一参数是 `Application` 参数二是链游玩家平台分配的 `appid` 参数三是链游玩家平台分配的 `secret`，
`setDebug` 方法开启sdk debug 和 release模式
```
  LywjSdkManger.getInstance().init(this, "xxx", "xxx");
  LywjSdkManger.getInstance().setDebug(true);
  ```
##### 登录注册 
`this` 代表传当前`Activity` 的`Context`
```
 LywjSdkManger.getInstance().starLogin(this);
 LywjSdkManger.getInstance().starRegister(this);
 ```
##### 退出登录 
```
   LywjSdkManger.getInstance().loginOut();
```
##### 支付
支付需传商品价格 和 商品描述
```
 LywjSdkManger.getInstance().starPay(this, 商品价格, 商品描述);
 ```
##### 事件回调 
监听登录和登出事件的回调方法 登录成功会返回 `UserInfo` 失败返回错误原因
 ```
 LywjSdkManger.getInstance().registerLoginEvent(this, new LywjSdkManger.OnLoginListener() {
            @Override
            public void onLoginSuccess(UserInfo userInfo) {

            }

            @Override
            public void onLoginFailed(String message) {

            }

            @Override
            public void onLoginOut(String message) {

            }
        });
 ```
 监听支付结果的回调
 ```
 LywjSdkManger.getInstance().registerPayEvent(this, new LywjSdkManger.OnPayListener() {
            @Override
            public void onPaySuccess(OrderDetail orderDetail) {

            }

            @Override
            public void onPayFailed(String message) {

            }
        });
 ```       
## 冲突
如果有 `com.android.support` 冲突提示的话，可以使用以下方法 拷贝至 应用工程下的 `build.gradle` 文件中来统一使用相同版本 
```
configurations.all {
    resolutionStrategy.eachDependency { DependencyResolveDetails details ->
        def requested = details.requested
        if (requested.group == 'com.android.support') {
            if (!requested.name.startsWith("multidex")) {
                details.useVersion '28.0.0'
            }
        }
    }
```
