package com.deep.infra.persistence.sql.mapper;


import com.deep.api.request.ImmuneRequest;
import com.deep.domain.model.ImmunePlanModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.math.BigInteger;
import java.util.List;

@Mapper
public interface ImmunePlanMapper {
    int setImmunePlanModel(@Param("immunePlanModel") ImmunePlanModel immunePlanModel);


    List<ImmunePlanModel> getImmunePlanModel(@Param("immunePlanModel") ImmuneRequest immunePlanModel,

                                             RowBounds bounds);

    ImmunePlanModel getImmunePlanModelByfactoryNumAndcrowdNumAndimmuneTime(@Param("factoryNum") BigInteger factoryNum,
                                                                           @Param("crowdNum") String crowdNum,
                                                                           @Param("immuneTime") String immuneTime);

    List<ImmunePlanModel> getImmunePlanModelByProfessor(@Param("ispassCheck") String ispassCheck ,
                                                        RowBounds bounds);

    List<ImmunePlanModel> getImmunePlanModelBySupervisor(@Param("ispassSup") String ispassSup,
                                                         RowBounds bounds);

    ImmunePlanModel getImmunePlanModelByid(@Param("id") Long id);

    int deleteImmunePlanModelByid(@Param("id") Long id);


    int updateImmunePlanModelByProfessor(@Param("immunePlanModel") ImmunePlanModel immunePlanModel);
    int updateImmunePlanModelBySupervisor(@Param("immunePlanModel") ImmunePlanModel immunePlanModel);
    int updateImmunePlanModelByOperator(@Param("immunePlanModel") ImmunePlanModel immunePlanModel);


}
