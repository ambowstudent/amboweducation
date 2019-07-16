package com.test;
import com.ambowEducation.dao.StudentCourseGradeMapper;
import com.ambowEducation.po.StudentCourseGrade;
import com.ambowEducation.po.User;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class GradeMapperTest extends BaseTest{

    @Autowired
    private StudentCourseGradeMapper studentCourseGradeMapper;

    @Test
    public void test(){
        User user=new User();
        user.setId(0);
        user.setUsername("");
        user.setPassword("");
        user.setCreatetime(new Date());
        user.setRoles(Lists.newArrayList());

    }
    @Test
    public void findAllByManyCondition(){
      //  StudentCourseGrade studentCourseGrade=new StudentCourseGrade();
        List<StudentCourseGrade> manyCondition = studentCourseGradeMapper.findAllByManyCondition(null,null, null, null, 0);
        for (StudentCourseGrade studentCourseGrade1:
             manyCondition) {
            System.out.println(studentCourseGrade1);

        }

    }
}
