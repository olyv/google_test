package com.olyv.pages;

import org.openqa.selenium.WebDriver;

abstract class AbstractPageObject {

    WebDriver driver;

    AbstractPageObject(WebDriver driver) {
        this.driver = driver;
    }
}
