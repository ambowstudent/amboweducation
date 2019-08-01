package com.ambowEducation.dao;


import com.ambowEducation.po.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface RoleMapper {
    /**
     * 根据userId查询角色及权限，多表连接
     */

    @Insert("insert into t_user_role(user_id,role_id,remark) values(#{userId},#{roleId},#{remark})")
    public int insertUserRole(@Param("userId") Integer userId,
                              @Param("roleId") Integer roleId,
                              @Param("remark") String remark);

    @Delete("delete from t_user_role where user_id = #{userId }")
    public int deleteUserRole(@Param("userId") Integer userId);

    @Select(("SELECT t_r.id id,t_r.name name,t_r.description description  " +
            "from t_user_role t_ur LEFT JOIN t_role t_r ON t_ur.role_id=t_r.id " +
            " where t_ur.user_id=#{userId}"))
    @Results({
            @Result(id = true,column = "id",property = "id"),
          //  @Result(column = "id",property = "permissions",javaType = List.class ,many = @Many(select = "com.ambowEducation.dao.PermissionMapper.findByRoleId",fetchType = FetchType.EAGER))
    })
    List<Role> findByUserId(@Param("userId") int userId);
}
