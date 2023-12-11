package card;

public class AmExCC extends CreditCard {

	public AmExCC(String number, String expireDate, String holder) {
		super(number, expireDate, holder);
		this.cardType = "amex";
	}

	public static boolean is_match_pattern(String number) {
		return  (number.length() == 15)
				&& number.charAt(0) == '3' 
				&& (number.charAt(1) == '4' || number.charAt(1) == '7');
	}
}