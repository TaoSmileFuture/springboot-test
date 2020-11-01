package com.zhenglt.springboot.mapper;

import com.zhenglt.springboot.pojo.RoleBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ClassName RoleMapper
 * @Description
 * @Author zhenglt
 * @Date 2020/11/01 15:10
 **/
@Mapper
public interface RoleMapper {

    List<RoleBean> findByUserId(String userId);
}
