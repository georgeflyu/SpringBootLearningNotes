package com.learning.utils.lock.mapper;


import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.data.repository.query.Param;

import com.learning.utils.lock.entity.LockResource;

@Mapper
public interface LockResourceMapper {

    @Select("select * from sys_lock_resource where id = #{id}")
    LockResource select(@Param("id") Long id);

    @Select("select * from sys_lock_resource where resource_name = #{resourceName}")
    LockResource selectByResourceName(@Param("resourceName") String resourceName);

    @Select("select * from sys_lock_resource where resource_name = #{resourceName} for update")
    LockResource selectForUpdate(@Param("resourceName") String resourceName);

    @Update("update sys_lock_resource set count = count+1 where id = #{id}")
    int updateCount(@Param("id") Long id);

    @Update("update sys_lock_resource set count = count-1 where id = #{id}")
    int updateCountDecrease(@Param("id") Long id);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("insert into sys_lock_resource (resource_name,count,node_info,`desc`)values(#{resourceName},#{count},#{nodeInfo},#{desc})")
    int insertLockResource(LockResource lockResource);

    @Delete("delete from sys_lock_resource where id = #{id}")
    int deleteLockResource(@Param("id") Long id);
}
