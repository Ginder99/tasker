package com.cdi.tasker.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.cdi.tasker.ui.UserType;

@Entity
@Table(name = "USERS")
public class LoginUser
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "USER_NAME", unique = true)
    private String userName;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "USER_TYPE")
    private String userType;

    public LoginUser()
    {
    }

    public LoginUser(String name, String userName, String password, UserType userType)
    {
        super();
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.userType = userType.getValue();
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getUserType()
    {
        return userType;
    }

    public void setUserType(String userType)
    {
        this.userType = userType;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return "LoginUser [id=" + id + ", name=" + name + ", userName=" + userName + ", password=" + password
                + ", userType=" + userType + "]";
    }
}
