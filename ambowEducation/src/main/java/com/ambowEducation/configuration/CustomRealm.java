package com.ambowEducation.configuration;

import com.ambowEducation.po.User;
import com.ambowEducation.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private UserService service;
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//        User userNew =(User) principalCollection.getPrimaryPrincipal();
//        User user = service.findAllUserAndRoleAndPer(userNew.getUsername());
//        List<String> stringRole=new ArrayList<>();
//        List<String> stringPermission=new ArrayList<>();
//        for(Role role:user.getRoleList()){
//            if(role!=null){
//                stringRole.add(role.getName());
//                for(Permission permission:role.getPermissionList()){
//                    if(permission!=null){
//                        stringPermission.add(permission.getName());
//                    }
//                }
//            }
//        }
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
//        info.addStringPermissions(stringPermission);
//        info.addRoles(stringRole);
        return info;
    }

    //登录验证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
    String username=(String) authenticationToken.getPrincipal();
        User user = service.findByUsernameBasicInfo(username);
        if(user==null||user.getPassword()==null||"".equals(user.getPassword())){
            return null;
        }
        return new SimpleAuthenticationInfo(user, user.getPassword(), this.getClass().getName());
    }
}
