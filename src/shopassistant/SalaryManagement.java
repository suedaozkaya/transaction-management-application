package shopassistant;

import transaction.Transaction;

public class SalaryManagement {

    private static final int WEEKLY_SALARY_1 = 1500;
    private static final int WEEKLY_SALARY_2 = 2000;
    private static final int WEEKLY_SALARY_3 = 2500;
    private static final int WEEKLY_SALARY_4 = 3000;
    private ShopAssistant[] shopAssistants = new ShopAssistant[100];

    public SalaryManagement() {
    }

    public int chooseWeeklySalary(int seniority) {
        if (seniority < 1) {
            return WEEKLY_SALARY_1;
        } else if (seniority < 3) {
            return WEEKLY_SALARY_2;
        } else if (seniority < 5) {
            return WEEKLY_SALARY_3;
        } else {
            return WEEKLY_SALARY_4;
        }
    }

    public void addShopAssistant(ShopAssistant[] assistants) {
        int index = 0;
        for (ShopAssistant shopAssistant : assistants) {
            shopAssistants[index] = shopAssistant;
            index++;
        }
    }

    public ShopAssistant getAssistantByIndex(int index) {
        return shopAssistants[index].getShopAssistant();
    }

    public ShopAssistant[] getAllAssistants() {
        ShopAssistant[] copy = new ShopAssistant[100];
        int index = 0;
        for (ShopAssistant assistant : shopAssistants) {
            copy[index] = assistant;
            index++;
        }
        return copy;
    }

    public double calculateCommision(double totalRevenue) {
        if (totalRevenue > 7500) {
            return totalRevenue * 0.03;
        } else {
            return totalRevenue * 0.01;
        }
    }

    public double calculateSalary(ShopAssistant shopAssistant) {
        double totalRevenue = 0;
        int weeklySalary = chooseWeeklySalary(shopAssistant.getSeniority());
        Transaction[] transactionsOfAssistant = shopAssistant.getTransactions();
        for (Transaction transaction : transactionsOfAssistant) {
            totalRevenue += transaction.calculateTotalRevenue();
        }
        double commision = calculateCommision(totalRevenue);
        return (weeklySalary * 4) + commision;
    }
}
