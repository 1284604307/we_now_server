import com.alibaba.fastjson.PropertyNamingStrategy;
import com.ruoyi.common.utils.http.HttpUtils;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ming
 * @ClassName: get
 * @Description: (用一句话描述该文件做什么)
 * @email 17564500258@163.com
 * @Date 2020/4/25 10:57
 */
public class get {

    public static void main(String[] args) {

        Pattern p = Pattern.compile("#[\\s\\S]+?#");
        Matcher m = p.matcher("#test#aasd# asd 撒asd  阿斯顿#asdf#");
        List<String> stringList = new ArrayList<String>();
        while (m.find()) {
            System.out.println(m.group(0));
            stringList.add(m.group(0));
        }
        System.out.println(stringList.toString());
        ;
        System.out.println(String.join(",",stringList));
//        String s = "http://whois.pconline.com.cn/ip.jsp";
//        s  = HttpUtils.sendPost(s, "ip=" + "112.37.127.255");
//        System.out.println(s);
    }
    public static String getResultsStr(Class origin) throws UnsupportedEncodingException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("@Results({\n");
        for (Field field : origin.getDeclaredFields()) {
            String property = field.getName();
            //映射关系：对象属性(驼峰)->数据库字段(下划线)
//            String column = new PropertyNamingStrategy.SnakeCaseStrategy().translate(field.getName()).toUpperCase();
//            stringBuilder.append(String.format("@Result(property = \"%s\", column = \"%s\"),\n", property, column));
        }

        stringBuilder.append("})");
        return stringBuilder.toString();
    }
}
