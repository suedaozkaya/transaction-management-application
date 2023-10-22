package fileIO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import product.Product;
import shopassistant.*;
import transaction.*;

public class FileIO {
    SalaryManagement salaryManagement;
    TransactionManagement transactionManagement;

    public FileIO(SalaryManagement salaryManagement, TransactionManagement transactionManagement) {
        this.salaryManagement = salaryManagement;
        this.transactionManagement = transactionManagement;
        readProductsFile();
        readAssistantsFile();
    }

    /*Products array work properly, however the method createTransactions on Transaction Management object
     * creates same 1500 Transactions whose index 1499, has same products. The transaction which should be 
     * last element be fulled with whole transaction array...
    */
    //PROBLEM SOLVED ABOVE*
    public void readProductsFile() { 
        Product[] products = new Product[90];
        int currentIndex = 0;

        String filePath = "C:\\Users\\pc\\IdeaProjects\\transactionManagementApplication\\files\\products.csv";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words =  line.split(";");

                Integer productID = Integer.parseInt(words[0]);
                String productName = words[1];
                String price = words[2].replace(",", ".");
                Double productPrice = Double.parseDouble(price);

                Product product = new Product(productID, productName, productPrice * generateRandomQuantityOneToTen());
                products[currentIndex] = product;
                //System.out.println("Product" + productID + " added succesfully");
                currentIndex++;
            }
            transactionManagement.createTransactions(products);
            // Transaction[] transactions = tManagement.getAllTransactions();
            // System.out.println(transactions[0]);
            // System.out.println(transactions[1499]);
            // for (Transaction transaction : transactions) {
            //     System.out.println(transaction.toString());
            // }

        } catch (IOException e) {
            System.err.println("An error occured while reading from the file: " + e.getMessage());
        }
    }

    public void readAssistantsFile() {
        ShopAssistant[] assistanList = new ShopAssistant[100];
        int currentIndex = 0;

        String filePath = "C:\\Users\\pc\\IdeaProjects\\transactionManagementApplication\\files\\shopAssistants.csv";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split(";");
                int ID = Integer.parseInt(words[0]);
                ShopAssistant assistant = new ShopAssistant(ID, words[1], words[2], words[3]);
                assistanList[currentIndex] = assistant;
                currentIndex++;
                //System.out.println("Assistant" + ID + " added succesfully");
            }
            salaryManagement.addShopAssistant(assistanList);
            // assistanList = sManagement.getAllAssistants();
            // for (ShopAssistant shopAssistant : assistanList) {
            //     System.out.println(shopAssistant.toString());
            // }


        } catch (IOException e) {
            System.err.println("An error occured while reading from the file: " + e.getMessage());
        }
    }

    private static int generateRandomQuantityOneToTen() {
        return (int) (Math.random() * 10) + 1;
    }
}
