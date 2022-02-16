package zxl;

import org.apache.http.*;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.HttpClients;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * @className Clock
 * @author: zxl
 * @describe: 打卡
 * @date: 2022/2/11/13:03
 * @vision: 1.0
 */
public class Clock {

    public  void clock(String authorization,String receiveMail,String receiveNicknam,String new_name) throws IOException {
        CloseableHttpClient client = null;
        HttpPost httpPost = null;
        HttpResponse httpResponse = null;
        MailSender mailSender = new MailSender();
        try {
            client = DefaultSSLClient.createSSLClient();
            client = HttpClientBuilder.create().build();
            httpPost = new HttpPost("https://service.banjixiaoguanjia.com/app/notify/feedbackWithOss");
            StringEntity stringEntity = new StringEntity(CreatJson.creatJson(new_name), Charset.forName("utf-8"));
            httpPost.setEntity(stringEntity);
            httpPost.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");
            httpPost.setHeader(HttpHeaders.ACCEPT, "application/json");
            httpPost.setHeader(HttpHeaders.AUTHORIZATION, authorization);
            httpPost.setHeader("app-info", "1/0.8.3/156");
            httpPost.setHeader(HttpHeaders.USER_AGENT, "Dart/2.10 (dart:io)");
            httpPost.setHeader(HttpHeaders.ACCEPT_ENCODING, "gzip");
            System.out.println("Executing request" + httpPost.getRequestLine());
            httpResponse = client.execute(httpPost);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {

                System.out.println("success");
                System.out.println("--------------httpResponse.getStatusLine()-----------");
                System.out.println(httpResponse.getStatusLine());
                System.out.println("-------------httpPost.getEntity()------------------");
                System.out.println(httpPost.toString());
                System.out.println(httpPost.getMethod());
                Header[] allHeaders = httpPost.getAllHeaders();
                for (Header allHeader : allHeaders) {
                    System.out.println(allHeader);
                }
                System.out.println("-----------CreatJson.creatJson()----------");
                System.out.println(CreatJson.creatJson(new_name));
                System.out.println("-----------httpResponse.getEntity()--------------");
                System.out.println(httpResponse.getEntity());
//                System.out.println("------------httpResponse.getEntity().getContent()------------");
//                System.out.println(httpResponse.getEntity().getContent().read(new byte[1024]));
                System.out.println("---------httpResponse.getEntity().getContentType()----");
                System.out.println(httpResponse.getEntity().getContentType());
                System.out.println("------------EntityUtils.toString(httpResponse.getEntity())-----");
                System.out.println(EntityUtils.toString(httpResponse.getEntity()));
                mailSender.sendScMail(receiveMail,receiveNicknam);
            } else {

                System.out.println("---------false---------");
                System.out.println("----httpResponse.toString()---");
                System.out.println(httpResponse.toString());
                System.out.println("-------httpResponse.getEntity()----------");
                HttpEntity entity = httpResponse.getEntity();
                System.out.println(entity);
                System.out.println("----------EntityUtils.toString(httpResponse.getEntity())-----------");
                System.out.println(EntityUtils.toString(httpResponse.getEntity()));
                mailSender.sendFaMail(receiveMail,receiveNicknam);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (client != null) {
                try {
                    client.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
