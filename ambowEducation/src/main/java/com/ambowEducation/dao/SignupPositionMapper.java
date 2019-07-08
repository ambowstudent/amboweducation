package com.ambowEducation.dao;

import com.ambowEducation.po.SignupPosition;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SignupPositionMapper {

    @Insert("insert into t_signup_position values(default,#{pId},#{sId})")
    public int insertSignupPosition(SignupPosition s);//学生报名后 在报名表增加一条信息（学生id 和职位id）;

    @Select("select * from t_signup_position where p_id=#{id}")
    public List<SignupPosition> queryStudentsBypId(Integer id);//通过职位id来查看报名的学生

    @Select("select * from t_signup_position where s_id=#{id}")
    public List<SignupPosition> queryPositionsBysId(Integer id);//通过学生id来查看已经报名的职位

    @Select("select * from t_signup_position where s_id=#{sId} and p_id=#{pId}")
    public SignupPosition queryRecord(@Param("sId") Integer sId,
                                      @Param("pId") Integer pId);//查看学生是否已报名当前职位 如果已经报名 则提示不可重复报名









}
