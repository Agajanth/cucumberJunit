package com.test.app;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.test.app.utilidades.*;
import static org.junit.Assert.assertEquals;


public class AppTest{
    WebDriver myDrive;
    String baseUrl = "https://demoqa.com/";
    String expectedTitle = "ToolsQA";
    String titulo;





    @Given("La página principal demoqa, y el titulo es ToolsQA")
    public void principal_page(){

        System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"/drivers/chromedriver.exe");
        myDrive = new ChromeDriver();
        myDrive.manage().window().maximize();
        myDrive.get(baseUrl);
        titulo = myDrive.getTitle();
        assertEquals(titulo,expectedTitle);
        scrollDown(myDrive);
        Assert.assertTrue(waitForPageToLoad(myDrive));
    }

    @When("verificamos titulo, click en button")
    public void get_title(){


        WebElement link = myDrive.findElement(By.xpath("//*[@id=\"app\"]/div/div/div[2]/div/div[3]"));
        highLight(link,myDrive);
        link.click();
        scrollDown(myDrive);

        List<WebElement> listElements    = Collections.singletonList(myDrive.findElement(By.xpath("//*[@id=\"app\"]/div/div/div[2]/div[1]/div/div/div[3]/div/ul/li")));
        for (WebElement element:listElements) {
            if (element.getAttribute("id").equals("item-0")){
                element.click();
            }
        }

    }

    @Then("click en home y verificar new tab")
    public void assertar(){
        myDrive.findElement(By.id("tabButton")).click();
        ArrayList<String> tabs2 = new ArrayList<>(myDrive.getWindowHandles());
        myDrive.switchTo().window(tabs2.get(1));

        WebElement resultado = myDrive.findElement(By.id("sampleHeading"));
        String esperado = resultado.getText();

        assertEquals(esperado,"This is a sample page");
        myDrive.quit();
    }


}
