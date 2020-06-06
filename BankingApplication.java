import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BankingApplication {

    public static void main(String[] args) throws Exception {

        List<String> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("/Users/danayfernandez/Desktop/Programing Tutorials and Practice/Projects/Banking Apllication/Customerinfo.csv"))) {
        String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                for(String token : values){
                    records.add(token.toString());
                }
            }
        }
        catch (Exception e) {
            
        }

        Scanner getInfo = new Scanner(System.in);
        System.out.println("Please enter your Customer ID");
        String customerID = getInfo.nextLine();

        

        if(!records.contains(customerID)){
            FileWriter saveInfoExists = new FileWriter("/Users/danayfernandez/Desktop/Programing Tutorials and Practice/Projects/Banking Apllication/Customerinfo.csv", true);
            System.out.println("Please enter your Full Name");
            String customerNAME = getInfo.nextLine();
            System.out.println("Please enter your current balance");
            String balance = getInfo.nextLine();
            double customerBAL = Double.parseDouble(balance);

            records.add(customerID);
            records.add(customerNAME);
            records.add(balance);
            saveInfoExists.append(customerID);
            saveInfoExists.append(",");
            saveInfoExists.append(customerNAME);
            saveInfoExists.append(",");
            saveInfoExists.append(balance);
            saveInfoExists.append("\n");
            
             BankAccount currentCustomer = new BankAccount(customerID,customerNAME, customerBAL);
             currentCustomer.showMenu();

             saveInfoExists.flush();
             saveInfoExists.close();   
         }
        else{
            String customerNAME = records.get(records.indexOf(customerID)+1).toString();
            String balance = records.get(records.indexOf(customerID)+2).toString();
            double customerBAL = Double.parseDouble(balance);
            BankAccount currentCustomer = new BankAccount(customerID,customerNAME, customerBAL);
            Double newBAL = currentCustomer.showMenu();
            String newbal = Double.toString(newBAL);
            FileWriter saveInfoNotExists = new FileWriter("/Users/danayfernandez/Desktop/Programing Tutorials and Practice/Projects/Banking Apllication/Customerinfo.csv");

            records.set(records.indexOf(customerID)+2, newbal);

            for (int i=0; i < records.size()-2; i+=33
            {
                saveInfoNotExists.append(records.get(i));
                saveInfoNotExists.append(",");
                saveInfoNotExists.append(records.get(i+1));
                saveInfoNotExists.append(",");
                saveInfoNotExists.append(records.get(i+2));
                saveInfoNotExists.append("\n");
            }
            saveInfoNotExists.flush();
            saveInfoNotExists.close();   

        }

       
        

   getInfo.close();
   
    }

}
 class BankAccount{
    
    double balance;
    double previousTransaction;
    String customerName;
    String customerId;

    BankAccount(String cname, String cid, double bal){
        customerName = cname;
        customerId = cid;
        balance = bal;
    }

    void deposit(double amount){
        if(amount != 0){
            balance += amount;
            previousTransaction = amount;
        }
    }

    void withdraw(double amount){
        if(amount != 0){
            balance -= amount;
            previousTransaction = -amount;
        }
    }

    void getPreviousTransaction(){
        if(previousTransaction>0){
            System.out.println("You deposited "+previousTransaction);
        }
        else if (previousTransaction<0){
            System.out.println("You witdrawn "+Math.abs(previousTransaction));
        }
        else{
            System.out.println("No previous transaction ocurred");
        }
    }

    double showMenu(){
        char option='\0';
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome " + customerName);
        System.out.println("Your ID is "+ customerId);
        System.out.println("Your current balance is " + balance);
        System.out.println("\n");
        System.out.println("Available options are:");
        System.out.println("A. Check Balance");
        System.out.println("B. Deposit");
        System.out.println("C. Withdraw");
        System.out.println("D. Previous Transaction");
        System.out.println("E. Exit");

        do
        {
            System.out.println("=================================================");
            System.out.println("Enter an option");
            System.out.println("=================================================");
            option = sc.next().charAt(0);
            System.out.println("\n");

            switch(option){
                case 'A':
                case 'a':
                    System.out.println("------------------------------------------------");
                    System.out.println("Balance = "+ balance);
                    System.out.println("------------------------------------------------");
                    System.out.println("\n");
                    break;
                case 'B':
                case 'b':
                    System.out.println("-------------------------------------------------");
                    System.out.println("Enter an amount to deposit");
                    System.out.println("-------------------------------------------------");
                    deposit(sc.nextDouble());
                    System.out.println("-------------------------------------------------");
                    System.out.println("Balance = "+ balance);
                    System.out.println("------------------------------------------------");
                    System.out.println("\n");
                    break;
                case 'C':
                case 'c':
                    System.out.println("-------------------------------------------------");
                    System.out.println("Enter an amount to withdraw");
                    System.out.println("-------------------------------------------------");
                    withdraw(sc.nextDouble());
                    System.out.println("-------------------------------------------------");
                    System.out.println("Balance = "+ balance);
                    System.out.println("------------------------------------------------");
                    System.out.println("\n");
                    break;
                case 'D':
                case 'd':
                    System.out.println("--------------------------------------------------");
                    getPreviousTransaction();
                    System.out.println("--------------------------------------------------");
                    System.out.println("\n");
                    break;
                case 'E':
                    System.out.println("***************************************************");
                    break;
                default:
                    System.out.println("Invalid option!");
                    break;

            
            }
        }
            while(option !='E');
            
            System.out.println("Thank you for using our services");
         
            sc.close();
            return balance;
        }
        

    }

 

