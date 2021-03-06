package com.ambowEducation.Exception;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserException extends RuntimeException {

    private Integer code;
    private String message;
}
