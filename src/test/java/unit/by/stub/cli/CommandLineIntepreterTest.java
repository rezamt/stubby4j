package unit.by.stub.cli;

import by.stub.cli.CommandLineIntepreter;
import by.stub.testing.junit.categories.UnitTest;
import junit.framework.Assert;
import org.apache.commons.cli.MissingArgumentException;
import org.apache.commons.cli.ParseException;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.Map;

/**
 * @author Alexander Zagniotov
 * @since 6/24/12, 2:32 AM
 */
@Category(UnitTest.class)
public class CommandLineIntepreterTest {

   @Test
   public void shouldBeTrueWhenYamlIsProvided() throws Exception {
      CommandLineIntepreter.parseCommandLine(new String[]{"--data", "somefilename.yaml"});
      final boolean isYamlProvided = CommandLineIntepreter.isYamlProvided();
      Assert.assertEquals(true, isYamlProvided);
   }

   @Test
   public void shouldBeFalseThatYamlIsNotProvided() throws Exception {
      CommandLineIntepreter.parseCommandLine(new String[]{"alex", "zagniotov"});
      final boolean isYamlProvided = CommandLineIntepreter.isYamlProvided();
      Assert.assertEquals(false, isYamlProvided);
   }

   @Test(expected = ParseException.class)
   public void shouldFailOnInvalidCommandlineLongOptionString() throws Exception {
      CommandLineIntepreter.parseCommandLine(new String[]{"--alex"});
   }

   @Test(expected = ParseException.class)
   public void shouldFailOnInvalidCommandlineShortOptionString() throws Exception {
      CommandLineIntepreter.parseCommandLine(new String[]{"-z"});
   }

   @Test(expected = MissingArgumentException.class)
   public void shouldFailOnMissingArgumentForExistingShortOption() throws Exception {
      CommandLineIntepreter.parseCommandLine(new String[]{"-a"});
   }

   @Test(expected = MissingArgumentException.class)
   public void shouldFailOnMissingArgumentForExistingLongOption() throws Exception {
      CommandLineIntepreter.parseCommandLine(new String[]{"--data"});
   }

   @Test
   public void testIsHelpWhenLongOptionGiven() throws Exception {
      CommandLineIntepreter.parseCommandLine(new String[]{"--help"});
      final boolean isHelp = CommandLineIntepreter.isHelp();
      Assert.assertEquals(true, isHelp);
   }

   @Test
   public void shouldReturnEmptyCommandlineParamsWhenHelpPresent() throws Exception {
      CommandLineIntepreter.parseCommandLine(new String[]{"--help"});
      final Map<String, String> params = CommandLineIntepreter.getCommandlineParams();
      Assert.assertEquals(0, params.size());
   }

   @Test
   public void shouldReturnEmptyCommandlineParams() throws Exception {
      CommandLineIntepreter.parseCommandLine(new String[]{});
      final Map<String, String> params = CommandLineIntepreter.getCommandlineParams();
      Assert.assertEquals(0, params.size());
   }

   @Test
   public void shouldReturnCommandlineParams() throws Exception {
      CommandLineIntepreter.parseCommandLine(new String[]{"--data", "somefilename.yaml", "-s", "12345", "--admin", "567"});
      final Map<String, String> params = CommandLineIntepreter.getCommandlineParams();
      Assert.assertEquals(3, params.size());
   }

   @Test
   public void shouldGetCurrentJarLocation() throws Exception {
      final String currentJarLocation = CommandLineIntepreter.getCurrentJarLocation(CommandLineIntepreter.class);
      Assert.assertEquals("stubby4j-x.x.x-SNAPSHOT.jar", currentJarLocation);
   }
}
