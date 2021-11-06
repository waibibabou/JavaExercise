package banking;

/*
 * This class creates the program to test the banking classes.
 * It creates a set of customers, with a few accounts each,
 * and generates a report of current account balances.
 */

//import banking.*;

import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();
        Customer customer;
        Account account;
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
                boolean protectWithSavings;
                char op = type[0].charAt(0);
                if (op == 'C' || op == 'c') {
                    balance = Double.parseDouble(type[1]);
                    protectWithSavings = Boolean.parseBoolean(type[2]);
                    if (protectWithSavings) {
                        customer.setChecking(new CheckingAccount(balance,
                                customer.getSavings().balance));
                        System.out.println(customer + " create Checking account: " + customer.getChecking().getBalance() + " with a protecting savings accout: " + customer.getSavings().getBalance() );
                    } else {
                        customer.setChecking(new CheckingAccount(balance));
                        System.out.println(customer + " create Checking account: " + customer.getChecking().getBalance() + " without protecting savings accout.");
                    }
                } else if (op == 'S' || op == 's') {
                    balance = Double.parseDouble(type[1]);
                    double interestRate = Double.parseDouble(type[2]);
                    customer.setSavings(new SavingsAccount(balance,
                            interestRate));
                    System.out.println(customer + " create savings account: " + customer.getSavings().getBalance() );
                }
            }
        }
        int nOPs = s.nextInt();
        s.nextLine();
        while (nOPs-- > 0) {
            String[] sOP = s.nextLine().split(" ");
            char op = sOP[0].charAt(0);
            int customerIndex = Integer.parseInt(sOP[1]);
            double amount = Double.parseDouble(sOP[2]);
            boolean result;
            switch(op){
                case 'w':
                case 'W':
                    customer = bank.getCustomer(customerIndex);
                    result = customer.getChecking().withdraw(amount);
                    System.out.println("Checking acct " + customer + "withdraw " + amount + " succeeds? " + result);
                    break;
                case 'd':
                case 'D':
                    customer = bank.getCustomer(customerIndex);
                    result = customer.getChecking().deposit(amount);
                    System.out.println("Checking acct " + customer + "deposit " + amount + " succeeds? " + result);
                    break;
            }
        }
        // Generate a report
        for (int cust_idx = 0; cust_idx < bank.getNumOfCustomers(); cust_idx++) {
            customer = bank.getCustomer(cust_idx);
            System.out.println();
            System.out.println("Customer: " + customer.getLastName() + ", "
                    + customer.getFirstName());
            account = customer.getChecking();
            System.out.println("    has a checking balance of " + account.getBalance());
            account = customer.getSavings();
            if (account != null)
                System.out.println("    has a savings balance of " + account.getBalance());
        }
    }
}
/* PRESET CODE END - NEVER TOUCH CODE ABOVE */



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
//    private Account savingsAccount;
    private CheckingAccount checkingAccount;

    @Override
    public String toString() {
        return "["+lastName+", "+firstName+"]";
    }

    public Customer(String f,String l){
        firstName=f;
        lastName=l;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Account getSavings(){
        return checkingAccount.getSavingAccount();
    }

    public Account getChecking(){
        return checkingAccount;
    }
    public void setSavings(Account account){
        checkingAccount.setSavingAccount(account);
    }
    public void setChecking(Account account){
        checkingAccount= (CheckingAccount) account;
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

    public SavingsAccount(double protection) {
        super(protection);
    }
}


class CheckingAccount extends Account{

    private Account savingAccount;

    public CheckingAccount(double balance){
        super(balance);
    }
    public CheckingAccount(double balance,double protection){
        super(balance);
        savingAccount=new SavingsAccount(protection);
    }
    public boolean withdraw(double amt){
        if(balance>=amt){
            balance-=amt;
            return true;
        }
        else if(savingAccount.balance+balance>=amt){
            savingAccount.balance-=amt-balance;
            balance=0;
            return true;
        }
        return false;
    }

    public void setSavingAccount(Account savingAccount) {
        this.savingAccount = savingAccount;
    }

    public Account getSavingAccount() {
        return savingAccount;
    }
}
