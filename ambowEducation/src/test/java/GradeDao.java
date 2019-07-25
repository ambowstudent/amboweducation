import com.ambowEducation.dao.StudentCourseGradeMapper;
import com.ambowEducation.po.StudentCourseGrade;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class GradeDao extends BaseDao{

    @Autowired
    private StudentCourseGradeMapper studentCourseGradeMapper;

    @Test
    public void test(){
        List<StudentCourseGrade> oneStudentAllGrade = studentCourseGradeMapper.findOneStudentAllGrade(22);
        System.out.println(oneStudentAllGrade);
    }
    @Test
    public void test2(){
        List<StudentCourseGrade> allByManyCondition = studentCourseGradeMapper.findAllByManyCondition("201901001", null, null, null, null);
        System.out.println(allByManyCondition);
    }
}
