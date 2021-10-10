package com.manhpd.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Notification {

    private String id;

    private String actionType;

    private String createdDay;

    private String createdMonth;

    private String createdYear;

    private String creatorId;

    private String description;

    private String expiredTime;

    private String featureId;

    private String isAuth;

    private String lastUpdateTime;

    private String link;

    private String objectId;

    private String objectCode;

    private String objectType;

    private String parentId;

    private String promotionId;

    private int sentTime;

    private int site;

    private int sortOrder;

    private String title;

    private String type;

    private String type1;

    private int updateTime;

    private String updateUserId;

    private String campaignCode;

    private String campaignPush;

    @Override
    public String toString() {
        return "Created day: " + this.createdDay + " -Title: " + this.title + " -Description: " + this.description + " - Link: " + this.link + " - ObjectId: " + this.objectId + "\n"
                + " -ObjectCode: " + this.objectCode + " -parentId: " + this.parentId + " - CampaignCode: " + this.campaignCode + " -Link: " + this.link;
    }

}