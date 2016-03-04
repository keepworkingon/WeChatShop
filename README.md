# WeChatShop
环境：IDEA 15

java 1.8、tomcat8、mysql数据库

操作指南：
在数据库中执行下列测试用例代码：

```
create database test;
use test;
show tables;
  CREATE TABLE `user` (  
  `id` int(11) NOT NULL AUTO_INCREMENT,  
  `user_name` varchar(40) NOT NULL,  
  `password` varchar(255) NOT NULL,  
  PRIMARY KEY (`id`)  
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;  


insert  into `user`(`id`,`user_name`,`password`) values (1,'吴渊','123456'); 

```

另外，配置文件（resources包里的jobc.properties）中的
```
jdbc.url=jdbc:mysql://localhost:8889/test 
jdbc.username=root
jdbc.password=root
```
改成自己的端口，还有mysql的用户名密码
然后右键项目，选择 `maven download source`，引入相应的依赖包。
可以先run以下test，看是否单元测试成功（终端输出数据库中的'name'字段的内容就是成功了），然后成功的话，后面再配tomcat，在tomcat中跑

未完待续...



