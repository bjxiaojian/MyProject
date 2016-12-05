package com.xxx.dao;

import com.xxx.model.UserLabelDesc;

public interface UserLabelDescMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserLabelDesc record);

    int insertSelective(UserLabelDesc record);

    UserLabelDesc selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserLabelDesc record);

    int updateByPrimaryKey(UserLabelDesc record);
}