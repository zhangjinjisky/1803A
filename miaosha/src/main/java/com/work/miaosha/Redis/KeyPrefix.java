package com.work.miaosha.Redis;

public interface KeyPrefix {

    public int expireSeconds();

    public String  getPrefix();

}
