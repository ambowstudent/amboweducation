import com.ambowEducation.service.StudentCourseGradeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring*.xml")
public class test {

    @Autowired
    private StudentCourseGradeService studentCourseGradeService;

    @Test
    public void test(){
        try {
            Map<String, List<Map<String, Object>>> stringListMap = studentCourseGradeService.selectThreeYearSal();
            System.out.println(stringListMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
