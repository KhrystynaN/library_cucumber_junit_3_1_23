package com.library.utilities;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BrowserUtils {

    //this method will accept int (int seconds)
    //and execute Thread.sleep method foe given duration
    public static void sleep(int seconds){
        seconds *=1000;
        try{
            Thread.sleep(seconds);
        }catch (InterruptedException e){

        }

    }

    public static void switchWindowAndVerify( String expectedInUrl, String expectedInTitle ){

        Set<String> allWindowsHandles = Driver.getDriver().getWindowHandles();

        for (String eachWindowHandle : allWindowsHandles) {
            Driver.getDriver().switchTo().window(eachWindowHandle);
            System.out.println("Current URL: "+ Driver.getDriver().getCurrentUrl());

            if(Driver.getDriver().getCurrentUrl().contains(expectedInUrl)){
                break;
            }
        }

        String actualInTitle = Driver.getDriver().getTitle();

        Assert.assertTrue(actualInTitle.contains(expectedInTitle));

    }

    public static void verifyTitle( String expectedTitle){
        Assert.assertEquals(Driver.getDriver().getTitle(),expectedTitle);
    }

    public static void verifyTitleContains( String expectedInTitle){
        Assert.assertTrue(Driver.getDriver().getTitle().contains(expectedInTitle));
    }

    //This method accepts WebElement target,
    //and waits for that WebElement not to be displayed on the page
    public static void waitForInvisibilityOf(WebElement target){
        //Create the object of 'WebDriverWait' class, and set up the constructor args
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));

        //use 'wait' object with the proper syntax to create explicit wait conditions
        wait.until(ExpectedConditions.invisibilityOf(target));
    }

    //This method accepts String title,
    //and waits for that Title contains given String value
    public static void waitForTitleContains(String title){
        //Create the object of 'WebDriverWait' class, and set up the constructor args
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));

        //use 'wait' object with the proper syntax to create explicit wait conditions
        wait.until(ExpectedConditions.titleContains(title));
    }

//this method accepts a dropdown elements and returns a List<String> that contains all options value as String
    public static List<String> dropdownOptions_as_String(WebElement dropdownElement){
        Select month = new Select(dropdownElement);
        //Storing all of the actual options into a List of WebElements
        List<WebElement> actualMonth_as_WebElement = month.getOptions();
        //Creating an EMPTY list of String to store ACTUAL <option> as String
        List<String> actualMonth_as_String = new ArrayList<>();
        //Looping through the List<WebElement>,getting all the options' text, and storing them into List<String>
        for (WebElement each : actualMonth_as_WebElement) {
            actualMonth_as_String.add(each.getText());
        }
        return actualMonth_as_String;
    }

    public static void clickRadioButton(List<WebElement> radioButtons, String attributeValue){
        for (WebElement each : radioButtons) {
            if (each.getAttribute("value").equalsIgnoreCase(attributeValue)){
                each.click();
            }
        }
    }


}
