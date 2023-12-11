package card;

public class Discovery extends CreditCard {

	public Discovery(String number, String expireDate, String holder) {
		super(number, expireDate, holder);
		this.cardType = "discover";
	}

	public static boolean is_match_pattern(String number) {
		return  (number.length() == 16) 
				&& number.substring(0, 4).equals("6011");
	}
}