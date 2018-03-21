package com.deep.domain.service;

import com.deep.domain.model.GenealogicalFilesTransModel;
import com.deep.infra.persistence.sql.mapper.GenealogicalFilesTransMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * create by zhongrui on 18-3-19.
 */


//在查询时 允许输入三种耳牌查询
//输入耳牌时 先进行信息转换
@Service
public class GenealogicalFilesTransService {

    @Resource
    private GenealogicalFilesTransMapper genealogicalFilesTransMapper;

    public void setGenealogicalFilesTransModel(GenealogicalFilesTransModel genealogicalFilesTransModel){
        genealogicalFilesTransMapper.setGenealogicalFilesTransModel(genealogicalFilesTransModel);
    }

    public GenealogicalFilesTransModel getGenealogicalFilesTransModelByimmuneEartag(String nextOwnerFactory){
        GenealogicalFilesTransModel genealogicalFilesTransModel = this.genealogicalFilesTransMapper.getGenealogicalFilesTransModelBytrademarkEartag(nextOwnerFactory);
        return genealogicalFilesTransModel;
    }
    public List<GenealogicalFilesTransModel> getAllGenealogicalFilesTransModel(){
        List<GenealogicalFilesTransModel> genealogicalFilesTransModels = this.genealogicalFilesTransMapper.getAllGenealogicalFilesTransModel();
        return genealogicalFilesTransModels;
    }


}
