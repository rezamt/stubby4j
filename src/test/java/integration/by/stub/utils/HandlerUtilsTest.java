package integration.by.stub.utils;

import by.stub.exception.Stubby4JException;
import by.stub.testing.junit.categories.IntegrationTest;
import by.stub.utils.HandlerUtils;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

/**
 * @author Alexander Zagniotov
 * @since 6/27/12, 10:26 AM
 */
@Category(IntegrationTest.class)
public class HandlerUtilsTest {

   @Test
   public void shouldGetHtmlResourceByName() throws Exception {
      final String templateContent = HandlerUtils.getHtmlResourceByName("test-template");
      Assert.assertEquals("<html><head></head><body>%s</body></html>", templateContent);
   }

   @Test
   public void shouldPopulateHtmlTemplate() throws Exception {
      final String templateContent = HandlerUtils.populateHtmlTemplate("test-template", "alex");
      Assert.assertEquals("<html><head></head><body>alex</body></html>", templateContent);
   }

   @Test(expected = Stubby4JException.class)
   public void shouldNotPopulateNonExistentHtmlTemplate() throws Exception {
      HandlerUtils.populateHtmlTemplate("non-existent-template", "alex");
   }
}
