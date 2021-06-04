package com.qqhr.provider.dto;

import com.qqhr.golden.base.BaseRequestDto;

/**
 * @Author WilliamDragon
 * @Date 2021/4/27 9:38
 * @Version 1.0
 */

public class OrderDto extends BaseRequestDto {
    private String name;
    private String age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
