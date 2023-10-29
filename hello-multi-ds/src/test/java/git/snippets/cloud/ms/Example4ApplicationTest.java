package git.snippets.cloud.ms;


import git.snippets.cloud.ms.bo.Example2ProductBo;
import git.snippets.cloud.ms.entity.Example2ProductEntity;
import git.snippets.cloud.ms.service.Example4UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class Example4ApplicationTest {

    @Resource
    private Example4UserService example4UserService;

    @Test
    public void toGet() {
        //5678L
        long goodId = 5678L;
        Example2ProductBo example2ProductBo = new Example2ProductBo();
        example2ProductBo.setGoodId(goodId);
        List<Example2ProductEntity> result1 = example4UserService.selectFromMaster(example2ProductBo);
        System.out.println(result1);
        List<Example2ProductEntity> result2 = example4UserService.selectFromSlave1(example2ProductBo);
        System.out.println(result2);
        List<Example2ProductEntity> result3 = example4UserService.selectFromSlave2(example2ProductBo);
        System.out.println(result3);

    }

}
