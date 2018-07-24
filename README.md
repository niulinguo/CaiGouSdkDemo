# 使用方法

### 安卓采购App

```
百度云
链接:https://pan.baidu.com/s/1iTh9Aqzxfs7ulpovmVLM8g
密码:4p90
```

### 使用隐式意图跳转打开主页面

> 提供五种方式进行页面跳转

1.
``` java
    final Intent intent = new Intent(Intent.ACTION_VIEW);
    intent.setData(Uri.parse("proucrement://user=admin,password=admin,server=192.168.1.1,port=8080"));
    return intent;
```

2.
``` java
    final Intent intent = new Intent(Intent.ACTION_VIEW);
    intent.setData(Uri.parse("proucrement://"));
    intent.putExtra("user", "admin");
    intent.putExtra("password", "admin");
    intent.putExtra("server", "192.168.1.1");
    intent.putExtra("port", "8080");
    return intent;
```

3.
``` java
    final Intent intent = new Intent();
    intent.setComponent(new ComponentName("com.zhu.procurement", "com.zhu.ec.mainmenu.MainActivity"));
    intent.putExtra("user", "admin");
    intent.putExtra("password", "admin");
    intent.putExtra("server", "192.168.1.1");
    intent.putExtra("port", "8080");
    return intent;
```

4.
``` java
    final Intent intent = new Intent();
    intent.setComponent(new ComponentName("com.zhu.procurement", "com.zhu.ec.mainmenu.MainActivity"));
    intent.putExtra("JSON_RESULT", "{" +
            "\"user\"=\"admin\"," +
            "\"password\"=\"admin\"," +
            "\"server\"=\"192.168.1.1\"," +
            "\"port\"=\"8080\"" +
            "}");
    return intent;
```

5.
``` java
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("user", "admin");
    jsonObject.put("login_info", loginInfo.toString());
    jsonObject.put("time", time);
    jsonObject.put("device_id", deviceId);
    jsonObject.put("server", "192.168.1.1");
    jsonObject.put("port", "8080");
    final Intent intent = new Intent();
    intent.setComponent(new ComponentName("com.zhu.procurement", "com.zhu.ec.mainmenu.MainActivity"));
    intent.putExtra("JSON_RESULT", jsonObject.toString());
    return intent;
```

请修改意图的四个参数：账号、密码、接口地址和端口号


### ReleaseNote

```
百度云
链接:https://pan.baidu.com/s/1zWCGM51X7pTJBfHhGtBLrw
密码:4b31
```

### 升级功能测试方法

1. 下载安卓0.9.28版本app
2. 将0.9.29版本app上传至蒲公英平台
3. 打开app进入首页，提示升级

```
百度云（0.9.28版本）
链接:https://pan.baidu.com/s/1iTh9Aqzxfs7ulpovmVLM8g
密码:4p90

百度云（0.9.29版本）
链接:https://pan.baidu.com/s/1fcQ-IRRJWuLmBXsbSKNEHA
密码:3uyj
```