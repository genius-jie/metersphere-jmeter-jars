package com.zlj.utils;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

public class LinuxsshUtill {
    public static String exeCommand(String host, int port, String user, String password, String command) throws JSchException, IOException {
            JSch jsch = new JSch();
            Session session = jsch.getSession(user, host, port);
            session.setConfig("StrictHostKeyChecking", "no");
            //    java.util.Properties config = new java.util.Properties();
            //   config.put("StrictHostKeyChecking", "no");
            session.setPassword(password);
            session.connect();
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
