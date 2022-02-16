import zxl.Clock;

import java.io.IOException;

/**
 * @className Start
 * @author: zxl
 * @describe: test
 * @date: 2022/2/15/21:52
 * @vision: 1.0
 */
public class Start {
    public static void main(String[] args) throws IOException {
        Clock clock = new Clock();
        String authorization = "抓包获取授权码";
        String receiveNickname = "你好，主人！！";
        String receiveMail = "第一个邮箱";
        String new_name = "抓包获取图片名称";
        clock.clock(authorization, receiveMail,receiveNickname,new_name);
        String authorization1 = "抓包获取授权码";
        String receiveNickname1 = "xxx";
        String receiveMail1 = "第二个邮箱";
        String new_name1 = "抓包获取图片名称";
        clock.clock(authorization1, receiveMail1,receiveNickname1,new_name1);

    }
}
