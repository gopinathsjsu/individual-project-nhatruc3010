package tests;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.junit.experimental.categories.Category;

import card.AmExCC;
import card.Discovery;
import card.MasterCC;
import card.VisaCC;

import java.util.*;
import io.CSVIO;
import io.DataIO;
import io.JsonIO;
import io.XMLIO;
import card.CreditCard;
import cardReader.CreditCardReader;

public class TestCreditCardReader {
	
	public static class CardReaderTest {
        @Test
        public void testreadJson() {
    		String inputName = "input_file-1+_281_29.json";
    		CreditCardReader reader = new CreditCardReader(inputName);
    		reader.readCards();
        }
    }
	
    public static class JsonIOTests {
        @Test
        public void testreadJson() {
            DataIO dataIO = new JsonIO();
    		String inputName = "input_file-1+_281_29.json";
    		String outputName = inputName.replace(".", "_output.");
    		List<CreditCard> cardList = dataIO.readInputs(inputName);
    		dataIO.writeOutputs(outputName, cardList);
        }
    }
    
    public static class XMLIOTests {
        @Test
        public void testreadJson() {
            DataIO dataIO = new XMLIO();
    		String inputName = "input_file.xml";
    		String outputName = inputName.replace(".", "_output.");
    		List<CreditCard> cardList = dataIO.readInputs(inputName);
    		dataIO.writeOutputs(outputName, cardList);
        }
    }
    
    public static class CSVIOTests {
        @Test
        public void testreadJson() {
            DataIO dataIO = new CSVIO();
    		String inputName = "input_file-1.csv";
    		String outputName = inputName.replace(".", "_output.");
    		List<CreditCard> cardList = dataIO.readInputs(inputName);
    		dataIO.writeOutputs(outputName, cardList);
        }
    }
	
	@Category(MasterCardTests.class)
    public static class MasterCreditCardTests {
        @Test
        public void testPositiveCreditCardNumberValidation() {
            for (Object[] testCase : MasterCardTests.testCases()) {
                String creditCardNumber = (String) testCase[0];
                boolean expectedResult = (boolean) testCase[1];
                assertEquals(expectedResult, MasterCC.is_match_pattern(creditCardNumber));
            }
        }
    }
	
	@Category(VisaCardTests.class)
    public static class VisaCreditCardTests {
        @Test
        public void testPositiveCreditCardNumberValidation() {
            for (Object[] testCase : VisaCardTests.testCases()) {
                String creditCardNumber = (String) testCase[0];
                boolean expectedResult = (boolean) testCase[1];
                assertEquals(expectedResult, VisaCC.is_match_pattern(creditCardNumber));
            }
        }
    }
	
	@Category(DiscoveryCardTests.class)
    public static class DiscoveryCreditCardTests {
        @Test
        public void testPositiveCreditCardNumberValidation() {
            for (Object[] testCase : DiscoveryCardTests.testCases()) {
                String creditCardNumber = (String) testCase[0];
                boolean expectedResult = (boolean) testCase[1];
                assertEquals(expectedResult, Discovery.is_match_pattern(creditCardNumber));
            }
        }
    }
	
	@Category(AmExCardTests.class)
    public static class AmExCreditCardTests {
        @Test
        public void testPositiveCreditCardNumberValidation() {
            for (Object[] testCase : AmExCardTests.testCases()) {
                String creditCardNumber = (String) testCase[0];
                boolean expectedResult = (boolean) testCase[1];
                assertEquals(expectedResult, AmExCC.is_match_pattern(creditCardNumber));
            }
        }
    }



    public interface MasterCardTests {
        static Collection<Object[]> testCases() {
            return Arrays.asList(new Object[][]{
                    {"5111111111111111", true},         
                    {"5211111678111111", true},
                    {"5211123111111111", true},
                    {"5311111111111111", true},         
                    {"5411111111111111", true},         
                    {"5511111111111111", true},         
                    {"5611111111111111", false},         
                    {"51111111111111111", false},         
                    {"511111111111111", false},         
                    {"51111111111111111", false},         
                    {"5111111111111", false},
                    {"123", false},
            });
        }
    }
    
    public interface VisaCardTests {
        static Collection<Object[]> testCases() {
            return Arrays.asList(new Object[][]{
                    {"4111111111111", true},         
                    {"4111111111111111", true},         
                    {"531111111111111", false},         
                    {"54111111111111", false},         
                    {"5511111111111111", false},         
                    {"561111111111111", false},         
                    {"5111111111111111", false},         
                    {"5111111111111", false},         
                    {"51111111111111111", false},         
                    {"5111111111111", false},         
                    {"123", false},
            });
        }
    }
    
    public interface DiscoveryCardTests {
        static Collection<Object[]> testCases() {
            return Arrays.asList(new Object[][]{
                    {"6011111111111111", true},         
                    {"60111111111111111", false},         
                    {"60111111111111", false},         
                    {"5311111111111111", false},         
                    {"54111111111111", false},         
                    {"5511111111111111", false},         
                    {"5611111111111111", false},         
                    {"51111111111111", false},         
                    {"511111111111111", false},         
                    {"51111111111111111", false},         
                    {"111", false},         
            });
        }
    }
    
    public interface AmExCardTests {
        static Collection<Object[]> testCases() {
            return Arrays.asList(new Object[][]{
                    {"341111111111111", true},
                    {"371111111111111", true},
                    {"5211111111111111", false},         
                    {"5311111111111111", false},         
                    {"54111111111111", false},         
                    {"5511111111111111", false},         
                    {"56111111111111", false},         
                    {"51111111111111111", false},         
                    {"511111111111111", false},         
                    {"51111111111111111", false},         
                    {"5111111111111", false},         
                    {"111", false},         
            });
        }
    }


}
