package com.jiang;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.code.kaptcha.Producer;
import com.jiang.mapper.CarouselPictureMapper;
import com.jiang.mapper.NavigationUrlMapper;
import com.jiang.mapper.SearchNavigationMapper;
import com.jiang.mapper.UserMapper;
import com.jiang.pojo.CarouselPicture;
import com.jiang.pojo.NavigationUrl;
import com.jiang.pojo.SearchNavigation;
import com.jiang.pojo.User;
import com.jiang.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@SpringBootTest
public class MybatisPlusApplicationTests {

    //继承了BaseMapper所有的方法，可以编写自己的扩展方法
    @Autowired
    private UserMapper userMapper;

    @Resource
    private UserService userService;

    @Test
    public void testUser(){
        System.out.println("--------selectAll method test-------");
        //查询全部用户，参数是一个Wrapper，条件构造器，先不使用为null
        List<User> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);
    }

    @Test
    public void saveUser(){
        System.out.println("--------saveUser method test-------");
        User user = new User();
        user.setName("111");
        user.setEmail("111");
        user.setPassword("111");
        userService.save(user);
    }

    @Autowired
    private CarouselPictureMapper pictureMapper;
    @Test
    public void testPic(){
        System.out.println("--------selectAll method test-------");
        QueryWrapper<CarouselPicture> wrapper = new QueryWrapper();
        wrapper.last("limit 2");
        List<CarouselPicture> picList = pictureMapper.selectList(wrapper);
        picList.forEach(System.out::println);
    }

    @Autowired
    private NavigationUrlMapper navigationUrlMapper;
    @Test
    public void testNavigationUrl(){
        System.out.println("--------selectAll method test-------");

        List<NavigationUrl> navigationUrlList = navigationUrlMapper.selectList(null);
        Map<String, List<NavigationUrl>> listMap = navigationUrlList.stream().collect(Collectors.groupingBy(NavigationUrl::getTag));
        List<Map<String, Object>> urlList = new ArrayList<>();
        for (Map.Entry<String, List<NavigationUrl>> entry : listMap.entrySet()) {
            Map<String, Object> map = new HashMap<>();
            map.put("tag", entry.getKey());
            map.put("list", entry.getValue());
            urlList.add(map);
        }

        for (Map<String, Object> map : urlList) {
            System.out.println(map.get("tag"));
            System.out.println(map.get("list"));
        }
    }

    @Autowired
    private SearchNavigationMapper searchNavigationMapper;
    @Test
    public void testSearch(){
        System.out.println("--------selectAll method test-------");
        QueryWrapper<SearchNavigation> wrapper = new QueryWrapper();
        wrapper.last("limit 2");
        List<SearchNavigation> picList = searchNavigationMapper.selectList(wrapper);
        picList.forEach(System.out::println);
    }

    @Autowired
    StringRedisTemplate stringRedisTemplate;  //操作k-v都是字符串的

    @Autowired
    private RedisTemplate redisTemplate;  //k-v都是对象的

    /**
     * Redis常见的五大数据类型
     * String（字符串）、List（列表）、Set（集合）、Hash（散列）、ZSet（有序集合）
     * stringRedisTemplate.opsForValue()[String（字符串）]
     * stringRedisTemplate.opsForList()[List（列表）]
     * stringRedisTemplate.opsForSet()[Set（集合）]
     * stringRedisTemplate.opsForHash()[Hash（散列）]
     * stringRedisTemplate.opsForZSet()[ZSet（有序集合）]
     */
    @Test
    public void test01() {
        String bing = "https://cn.bing.com";
        String body = "{\"images\": [{\"url\": \"/th?id=OHR.ReefAwareness_ZH-CN8840949729_1920x1080.jpg&rf=LaDigue_1920x1080.jpg&pid=hp\"}]}";
        JSONObject jsonObj = JSON.parseObject(body);
        List<Object> images = (List<Object>) jsonObj.get("images");
        if (!images.isEmpty()) {
            JSONObject img = JSON.parseObject(images.get(0).toString());
            Object url = img.get("url");
            System.out.println(bing+url);
        }

    }

    @Test
    public void test02() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");//设置格式
        Calendar calendar = Calendar.getInstance(); //创建Calendar 的实例
        calendar.add(Calendar.DATE, -1); //当前时间减去一天，即一天前的时间
        format.format(calendar.getTime());
    }

    @Autowired
    private Producer checkCode;

    @Test
    public void testCode() {
        Object members = redisTemplate.opsForValue().get("code:776289764@qq.com");
        if (Objects.isNull(members)) {
            System.out.println("验证码已失效!");
        }else {
            System.out.println(members);
        }
    }



}
