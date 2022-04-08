package com.muxi.java.example.hutool;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.lang.Console;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.muxi.java.example.domain.User;
import com.muxi.java.example.web.SysRoleController;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;


/**
 * Hutool是一个小而全的Java工具类库，
 * 它帮助我们简化每一行代码，避免重复造轮子。
 */
public class Hutool {

    private static final Log log = LogFactory.get();

    public static void main(String[] args) throws IOException {

        /** Convert */
        // 转换为字符串
        int a = 1;
        String aStr = Convert.toStr(a);
        // 转换为指定类型数组
        String[] b = {"1", "2", "3", "4"};
        Integer[] bArr = Convert.toIntArray(b);
        // 转换为日期对象
        String dateStr = "2017-05-06";
        Date date = Convert.toDate(dateStr);
        // 转换为列表
        String[] strArr = {"a", "b", "c", "d"};
        List<String> strList = Convert.toList(String.class, strArr);

        /** DateUtil */
        // Date、long、Calendar之间的相互转换
        // 当前时间
        Date date2 = DateUtil.date();
        // Calendar转Date
        date2 = DateUtil.date(Calendar.getInstance());
        // 时间戳转Date
        date2 = DateUtil.date(System.currentTimeMillis());
        // 自动识别格式转换
        String dateStr2 = "2017-03-01";
        date2 = DateUtil.parse(dateStr2);
        // 自定义格式化转换
        date2 = DateUtil.parse(dateStr2, "yyyy-MM-dd");
        // 格式化输出日期
        String format = DateUtil.format(date2, "yyyy-MM-dd");
        // 获得年的部分
        int year = DateUtil.year(date2);
        // 获得月份，从0开始计数
        int month = DateUtil.month(date2);
        // 获取某天的开始、结束时间
        Date beginOfDay = DateUtil.beginOfDay(date2);
        Date endOfDay = DateUtil.endOfDay(date2);
        // 计算偏移后的日期时间
        Date newDate = DateUtil.offset(date2, DateField.DAY_OF_MONTH, 2);
        // 计算日期时间之间的偏移量
        long betweenDay = DateUtil.between(date2, newDate, DateUnit.DAY);

        /** JSONUtil */
        User user = new User();
        user.setAge(10);
        user.setName("小米");
        user.setSex("male");
        // 对象转化为JSON字符串
        String jsonStr = JSONUtil.parse(user).toString();
        log.info("jsonUtil parse:{}", jsonStr);
        // JSON字符串转化为对象
        User u = JSONUtil.toBean(jsonStr, User.class);
        log.info("jsonUtil toBean:{}", u);
        List<User> userList = new ArrayList<>();
        userList.add(user);
        String jsonListStr = JSONUtil.parse(userList).toString();
        // JSON字符串转化为列表
        userList = JSONUtil.toList(new JSONArray(jsonListStr), User.class);
        log.info("jsonUtil toList:{}", userList);

        /** StrUtil */
        // 判断是否为空字符串
        String str = "test";
        StrUtil.isEmpty(str);
        StrUtil.isNotEmpty(str);
        // 去除字符串的前后缀
        StrUtil.removeSuffix("a.jpg", ".jpg");
        StrUtil.removePrefix("a.jpg", "a.");
        // 格式化字符串
        String template = "这只是个占位符:{}";
        String str2 = StrUtil.format(template, "我是占位符");
        log.info("/strUtil format:{}", str2);

        /** ClassPathResource */
        // ClassPath单一资源访问类，可以获取classPath下的文件，在Tomcat等容器下，classPath一般是WEB-INF/classes。
        // 获取定义在src/main/resources文件夹中的配置文件
        ClassPathResource resource = new ClassPathResource("application.yml");
        Properties properties = new Properties();
        properties.load(resource.getStream());
        log.info("/classPath:{}", properties);

        /** ReflectUtil */
        // Java反射工具类，可用于反射获取类的方法及创建对象。
        // 获取某个类的所有方法
        Method[] methods = ReflectUtil.getMethods(User.class);
        log.info("class methods: {}", methods);
        // 获取某个类的指定方法
        Method method = ReflectUtil.getMethod(User.class, "getName");
        log.info("class method name: {}", method);
        // 使用反射来创建对象
        User user1 = ReflectUtil.newInstance(User.class);
        // 反射执行对象的方法
        ReflectUtil.invoke(user1, "setName", "jack");
        log.info("Reflect: {}", user1.getName());

        /** BeanUtil */
        // JavaBean工具类，可用于Map与JavaBean对象的互相转换以及对象属性的拷贝。
        User u2 = new User();
        u2.setAge(20);
        u2.setName("Juliet");
        u2.setSex("female");
        // Bean转Map
        Map<String, Object> map = BeanUtil.beanToMap(u2);
        log.info("beanUtil bean to map:{}", map);
        // Map转Bean
        User u2Map = BeanUtil.mapToBean(map, User.class, false);
        log.info("beanUtil map to bean:{}", u2Map);
        // Bean属性拷贝
        User copyUser = new User();
        BeanUtil.copyProperties(u2, copyUser);
        log.info("beanUtil copy properties:{}", copyUser.getName());

        /** CollUtil */
        // 集合操作的工具类，定义了一些常用的集合操作。
        // 数组转换为列表
        String[] array = new String[]{"a", "b", "c", "d", "e"};
        List<String> list = CollUtil.newArrayList(array);
        // join：数组转字符串时添加连接符号
        String joinStr = CollUtil.join(list, ",");
        log.info("collUtil join:{}", joinStr);
        // 将以连接符号分隔的字符串再转换为列表
        List<String> splitList = StrUtil.split(joinStr, ',');
        log.info("collUtil split:{}", splitList);
        // 创建新的Map、Set、List
        HashMap<Object, Object> newMap = MapUtil.newHashMap();
        HashSet<Object> newHashSet = CollUtil.newHashSet();
        ArrayList<Object> newList = CollUtil.newArrayList();
        // 判断列表是否为空
        CollUtil.isEmpty(list);

        /** AnnotationUtil */
        // 注解工具类，可用于获取注解与注解中指定的值。
        //获取指定类、方法、字段、构造器上的注解列表
        Annotation[] annotationList = AnnotationUtil.getAnnotations(SysRoleController.class, false);
        log.info("annotationUtil annotations:{}", annotationList);
        // 获取指定类型注解
        Api api = AnnotationUtil.getAnnotation(SysRoleController.class, Api.class);
        log.info("annotationUtil api value:{}", ObjectUtil.isNull(api) ? "xxx" : api.description());
        // 获取指定类型注解的值
        Object annotationValue = AnnotationUtil.getAnnotationValue(SysRoleController.class, RequestMapping.class);

        /** SecureUtil */
        // 加密解密工具类，可用于MD5加密。
        // MD5加密
        String strPwd = "123456";
        String md5Str = SecureUtil.md5(strPwd);
        log.info("secureUtil md5:{}", md5Str);

        /** CaptchaUtil */
        // 图形验证码工具类，可用于生成图形验证码。
        // 生成验证码图片
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100);
        // 图形验证码写出，可以写出到文件，也可以写出到流
        lineCaptcha.write("D:/line.png");
        // 输出code
        Console.log(lineCaptcha.getCode());
        // 验证图形验证码的有效性，返回boolean值
        lineCaptcha.verify("1234");
        // 重新生成验证码
        lineCaptcha.createCode();
        lineCaptcha.write("D:/line.png");
        // 新的验证码
        Console.log(lineCaptcha.getCode());
        // 验证图形验证码的有效性，返回boolean值
        lineCaptcha.verify("1234");

