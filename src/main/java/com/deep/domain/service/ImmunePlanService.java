package com.deep.domain.service;


import com.deep.api.request.ImmuneRequest;
import com.deep.domain.model.ImmunePlanModel;
import com.deep.infra.persistence.sql.mapper.ImmunePlanMapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.List;

@Service
public class ImmunePlanService {
    @Resource
    private ImmunePlanMapper immunePlanMapper;

    public void setImmunePlanModel(ImmunePlanModel immunePlanModel){
        this.immunePlanMapper.setImmunePlanModel(immunePlanModel);
    }


    public List<ImmunePlanModel> getImmunePlanModel(ImmuneRequest immunePlanModel,
                                                    RowBounds bounds){
        return this.immunePlanMapper.getImmunePlanModel(immunePlanModel,bounds);
    }


    public ImmunePlanModel getImmunePlanModelByfactoryNumAndcrowdNumAndimmuneTime(BigInteger factoryNum, String crowdNum, String immuneTime){
        return this.immunePlanMapper.getImmunePlanModelByfactoryNumAndcrowdNumAndimmuneTime(factoryNum,crowdNum,immuneTime);
    }

    public List<ImmunePlanModel> getImmunePlanModelByProfessor(Integer isPass, RowBounds bounds){
        return this.immunePlanMapper.getImmunePlanModelByProfessor(isPass,bounds);
    }

    public List<ImmunePlanModel> getImmunePlanModelBySupervisor(Integer isPass1, RowBounds bounds){
        return this.immunePlanMapper.getImmunePlanModelBySupervisor(isPass1,bounds);
    }

    public ImmunePlanModel getImmunePlanModelByid(Long id){
        return this.immunePlanMapper.getImmunePlanModelByid(id);
    }

    public int updateImmunePlanModelByProfessor(ImmunePlanModel immunePlanModel){
        return this.immunePlanMapper.updateImmunePlanModelByProfessor(immunePlanModel);
    }

    public int updateImmunePlanModelBySupervisor(ImmunePlanModel immunePlanModel){
        return this.immunePlanMapper.updateImmunePlanModelBySupervisor(immunePlanModel);
    }

    public int updateImmunePlanModelByOperator(ImmunePlanModel immunePlanModel){
        return this.immunePlanMapper.updateImmunePlanModelByOperator(immunePlanModel);
    }


    public int deleteImmunePlanModelByid(Long id){
        return this.immunePlanMapper.deleteImmunePlanModelByid(id);
    }


}
