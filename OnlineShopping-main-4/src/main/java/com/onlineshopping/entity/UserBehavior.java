package com.onlineshopping.entity;

import java.util.Date;

public class UserBehavior {
    private Integer behaviorId;
    private Integer userId;
    private Integer productId;
    private String behaviorType;
    private Date behaviorTime;

    // Getters and Setters
    public Integer getBehaviorId() {
        return behaviorId;
    }

    public void setBehaviorId(Integer behaviorId) {
        this.behaviorId = behaviorId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getBehaviorType() {
        return behaviorType;
    }

    public void setBehaviorType(String behaviorType) {
        this.behaviorType = behaviorType;
    }

    public Date getBehaviorTime() {
        return behaviorTime;
    }

    public void setBehaviorTime(Date behaviorTime) {
        this.behaviorTime = behaviorTime;
    }
}