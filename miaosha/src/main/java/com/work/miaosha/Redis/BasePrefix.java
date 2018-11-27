package com.work.miaosha.Redis;

public abstract class BasePrefix implements KeyPrefix{

    private int expireSeconds;

    private String Prefix;

    public BasePrefix(String prefix) {//0代表永不过期
        this(0,prefix);
    }

    public BasePrefix(int expireSeconds, String Prefix) {
        this.expireSeconds = expireSeconds;
        this.Prefix = Prefix;
    }

    @Override
    public int expireSeconds() {//默认0永不过期
        return expireSeconds;
    }

    @Override
    public String getPrefix() {
        String className = getClass().getSimpleName();
        return className +":"+Prefix;
    }
}
