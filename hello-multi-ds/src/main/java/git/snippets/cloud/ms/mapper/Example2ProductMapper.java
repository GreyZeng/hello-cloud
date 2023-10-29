package git.snippets.cloud.ms.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import git.snippets.cloud.ms.bo.Example2ProductBo;
import git.snippets.cloud.ms.entity.Example2ProductEntity;

import java.util.List;

public interface Example2ProductMapper extends BaseMapper<Example2ProductEntity> {
    List<Example2ProductEntity> queryGoodInfoByGoodId(Example2ProductBo example2ProductBo);
}
