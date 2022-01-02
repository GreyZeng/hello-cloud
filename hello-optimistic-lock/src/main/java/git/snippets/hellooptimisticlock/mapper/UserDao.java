package git.snippets.hellooptimisticlock.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import git.snippets.hellooptimisticlock.User;
import org.springframework.stereotype.Repository;

/**
 * @author <a href="mailto:410486047@qq.com">Grey</a>
 * @date 2022/1/2
 * @since 1.8
 */
@Repository // 表示持久层
public interface UserDao extends BaseMapper<User> {

}