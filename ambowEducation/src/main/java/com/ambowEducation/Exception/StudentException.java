package com.ambowEducation.Exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentException extends RuntimeException {
    private Integer code;
    private String message;
}
