package com.learning.dao.db1;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import com.learning.entity.LogRecord;

/**
 * (LogRecord)表数据库访问层
 *
 * @author makejava
 * @since 2023-06-23 13:43:48
 */
@Mapper
public interface LogRecordDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    LogRecord queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param logRecord 查询条件
     * @param pageable  分页对象
     * @return 对象列表
     */
    List<LogRecord> queryAllByLimit(LogRecord logRecord, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param logRecord 查询条件
     * @return 总行数
     */
    long count(LogRecord logRecord);

    /**
     * 新增数据
     *
     * @param logRecord 实例对象
     * @return 影响行数
     */
    int insert(LogRecord logRecord);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<LogRecord> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<LogRecord> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<LogRecord> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<LogRecord> entities);

    /**
     * 修改数据
     *
     * @param logRecord 实例对象
     * @return 影响行数
     */
    int update(LogRecord logRecord);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}

