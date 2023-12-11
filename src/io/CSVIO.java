package io;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import card.CreditCard;
import cardReader.CreditCardCreater;

import java.io.FileWriter;
import java.io.IOException;

public class CSVIO extends DataIO{
	public List<CreditCard> readInputs(String path) {
		List<CreditCard> data = new ArrayList<CreditCard>();
		
		try {
            // Specify the path to your CSV file
            File csvFile = new File(path); // Replace with the path to your CSV file

            // Create a Scanner to read the CSV file
            Scanner scanner = new Scanner(csvFile);

            // Read and print header (assuming the first line is the header)
            if (scanner.hasNextLine()) {
                String header = scanner.nextLine();
                System.out.println("Header: " + header);
            }

            // Read and process each line in the CSV file
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                // Split the line into fields using a comma as the delimiter
                String[] fields = line.split(",");

                // Process the fields (you can replace this with your own logic)
                String cardNumber = fields[0];
                String expirationDate = fields[1];
                String cardHolderName = fields[2];

                data.add(CreditCardCreater.createCardInstance(cardNumber, expirationDate, cardHolderName));
            }

            // Close the scanner
            scanner.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
		
		return data;
	}
	
	private static String escapeCsvField(String field) {
        // Escape the field if it contains commas or double quotes
        if (field.contains(",") || field.contains("\"")) {
            return "\"" + field.replace("\"", "\"\"") + "\"";
        } else {
            return field;
        }
    }

	@Override
	public void writeOutputs(String path, List<CreditCard> data) {
		try (FileWriter writer = new FileWriter(path)) {
            // Write CSV header
            writer.append("Card Number,Expiration Date,Card Holder Name,Card Type\n");

            // Write each credit card to the CSV file
            for (CreditCard creditCard : data) {
                writer.append(escapeCsvField(creditCard.getNumber()))
                        .append(",")
                        .append(escapeCsvField(creditCard.getExpireDate()))
                        .append(",")
                        .append(escapeCsvField(creditCard.getHolder()))
                        .append(",")
                        .append(escapeCsvField(creditCard.getCardType()))
                        .append("\n");
            }

            System.out.println("Credit cards written to " + path);
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}
}