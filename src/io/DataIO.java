package io;
import java.util.List;

import card.CreditCard;

public abstract class DataIO{
	public abstract List<CreditCard> readInputs(String path);
	public abstract void writeOutputs(String path, List<CreditCard> data);
}