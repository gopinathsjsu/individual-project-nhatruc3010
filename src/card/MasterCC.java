package card;

public class MasterCC extends CreditCard {

	public MasterCC(String number, String expireDate, String holder) {
		super(number, expireDate, holder);
		this.cardType = "master";
	}

	public static boolean is_match_pattern(String number) {
		// First digit is a 5, second digit is in range 1 through 5 inclusive. Only valid length of number is 16 digits.
		return  number.length() == 16
				&& number.charAt(0) == '5' 
				&& Integer.parseInt(number.substring(1, 2)) >=1 
				&& Integer.parseInt(number.substring(1, 2)) <=5;
	}
	
}