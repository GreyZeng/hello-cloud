package git.snippets.hellomybatisplus;

import lombok.Data;

/**
 * @author <a href="mailto:410486047@qq.com">Grey</a>
 * @date 2022/1/2
 * @since 1.8
 */
@Data
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
