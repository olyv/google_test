package com.olyv.driver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nullable;

class BrowserTypeResolver {

    private final Logger log = LoggerFactory.getLogger(BrowserTypeResolver.class);

    BrowserType getType() {
        var property = getProperty();
        BrowserType browserType;
        if(property == null) {
            browserType = BrowserType.CHROME;
            log.warn("Defaulting browser to Chrome");
        } else {
            browserType = BrowserType.valueOf(property);
        }
        return browserType;
    }

    @Nullable
    private String getProperty() {
        var property = System.getProperty("browser.name");
        log.info("${browser.name} property was read as {}", property);
        return property;
    }
}
