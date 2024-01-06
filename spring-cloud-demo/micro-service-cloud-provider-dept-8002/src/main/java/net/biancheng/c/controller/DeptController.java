package net.biancheng.c.controller;

import lombok.extern.slf4j.Slf4j;
import net.biancheng.c.entity.Dept;
import net.biancheng.c.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 服务提供者的控制层
 * author:c语言中文网 c.biancheng.net
 */
@RestController
@Slf4j
public class DeptController {
    @Autowired
    private DeptService deptService;

    @Value("${server.port}")
    private String serverPort;

    @RequestMapping(value = "/dept/get/{id}", method = RequestMethod.GET)
    public Dept get(@PathVariable("id") int id) {
        return deptService.get(id);
    }

    // http://localhost:8002/dept/list
    @RequestMapping(value = "/dept/list", method = RequestMethod.GET)
    public List<Dept> list() {
        return deptService.selectAll();
    }

    //超时测试,该服务的响应时间为 5 秒  http://localhost:8002/dept/feign/timeout
    @RequestMapping(value = "/dept/feign/timeout")
    public String DeptFeignTimeout() {
        //暂停 5 秒
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }
}