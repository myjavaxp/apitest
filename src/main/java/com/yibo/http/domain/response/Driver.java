package com.yibo.http.domain.response;

import java.util.StringJoiner;

/**
 * 从后端拷贝的返回体
 */
public class Driver {
    private Long businessId;

    private String name;

    private String phoneNumber;

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Driver.class.getSimpleName() + "[", "]")
                .add("businessId=" + businessId)
                .add("name='" + name + "'")
                .add("phoneNumber='" + phoneNumber + "'")
                .toString();
    }
}
