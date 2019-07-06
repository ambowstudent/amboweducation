package com.ambowEducation.dao;


import com.ambowEducation.po.User;
import org.apache.ibatis.annotations.Insert;

public interface UserDao {


    @Insert("insert into t_user values(default,#{username},#{password},#{createtime})")
    public int insertUser(User user);

}
