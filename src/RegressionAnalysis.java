import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;

/**
 * 
 * This class will be used to run the regression and return the values, using
 * The Apache Commons Mathematics Library
 * @author William & Ryan
 */

public class RegressionAnalysis {

	StockCharacteristics stocks;

	// Constructor
	public RegressionAnalysis() {
	}
	
	
	/**
	 * Uses the UserInteraction class to get the stock the user inputs into the
	 * console
	 * 
	 * @return ourSelectedStock
	 */
	public StockCharacteristics stockInput() {

		StockList stocks = new StockList();

		ArrayList<String> listStocks = stocks.listOfStocks();

		UserInteraction userInput = new UserInteraction();

		String stockTicker = userInput.phaseOneRun(listStocks);

		StockCharacteristics stocksInCharacteristics = new StockCharacteristics();

		HashMap<String, StockCharacteristics> hasStocks = stocksInCharacteristics.stocksListedInHashMap();

		StockCharacteristics ourSelectedStock = hasStocks.get(stockTicker);

		return ourSelectedStock;
	}

	/**
	 * Calculates the regression of a given stock and returns a double array with
	 * the regressors inside
	 * 
	 * @return betas
	 */
	public double[] calculateRegression() {

		StockCharacteristics stocks = new StockCharacteristics();

		double[] outcomes = new double[stocks.readData().size()];

		for (int i = 0; i < stocks.readData().size(); i++) {

			outcomes[i] = stocks.readData().get(i).getStockPrice2019();
		}

		double[][] controls = new double[stocks.readData().size()][];

		for (int i = 0; i < stocks.readData().size(); i++) {

			controls[i] = new double[] { stocks.readData().get(i).getStockPrice2018(),
					stocks.readData().get(i).getIndustry(), stocks.readData().get(i).getTotalRevenueYOY(),
					stocks.readData().get(i).getNetIncomeYOY(), stocks.readData().get(i).getEPS(),
					stocks.readData().get(i).getMarketCap(), stocks.readData().get(i).getDebtToEquity(),
					stocks.readData().get(i).getFreeCashFlow(), stocks.readData().get(i).getBeta(),
					stocks.readData().get(i).getMovingAverage(), stocks.readData().get(i).getStockGrowthRate() };
		}

		OLSMultipleLinearRegression stockRegression = new OLSMultipleLinearRegression();

		stockRegression.newSampleData(outcomes, controls);
		stockRegression.calculateAdjustedRSquared();

		double[] betas = stockRegression.estimateRegressionParameters();

		return betas;
	}

	/**
	 * Calculates the predicted stock price of the stock that the user has inputed
	 * @param the stock the user inputs
	 * @return predictedStockPrice
	 */
	
	public double calculatedRegressionStockPrice(StockCharacteristics stock) {

		RegressionAnalysis regression = new RegressionAnalysis();

		double[] regressionCalculated = regression.calculateRegression();

		double intercept = regressionCalculated[0];
		double stockPrice2018 = regressionCalculated[1];
		double industry = regressionCalculated[2];
		double revenueGrowthRate = regressionCalculated[3];
		double netIncome = regressionCalculated[4];
		double EPS = regressionCalculated[5];
		double marketCap = regressionCalculated[6];
		double debtToEquity = regressionCalculated[7];
		double freeCashFlow = regressionCalculated[8];
		double beta = regressionCalculated[9];
		double movingAverage = regressionCalculated[10];
		double stockGrowth = regressionCalculated[11];

		double predictedIntercept = intercept;
		double predictedStockPrice2018 = stockPrice2018 * stock.getStockPrice2018();
		double predictedIndustry = industry * stock.getIndustry();
		double predictedRevenueGrowthRate = revenueGrowthRate * stock.getTotalRevenueYOY();
		double predictedNetIncome = netIncome * stock.getNetIncomeYOY();
		double predictedEPS = EPS * stock.getEPS();
		double predictedMarketCap = marketCap * stock.getMarketCap();
		double predictedDebtToEquity = debtToEquity * stock.getDebtToEquity();
		double predictedFreeCashFlow = freeCashFlow * stock.getFreeCashFlow();
		double predictedBeta = beta * stock.getBeta();
		double predictedMovingAverage = movingAverage * stock.getMovingAverage();
		double predictedStockGrowth = stockGrowth * stock.getStockGrowthRate();
		double predictedStockPrice = predictedIntercept + predictedStockPrice2018 + predictedIndustry
				+ predictedRevenueGrowthRate + predictedNetIncome + predictedEPS + predictedMarketCap
				+ predictedDebtToEquity + predictedFreeCashFlow + predictedBeta + predictedMovingAverage
				+ predictedStockGrowth;

		return predictedStockPrice;

	}
}