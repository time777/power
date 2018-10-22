package com.yunshuju.domain.dao;

import org.apache.ibatis.annotations.Mapper;

import com.yunshuju.domain.entity.SysUser;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YuYanHui
 * @since 2018-09-06
 */
@Mapper
public interface SysUserDao extends BaseMapper<SysUser> {

}
