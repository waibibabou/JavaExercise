package banking;

/*
 * This class creates the program to test the banking classes.
 * It creates a set of customers, with a few accounts each,
 * and generates a report of current account balances.
 */

//import banking.*;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Objects;
import java.util.Scanner;

public class Main{

    public static String formatTosepara(float data) {
        DecimalFormat df = new DecimalFormat("#,###.00");
        return df.format(data);
    }

    public static void main(String[] args) {
        Bank bank = new Bank();
        NumberFormat currency_format = NumberFormat.getCurrencyInstance();

        Customer customer;
        int curCustomer = 0;
        Scanner s = new Scanner(System.in);
        int t = s.nextInt();
        s.nextLine();
// Create several customers and their accounts according to data
        while (t-- > 0) {
            String f = s.next();
            String l = s.next();
            s.nextLine();
            bank.addCustomer(f, l);
            customer = bank.getCustomer(curCustomer++);
            int numAccount = s.nextInt();
            s.nextLine();
            while (numAccount-- > 0) {
                String[] type = s.nextLine().split(" ");
                double balance;
                double interesOrProtect;
                if (Objects.equals(type[0], "C") || Objects.equals(type[0], "c")) {
                    balance = Double.parseDouble(type[1]);
                    if (type.length == 3) {
                        interesOrProtect = Double.parseDouble(type[2]);
                        customer.addAccount(new CheckingAccount(balance,
                                interesOrProtect));
                    } else {
                        customer.addAccount(new CheckingAccount(balance));

                    }
                } else if (Objects.equals(type[0], "S") || Objects.equals(type[0], "s")) {
                    balance = Double.parseDouble(type[1]);
                    interesOrProtect = Double.parseDouble(type[2]);
                    customer.addAccount(new SavingsAccount(balance,
                            interesOrProtect));
                } else if (Objects.equals(type[0], "A") || Objects.equals(type[0], "a")) {
                    int cIndex = Integer.parseInt(type[1]);
                    int aIndex = Integer.parseInt(type[2]);
                    customer.addAccount(bank.getCustomer(cIndex).getAccount(
                            aIndex));
                }
            }
        }

// Generate a report
        System.out.println("CUSTOMERS REPORT");
        System.out.println("================");

        for ( int cust_idx = 0; cust_idx < bank.getNumOfCustomers(); cust_idx++ ) {
            customer = bank.getCustomer(cust_idx);

            System.out.println();
            System.out.println("Customer: "
                    + customer.getLastName() + ", "
                    + customer.getFirstName());

            for ( int acct_idx = 0; acct_idx < customer.getNumOfAccounts(); acct_idx++ ) {
                Account account = customer.getAccount(acct_idx);
                String account_type = "";
                if(account instanceof SavingsAccount){
                    System.out.print("    Savings Account$");
                    System.out.println(formatTosepara((float) account.getBalance()));
                }
                else{
                    System.out.print("    Checking Account$");
                    System.out.println(formatTosepara((float) account.getBalance()));
                }
            }
        }
    }
}



class Bank{
    Customer[] customers;
    int numberOfCustomers;
    public Bank(){
        customers=new Customer[20];
    }
    public void addCustomer(String f,String l){
        customers[numberOfCustomers++]=new Customer(f,l);
    }
    public int getNumOfCustomers(){
        return numberOfCustomers;
    }
    public Customer getCustomer(int index){
        return customers[index];
    }

}

class Customer{
    private String firstName;
    private String lastName;
    private Account[] accounts;
    private int number;
    public Customer(String f,String l){
        firstName=f;
        lastName=l;
        accounts=new Account[20];
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void addAccount(Account account){
        accounts[number++]=account;
    }

    public Account getAccount(int index){
        return accounts[index];
    }

    public int getNumOfAccounts(){
        return number;
    }

}


class Account {
    protected double balance;
    public Account(double init_balance){
        balance=init_balance;
    }
    public double getBalance(){
        return balance;
    }
    public boolean deposit(double amt){
        balance+=amt;
        return true;
    }
    public boolean withdraw(double amt){
        if(balance>=amt){
            balance-=amt;
            return true;
        }
        return false;
    }
}


class SavingsAccount extends Account{
    private double interestRate;

    public SavingsAccount(double balance,double interest_rate){
        super(balance);
        this.interestRate=interest_rate;
    }
}

class CheckingAccount extends Account{
    private double overdraftProtection;
    public CheckingAccount(double balance){
        super(balance);
    }
    public CheckingAccount(double balance,double protection){
        super(balance);
        this.overdraftProtection=protection;
    }
    public boolean withdraw(double amt){
        if(balance>=amt){
            balance-=amt;
            return true;
        }
        else if(overdraftProtection+balance>=amt){
            overdraftProtection-=amt-balance;
            balance=0;
            return true;
        }
        return false;
    }

}
