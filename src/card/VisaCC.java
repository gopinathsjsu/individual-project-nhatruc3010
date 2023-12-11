package card;


public class VisaCC extends CreditCard {
	
	public VisaCC(String number, String expireDate, String holder) {
		super(number, expireDate, holder);
		this.cardType = "visa";
	}
	
	public static boolean is_match_pattern(String number) {
		return  (number.length() == 13 || number.length() == 16)
				&& number.charAt(0) == '4';
	}
}