import com.ambowEducation.dao.TechnicalTeacherMapper;
import com.ambowEducation.dao.WorkMapper;
import com.ambowEducation.service.StudentCourseGradeService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class WorkTest extends BaseDao {

    @Autowired
    private WorkMapper workMapper;

    @Autowired
    private TechnicalTeacherMapper technicalTeacherMapper;

    @Autowired
    private StudentCourseGradeService studentCourseGradeService;

    @Test
    public void test(){
        List<Map<String, Object>> maps = workMapper.selectEveryTypeCountByTechId(2);
        System.out.println(maps);

        int count = technicalTeacherMapper.findTechnicalTeacherInStudentCount(2);
        System.out.println(count);
    }

    @Test
    public void test2(){
        try {
            List<Map<String, Object>> employment = studentCourseGradeService.findStudentWorkRateOfEmployment(2);
            System.out.println(employment);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void test3(){
        try {
            List<Map<String, Object>> percent = studentCourseGradeService.findAllStudentWorkPercent();
                System.out.println(percent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
