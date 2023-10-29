package git.snippets.cloud.ms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("git.snippets.cloud.ms.mapper")
public class Example4Application {
    public static void main(String[] args) {
        SpringApplication.run(Example4Application.class, args);
    }
}
