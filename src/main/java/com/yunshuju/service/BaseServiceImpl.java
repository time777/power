package com.yunshuju.service;

import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;


@Transactional(readOnly = true, rollbackFor = Exception.class)
public class BaseServiceImpl<M extends BaseMapper<T>, T extends Model> extends ServiceImpl<M, T>  {

}
