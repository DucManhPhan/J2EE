package com.manhpd.dto;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LstParameter {

    private String PARAM_ID;

    private String SYSTEM_ID;

    private String APP_CODE;

    private String PARAM_NAME;

    private String PARAM_VALUE;

    private String PARAM_MEANING;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
