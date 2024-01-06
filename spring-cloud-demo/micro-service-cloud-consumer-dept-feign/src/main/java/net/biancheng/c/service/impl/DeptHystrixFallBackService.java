package net.biancheng.c.service;

import org.springframework.stereotype.Component;

/**
 * Hystrix 服务降级
 * 解耦回退逻辑
 */
@Component
public class DeptHystrixFallBackService implements DeptHystrixService {
    /**
     * 不管是业务方法指定的降级方法还是全局降级方法，它们都必须和业务方法在同一个类中才能生效，业务逻辑与降级逻辑耦合度极高
     * 这里直接对绑定的接口进行降级回调  服务绑定接口 DeptHystrixService 标注的 @FeignClient 注解中添加 fallback 属性 指定该类即可
     */
    @Override
    public String deptInfo_Ok(Integer id) {
        return "--------------------C语言中文网提醒您，系统繁忙，请稍后重试！（解耦回退方法触发）-----------------------";
    }

    @Override
    public String deptInfo_Timeout(Integer id) {
        return "--------------------C语言中文网提醒您，系统繁忙，请稍后重试！（解耦回退方法触发）-----------------------";
    }

    @Override
    public String deptCircuitBreaker(Integer id) {
        return "--------------------C语言中文网提醒您，系统繁忙，请稍后重试！（解耦回退方法触发）-----------------------";
    }
}