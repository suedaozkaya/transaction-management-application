package transaction;

import product.Product;

public class TransactionManagement {
    private static final int TOTAL_ASSISTANTS = 100;
    private static final int TRANSACTION_PER_ASSİSTANT = 15;
    private static Transaction[][] transactions = new Transaction[TOTAL_ASSISTANTS][TRANSACTION_PER_ASSİSTANT];

    public TransactionManagement() {
    }

    public void addTransactions(Transaction transaction, int row, int column) {
        transactions[row][column] = transaction;
    }

    public static Transaction[] getFifteenTransactions(int index) {
        try {
            return transactions[index].clone();
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Invalid assistant ID" + e.getMessage());
        }
        return null;
    }

    public Transaction[] getAllTransactions() {
        Transaction[] transactionList = new Transaction[TOTAL_ASSISTANTS * TRANSACTION_PER_ASSİSTANT];
        int currentIndex = 0;
        for (int i = 0; i < TOTAL_ASSISTANTS; i++) {
            for (int j = 0; j < TRANSACTION_PER_ASSİSTANT; j++) {
                transactionList[currentIndex] = transactions[i][j];
                currentIndex++;
            }
        }
        return transactionList;
    }

    public void createTransactions(Product[] products) {
        int row = 0;
        int column = 0;
        int ID = 0;
        while (row < TOTAL_ASSISTANTS) {
            while (column < TRANSACTION_PER_ASSİSTANT) {
                Transaction transaction = new Transaction(ID, getThreeRandomProducts(products));
                addTransactions(transaction, row, column);
                //System.out.println(transaction.toString() + " added succesfully");
                ID++;
                column++;
            }
            row++;
            column = 0;
        }
    }

    private Product[] getThreeRandomProducts(Product[] products) {
        Product[] randomProducts = new Product[3];
        int randomIndex;
        for (int i = 0; i < randomProducts.length; i++) {
            randomIndex = (int) (Math.random() * products.length);
            randomProducts[i] = products[randomIndex];
        }
        return randomProducts;
    }

}
