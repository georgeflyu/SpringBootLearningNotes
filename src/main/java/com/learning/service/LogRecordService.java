package com.learning.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.learning.entity.LogRecord;

/**
 * (LogRecord)表服务接口
 *
 * @author makejava
 * @since 2023-06-23 13:43:49
 */
public interface LogRecordService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    LogRecord queryById(Integer id);

    /**
     * 分页查询
     *
     * @param logRecord   筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<LogRecord> queryByPage(LogRecord logRecord, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param logRecord 实例对象
     * @return 实例对象
     */
    LogRecord insert(LogRecord logRecord);

    /**
     * 修改数据
     *
     * @param logRecord 实例对象
     * @return 实例对象
     */
    LogRecord update(LogRecord logRecord);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
