package query;

import java.text.DecimalFormat;
import java.util.Arrays;

import fileIO.FileIO;
import product.Product;
import shopassistant.SalaryManagement;
import shopassistant.ShopAssistant;
import transaction.Transaction;
import transaction.TransactionManagement;

public class Query {

    TransactionManagement transactionManagement = new TransactionManagement();
    SalaryManagement salaryManagement = new SalaryManagement();
    FileIO fileIO = new FileIO(salaryManagement, transactionManagement);
    ShopAssistant[] assistantArray = salaryManagement.getAllAssistants();
    Transaction[] transactionArray = transactionManagement.getAllTransactions();


    public Query() {
    }

    public void test(){
        System.out.println(Arrays.toString(transactionArray));
    }

    public Transaction getHighestPriceTransaction() {
        Transaction highestPriceTransaction = transactionArray[0];
        for (Transaction transaction : transactionArray) {
            if (transaction.getTransactionTotalPrice() > highestPriceTransaction.getTransactionTotalPrice()) {
                highestPriceTransaction = transaction;
            }
        }
        return highestPriceTransaction;
    }

    public Transaction getLowestPriceTransaction() {
        Transaction lowestPriceTransaction = transactionArray[0];
        for (Transaction transaction : transactionArray) {
            if (transaction.getTransactionTotalPrice() < lowestPriceTransaction.getTransactionTotalPrice()) {
                lowestPriceTransaction = transaction;
            }
        }
        return lowestPriceTransaction;
    }

    public Product getExpensiveProduct(Transaction transaction) {
        Product[] products;
        products = transaction.getTransactionProducts();
        Product expensiveProduct = products[0];
        for (Product product : products) {
            if (product.getProductPrice() > expensiveProduct.getProductPrice()) {
                expensiveProduct = product;
            }
        }
        return expensiveProduct;
    }

    public double getLowestTransactionFee() {
        Transaction lowestFeeTransaction = transactionArray[0];
        for (Transaction transaction : transactionArray) {
            //System.out.println(transaction.getTransactionTotalPrice());
            //System.out.println(lowestFeeTransaction.getTransactionFee().toString() + "vs" + transaction.getTransactionFee().toString());
            if (transaction.getTransactionFee() < lowestFeeTransaction.getTransactionFee()) {
                lowestFeeTransaction = transaction;
            }
        }
        return lowestFeeTransaction.getTransactionFee();
    }

    public ShopAssistant getHighestSalaryAssistant() {

        ShopAssistant highestSalaryAssistant = assistantArray[0];
        for (ShopAssistant shopAssistant : assistantArray) {
            if (salaryManagement.calculateSalary(shopAssistant) > salaryManagement
                    .calculateSalary(highestSalaryAssistant)) {
                        highestSalaryAssistant = shopAssistant;
            }
        }
        return highestSalaryAssistant;
    }

    public String getTotalProfit() {
        DecimalFormat dF = new DecimalFormat(); 
        double totalSalaryPaid = 0.0;
        double totalRevenue = 0.0;
        for (ShopAssistant shopAssistant : assistantArray) {
            totalSalaryPaid += salaryManagement.calculateSalary(shopAssistant);
        }
        for (Transaction transaction : transactionArray) {
            totalRevenue += transaction.calculateTotalRevenue();
        }
        return dF.format(totalRevenue - totalSalaryPaid);
    }



}
