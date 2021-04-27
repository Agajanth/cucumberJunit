package com.test.app;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

import static com.test.app.utilidades.*;

public class toolTipTest {

    WebDriver myDrive;
    Point location;
    String baseUrl = "https://demoqa.com/";
    String expectedTitle = "ToolsQA";
    String titulo;
    Actions action;

    String describe;


    @Given("entramos  a la pagina de demoQA")
    public void paginaPrincipal(){
        System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"/drivers/chromedriver.exe");
        myDrive = new ChromeDriver();
        myDrive.manage().window().maximize();
        myDrive.get(baseUrl);
        titulo = myDrive.getTitle();
        Assert.assertTrue(waitForPageToLoad(myDrive));
        Assert.assertEquals(titulo,expectedTitle);

    }

    @When("vamos a la parte de widgets")
    public void widgets() throws InterruptedException {
        scrollDown(myDrive);
        WebElement widget=   myDrive.findElement(By.xpath("/html/body/div/div/div/div[2]/div/div[4]"));
        highLight(widget,myDrive);
        widget.click();
        Assert.assertTrue(waitForPageToLoad(myDrive));
        scrollDown(myDrive);
        scrollDown(myDrive);
        List<WebElement> listElements    = myDrive.findElements(By.xpath("//*[@id=\"app\"]/div/div/div[2]/div[1]/div/div/div[4]/div/ul/li"));
        for (WebElement element : listElements) {
            if (element.getAttribute("id").equals("item-6")){


                element.click();
            }
        }


    }


//*[@id="app"]/div/div/div[2]/div[1]/div/div/div[4]/div/ul




    @Then("Ponemos encima al mouse y verificamos la propiedad")
    public void interaccionFinal() throws InterruptedException {

        scrollUp(myDrive);
        scrollUp(myDrive);

        WebElement button_over  = myDrive.findElement(By.id("toolTipButton"));
        highLight(button_over,myDrive);
        location = button_over.getLocation();
        action = new Actions(myDrive);
        System.out.println(location.getX());
        System.out.println(location.getY());
        action.moveToElement(button_over).perform();

        Thread.sleep(2000);
        WebElement descr = myDrive.findElement(By.xpath("//*[@id=\"buttonToolTip\"]/div[2]"));

        describe = button_over.getAttribute("title");
        System.out.println(describe);

        describe = descr.getText();
        Assert.assertEquals(describe,"You hovered over the Button");
        System.out.println(describe);
        myDrive.quit();

    }
}
