package com.six.group.entity;


import java.io.Serializable;

public class Logs implements Serializable {

  private int logId;
  private String logUserId;
  private int logState;
  private String logDate;
  private String logAward;


  public int getLogId() {
    return logId;
  }

  public void setLogId(int logId) {
    this.logId = logId;
  }


  public String getLogUserId() {
    return logUserId;
  }

  public void setLogUserId(String logUserId) {
    this.logUserId = logUserId;
  }


  public int getLogState() {
    return logState;
  }

  public void setLogState(int logState) {
    this.logState = logState;
  }


  public String getLogDate() {
    return logDate;
  }

  public void setLogDate(String logDate) {
    this.logDate = logDate;
  }


  public String getLogAward() {
    return logAward;
  }

  public void setLogAward(String logAward) {
    this.logAward = logAward;
  }

}
