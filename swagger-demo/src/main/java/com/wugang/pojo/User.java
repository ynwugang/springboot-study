package com.wugang.pojo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

//@Api(注释)
@ApiModel("用户实体类")
public class User {
    @ApiModelProperty("用户ID")
    private Integer id;
    @ApiModelProperty("用户名")
    private String name;
    @ApiModelProperty("密码")
    private String pwd;
    @ApiModelProperty("角色")
    private String prem;

    public User() {
    }

    public User(Integer id, String name, String pwd, String prem) {
        this.id = id;
        this.name = name;
        this.pwd = pwd;
        this.prem = prem;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getPrem() {
        return prem;
    }

    public void setPrem(String prem) {
        this.prem = prem;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                ", prem='" + prem + '\'' +
                '}';
    }
}
