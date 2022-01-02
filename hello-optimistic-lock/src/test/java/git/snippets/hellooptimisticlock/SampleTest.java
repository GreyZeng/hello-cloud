package git.snippets.hellooptimisticlock;

import git.snippets.hellooptimisticlock.mapper.UserDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class SampleTest {
    @Autowired
    private UserDao userDao;

    @Test
    public void contextLoads() {
        // 调用BaseMapper查询方法<selectList>
        List<User> list = userDao.selectList(null);
        list.forEach(System.out::println);
    }

}
