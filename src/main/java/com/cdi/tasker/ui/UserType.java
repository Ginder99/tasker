package com.cdi.tasker.ui;

public enum UserType
{
    STUDENT("STUDENT"), ADVISOR("ADVISOR"), ADMINISTRATOR("ADMINISTRATOR");

    private final String value;

    UserType(String value)
    {
        this.value = value;
    }

    public String getValue()
    {
        return value;
    }
}
