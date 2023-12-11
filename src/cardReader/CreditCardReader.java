package cardReader;
import java.util.*;

import card.CreditCard;
import io.CSVIO;
import io.DataIO;
import io.JsonIO;
import io.XMLIO;


public class CreditCardReader {
	String inputName;
	String outputName;
	DataIO dataIO = null;
	List<CreditCard> cardList;
	
	public CreditCardReader(String inputName) {
		this.inputName = inputName;
		
		if(inputName.endsWith("xml")) {
			dataIO = new XMLIO();	
		}else if(inputName.endsWith("csv")) {
			dataIO = new CSVIO();
		}else if(inputName.endsWith("json")) {
			dataIO = new JsonIO();
		}else {
			System.err.print("Given format of input does not be supported.");
		}
		
		outputName = inputName.replace(".", "_output.");
	}
	
	public void readCards() {
		cardList = dataIO.readInputs(inputName);
		dataIO.writeOutputs(outputName, cardList);
		System.out.println(cardList);
	}
	
	@SuppressWarnings("null")
	public static void main(String[] args) {
		String inputName = "input_file.xml";
		CreditCardReader reader = new CreditCardReader(inputName);
		reader.readCards();
	}
}
