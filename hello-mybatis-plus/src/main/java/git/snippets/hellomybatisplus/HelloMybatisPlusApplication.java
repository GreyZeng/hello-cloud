package git.snippets.hellomybatisplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("git.snippets.hellomybatisplus.mapper")
public class HelloMybatisPlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloMybatisPlusApplication.class, args);
    }

}
