package com.manhpd.utils;

import com.manhpd.dto.LstParameter;
import org.w3c.dom.Element;

import java.util.Optional;

public class ConverterUtils {

    public static LstParameter toLstParameter(Element domElement) {
        return Optional.of(domElement)
                       .map(element -> {
                            LstParameter lstParameter = new LstParameter();
                            lstParameter.setPARAM_ID(element.getElementsByTagName(Constant.PARAM_ID_FIELD)
                                    .item(0).getTextContent());
                            lstParameter.setSYSTEM_ID(element.getElementsByTagName(Constant.SYSTEM_ID_FIELD)
                                    .item(0).getTextContent());
                            lstParameter.setAPP_CODE(element.getElementsByTagName(Constant.APP_CODE_FIELD)
                                    .item(0).getTextContent());
                            lstParameter.setPARAM_NAME(element.getElementsByTagName(Constant.PARAM_NAME_FIELD)
                                    .item(0).getTextContent());
                            lstParameter.setPARAM_VALUE(element.getElementsByTagName(Constant.PARAM_VALUE_FIELD)
                                    .item(0).getTextContent());
                            lstParameter.setPARAM_MEANING(element.getElementsByTagName(Constant.PARAM_MEANING_FIELD)
                                    .item(0).getTextContent());

                            return lstParameter;
                       })
                       .orElseThrow(() -> new RuntimeException());
    }

}
