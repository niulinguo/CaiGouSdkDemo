# 使用方法

1. 安卓采购App

```
百度云
链接:https://pan.baidu.com/s/1EZhF07_MYzD6uXj_PqmP0w
密码:6tqj
```

2. 使用隐式意图跳转打开主页面

``` java
        final Intent intent = new Intent(Intent.ACTION_VIEW);
        final Uri data = Uri.parse("proucrement://user=admin,password=admin,server=192.168.1.1,port=8080");
        intent.setData(data);
        try {
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "没有安装采购App", Toast.LENGTH_SHORT).show();
        }
```

请修改意图的四个参数：账号、密码、接口地址和端口号