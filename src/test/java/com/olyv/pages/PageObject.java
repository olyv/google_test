package com.olyv.pages;

import org.openqa.selenium.WebDriver;

abstract class PageObject {

    WebDriver driver;

    PageObject(WebDriver driver) {
        this.driver = driver;
    }
}
