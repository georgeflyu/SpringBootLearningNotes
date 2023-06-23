package com.learning.utils.lock.dao;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LockDao {

    private Long          id;
    private String        resourceName;
    private String        description;
    private Long          count;
    private String        nodeInfo;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
