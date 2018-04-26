package com.deep.infra.persistence.sql.mapper;

import com.deep.api.request.OperationCoditionRequest;
import com.deep.domain.model.OperationFile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by huangwenhai on 2018/4/17.
 */

@Mapper
public interface OperationFileMapper {

  List<OperationFile> getOperationFile(@Param("operationCoditionRequest")OperationCoditionRequest operationCoditionRequest);

  int addOperationFile(@Param("operationFile")OperationFile operationFile);

  int updateOperationFile(@Param("id")int id, @Param("checkStatus") short checkStatus);

}
