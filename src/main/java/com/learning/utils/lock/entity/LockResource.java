package com.learning.utils.lock.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 123@hand-china.com 2019-09-20 13:50:51
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LockResource {

    private Long   id;
    private String resourceName;
    private String description;
    private Long   reentrantCount;
    private String nodeInfo;
    private Date   createDate;
    private Date   updateDate;
}
