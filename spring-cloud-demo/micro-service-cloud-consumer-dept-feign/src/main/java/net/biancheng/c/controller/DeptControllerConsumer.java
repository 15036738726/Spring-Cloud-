package net.biancheng.c.controller;

import net.biancheng.c.entity.Dept;
import net.biancheng.c.service.DeptFeignService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class DeptControllerConsumer {

    // 通过接口调用 相比较于consumer80模块中的restTemplate调用更加的简洁
    @Resource
    private DeptFeignService deptFeignService;

    // http://localhost:8000/consumer/dept/get/1
    @RequestMapping(value = "/consumer/dept/get/{id}")
    public Dept get(@PathVariable("id") Integer id) {
        return deptFeignService.get(id);
    }

    // http://localhost:8000/consumer/dept/list
    @RequestMapping(value = "/consumer/dept/list")
    public List<Dept> list() {
        return deptFeignService.list();
    }

    // http://localhost:8000/consumer/dept/feign/timeout
    @RequestMapping(value = "/consumer/dept/feign/timeout")
    public String DeptFeignTimeout() {
        // openFeign-ribbon 客户端一般默认等待一秒钟，超过该时间就会报错
        return deptFeignService.DeptFeignTimeout();
    }
}