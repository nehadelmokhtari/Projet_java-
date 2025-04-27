package com.epf.Config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import org.springframework.lang.NonNull;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { WebConfig.class,
        com.epf.Persistance.Config.ConfigBDD.class };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }
    @Override
    @NonNull
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
}