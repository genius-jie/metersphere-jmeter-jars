package com.zlj.utils;


import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import com.zlj.utils.LinuxsshUtill.LinuxsshServer;

public class LinuxsshTest {


    @SneakyThrows
    @Test
    public void test1() {
        String host = "10.1.56.161";
        int port = 22;
        String user = "root";
        String password = "Uyun_123";
        String command = "javapath=`ps -ef |grep java|head -1|awk '{print $8}'`&&ln -fs $javapath /usr/local/bin/java";
        LinuxsshServer linuxsshServer = LinuxsshUtill.bulidServer(host, port, user, password);
        linuxsshServer.exeCommand(command);
    }
}