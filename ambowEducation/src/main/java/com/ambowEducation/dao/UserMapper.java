package com.ambowEducation.dao;


import com.ambowEducation.po.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    /**
     * 添加user数据
     * @param user
     * @return
     */
    @Insert("insert into t_user values(default,#{username},#{password},#{createtime})")
    public int insertUser(User user);

    /**
     * 通过username查询角色
     */
    @Select("select * from t_user where username=#{username}")
    User findByName(@Param("username") String username);
    /**
     * 通过id查询角色
     */
    @Select("select * from t_user where id=#{id}")
    User findById(int id);
    /**
     * 通过账号密码查询角色
     */
    @Select("select * from t_user where username=#{username} and password=#{password}")
    User findByNameAndPassword(@Param("username") String username,@Param("password")String password);
}