package git.snippets.cloud.ms.service;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import git.snippets.cloud.ms.bo.Example2ProductBo;
import git.snippets.cloud.ms.entity.Example2ProductEntity;
import git.snippets.cloud.ms.mapper.Example2ProductMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@DS("master")
public class Example4UserServiceImpl extends ServiceImpl<Example2ProductMapper, Example2ProductEntity> implements Example4UserService {

    @Resource
    private Example2ProductMapper example2ProductMapper;

    @DS("master")
    @Override
    public List<Example2ProductEntity> selectFromMaster(Example2ProductBo example2ProductBo) {
        return example2ProductMapper.queryGoodInfoByGoodId(example2ProductBo);
    }

    @DS("slave_1")
    @Override
    public List<Example2ProductEntity> selectFromSlave1(Example2ProductBo example2ProductBo) {
        return example2ProductMapper.queryGoodInfoByGoodId(example2ProductBo);
    }

    @DS("slave_2")
    @Override
    public List<Example2ProductEntity> selectFromSlave2(Example2ProductBo example2ProductBo) {
        return example2ProductMapper.queryGoodInfoByGoodId(example2ProductBo);
    }
}
