package com.demo.dao;

import com.demo.entity.TvChannel;

public interface TvChannelMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_tv_channel
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer tId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_tv_channel
     *
     * @mbg.generated
     */
    int insert(TvChannel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_tv_channel
     *
     * @mbg.generated
     */
    int insertSelective(TvChannel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_tv_channel
     *
     * @mbg.generated
     */
    TvChannel selectByPrimaryKey(Integer tId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_tv_channel
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(TvChannel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_tv_channel
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(TvChannel record);
}