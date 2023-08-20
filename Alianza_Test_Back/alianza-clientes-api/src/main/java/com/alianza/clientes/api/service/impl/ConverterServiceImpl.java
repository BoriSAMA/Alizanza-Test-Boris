package com.alianza.clientes.api.service.impl;

import com.google.common.collect.Lists;
import com.alianza.clientes.api.service.ConverterService;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.DefaultConversionService;

import java.util.List;
import java.util.Set;

/**
 * Implementacion base del converter service, esta incluye tambien la conversion de tipos basicos java.
 */
public final class ConverterServiceImpl extends DefaultConversionService implements ConverterService, InitializingBean {

    @Autowired(required = false)
    private Set<Converter<?, ?>> converters;

    @Override
    public <S, T> T convertTo(S source, Class<T> targetClass) {
        return convert(source, targetClass);
    }

    @Override
    public <S, T> List<T> convertTo(List<S> source, Class<T> targetClass) {
        List<T> result = null;
        if (source != null) {
            result = Lists.newArrayList();
            for (S s : source) {
                result.add(convert(s, targetClass));
            }
        }
        return result;
    }

    @Override
    public <S, T> boolean canConvertTo(Class<S> sourceClass, Class<T> targetClass) {
        return canConvert(sourceClass, targetClass);
    }

    @Override
    public void afterPropertiesSet() {
        if (converters != null) {
            for (Converter<?, ?> converter : converters) {
                addConverter(converter);
            }
        }
    }

    public void setConverters(Set<Converter<?, ?>> converters) {
        this.converters = converters;
    }

}
