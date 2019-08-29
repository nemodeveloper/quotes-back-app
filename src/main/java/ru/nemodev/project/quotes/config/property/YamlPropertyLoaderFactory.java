package ru.nemodev.project.quotes.config.property;

import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.DefaultPropertySourceFactory;
import org.springframework.core.io.support.EncodedResource;

import java.io.IOException;

/**
 * created by simanov-an on 2019-08-28
 */
public class YamlPropertyLoaderFactory extends DefaultPropertySourceFactory
{
    @Override
    public PropertySource<?> createPropertySource(String name, EncodedResource resource) throws IOException
    {
        return new YamlPropertySourceLoader().load(resource.getResource().getFilename(), resource.getResource()).get(0);
    }
}