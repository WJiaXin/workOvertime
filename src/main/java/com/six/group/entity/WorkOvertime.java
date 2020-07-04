package com.six.group.entity;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;


@ApiModel(value="workOvertime对象")
public class WorkOvertime implements Serializable {

  private String data;
  private String award;
  private int type;


  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
  }


  public String getAward() {
    return award;
  }

  public void setAward(String award) {
    this.award = award;
  }


  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }

}
