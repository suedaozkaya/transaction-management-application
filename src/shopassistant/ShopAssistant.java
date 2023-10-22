package shopassistant;

import java.text.DecimalFormat;

import transaction.*;

public class ShopAssistant {

    private Integer ID;
    private String name;
    private String surname;
    private String phoneNumber;
    private Integer seniority;
    private Transaction[] transactionList = null;

    public ShopAssistant(Integer ID, String name, String surname, String phoneNumber) {
        this.ID = ID;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        seniority = (int) (Math.random() * 16);
        transactionList = TransactionManagement.getFifteenTransactions(ID);
    }

    public ShopAssistant(ShopAssistant original) {
        ID = original.ID;
        name = original.name;
        surname = original.surname;
        phoneNumber = original.phoneNumber;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setSurName(String surname) {
        this.surname = surname;
    }

    public String getSurName() {
        return surname;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getSeniority() {
        return seniority;
    }

    public Transaction[] getTransactions() {
        Transaction[] copy = new Transaction[15];
        int index = 0;
        for (Transaction transaction : transactionList) {
            copy[index] = transaction;
            index++;
        }
        return copy;
    }

    public ShopAssistant getShopAssistant() {
        return new ShopAssistant(this);
    }

    private double calculateTotalRevenue() {
        double totalRevenue = 0.0;
        for (Transaction transaction : transactionList) {
            totalRevenue += transaction.calculateTotalRevenue();
        }
        return totalRevenue;
    }

    public String toString() {
        DecimalFormat dF = new DecimalFormat("#.##");
        SalaryManagement sManagement = new SalaryManagement();
        return "ShopAssistant{\n" + "ID: " + ID + "\n" + "Name Surname: " + name + " " + surname + "\n"
                + "Phone Number: " + phoneNumber
                + "\n" + "Seniority: " + seniority + "\n" + "Weekly Salary: "
                + sManagement.chooseWeeklySalary(seniority)
                + "\n" + "Commision: " + dF.format(sManagement.calculateCommision(calculateTotalRevenue()))
                + "\n" + "Total Salary: " + dF.format(sManagement.calculateSalary(this)) + "\n"
                + "}";
    }

    public boolean equalsShopAssistant(ShopAssistant otherShopAssistant) {
        if (otherShopAssistant == null) {
            return false;
        } else {
            return ID.equals(otherShopAssistant.ID) && name.equals(otherShopAssistant.name)
                    && surname.equals(otherShopAssistant.surname) && phoneNumber.equals(otherShopAssistant.phoneNumber);
        }
    }
}
