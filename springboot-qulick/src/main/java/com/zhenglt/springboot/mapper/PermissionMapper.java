package com.zhenglt.springboot.mapper;

import com.zhenglt.springboot.pojo.PermissionBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ClassName PermissionMapper
 * @Description
 * @Author zhenglt
 * @Date 2020/11/01 15:11
 **/
@Mapper
public interface PermissionMapper {

    List<PermissionBean> findByUserId(String userId);
}
