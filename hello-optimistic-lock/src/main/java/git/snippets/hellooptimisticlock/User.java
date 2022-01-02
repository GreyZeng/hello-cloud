package git.snippets.hellooptimisticlock;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author <a href="mailto:410486047@qq.com">Grey</a>
 * @date 2022/1/2
 * @since 1.8
 */
@Data
public class User {
    // 常见的主键生成策略(UUID/MySQL自增长/雪花算法/redis/zookeeper...)
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
