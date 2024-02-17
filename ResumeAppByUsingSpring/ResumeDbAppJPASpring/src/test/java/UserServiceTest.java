import static org.junit.Assert.assertEquals;

import java.util.List;
import org.junit.Test;
import com.company.entity.User;
import com.company.service.impl.UserServiceImpl;
import com.company.service.inter.UserServiceInter;

public class UserServiceTest {

    @Test
    public void testGetAll() {
        UserServiceInter userService = new UserServiceImpl();
        List<User> users = userService.getAll(null, null, null);
        assertEquals("user size must be 2", 2, users.size());
    }
}