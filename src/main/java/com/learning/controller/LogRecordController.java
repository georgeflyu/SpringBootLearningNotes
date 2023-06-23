package com.learning.controller;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.entity.LogRecord;
import com.learning.service.LogRecordService;

/**
 * (LogRecord)表控制层
 *
 * @author makejava
 * @since 2023-06-23 13:43:48
 */
@RestController
@RequestMapping("logRecord")
public class LogRecordController {

    /**
     * 服务对象
     */
    @Resource
    private LogRecordService logRecordService;

    /**
     * 分页查询
     *
     * @param logRecord   筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<LogRecord>> queryByPage(LogRecord logRecord, PageRequest pageRequest) {
        return ResponseEntity.ok(this.logRecordService.queryByPage(logRecord, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<LogRecord> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.logRecordService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param logRecord 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<LogRecord> add(LogRecord logRecord) {
        return ResponseEntity.ok(this.logRecordService.insert(logRecord));
    }

    /**
     * 编辑数据
     *
     * @param logRecord 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<LogRecord> edit(LogRecord logRecord) {
        return ResponseEntity.ok(this.logRecordService.update(logRecord));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Integer id) {
        return ResponseEntity.ok(this.logRecordService.deleteById(id));
    }

}

