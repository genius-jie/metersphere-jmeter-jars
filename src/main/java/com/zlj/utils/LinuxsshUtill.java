package com.zlj.utils;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

public class LinuxsshUtill {
    public static LinuxsshServer bulidServer(String host, int port, String user, String password) throws JSchException {
        return new LinuxsshUtill.LinuxsshServer(host, port, user, password);
    }

    public static class LinuxsshServer {
        Session session = null;

        private LinuxsshServer(String host, int port, String user, String password) throws JSchException {
            JSch jsch = new JSch();
            session = jsch.getSession(user, host, port);
            session.setConfig("StrictHostKeyChecking", "no");
            session.setPassword(password);
            session.connect();
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
}
