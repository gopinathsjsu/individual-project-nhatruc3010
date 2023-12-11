package card;


public class CreditCard {
	protected String number;
	protected String expireDate;
	protected String holder;
	protected String cardType;
	
	public CreditCard(String number, String expireDate, String holder) {
		this.number = number;
		this.expireDate = expireDate;
		this.holder = holder;
		this.cardType = "Not identified";
	}
	
	public static boolean is_match_pattern(String pattern) {
		return true;
	}
	
	public String toString() {
		return String.format("""
				
				Type: %s
				Number: %s
				Expire Date: %s
				Holder: %s
				
				""", this.cardType, this.number, this.expireDate, this.holder);
	}
	
	public String getNumber() {
        return number;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public String getHolder() {
        return holder;
    }
    public String getCardType() {
		return cardType;
	};
}