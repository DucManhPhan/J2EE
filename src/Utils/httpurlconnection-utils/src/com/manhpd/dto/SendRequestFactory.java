package com.manhpd.dto;

import com.manhpd.utils.http_request.DoRequest;
import com.manhpd.utils.http_request.SendRequest;
import com.manhpd.utils.http_request.SendRequestWithBodyData;

import java.util.Optional;

public class SendRequestFactory {

    public static Optional<DoRequest> createSendRequest(SendRequestType type) {
        switch (type) {
            case NORMAL_SEND_REQUEST:
                return Optional.of(new SendRequest());

            case SEND_REQUEST_WITH_BODY_DATA:
                return Optional.of(new SendRequestWithBodyData());

            default:
                return Optional.empty();
        }
    }

}
