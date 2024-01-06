package net.biancheng.c.service;

import net.biancheng.c.entity.Dept;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

//添加为容器内的一个组件
@Component
// 服务提供者提供的服务名称，即 application.name  @FeignClient 注解实现对服务接口的绑定
@FeignClient(value = "MICROSERVICECLOUDPROVIDERDEPT")
public interface DeptFeignService {
    //对应服务提供者（8001、8002、8003）Controller 中定义的方法 直接复制过来，删除掉方法代码，保留方法接口，本服务通过调用模块内部接口，实现调用目标服务的接口
    @RequestMapping(value = "/dept/get/{id}", method = RequestMethod.GET)
    public Dept get(@PathVariable("id") int id);

    @RequestMapping(value = "/dept/list", method = RequestMethod.GET)
    public List<Dept> list();

    @RequestMapping(value = "/dept/feign/timeout")
    public String DeptFeignTimeout();

}