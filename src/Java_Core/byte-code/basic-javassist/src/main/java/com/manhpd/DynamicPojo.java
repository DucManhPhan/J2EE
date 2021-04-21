package com.manhpd;

import com.manhpd.exception.ExistedClassException;
import com.manhpd.service.GenMethodParams;
import com.manhpd.service.GeneratingMethods;
import com.manhpd.service.MethodType;
import com.manhpd.utils.ValidationUtils;
import javassist.*;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;

public class DynamicPojo {

    /**
     *
     * @param className
     * @param properties
     * @return
     * @throws NotFoundException
     * @throws CannotCompileException
     */
    public static Class<?> generate(String className, Map<String, Class<?>> properties)
                                                throws NotFoundException, CannotCompileException {
        CtClass cc = DynamicPojo.initalizeDynamicClass(className);

        for (Map.Entry<String, Class<?>> entry : properties.entrySet()) {
            boolean isClazzExisted = ValidationUtils.hasClassExist(className);
            if (!isClazzExisted) {
                DynamicPojo.generate(entry.getValue().getName(), properties);
            }

            GenMethodParams methodParams = GenMethodParams.builder()
                                                          .declaringClass(cc)
                                                          .fieldClass(entry.getValue())
                                                          .fieldName(entry.getKey())
                                                          .build();
            cc.addField(new CtField(resolveCtClass(entry.getValue()), entry.getKey(), cc));
            cc.addMethod(GeneratingMethods.generate(MethodType.GETTER_METHOD, methodParams));
            cc.addMethod(GeneratingMethods.generate(MethodType.SETTER_METHOD, methodParams));
        }

        return cc.toClass();
    }

    /**
     * Based on class name, initialize class file
     *
     * @param className
     * @return
     * @throws NotFoundException
     */
    private static CtClass initalizeDynamicClass(String className) throws NotFoundException {
        ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.getOrNull(className);
        if (Objects.isNull(cc)) {
            throw new ExistedClassException(cc.getName() + " already existed!");
        }

        cc = pool.makeClass(className);
        cc.addInterface(resolveCtClass(Serializable.class));

        return cc;
    }

    /**
     * get CtClass object based on Class type in ClassPool of javassist
     *
     * @param clazz
     * @return
     * @throws NotFoundException
     */
    private static CtClass resolveCtClass(Class<?> clazz) throws NotFoundException {
        ClassPool pool = ClassPool.getDefault();
        return pool.get(clazz.getName());
    }

}
