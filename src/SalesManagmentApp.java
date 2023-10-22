import query.Query;

import java.text.DecimalFormat;

public class SalesManagmentApp {
    public static void main(String[] args) {

        Query query = new Query();
        //query.test();
        DecimalFormat dF = new DecimalFormat("#.##"); //todo main de bunlar olmayacak
        System.out.println("Highest total price transaction\n" + query.getHighestPriceTransaction().toString());
        //System.out.println("Lowest total price transaction\n" + query.getLowestPriceTransaction().toString());
        System.out.println("The most expensive product in the lowest price transaction:\n"
                + query.getExpensiveProduct(query.getLowestPriceTransaction()).toString());
        System.out.println("The lowest transaction fee:\n" + dF.format(query.getLowestTransactionFee()));

        System.out.println("The highest salary shop assistant:\n" + query.getHighestSalaryAssistant().toString());
        System.out.println("Total Profit: " + query.getTotalProfit());

    }
}
