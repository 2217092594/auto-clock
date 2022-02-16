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
        String authorization = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJvcGVuaWQiOiJvV1JrVTBSbEtoRUdvNzhVbnZOUDA0YllsR3EwIiwidW5pb25pZCI6Im90Z3pwdndBTlJ1N1VKNkVxNzJsNm5vUEROVGciLCJwbGF0Zm9ybSI6ImFwcCIsImlhdCI6MTY0NDExNDkyMH0.yUlB1Wyye60AavnX2vMt6Nd4AVVXU2Ks_omdr8ECvAY";
        String receiveNickname = "你好，主人！！";
        String receiveMail = "2217092594@qq.com";
        String new_name = "app_image_613081c6d510782a4a05ae04/52e5a923b750d8a89baf3650fe457f29_1644652871362.jpg";
        clock.clock(authorization, receiveMail,receiveNickname,new_name);
        String authorization1 = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJvcGVuaWQiOiJvV1JrVTBRa3B6YzlwQTJ0MExMaUxpZjJiRmp3IiwidW5pb25pZCI6Im90Z3pwdl96WmtIQjdPM1gwTW1zTVlOeFZ0bVEiLCJwbGF0Zm9ybSI6ImFwcCIsImlhdCI6MTY0NDkzNjE4MX0.-bV1uzksJgS15Xer8Z4abMYRfHEhDZs4eSH-8M9ZFw8";
        String receiveNickname1 = "范彦博先生";
        String receiveMail1 = "741313672@qq.com";
        String new_name1 = "11oWRkU0Qkpzc9pA2t0LLiLif2bFjw_img/YnL6_cos@186.jpg";
        clock.clock(authorization1, receiveMail1,receiveNickname1,new_name1);

    }
}
