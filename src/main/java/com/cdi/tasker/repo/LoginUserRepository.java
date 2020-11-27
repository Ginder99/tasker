package com.cdi.tasker.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cdi.tasker.model.LoginUser;

@Repository
public interface LoginUserRepository extends JpaRepository<LoginUser, String>
{
    public LoginUser findByUserNameAndPassword(String userName, String password);

    public LoginUser findByUserName(String userName);

    public Integer deleteByUserName(String userName);
}
