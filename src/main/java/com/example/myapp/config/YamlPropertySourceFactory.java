package com.example.myapp.config;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * @author rmartynov
 * @since 14.01.2025
 */
public class YamlPropertySourceFactory implements PropertySourceFactory {

    @Override
    public PropertySource<?> createPropertySource(String name, EncodedResource resource) throws IOException {
        if (resource.getResource().exists()) {
            Properties propertiesFromYaml = loadYamlIntoProperties(resource.getResource());
            String sourceName = name != null ? name : resource.getResource().getFilename();
            return new PropertiesPropertySource(sourceName, propertiesFromYaml);
        }
        throw new FileNotFoundException();
    }

    private static Properties loadYamlIntoProperties(Resource resource) throws IOException {
        YamlPropertiesFactoryBean factory = new YamlPropertiesFactoryBean();
        factory.setResources(resource);
        factory.afterPropertiesSet();
        return factory.getObject();
    }
}
