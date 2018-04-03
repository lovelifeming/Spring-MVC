package com.zsm.springmvc.listener;

import com.zsm.springmvc.mdel.User;
import com.zsm.springmvc.service.RedisCacheUtil;
import com.zsm.springmvc.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;


/**
 * 监听器，用于项目启动的时候初始化数据到缓存
 *
 * @Author: zengsm.
 * @Description:
 * @Date:Created in 2018/4/3.
 * @Modified By:
 */
@Service
public class RedisCacheListener implements ApplicationListener<ContextRefreshedEvent>
{
    private final Logger logger = Logger.getLogger(RedisCacheListener.class);

    @Autowired
    private RedisCacheUtil<Object> redisCacheUtil;

    @Autowired
    private UserService userService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event)
    {
        if (event.getApplicationContext().getDisplayName().equals("Root WebApplicationContext"))
        {
            User user = userService.findUserByName("admin");
            redisCacheUtil.setCacheObject("admin", user);
        }
    }
}
