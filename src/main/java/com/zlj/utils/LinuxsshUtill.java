package com.zlj.utils;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.apache.commons.io.IOUtils;
import org.apache.kafka.clients.producer.KafkaProducer;

import java.io.IOException;
import java.io.InputStream;

public class LinuxsshUtill {

    //定义单例
    private static Session session;
    private static LinuxsshUtill linuxsshUtill;

    private LinuxsshUtill() {
    }


    public static synchronized LinuxsshUtill getInstance(String host, int port, String user, String password) throws JSchException {

        if (linuxsshUtill == null) {
            JSch jsch = new JSch();
            session = jsch.getSession(user, host, port);
            session.setConfig("StrictHostKeyChecking", "no");
            session.setPassword(password);
            session.connect();
            linuxsshUtill = new LinuxsshUtill();
        }
        return linuxsshUtill;
    }

    public String exeCommand(String command) throws JSchException, IOException {

        ChannelExec channelExec = (ChannelExec) session.openChannel("exec");
        InputStream in = channelExec.getInputStream();
        channelExec.setCommand(command);
        channelExec.setErrStream(System.err);
        channelExec.connect();
        String out = IOUtils.toString(in, "UTF-8");
        channelExec.disconnect();
        session.disconnect();
        return out;
    }

}
