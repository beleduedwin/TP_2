import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class selenium_test {
    //j'ai tjrs besoin d'1 driver dans selenium...
    WebDriver driver;//insertion de drive de chrome

    @Before//s'execute avant toutes les methode qui commence par @test
    public void setup()
    {
        //driver = new ChromeDriver();//instance de l'objet drive
        driver = new FirefoxDriver();//instance de l'objet drive

        //dans ce cas on peux enlever le sleep, se defini au debbut; cette instruction ne parche qu'avec le findElement
        //NB : est utile quand la connexion met du temps à afficher
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);//si l'objet est présent mais pas visible ca ne marche pas
        driver.get("http:www.google.com");//Ouvrir une page
    }

    @After//s'execute à la fin de chaque methode qui comprend @test
    public void quitBowser()
    {
        //ce code sert de quitter la fenetre chrome ouverte
        driver.quit();
    }

    @Test
    public void testEnter() throws InterruptedException {
        //je compare cette chaine de caractère avec ce qui doit s'afficher sur l'ecran
        //String excepted = "Edwin Beledu - Stage Développeur Web Fullstack - AutoWebbb ...";
        String excepted = "République française - France — Wikipédia";
        WebElement barreRecherche = driver.findElement(By.id("lst-ib"));
        //barreRecherche.sendKeys( "edwin beledu");//envoyer la clé
        barreRecherche.sendKeys( "france");//envoyer la clé
        barreRecherche.sendKeys(Keys.ENTER);//tape sur enter

       WebElement premierResultat =  driver.findElement(By.cssSelector(".rc>.r>a"));//


        //le path de mon objet
        //WebElement premierResultat =  driver.findElement(By.xpath("//*[@id=\"rso\"]/div[1]/div/div[1]/div/div/h3/a"));
        Assert.assertEquals(excepted, premierResultat.getText());

        Thread.sleep(1000);//Garder la page une segonde le temps que le resultat s'affiche
        //l'objet xpath
        //*[@id="rso"]/div[2]/div/div/div/div/h3/a
        //pour lancer la recherche
       // barreRecherche.submit();//ne marche pas tout le temps
    }

    @Test
    public void testClic()throws InterruptedException
    {
        //via la classe WebElement, on crée une variable et met id à rechercher
        WebElement barreRecherche = driver.findElement(By.id("lst-ib"));
        barreRecherche.sendKeys( "edwin");
       // barreRecherche.sendKeys(Keys.ENTER);

        //Thread.sleep(1000);//Garder la page une segonde

        WebElement buttomRecherche = driver.findElement(By.className("lsb"));
        buttomRecherche.click();
        //pour lancer la recherche
        // barreRecherche.submit();//ne marche pas tout le temps


    }
}
