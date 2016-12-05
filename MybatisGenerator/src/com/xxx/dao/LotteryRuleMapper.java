package com.xxx.dao;

import com.xxx.model.LotteryRule;

public interface LotteryRuleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LotteryRule record);

    int insertSelective(LotteryRule record);

    LotteryRule selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LotteryRule record);

    int updateByPrimaryKey(LotteryRule record);
}