        /** Validator */
        // 字段验证器，可以对不同格式的字符串进行验证，比如邮箱、手机号、IP等格式。
        //判断是否为邮箱地址
        boolean result = Validator.isEmail("macro@qq.com");
        log.info("Validator isEmail:{}", result);
        // 判断是否为手机号码
        result = Validator.isMobile("18911111111");
        log.info("Validator isMobile:{}", result);
        // 判断是否为IPV4地址
        result = Validator.isIpv4("192.168.3.101");
        log.info("Validator isIpv4:{}", result);
        // 判断是否为汉字
        result = Validator.isChinese("你好");
        log.info("Validator isChinese:{}", result);
        // 判断是否为身份证号码（18位中国）
        result = Validator.isCitizenId("123456");
        log.info("Validator isCitizenId:{}", result);
        // 判断是否为URL
        result = Validator.isUrl("http://www.baidu.com");
        log.info("Validator isUrl:{}", result);
        // 判断是否为生日
        result = Validator.isBirthday("2020-02-01");
        log.info("Validator isBirthday:{}", result);

        /** DigestUtil */
        // 摘要算法工具类，支持MD5、SHA-256、Bcrypt等算法。

        String password = "123456";
        // 计算MD5摘要值，并转为16进制字符串
        String result2 = DigestUtil.md5Hex(password);
        log.info("DigestUtil md5Hex:{}", result2);
        // 计算SHA-256摘要值，并转为16进制字符串
        result2 = DigestUtil.sha256Hex(password);
        log.info("DigestUtil sha256Hex:{}", result2);
        // 生成Bcrypt加密后的密文，并校验
        String hashPwd = DigestUtil.bcrypt(password);
        boolean check = DigestUtil.bcryptCheck(password,hashPwd);
        log.info("DigestUtil bcryptCheck:{}", check);

        /** HttpUtil */
        // Http请求工具类，可以发起GET/POST等请求。
        String response = HttpUtil.get("http://localhost:8089/dev-api/epc/epc/list");
        log.info("HttpUtil get:{}", response);

    }
}
