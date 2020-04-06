import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * This class is used to create a list of stock tickers and a hashMap containing info from the source CSV
 * @author William & Ryan 
 */

public class StockList {

	StockCharacteristics stocks;

	// Constructor
	public StockList() {
	}
	
	/**
	 * Creates an ArrayList with all the stock tickers in alphabetical order.
	 * This method will use polymorphism in the future
	 * @return stockList
	 */

	public ArrayList<String> listOfStocks() {

		StockCharacteristics stocks = new StockCharacteristics();

		ArrayList<StockCharacteristics> stocksInCharacteristicsFormat = stocks.readData();

		ArrayList<String> stockList = new ArrayList<String>();

		for (int i = 0; i < stocksInCharacteristicsFormat.size(); i++) {

			stockList.add(stocksInCharacteristicsFormat.get(i).getStockName());
		}

		return stockList;
	}

	/**
	 * Creates a HashMap of stocks where the key is the company's ticker and the
	 * value is a stock in the StockCharacteristics class.
	 * 
	 * @return listOfStocks
	 */

	public HashMap<String, ArrayList<String>> readData() {

		File stocks = new File("List_Of_Stocks.csv");

		try {

			Scanner scanner = new Scanner(stocks);
			scanner.nextLine();

			HashMap<String, ArrayList<String>> listOfStocks = new HashMap<String, ArrayList<String>>();

			while (scanner.hasNextLine()) {

				ArrayList<String> stockSymbolAndIndustry = new ArrayList<String>();

				String storage = scanner.nextLine();
				String[] infoSeparation = storage.split(",");

				stockSymbolAndIndustry.add(infoSeparation[0]);
				stockSymbolAndIndustry.add(infoSeparation[3]);
				listOfStocks.put(infoSeparation[1], stockSymbolAndIndustry);
			}

			return listOfStocks;

		}

		catch (Exception e) {

			System.out.println("Something went wrong");

			return null;
		}
	}

}