package com.learning.controller;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learning.annotations.Authorization;
import com.learning.annotations.Log;
import com.learning.dao.db2.DepartmentDao;
import com.learning.entity.Department;
import com.learning.utils.TreeUtils;
import com.learning.utils.lock.entity.Student;

@RestController
@RequestMapping("test")
public class TestController {

    @Resource
    DepartmentDao departmentDao;

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
//    @RequestMapping(method = RequestMethod.GET)
    public Object test02() {
        System.out.println("hello world");
        return "结果";
    }

    @Log
    @Authorization(allowUserArray = {"gwx11165771"}, allowUserListHandler = "w3", debug = true)
    @RequestMapping(value = "/03", method = RequestMethod.GET)
    public Object test03() {
        List<Department> departments = departmentDao.queryAll();
        List<Department> trees = TreeUtils.generateTrees(departments);
        System.out.println(trees);
        return trees;
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(4); // 创建线程池

        // 创建Callable任务列表
        Callable<String> task1 = () -> {
            // 执行任务1
            Thread.sleep(2000);
            return "Task 1";
        };

        Callable<String> task2 = () -> {
            // 执行任务2
            Thread.sleep(3000);
            return "Task 2";
        };

        Callable<String> task3 = () -> {
            // 执行任务3
            Thread.sleep(4000);
            return "Task 3";
        };

        Callable<String> task4 = () -> {
            // 执行任务4
            Thread.sleep(5000);
            return "Task 4";
        };

        try {
            // 提交任务并获取Future对象
            Future<String> future1 = executorService.submit(task1);
            Future<String> future2 = executorService.submit(task2);
            Future<String> future3 = executorService.submit(task3);
            Future<String> future4 = executorService.submit(task4);

            // 等待任意一个任务完成
            CompletionService<String> completionService = new ExecutorCompletionService<>(executorService);
            Future<String> completedFuture = completionService.take();

            // 取消其他任务的执行
            future1.cancel(true);
            future2.cancel(true);
            future3.cancel(true);
            future4.cancel(true);

            // 打印完成的任务结果
            System.out.println("Completed Task: " + completedFuture.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown(); // 关闭线程池
        }
    }
}
