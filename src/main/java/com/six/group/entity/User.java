package com.six.group.entity;


import java.io.Serializable;

public class User implements Serializable {

  private String name;
  private String pwd;
  private int role;
  private String phone;


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getPwd() {
    return pwd;
  }

  public void setPwd(String pwd) {
    this.pwd = pwd;
  }


  public int getRole() {
    return role;
  }

  public void setRole(int role) {
    this.role = role;
  }


  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

}
