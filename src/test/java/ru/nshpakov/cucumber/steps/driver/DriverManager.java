package ru.nshpakov.cucumber.steps.driver;

import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public abstract class DriverManager {
    protected ThreadLocal<WebDriver> drivers = new ThreadLocal<>();

    protected abstract WebDriver createDriver();

    public void quitDriver() {
        if (null != drivers.get()) {
            try {
                drivers.get().close();
                drivers.remove();
            } catch (Exception e) {
                throw new RuntimeException("Не удалось выполнить quitDriver()", e);
            }

        }
    }

    public WebDriver getDriver() {
        if (null == drivers.get()) {
            drivers.set(this.createDriver());
        }
        drivers.get().manage().timeouts().implicitlyWait(1L, TimeUnit.SECONDS);
        return drivers.get();
    }
}