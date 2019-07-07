package com.ambowEducation.dao;


import com.ambowEducation.po.Role;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface RoleMapper {

    /**
     * 根据userId查询角色及权限，多表连接
     */
    @Select(("SELECT t_r.id id,t_r.name name,t_r.description description  " +
            "from t_user_role t_ur LEFT JOIN t_role t_r ON t_ur.role_id=t_r.id " +
            " where t_ur.user_id=#{userId}"))
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "id",property = "permissions" ,many = @Many(select = "package com.ambowEducation.dao.PermissionMapper.findByRoleId",fetchType = FetchType.DEFAULT))
    })
    List<Role> findByUserId(@Param("userId") int userId);
}