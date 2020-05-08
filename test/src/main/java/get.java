import com.alibaba.fastjson.PropertyNamingStrategy;
import com.ruoyi.common.utils.http.HttpUtils;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;

/**
 * @author ming
 * @ClassName: get
 * @Description: (用一句话描述该文件做什么)
 * @email 17564500258@163.com
 * @Date 2020/4/25 10:57
 */
public class get {

    public static void main(String[] args) {

        String s = "http://whois.pconline.com.cn/ip.jsp";
        s  = HttpUtils.sendPost(s, "ip=" + "112.37.127.255");
        System.out.println(s);
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
