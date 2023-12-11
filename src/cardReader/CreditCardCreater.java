package cardReader;

import card.AmExCC;
import card.CreditCard;
import card.Discovery;
import card.MasterCC;
import card.VisaCC;

public class CreditCardCreater {
	public static CreditCard createCardInstance(String number, String expireDate, String holder) {
		
		if(AmExCC.is_match_pattern(number)) {
			return new AmExCC(number, expireDate, holder);
		}else if(MasterCC.is_match_pattern(number)) {
			return new MasterCC(number, expireDate, holder);
		}else if(VisaCC.is_match_pattern(number)) {
			return new VisaCC(number, expireDate, holder);
		}else if(Discovery.is_match_pattern(number)) {
			return new Discovery(number, expireDate, holder);
		}else {
			return new CreditCard(number, expireDate, holder);
		}
	}
}