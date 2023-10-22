package transaction;

import java.text.DecimalFormat;

import product.Product;

public class Transaction {

    private Integer ID;
    private Product[] products;
    //TODO private Integer[] quantities;
    private Double totalPrice = 0.0;
    private Double transactionFee;

    public Transaction() {
    }

    public Transaction(Integer ID, Product[] products) {
        this.ID = ID;
        this.products = products;
        //this.quantities = generateThreeRandomOneToTen();
        this.totalPrice = calculateTotalPrice();
        transactionFee = calculateTransactionFee();
    }

    /*private Integer[] generateThreeRandomOneToTen(){
        Integer[] randomNumbers = new Integer[3];
        randomNumbers[0] = (int) (Math.random() * 10) + 1;
        randomNumbers[1] = (int) (Math.random() * 10) + 1;
        randomNumbers[2] = (int) (Math.random() * 10) + 1;
        return randomNumbers;
    }*///TODO

    public Transaction(Transaction original) {
        ID = original.ID;
        products = original.products;
        totalPrice = original.totalPrice;
        transactionFee = original.transactionFee;
    }

    public void setTransactionID(Integer ID) {
        this.ID = ID;
    }

    public Integer getTransactionID() {
        return ID;
    }

    public void setTransactionProducts(Product[] products) {
        if (products.length == 3) {
            this.products = products;
        } else {
            System.out.println("Three products must be entered.");
            return;
        }
    }

    public Product[] getTransactionProducts() {
        Product[] copy = new Product[3];
        int index = 0;
        for (Product product : products) {
            copy[index] = product;
            index++;
        }
        return copy;
    }

    private Double calculateTotalPrice() {
        totalPrice = 0.0;
        for (Product product : products) {
            /*for (Integer quantity: quantities) {
                totalPrice += product.getProductPrice()*quantity;
            }*/
            totalPrice += product.getProductPrice();
        }
        return totalPrice;
    }

    public Double getTransactionTotalPrice() {
        return totalPrice;
    }

    public Double calculateTransactionFee() {

        if (totalPrice <= 499) {
            transactionFee = totalPrice * 0.01;
        } else if (totalPrice <= 799) {
            transactionFee = totalPrice * 0.03;
        } else if (totalPrice <= 999) {
            transactionFee = totalPrice * 0.05;
        } else {
            transactionFee = totalPrice * 0.09;
        }
        return transactionFee;
    }

    public Double getTransactionFee() {
        return transactionFee;
    }

    public Double calculateTotalRevenue() {
        return totalPrice + transactionFee;
    }

    public String toString() {
        DecimalFormat dF = new DecimalFormat("#.##");
        return "Transaction|" + ID + "{\n" + "    Products{\n" + "        " + products[0].toString() + "\n" + "        "
                + products[1].toString() + "\n" + "        "
                + products[2].toString() + "\n" + "    }\n" + "    Total Price: " + dF.format(totalPrice) + "\n"
                + "    Transaction Fee: " + dF.format(transactionFee)
                + "\n" + "}";
    }

    public boolean equalsTransaction(Transaction otherTransaction) {
        if (otherTransaction == null) {
            return false;
        } else {
            return ID.equals(otherTransaction.ID)  && totalPrice.equals(otherTransaction.totalPrice)
                    && transactionFee.equals( otherTransaction.transactionFee)
                    && products[0].equalsProduct(otherTransaction.products[0])
                    && products[1].equalsProduct(otherTransaction.products[1])
                    && products[2].equalsProduct(otherTransaction.products[2]);
        }
    }

}
