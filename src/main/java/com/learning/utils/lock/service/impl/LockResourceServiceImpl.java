package com.learning.utils.lock.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.learning.utils.lock.entity.LockResource;
import com.learning.utils.lock.mapper.LockResourceMapper;
import com.learning.utils.lock.service.LockResourceService;


@Service
public class LockResourceServiceImpl implements LockResourceService {

    @Autowired
    private LockResourceMapper lockResourceMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean lock(LockResource lockResource) {
        LockResource lr = lockResourceMapper.selectForUpdate(lockResource.getResourceName());
        if (lr != null && lr.getId() != null) {
            if (lr.getNodeInfo().equals(lockResource.getNodeInfo())) {
                System.out.println(lr.getNodeInfo() + lockResource.getNodeInfo());
                lockResourceMapper.updateCount(lr.getId());
                return true;
            }
        } else {
            lockResourceMapper.insertLockResource(lockResource);
            return true;
        }
        return false;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean unlock(LockResource lockResource) {
        LockResource lr = lockResourceMapper.select(lockResource.getId());
        if (lr != null && lr.getId() != null) {
            if (lr.getNodeInfo().equals(lockResource.getNodeInfo())) {
                if (lr.getReentrantCount() > 1) {
                    lockResourceMapper.updateCountDecrease(lockResource.getId());
                } else {
                    lockResourceMapper.deleteLockResource(lockResource.getId());
                }
                return true;
            }
        }
        return false;
    }
}
