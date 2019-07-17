import com.ambowEducation.po.User;
import com.ambowEducation.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring*.xml")
public class UserTest {

    @Autowired
    private UserService userService;

    @Test
    public void test(){
        User user = userService.findAllUserAndRoleAndPer("tu001");
      //  User user = userService.findAllUserAndRoleAndPer("201901002");
        System.out.println(user);
    }
}
