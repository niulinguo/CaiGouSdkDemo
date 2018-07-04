# 使用方法

### 安卓采购App

```
百度云
链接:https://pan.baidu.com/s/1EZhF07_MYzD6uXj_PqmP0w
密码:6tqj
```

### 使用隐式意图跳转打开主页面

> 提供三种方式进行页面跳转

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

请修改意图的四个参数：账号、密码、接口地址和端口号