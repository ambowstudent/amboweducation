package com.ambowEducation.Exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 这个类负责处理 AdminClassCourseServiceImpl 中的异常
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminClassCourseException extends RuntimeException {

    private Integer code;
    private String message;

}
