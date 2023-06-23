package com.learning.utils.lock.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LockDao {
    private Long id;
    private String resourceName;
    private String description;
    private Long count;
    private String nodeInfo;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
