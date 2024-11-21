package design.pattern.factory;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class BrowserCapabilitiesKey {

    private static final Logger logger = Logger.getLogger(BrowserCapabilitiesKey.class.getName());

    private final BrowserType browserType;
    private final String capabilityKey;

    public BrowserCapabilitiesKey(BrowserType browserType, Capabilities capabilities){
        this.browserType = browserType;
        this.capabilityKey = serializeCapabilities(browserType,capabilities);
    }

    private String serializeCapabilities(BrowserType browserType, Capabilities capabilities) {
        List<String> browserArgsUnmodified = new ArrayList<>();
        List<String> browserArgs;
        String tempCapabilityKey = null;

        logger.info("Got BrowserType " + browserType + ", and Capabilities " + capabilities + " to create a new capability key");

        logger.info("Getting the capability object for relevant browser");
        Object options = getCapabilitiesOptions(browserType, capabilities);

        logger.info("Getting the list of arguments from the options object");
        //Options is an object, to get the list of arguments we should verify if we got the Map object and then verify args are List object
        if(options instanceof Map<?, ?> browserOptionMap){
            Object myList = browserOptionMap.get("args");
            if(myList instanceof List<?> tempList){
                if(!tempList.isEmpty() && tempList.getFirst() instanceof String) browserArgsUnmodified = (List<String>) tempList;
               logger.info("Got the Browser Options Argument List ->  " + browserArgsUnmodified);
            }else{
                logger.warning("Args is not a list");
            }
        }
        try {
            tempCapabilityKey = new ObjectMapper().writeValueAsString(browserArgsUnmodified.stream().sorted().collect(Collectors.toList()));
        } catch (JacksonException e){
            // Logging exception using a logger instead of printStackTrace
            logger.log(Level.WARNING, "Error serializing capabilities: " + e.getMessage(), e);
            tempCapabilityKey = null;
        }

        logger.info("Created a capability key" + tempCapabilityKey);

        return tempCapabilityKey;

    }

    private Object getCapabilitiesOptions(BrowserType browserType, Capabilities capabilities){

        //Using a switch case to add the capabilities to an option object for relevant browser. A valid capability will return a Map.
        logger.info("Getting the capability object for relevant browser");
        return switch (browserType) {
            case CHROME ->
                    capabilities.getCapability("goog:chromeOptions");
            case FIREFOX ->
                    capabilities.getCapability("moz:firefoxOptions");
            default ->
                    throw new IllegalArgumentException("Wrong browserType was provided : " + browserType);
        };
    }

    // Overriding equals() and hashCode() to ensure that two BrowserCapabilitiesKey instances
    // with the same browserType and capabilityKey are considered equal.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Check if same object reference
        if (o == null || getClass() != o.getClass()) return false;
        BrowserCapabilitiesKey that = (BrowserCapabilitiesKey) o;
        return browserType == that.browserType && Objects.equals(capabilityKey, that.capabilityKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(browserType, capabilityKey); // Hash based on both fields
    }

}
