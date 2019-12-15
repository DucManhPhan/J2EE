package com.manhpd.utils;

import com.manhpd.dto.DataPush;
import com.manhpd.dto.Notification;

import java.util.Optional;

public class DataUtils {

    public static Notification createNotification(String content) {
        DataPush dataPush = DataPush.of(content);
        Notification notification = Notification.builder()
                .actionType("" + Optional.ofNullable(dataPush.getType()).orElse(0))
                .objectType("" + Optional.ofNullable(dataPush.getType()).orElse(0))
                .description(Optional.ofNullable(dataPush.getBody()).orElse(""))
                .id(Optional.ofNullable(dataPush.getId()).orElse(""))
                .objectId(Optional.ofNullable(dataPush.getObject_id()).orElse(""))
                .parentId(Optional.ofNullable(dataPush.getObject_code()).orElse(""))
                .objectCode(Optional.ofNullable(dataPush.getObject_code()).orElse(""))
                .promotionId(Optional.ofNullable(dataPush.getPromotion_id()).orElse(""))
                .title(Optional.ofNullable(dataPush.getTitle()).orElse(""))
                .featureId(Optional.ofNullable(dataPush.getFeature_id()).orElse(""))
                .isAuth(Optional.ofNullable(dataPush.getIsAuth()).orElse(""))
                .type("Notification")
                .type1("Notification")
                .campaignCode(Optional.ofNullable(dataPush.getCampaignCode()).orElse(""))
                .campaignPush(Optional.ofNullable(dataPush.getCampaignPush()).orElse(""))
                .link(Optional.ofNullable(dataPush.getLinkRedirect()).orElse(""))
                .build();

        System.out.println("Content of notification: " + notification.toString());
        return notification;
    }

    public static boolean isEmpty(String data) {
        return data == null || data.equals("");
    }
}
