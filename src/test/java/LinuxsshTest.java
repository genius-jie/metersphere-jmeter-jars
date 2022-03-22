import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.apache.commons.io.IOUtils;
import java.io.IOException;
import java.io.InputStream;
import static com.zlj.utils.LinuxsshUtill.exeCommand;

public class LinuxsshTest {


    @SneakyThrows
    @Test
    public void test1() {
        String host = "10.1.56.161";
        int port = 22;
        String user = "root";
        String password = "Uyun_123";
        String command = "javapath=`ps -ef |grep java|head -1|awk '{print $8}'`&&ln -fs $javapath /usr/local/bin/java";
        String res = exeCommand(host, port, user, password, command);
        System.out.println(res);
    }
}