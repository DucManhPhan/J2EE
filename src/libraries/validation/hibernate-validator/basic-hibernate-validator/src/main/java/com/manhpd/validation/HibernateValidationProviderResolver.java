package com.manhpd.validation;

import org.hibernate.validator.HibernateValidator;

import javax.validation.ValidationProviderResolver;
import java.util.Collections;
import java.util.List;

public class HibernateValidationProviderResolver implements ValidationProviderResolver {

    @Override
    public List getValidationProviders() {
        return Collections.singletonList(new HibernateValidator());
    }

}
