## 链游玩家SDK
## 介绍
链游玩家SDK提供登录注册、支付以及应用内悬浮框功能。
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
implementation 'com.lywj.sdk.android:lywj-sdk:1.0.6-beta'
 ```
 
## 混淆规则
混淆配置已经在链游玩家的aar包里生成了
## 代码使用
初始化SDK 在应用的 `Application` `onCreate` 方法中 初始化下方代码 `init` 方法第一参数是 `Application` 参数二是链游玩家平台分配的 `appid` 参数三是链游玩家平台分配的 `appkey`，
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
##### 初次注册或登录
注册后和登陆成功后返回的信息里user_id为空的情况下需要调用此方法来绑定游戏方跟链游玩家的账号，可以结合登录监听在 `onFirstLoginSuccess` 里添加以下代码
```
 LywjSdkManger.getInstance().bindUserId("游戏方用户ID", new OnBindUserIdCallback() {
            @Override
            public void onBindSuccess() {
                LogUtils.e("---onBindSuccess");
            }

            @Override
            public void onBindFailed(String message) {
                LogUtils.e("---onBindFailed", message);
            }
        });
```
##### 自动登录
如不需调用登陆接口直接登陆的情况下需调用此接口验证账号，游戏方根据返回的user_id获取该用户的游戏信息，返回信息中user_id为空的情况下需要调用绑定游戏方userid接口绑定双方关系
 ```
   LywjSdkManger.getInstance().autoLoginVerify(new OnAutoLoginVerifyCallback() {
            @Override
            public void onVerifySuccess(UserInfo userInfo) {

            }

            @Override
            public void onVerifyFailed(int code, String message) {
                //如code=202代表账号被封
            }
        });
 ```
##### 退出登录 
```
 LywjSdkManger.getInstance().loginOut();
```
##### 支付
支付需传商品价格、商品描述和用户唯一标识
```
 LywjSdkManger.getInstance().starPay(this, 商品价格, 商品描述,用户唯一标识,额外信息);
 ```
##### 事件回调 
监听注册事件的回调方法 注册成功会返回 `UserInfo` 失败返回错误原因
 ```
  LywjSdkManger.getInstance().registerRegisterEvent(this, new OnRegisterListener() {
            @Override
            public void onRegisterSuccess(UserInfo userInfo) {
                
            }

            @Override
            public void onRegisterFailed(String message) {

            }
        });
 ```
监听登录和登出事件的回调方法 登录成功会返回 `UserInfo` 失败返回错误原因
 ```
  LywjSdkManger.getInstance().registerLoginEvent(this, new OnLoginListener() {
                    @Override
                    public void onLoginSuccess(UserInfo userInfo) {

                    }

                    @Override
                    public void onFirstLoginSuccess(UserInfo userInfo) {
                        
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
 LywjSdkManger.getInstance().registerPayEvent(this, new OnPayListener() {
            @Override
            public void onPaySuccess(OrderDetail orderDetail) {

            }

            @Override
            public void onPayFailed(String message) {

            }
        });
 ```       
## 悬浮框功能
悬浮框提供链游玩家的钱包、礼包、代金券、消息等功能
### 悬浮框使用方法
在应用的 `Application` `onCreate` 方法中 初始化下方代码，`setFloatingClass` 可以传多个需要展示悬浮框的`class`，`class`必须继承为实现了 `Lifecycle` 功能的父类，如 `FragmentActivity` ，`AppCompatActivity`
```
 LywjSdkManger.getInstance().setFloatingClass(MainActivity.class);
```
可以通过以下方法来显示和隐藏悬浮框
```
 LywjSdkManger.getInstance().openFloatingWindow();
 LywjSdkManger.getInstance().closeFloatingWindow();
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
