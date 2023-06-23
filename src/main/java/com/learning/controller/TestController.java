package com.learning.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learning.annotations.Log;
import com.learning.utils.lock.entity.Student;

@RestController
@RequestMapping("test")
public class TestController {

    @Log
//    @Scheduled(cron = "0/10 * * * * ?")
    @RequestMapping(value = "/01/{school}", method = RequestMethod.POST)
    public Object test01(Student student, @RequestParam int rank, @PathVariable String school) {
        System.out.println(1 / 0);
        System.out.println("hello world");
        return "结果";
    }


    @Log
//    @Scheduled(cron = "0/10 * * * * ?")
    @RequestMapping(method = RequestMethod.GET)
    public Object test02() {
        System.out.println("hello world");
        return "结果";
    }
}
