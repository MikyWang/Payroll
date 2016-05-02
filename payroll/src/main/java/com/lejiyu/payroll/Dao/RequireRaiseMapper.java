package com.lejiyu.payroll.Dao;

import java.util.List;

import com.lejiyu.payroll.Entity.RequireRaise;

public interface RequireRaiseMapper {
    int deleteByPrimaryKey(Integer formId);

    int insert(RequireRaise record);

    int insertSelective(RequireRaise record);

    RequireRaise selectByPrimaryKey(Integer formId);
    
    List<RequireRaise> selectAll();
    
    int updateByPrimaryKeySelective(RequireRaise record);

    int updateByPrimaryKey(RequireRaise record);
}