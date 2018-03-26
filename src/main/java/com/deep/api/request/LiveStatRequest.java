package com.deep.api.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Created by huangwenhai on 2018/3/14.
 */

public class LiveStatRequest {
  @Min(value = 1)
  int pageNo;

  @Min(value = 1)
  @Max(value = 300)
  int pageSize;

  String userId;
  String pulldomain;

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public int getPageNo() {
    return pageNo;
  }

  public void setPageNo(int pageNo) {
    this.pageNo = pageNo;
  }

  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  public String getPulldomain() {
    return pulldomain;
  }

  public void setPulldomain(String pulldomain) {
    this.pulldomain = pulldomain;
  }
}