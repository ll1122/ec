/**
 * Copyright (c) 2012-2024 https://www.eryansky.com
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.eryansky.common.utils.io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

/**
 * Properties文件载入工具类. 可载入多个properties文件, 相同的属性在最后载入的文件中的值将会覆盖之前的值.
 * <p/>
 * 本类有两种使用方法:
 * 1.
 *
 * @author Eryan
 */
public class PropertiesLoader {

    /**
     * 静态内部类，延迟加载，懒汉式，线程安全的单例模式
     */
    private static final class Static {
        private static final ResourceLoader resourceLoader = new DefaultResourceLoader();
    }


    private static final Logger logger = LoggerFactory.getLogger(PropertiesLoader.class);

    private final Properties properties;
    private final String[] resourcesPaths;

    public PropertiesLoader(String... resourcesPaths) {
        properties = loadProperties(resourcesPaths);
        this.resourcesPaths = resourcesPaths;
    }

    public Properties getProperties() {
        return properties;
    }

    public String[] getResourcesPaths() {
        return resourcesPaths;
    }

    /**
     * 取出Property，但以System的Property优先.
     */
    private String getValue(String key) {
        String systemProperty = System.getProperty(key);
        if (systemProperty != null) {
            return systemProperty;
        }
        return properties.getProperty(key);
    }

    /**
     * 取出String类型的Property，但以System的Property优先,如果都為Null则抛出异常.
     */
    public String getProperty(String key) {
        String value = getValue(key);
        if (value == null) {
            throw new NoSuchElementException();
        }
        return value;
    }

    /**
     * 取出String类型的Property，但以System的Property优先.如果都為Null則返回Default值.
     */
    public String getProperty(String key, String defaultValue) {
        String value = getValue(key);
        return value != null ? value : defaultValue;
    }

    /**
     * 取出Integer类型的Property，但以System的Property优先.如果都為Null或内容错误则抛出异常.
     */
    public Integer getInteger(String key) {
        String value = getValue(key);
        if (value == null) {
            throw new NoSuchElementException();
        }
        return Integer.valueOf(value);
    }

    /**
     * 取出Integer类型的Property，但以System的Property优先.如果都為Null則返回Default值，如果内容错误则抛出异常
     */
    public Integer getInteger(String key, Integer defaultValue) {
        String value = getValue(key);
        return value != null ? Integer.valueOf(value) : defaultValue;
    }

    /**
     * 取出Double类型的Property，但以System的Property优先.如果都為Null或内容错误则抛出异常.
     */
    public Double getDouble(String key) {
        String value = getValue(key);
        if (value == null) {
            throw new NoSuchElementException();
        }
        return Double.valueOf(value);
    }

    /**
     * 取出Double类型的Property，但以System的Property优先.如果都為Null則返回Default值，如果内容错误则抛出异常
     */
    public Double getDouble(String key, Integer defaultValue) {
        String value = getValue(key);
        return value != null ? Double.parseDouble(value) : defaultValue;
    }

    /**
     * 取出Boolean类型的Property，但以System的Property优先.如果都為Null抛出异常,如果内容不是true/false则返回false.
     */
    public Boolean getBoolean(String key) {
        String value = getValue(key);
        if (value == null) {
            throw new NoSuchElementException();
        }
        return Boolean.valueOf(value);
    }

    /**
     * 取出Boolean类型的Property，但以System的Property优先.如果都為Null則返回Default值,如果内容不为true/false则返回false.
     */
    public Boolean getBoolean(String key, boolean defaultValue) {
        String value = getValue(key);
        return value != null ? Boolean.parseBoolean(value) : defaultValue;
    }

    /**
     * 写入properties信息
     *
     * @param resourcesPath 配置文件路径
     * @param key           名称
     * @param value         值
     */
    public void modifyProperties(String resourcesPath, String key, String value) {
        try (FileOutputStream outputFile = new FileOutputStream(resourcesPath)) {
            // 从输入流中读取属性列表（键和元素对）
            properties.setProperty(key, value);
            properties.store(outputFile, "modify");
            outputFile.flush();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * 载入多个properties文件, 相同的属性在最后载入的文件中的值将会覆盖之前的载入.
     * 文件路径使用Spring Resource格式, 文件编码使用UTF-8.
     *
     * @see org.springframework.beans.factory.config.PropertyPlaceholderConfigurer
     */
    private Properties loadProperties(String... resourcesPaths) {
        Properties props = new Properties();
        for (String location : resourcesPaths) {
            logger.debug("Loading properties file from:" + location);
            Resource resource = Static.resourceLoader.getResource(location);
            try (InputStream inputStream = resource.getInputStream()) {
                props.load(inputStream);
            } catch (IOException ex) {
                logger.info("Could not load properties from path:" + location + ", " + ex.getMessage());
            }
        }
        return props;
    }
}