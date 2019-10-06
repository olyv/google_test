package com.olyv.driver;

import com.google.common.collect.ImmutableMap;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testcontainers.containers.BrowserWebDriverContainer;
import org.testcontainers.containers.DefaultRecordingFileFactory;

import java.io.File;
import java.util.Map;
import java.util.function.Supplier;

import static com.olyv.driver.BrowserType.CHROME;
import static com.olyv.driver.BrowserType.FIREFOX;
import static org.testcontainers.containers.BrowserWebDriverContainer.VncRecordingMode.RECORD_FAILING;

public class ContainerFactory {

    private static final File RECORDING_DIR = new File("./target/");
    private Map<BrowserType, Supplier<BrowserWebDriverContainer>> containers = ImmutableMap.of(
            CHROME, this::chrome,
            FIREFOX, this::firefox
    );
    private final BrowserTypeResolver resolver = new BrowserTypeResolver();

    public BrowserWebDriverContainer getContainer() {
        var browserType = resolver.getType();
        var container = containers.get(browserType).get();
        return container.withRecordingMode(RECORD_FAILING, RECORDING_DIR)
                .withRecordingFileFactory(new DefaultRecordingFileFactory());
    }

    private BrowserWebDriverContainer chrome() {
        return new BrowserWebDriverContainer().withCapabilities(new ChromeOptions());

    }

    private BrowserWebDriverContainer firefox() {
        return new BrowserWebDriverContainer().withCapabilities(new FirefoxOptions());
    }
}
