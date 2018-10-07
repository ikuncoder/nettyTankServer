
# 坦克大战netty网络版服务端

## 功能

1)底层用netty框架接收客户端传来的动作指令，进行逻辑处理<br>
2)处理完接收到在指令后，将坦克的状态发送给两个客户端，包括位置，方向，血量，分数值等<br>



# 坦克大战

## netty网络版

1)nettyTankClient是网络版的坦克大战的客户端，主要是界面的显示<br>
2)nettyTankServer是网络版的坦克大战的服务端，主要是做逻辑的判断<br>

主类：tank.TankWorld

## 主要实现的功能

+ 支持1v1网络对战
+ AI坦克
+ 随机地图生成
+一次运行进行3局游戏，一局游戏结束后等待10s,自动进入下一局
+服务端可以同时接受多个客户端连接，根据客户端的连接数进行两两配对，配对成功的用户进入同一个房间进行游戏

## tips<br>
1)nettyTankServer是网络版坦克大战的服务端,需要先启动，启动类是protobuf.SubReqServer<br>
2)nettyTankClient是网络版坦克大战的客户端,需要启动两次或者多次，启动类是protobuf.SubReqClient<br>


## 协议
### 客户端和服务端之间用的是google的protobuf协议通讯
### 协议格式为：
1)服务端——>客户端：sign+playerId+player.x+player.y+direction+live+health+score+strength+respawnCounter<br>
2)客户端——>服务端：groupNum+sign+playerId+order<br>

##引包说明
需要引入的包是netty-all-5.0.0.Alpha1.jar和protobuf-java-2.5.0.jar

## 其他
1)AI寻路用的是A*算法<br>
2)随机地图是根据服务端传给客户端的地图数字选择地图<br>

## 说明
这个版本是本人为了熟悉netty框架的使用而进行二次开发改写的。