package BankAssesment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;

//Creating the Customer class
class Customer{
	private String firstName;
	private String lastName;
	
	//Adding constructor which assigins the values of firstName and Lastname when its object is created
	public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

	//Since the variables are private we need to use getters and setters inorder to access their values.
	public String getFirstName() {
		return firstName;	
	}
	public String getLastName() {
		return lastName;
	}
	
	public void setFirstName(String FName) {
		this.firstName = FName;
	}
	public void setLastName(String LName) {
		this.lastName = LName;
	}
	
}

//Creating the Account class which is the child class of Customer class so it extends to Customer class in order to get FirstName and LastName
class Account extends Customer{
	private int balance;
	private int accountNumber;
	
	
	public Account(String FName, String LName, int accNum, int accBal) {
        super(FName, LName); //superclass inorder to access its parent class variable.
        this.balance = accBal;
        this.accountNumber = accNum;
    }
	
	public int getBalance() {
		return balance;
	}
	public int getAccountNum() {
		return accountNumber;
	}
	
	//Method to deposit the amount given by user into account
	public void deposit(int amount) {
		if(amount>0) {
			balance += amount;
		}
		else {
			System.out.println("Invalid deposit amount...");
		}
		
	}
	
	//Method to Withdraw the required amount from the account
	public void withdraw(int amount) {
		if(amount<balance && amount>0) {
			balance -= amount;
		}
		else {
			System.out.println("Invalid withdraw Amount");
		}
	}
}

//Creating Transaction class where the transaction is peformed i.e withdraw and deposit method are peformed
class Transaction{
	public void transfer(Account acc1, Account acc2, int amount) {
		if(acc1.getBalance()>amount && amount>0) {
			acc1.withdraw(amount);
			acc2.deposit(amount);
			System.out.println("Transaction succssfull");
		}
		else {
			System.out.println("invalid amount Transaction failed");
		}
	}
}

//Creating Seperate ReadAccounts class inorder to read the data from csv file and store to seperate LinkedList Variable.
class ReadAccounts{
	//storing the individual value to its linked list
	public LinkedList<String> firstNames = new LinkedList<>();
	public LinkedList<String> lastNames = new LinkedList<>();
	public LinkedList<Integer> accounts = new LinkedList<>();
	public LinkedList<Integer> balances = new LinkedList<>();
	
	String url;
	
	public ReadAccounts(String URL) {
		url = URL;
	{
		
	try {
	//initiating bufferedreader to read the data from the file	
	BufferedReader reader = new BufferedReader(new FileReader(url));
	String line;
	while((line = reader.readLine()) != null) {
		String data [] = line.split(",");//As the csv file include comma seperated value this line of code spereates values and store them to data array.
		firstNames.add(data[0]);//assigning value to linkedlist array variable from the data array
		lastNames.add(data[1]);
		accounts.add(Integer.parseInt(data[2]));
		balances.add(Integer.parseInt(data[3]));
	}
	}
	
	catch (Exception e) {
        e.printStackTrace();
		}
	
	}	
		}
	
	// Getters and setters inorder to access the values of the private variable
public LinkedList<String> getfirstNames(){
	return firstNames;
	}

public LinkedList<String> getlastNames(){
	return lastNames;
	}
public LinkedList<Integer> getaccounts(){
	return accounts;
	}
public LinkedList<Integer> getbalances(){
	return balances;
	}

}

public class MainJava {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String filePath = "Accounts.csv";
		ReadAccounts readacc = new ReadAccounts(filePath);
		
		//? Individual linked list to store all individual data
		LinkedList<String> firstName = readacc.getfirstNames();
		System.out.println(firstName);
		LinkedList<String> lastName = readacc.getlastNames();
		LinkedList<Integer> accountList = readacc.getaccounts();
		LinkedList<Integer> balanceList = readacc.getbalances();
		
		//linked list to hold all of the account data
		LinkedList<Account> accounts = new LinkedList<>();
		
		for(int i = 0;i<firstName.size();i++) {
			accounts.add(new Account(firstName.get(i), lastName.get(i),accountList.get(i), balanceList.get(i)));
		}
		//Creating the instance of the GUI class and passing the accounts
		GUIBANK guibank = new GUIBANK(accounts);
		guibank.setVisible(true);
	}
}

