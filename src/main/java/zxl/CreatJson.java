package zxl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @className CreatJson
 * @author: zxl
 * @describe: 构建json
 * @date: 2022/2/10/23:12
 * @vision: 1.0
 */
public class CreatJson {
    public static String  creatJson(String new_name) throws IOException {
        //获取data.json的输入流
        ClassPathResource c = new ClassPathResource("data.json");
        InputStream inputStream = c.getInputStream();
        //将输入流转化成String
        StringBuffer stringBuffer = new StringBuffer();
        try {
            byte[] bytes = new byte[1024];
            int len;
            while ((len = inputStream.read(bytes)) != -1) {
//                System.out.println(new String(bytes,0,len));
                stringBuffer.append(new String(bytes, 0, len));
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            inputStream.close();
        }
//        System.out.println(stringBuffer);
        //获取jsonObject对象，置换新的daka_day
        Date date = new Date();
        SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = sm.format(date);
//        System.out.println(dateStr);
        JSONObject jb =JSONObject.parseObject(stringBuffer.toString(), Feature.OrderedField);
        jb.remove("daka_day");
        jb.put("daka_day",dateStr);
        jb.remove("new_name");
        jb.put("new_name",new_name);
        String newJson = jb.toString();
        return newJson;
//        System.out.println(newJson);
    }
}
