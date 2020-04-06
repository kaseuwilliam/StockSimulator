import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * This class prompts the user for their inputs in the console and handles
 * potential errors accordingly
 * @author William & Ryan
 */

public class UserInteraction {

	StockList stocks;

	// Constructor
	public UserInteraction() {
	}
	
	/**
	 * Prints a list of all stocks in the console
	 * 
	 * @param listOfStocks
	 */
	
	public static void professionalDisplay(ArrayList<String> listOfStocks) {

		for (int i = 0; i < listOfStocks.size(); i++) {

			System.out.format("%5s", i + 1 + ". " + listOfStocks.get(i));

			System.out.println();

		}
	}

	/**
	 * Prompts the user to enter a number between 1 and 480 which corresponds to a
	 * stock, and returns said number
	 * 
	 * @return numberToReturn
	 */
	
	public static int stockSelection() {

		int numberToReturn = 0;

		for (int i = 0; i < 1; i++) {

			System.out.println("\n What number do you select?");

			Scanner scanner = new Scanner(System.in);

			try {
				int numberInput = scanner.nextInt();

				if (numberInput < 481 && numberInput > 0) {

					numberToReturn = numberInput - 1;

				} else if (numberInput < 1) {

					System.out.println("Please type a number greater than 0");

					i--;

				} else {

					System.out.println("Please type in a number on the list");

					i--;
				}

			} catch (Exception e) {

				System.out.println("Please type in a number on the list");

				i--;
			}

		}

		return numberToReturn;
	}

	/**
	 * Confirms whether a user has selected the correct stock and returns a boolean
	 * as the key with the stock ticker the user selected as the value.
	 * 
	 * @param listOfStocks
	 * @return value
	 */
	
	public static HashMap<Boolean, String> stockSelectionConfirmation(ArrayList<String> listOfStocks) {

		int number = stockSelection();

		String stockTicker = listOfStocks.get(number);

		// number = number -1;

		HashMap<Boolean, String> value = new HashMap<Boolean, String>();

		boolean typeToReturn = false;

		for (int i = 0; i < 1; i++) {

			System.out.println("\n You selected the stock " + listOfStocks.get(number)
					+ ". Is this correct? \n \n Please type Yes or No");

			Scanner scanner = new Scanner(System.in);

			String userInput = scanner.next();

			userInput = userInput.toLowerCase();

			if (userInput.equals("yes")) {

				typeToReturn = true;

				System.out.println("Please wait approximately 15 seconds while we calculate the future stock price:");

			} else if (userInput.contentEquals("no")) {

				typeToReturn = false;

			} else {

				System.out.println("\n Please type in Yes or No and make sure there are no spaces");

				i--;
			}

		}

		value.put(typeToReturn, stockTicker);

		return value;
	}

	/**
	 * Runs phase one of the code which involves selecting the stock
	 * 
	 * @param listOfStocks
	 * @return stock ticker
	 */
	public String phaseOneRun(ArrayList<String> listOfStocks) {

		String stockTicker = "";

		for (int i = 0; i < 1; i++) {

			professionalDisplay(listOfStocks);

			HashMap<Boolean, String> selection = stockSelectionConfirmation(listOfStocks);

			boolean value = false;

			if (selection.containsKey(true)) {
				value = true;
				stockTicker = selection.get(true);
			}

			if (value == false) {
				i--;
			}

		}

		return stockTicker;
	}

	/**
	 * WE WILL UPDATE THIS LATER: Do you want to predict the price of another stock?
	 * ALso, we can write a file with the results.
	 * 
	 * Create and run phase two
	 */
}