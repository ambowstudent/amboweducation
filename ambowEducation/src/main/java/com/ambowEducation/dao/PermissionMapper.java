package com.ambowEducation.dao;


import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PermissionMapper {

    /**
     * 根据roleId查询权限
     */
    @Select("SELECT t_p.id id, t_p.name name,t_p.description description   " +
            "from t_role_permission t_rp LEFT JOIN t_permission t_p ON " +
            " t_rp.permission_id=t_p.id where t_rp.role_id=#{roleId}")
    List<PermissionMapper> findByRoleId(@Param("roleId") int roleId);

}