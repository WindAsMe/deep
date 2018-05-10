package com.deep.api.request;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by huangwenhai on 2018/4/17.
 */
public class OperationCoditionRequest {

  private Integer id;
  private Integer techCheckStatus;
  private Integer operationId;
  @JsonFormat(pattern = "yyy-MM-dd HH:mm:ss")
  private Timestamp checkTime;
  private Long factoryNum;
  private String factoryName;
  private List<Long> factoryList;
  private int page = 0;
  private int pageSize = 10;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {

    this.id = id;
  }

  public Integer getTechCheckStatus() {
    return techCheckStatus;
  }

  public void setTechCheckStatus(Integer techCheckStatus) {
    this.techCheckStatus = techCheckStatus;
  }

  public Integer getOperationId() {
    return operationId;
  }

  public void setOperationId(Integer operationId) {
    this.operationId = operationId;
  }

  public Timestamp getCheckTime() {
    return checkTime;
  }

  public void setCheckTime(Timestamp checkTime) {
    this.checkTime = checkTime;
  }

  public Long getFactoryNum() {
    return factoryNum;
  }

  public void setFactoryNum(Long factoryNum) {
    this.factoryNum = factoryNum;
  }

  public String getFactoryName() {
    return factoryName;
  }

  public void setFactoryName(String factoryName) {
    this.factoryName = factoryName;
  }

  public List<Long> getFactoryList() {
    return factoryList;
  }

  public void setFactoryList(List<Long> factoryList) {
    this.factoryList = factoryList;
  }

  public int getPage() {
    return page;
  }

  public void setPage(int page) {
    this.page = page;
  }

  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  @Override
  public String toString() {
    return "OperationCoditionRequest{" +
        "id=" + id +
        ", techCheckStatus=" + techCheckStatus +
        ", operationId=" + operationId +
        ", checkTime=" + checkTime +
        ", factoryNum=" + factoryNum +
        ", factoryName='" + factoryName + '\'' +
        ", factoryList=" + factoryList +
        '}';
  }

}
