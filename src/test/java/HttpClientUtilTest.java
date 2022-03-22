import com.zlj.utils.HttpClientUtil;
import lombok.SneakyThrows;
import org.apache.commons.text.StringEscapeUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HttpClientUtilTest {
    @SneakyThrows
    @Test
    public void test1() {
        String body = StringEscapeUtils.unescapeJava("{\"email\":\"admin\",\"passwd\":\"Admin@123\",\"tenantId\":\"e10adc3949ba59abbe56e057f20f88dd\",\"return_insite\":\"https://10.1.40.81:7508//#/\",\"tenantStrategy\":\"2\"}");
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
        headers.put("Cache-Control", "no-cache");
        HashMap<String, String> querys = new HashMap<String, String>();
        HttpResponse content = HttpClientUtil.doPost("https://10.1.40.81:7508", "/tenant/api/v1/user/login", headers, querys, body);
        String result = EntityUtils.toString(content.getEntity(), "UTF-8");
        JSONObject jsonObject = new JSONObject(result);
        String token = jsonObject.getJSONObject("data").get("token").toString();
        System.out.println(token);
    }

    @SneakyThrows
    @Test
    public void test2() {
        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 参数
        String payload = "{\n    \"username\": \"admin\",\n    \"passwd\": \"Tm9zNTc1Jg==\"\n}";
        // 定义请求的参数
        URI uri = new URIBuilder()
                .setScheme("http")
                .setHost("10.1.56.159")
                .setPath("/omp/api/v1/user/login")
                .setPort(7500) // int类型端口
                .build();
        // 创建http请求
        HttpPost request = new HttpPost(uri);
        request.setHeader("Content-Type", "application/json");
        request.setHeader("Cache-Control", "no-cache");

        request.setEntity(new StringEntity(StringEscapeUtils.unescapeJava(payload)));
        //response 对象
        CloseableHttpResponse response = null;

        response = httpclient.execute(request);
        // 判断返回状态是否为200
        if (response.getStatusLine().getStatusCode() == 200) {
            String content = EntityUtils.toString(response.getEntity(), "UTF-8");
            String cookie = response.getLastHeader("Set-Cookie").getValue();

            Pattern pattern = Pattern.compile("omp_token=(.*?);");
            Matcher matcher = pattern.matcher(cookie);
            if (matcher.find()) {
                String str = matcher.group(1);
                System.out.println(str);
            }
        }
    }
}
