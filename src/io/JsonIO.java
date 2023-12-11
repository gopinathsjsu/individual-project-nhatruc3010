package io;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import card.CreditCard;
import cardReader.CreditCardCreater;

import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;

public class JsonIO extends DataIO{
	public List<CreditCard> readInputs(String path) {
		List<CreditCard> data = new ArrayList<CreditCard>();
		
		try {
            // Specify the path to your JSON file
            File jsonFile = new File(path); // Replace with the path to your JSON file

            // Create a Gson instance
            Gson gson = new Gson();

            // Parse the JSON file into a JsonElement
            JsonArray cardsArray = gson.fromJson(new FileReader(jsonFile), JsonObject.class).getAsJsonArray("cards");
            System.out.println(cardsArray.size());
            // Process each card in the array
            for (int i = 0; i < cardsArray.size(); i++) {
                JsonObject cardObject = cardsArray.get(i).getAsJsonObject();                
                String cardNumber = cardObject.has("cardNumber") ? cardObject.get("cardNumber").getAsString() : "N/A";
                String expirationDate = cardObject.has("expirationDate") ? cardObject.get("expirationDate").getAsString() : "N/A";
                String cardHolderName = cardObject.has("cardHolderName") ? cardObject.get("cardHolderName").getAsString() : "N/A";
                data.add(CreditCardCreater.createCardInstance(cardNumber, expirationDate, cardHolderName));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
		
		return data;
	}

	@Override
	public void writeOutputs(String path, List<CreditCard> data) {
		// TODO Auto-generated method stub
		try (FileWriter writer = new FileWriter(path)) {
            // Create a Gson instance with pretty printing
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            // Convert the list to JSON and write to the file
            gson.toJson(data, writer);

            System.out.println("Credit cards written to " + path);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}