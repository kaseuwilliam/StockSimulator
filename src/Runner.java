public class Runner {
	
	StockCharacteristics stocks;
	
	/**
	 * This method will run the entire program
	 * @param args
	 * @author William & Ryan
	 */
	
	public static void main(String[] args) {
		
		StockCharacteristics stocks = new StockCharacteristics();
		
		RegressionAnalysis regression = new RegressionAnalysis();
		
		StockCharacteristics stockToInput = regression.stockInput();
		
		double price = regression.calculatedRegressionStockPrice(stockToInput);
		
		System.out.println("The predicted stock price is "+price);
	
	}

}