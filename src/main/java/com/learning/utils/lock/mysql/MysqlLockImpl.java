package com.learning.utils.lock.mysql;

import com.learning.utils.lock.entity.LockResource;
import com.learning.utils.lock.service.LockResourceService;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

@Component
@NoArgsConstructor
public class MysqlLockImpl implements Lock {

    private LockResource lockResource;

    @Resource
    private LockResourceService lockResourceService;

    public MysqlLockImpl(String name) {
        LockResource lockResource = new LockResource();
        lockResource.setReentrantCount(1L);
        String addr = null;
        try {
            addr = InetAddress.getLocalHost().getHostAddress();//获得本机IP
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        lockResource.setNodeInfo(addr);
        lockResource.setResourceName(name);
    }

    @Override
    public void lock() {
        while (true) {
            try{
                if(lockResourceService.lock(lockResource)){

                }
            }catch (Exception e){
                //System.out.println(e.getLocalizedMessage());
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return lockResourceService.lock(lockResource);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) {
        long endTime = System.currentTimeMillis() + unit.toMillis(time);
        while (System.currentTimeMillis() < endTime) {
            try{
                if(lockResourceService.lock(lockResource)){
                    return true;
                }
            }catch (Exception e){
                //System.out.println(e.getLocalizedMessage());
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public void unlock() {
        lockResourceService.unlock(lockResource);
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
