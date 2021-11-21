package com.software.crm.task;

import com.software.crm.service.CustomerService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author :wyanjia
 * @parm : 定时任务
 * @date : 2021/10/18 10:57
 * @return :
 */
@Component
public class JobTask {
    @Resource
    private CustomerService customerService;

    /**
     * @return :
     * @author :wyanjia
     * @parm : 每两秒执行一次
     * @date : 2021/10/18 11:01
     */
  // @Scheduled(cron = "0/2 * * * * ?")
    public void job() {
        System.out.println("定时任务开始执行--->" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        //调用需要被定时执行的方法
        customerService.updateCustomerState();
    }
}
