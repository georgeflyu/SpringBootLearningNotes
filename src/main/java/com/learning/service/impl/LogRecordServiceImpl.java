package com.learning.service.impl;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.learning.dao.db1.LogRecordDao;
import com.learning.entity.LogRecord;
import com.learning.service.LogRecordService;

/**
 * (LogRecord)表服务实现类
 *
 * @author makejava
 * @since 2023-06-23 13:43:49
 */
@Service
public class LogRecordServiceImpl implements LogRecordService {

    @Resource
    private LogRecordDao logRecordDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public LogRecord queryById(Integer id) {
        return this.logRecordDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param logRecord   筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<LogRecord> queryByPage(LogRecord logRecord, PageRequest pageRequest) {
        long total = this.logRecordDao.count(logRecord);
        return new PageImpl<>(this.logRecordDao.queryAllByLimit(logRecord, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param logRecord 实例对象
     * @return 实例对象
     */
    @Override
    public LogRecord insert(LogRecord logRecord) {
        this.logRecordDao.insert(logRecord);
        return logRecord;
    }

    /**
     * 修改数据
     *
     * @param logRecord 实例对象
     * @return 实例对象
     */
    @Override
    public LogRecord update(LogRecord logRecord) {
        this.logRecordDao.update(logRecord);
        return this.queryById(logRecord.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.logRecordDao.deleteById(id) > 0;
    }
}
