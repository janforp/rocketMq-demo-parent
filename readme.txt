Windows上安装mq
http://blog.csdn.net/u014134180/article/details/51790988

启动方式：
1.点击mqnamesrv.exe，启动name server，保持mqnamesrv.exe运行，不要关闭这个终端。
2.开启另一个windows终端cmd，进入解压的bin目录，输入mqbroker -n 127.0.0.1:9876启动broker，保持mqbroker.exe运行，不要关闭这个终端。