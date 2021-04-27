package com.test.app;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class utilidades {
    private static JavascriptExecutor js;
    public static String pageLoad;

    public static boolean highLight(WebElement webElement,WebDriver myDrive){
        js = (JavascriptExecutor) myDrive;
        for (int i =0;i<3;i++){
            try {
                js.executeScript("arguments[0].style.backgroundColor = 'RED'",webElement);
                Thread.sleep(2000);
                js.executeScript("arguments[0].setAttribute('style','background:')",webElement);


            }catch (Exception e){
                System.err.println("JSclass | highLight | error: "+ e.getMessage());
                return false;
            }
        }return true;
    }

    public static boolean scrollDown(WebDriver myDrive){

        try{
            js = (JavascriptExecutor)myDrive;
            js.executeScript("window.scrollBy(0,250)");
        }catch (Exception e){
            System.err.println("JSclass | scroll | error: "+ e.getMessage());
            return false;
        }return true;
    }

    public static boolean scrollUp(WebDriver myDrive){

        try{
            js = (JavascriptExecutor)myDrive;
            js.executeScript("window.scrollBy(0,-250)");
        }catch (Exception e){
            System.err.println("JSclass | scroll | error: "+ e.getMessage());
            return false;
        }return true;
    }

    public static boolean waitForPageToLoad(WebDriver myDrive){
        try {
            do {
                js = (JavascriptExecutor) myDrive;
                pageLoad = (String) js.executeScript("return document.readyState");
            }while (!pageLoad.equals("complete"));


        }catch (Exception e){
            System.err.println("JSclass | waitForPageToLoad | error: "+ e.getMessage());
            return false;

        }return true;
    }


}
