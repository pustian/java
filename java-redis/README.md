安装：官网下载安装
    https://github.com/dmajkic/redis/downloads


启动/关闭
在命令行窗口中输入 redis-server redis.windows.conf 启动redis
关闭命令行窗口就是关闭 redis。
---

redis作为windows服务启动方式
--- redis-server --service-install redis.windows.conf
启动服务：redis-server --service-start
停止服务：redis-server --service-stop


启动成功检查：
    redis-cli.exe -h 127.0.0.1 -p 6379
    set myKey abc
    结果：OK
    get myKey
    结果："abc"