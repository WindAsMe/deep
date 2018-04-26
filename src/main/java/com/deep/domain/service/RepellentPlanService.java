package com.deep.domain.service;


import com.deep.api.request.RepellentRequest;
import com.deep.domain.model.RepellentPlanModel;
import com.deep.infra.persistence.sql.mapper.RepellentPlanMapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.List;

@Service
public class RepellentPlanService {
    @Resource
    private RepellentPlanMapper repellentPlanMapper;
    public int setRepellentPlanModel(RepellentPlanModel repellentPlanModel) {
        return this.repellentPlanMapper.setRepellentPlanModel(repellentPlanModel);
    }


    public List<RepellentPlanModel> getRepellentPlanModel(RepellentRequest repellentPlanModel,
                                                          RowBounds bounds){
        return this.repellentPlanMapper.getRepellentPlanModel(repellentPlanModel,bounds);
    }

    public RepellentPlanModel getRepellentPlanModelByfactoryNumAndcrowdNumAndrepellentTimeAndrepellentName(BigInteger factoryNum,String crowdNum,String repellentTime,String repellentName){
        return this.repellentPlanMapper.getRepellentPlanModelByfactoryNumAndcrowdNumAndrepellentTimeAndrepellentName(factoryNum, crowdNum, repellentTime, repellentName);
    }

    public List<RepellentPlanModel> getRepellentPlanModelByProfessor(Integer ispassCheck, RowBounds bounds){
        return this.repellentPlanMapper.getRepellentPlanModelByProfessor(ispassCheck,bounds);
    }

    public List<RepellentPlanModel> getRepellentPlanModelBySupervisor(Integer ispassSup, RowBounds bounds){
        return this.repellentPlanMapper.getRepellentPlanModelBySupervisor(ispassSup,bounds);
    }

    public RepellentPlanModel getRepellentPlanModelByid(Long id){
        return this.repellentPlanMapper.getRepellentPlanModelByid(id);
    }

    public int updateRepellentPlanModelByProfessor(RepellentPlanModel repellentPlanModel){
        return this.repellentPlanMapper.updateRepellentPlanModelByProfessor(repellentPlanModel);
    }

    public int updateRepellentPlanModelBySupervisor(RepellentPlanModel repellentPlanModel){
        return this.repellentPlanMapper.updateRepellentPlanModelBySupervisor(repellentPlanModel);
    }

    public int updateRepellentPlanModelByOperator(RepellentPlanModel repellentPlanModel){
        return this.repellentPlanMapper.updateRepellentPlanModelByOperator(repellentPlanModel);
    }

    public int deleteRepellentPlanModelByid(Long id){
        return this.repellentPlanMapper.deleteRepellentPlanModelByid(id);
    }

}
