package com.zyz.demo1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhangyz
 * @version 1.0
 * @since 2024/4/28 14:51:04
 */
@RestController
public class OrderController {

    private List<OrderVo> orderVos = new ArrayList<>();
    private List<User> users = new ArrayList<>();

    //初始化时就创建好数据。模拟数据库已经存在的数据
    @PostConstruct
    public void createData() {
        long dataCount = 500;

        //创建订单数据。模拟已经插入到数据库的订单
        for (long i = 0; i < dataCount; i++) {
            OrderVo ordervo = new OrderVo();
            ordervo.setId(i + 1);
            ordervo.setUserId(i + 1);
            //防止电脑太快，导致都是同一个时间，所以加一个数
            ordervo.setCreateTime(LocalDateTime.now().plusSeconds(i));
            orderVos.add(ordervo);
        }

        //创建用户数据。模拟已经插入到数据库的用户
        for (long i = 0; i < dataCount; i++) {
            User user = new User();
            user.setId(i + 1);
            user.setUserName("用户名" + (i + 1));
            users.add(user);
        }
        orderVos = orderVos.stream()
                .sorted(Comparator.comparing(OrderVo::getCreateTime).reversed())
                .collect(Collectors.toList());
    }

    @GetMapping("/getorderDetails")
    public List<OrderVo> getorderDetails() {
        long startTime = System.currentTimeMillis();

        List<OrderVo> orderVoList;
        //这里是不同的执行方式(单线程/线程池)



        long endTime = System.currentTimeMillis();
        System.out.println("执行时间:" + (endTime - startTime) + " ms");
        return orderVoList;
    }


}