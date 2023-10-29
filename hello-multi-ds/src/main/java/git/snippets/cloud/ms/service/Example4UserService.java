package git.snippets.cloud.ms.service;


import git.snippets.cloud.ms.bo.Example2ProductBo;
import git.snippets.cloud.ms.entity.Example2ProductEntity;

import java.util.List;

public interface Example4UserService {

    List<Example2ProductEntity> selectFromMaster(Example2ProductBo example2ProductBo);

    List<Example2ProductEntity> selectFromSlave1(Example2ProductBo example2ProductBo);

    List<Example2ProductEntity> selectFromSlave2(Example2ProductBo example2ProductBo);
}
