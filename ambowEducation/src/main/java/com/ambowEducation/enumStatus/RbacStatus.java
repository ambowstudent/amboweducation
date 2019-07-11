package com.ambowEducation.enumStatus;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
public enum RbacStatus {
    NOT_LOGIN(-1,"对不起，请登录"),
    NOT_PERMIS(-3,"对不起，您缺少该权限"),
    SUCCESS_IN(1,"登陆成功"),
    ERROR_IN(-2,"用户名或密码错误"),
    SUCCESS_OUT(2,"退出成功");


    @Getter
    private Integer code;
    @Getter
    private String message;

}
