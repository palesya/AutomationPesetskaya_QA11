package PageObject.RabotaBy.FooterPage;

import PageObject.BasePage;
import org.openqa.selenium.By;
import org.testng.Assert;

public class FooterElement extends BasePage {

   private By totalFooter= By.xpath("//*[@class='supernova-footer HH-Supernova-Footer']");

   public FooterElement checkFooterElement(){
      Assert.assertTrue(isElementExists(totalFooter));
      return this;
   }
}
