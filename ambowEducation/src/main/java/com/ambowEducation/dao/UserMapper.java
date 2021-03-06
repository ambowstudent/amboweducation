package com.ambowEducation.dao;


import com.ambowEducation.dto.UserDto;
import com.ambowEducation.po.User;
import org.apache.ibatis.annotations.*;

public interface UserMapper {

    /**
     * 添加user数据
     * @param user
     * @return
     */
    @Insert("insert into t_user values(default,#{username},#{password},#{createtime})")
    public int insertUser(User user);

    /**
     * 通过username删除角色
     */
    @Delete("delete from t_user where username=#{username}")
    public int deleteUser(@Param("username") String username);

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
    /**
     * 根据用户名修改密码
     */
    @Update("update t_user set password=#{password} where username=#{username}")
    void updatePasswordByUsername(UserDto userDto);
    /**
     * 查询旧密码
     */
    @Select("select * from t_user where username=#{username} and password=#{oldPassword}")
    User findByUserByOldPassword(UserDto userDto);
}