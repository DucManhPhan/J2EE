package com.manhpd.staxXml.cursorApi;

import com.manhpd.dto.LstParameter;
import com.manhpd.utils.Constant;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.Characters;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class StartElementSRStage extends ProcessElementStaxCursor {

    private static final Logger logger = LogManager.getLogger(StartElementSRStage.class);

    private String[] childElements = new String[]{
            Constant.PARAM_ID_FIELD,
            Constant.SYSTEM_ID_FIELD,
            Constant.APP_CODE_FIELD,
            Constant.PARAM_NAME_FIELD,
            Constant.PARAM_VALUE_FIELD,
            Constant.PARAM_MEANING_FIELD
    };

    public StartElementSRStage(XMLStreamReader streamReader,
                               LstParameter lstParameter) {
        super(streamReader, lstParameter);
    }

    @Override
    public LstParameter process() {
        String elementName = this.streamReader.getLocalName();
        Optional.ofNullable(elementName)
                .filter(Constant.LST_PARAMETER_NODE::equalsIgnoreCase)
                .ifPresent(name -> this.lstParameter = new LstParameter());

        this.doAttributes();
        this.doChildElements(elementName);

        return this.lstParameter;
    }

    private void doAttributes() {
        Predicate<XMLStreamReader> hasAttributes = streamReader -> streamReader.getAttributeCount() > 0;
        Optional.of(this.streamReader)
                .filter(hasAttributes)
                .ifPresent(streamReader -> {
                    // do something with its attributes
                    System.out.println(streamReader.getAttributeName(0));
                });
    }

    public void doChildElements(String elementName) {
        Predicate<String> hasExistField = fieldName -> Stream.of(this.childElements)
                .anyMatch(name -> name.equalsIgnoreCase(fieldName));

        Optional.ofNullable(elementName)
                .filter(hasExistField)
                .ifPresent(name -> {
                    try {
                        BeanUtils.setProperty(this.lstParameter, name, this.streamReader.getElementText());
                    } catch (Exception e) {
                        e.printStackTrace();
                        logger.error(e);
                    }
                });
    }
}
