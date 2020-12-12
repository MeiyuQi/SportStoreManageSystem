package sportsStoreSystem;


import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.lang.String;
/**
	*@author Qi Meiyu
	*this class used to enable user to log in or log out or purchase or return or check information 
	*/
public class sportsStoreSystem {
	/**
	*the private static data filed userInput 
	*/
	private static String  userInput = "";
	/**
	*the static data filed in 
	*/
	static Scanner in = new Scanner(System.in);
	/**
	*the static data filed s 
	*/
	static stock s;
	/**
	*the static data filed em 
	*/
	static employees em;
	/**
	*the static data filed rec 
	*/
	static receipts rec;
	/**
	*the static data filed sup 
	*/
	static suppliers sup;
	/**
	*the private static data filed amountOfPurchase and formerReceiptCode
	*/
	static int amountOfPurchase,formerReceiptCode=0;
	/**
	*the data filed flag 
	*/
	int flag=0;
	/**
	*the data filed transactionDetails
	*/
	String transactionDetails="product name\t"+"amount\t"+"unit cost\t"+"total cost\t\n";
	/**
	*the data filed subTotle
	*/
	int subTotle;
	/**
	*the data filed tax 
	*/
	double tax;
	/**
	*the data filed recentReceiptCode
	*/
	String recentReceiptCode="";
	/**
	*the data filed temporaryReceipt
	*/
	ArrayList<String> temporaryReceipt = new ArrayList<String>();
	
	/**
	*method main()
	*enable user log in ,log out,purchase,return 
	**/
	public static void main(String [] args) throws Exception{
		sportsStoreSystem st = new sportsStoreSystem();
		for(int i=0;i<args.length;i++){
			if(args[i].equals("stock.csv")){
					 s = new stock(args[i]);
			}
			else if(args[i].equals("employees.csv")){
				
				em = new employees(args[i]);
			}
			else if(args[i].equals("suppliers.csv")){
				sup = new suppliers(args[i]);
			}
			else {
				
				rec = new receipts(args[i]);
			}
		}
		
		
		System.out.println("Log I)n		Log O)ut	P)urchase	R)eturn");
		
		userInput=in.nextLine();

		if(userInput.equals("I")){
			st.logIn();
		}
		else if(userInput.equals("O")){
			st.logOut();
		}
		else if(userInput.equals("P")){
			System.out.println("Sorry,you have not log in.");
		}
		else if(userInput.equals("R")){
			st.proReturn();
		}
	}
	/**
	* method logIn()
	* execute after user chose log in,check user information if he is employee
	**/
	public void logIn()throws Exception{
		System.out.println("Enter Employee ID");
		userInput=in.nextLine();
		if(em.checkEmployees(userInput)){
			System.out.println("Welcome "+em.getEmpName());
			if(em.getEmpType().equals("Manager")){
				System.out.println("Log O)ut	P)urchase	R)eturn		St)ock 		Sa)les ");
			}
			else {
				System.out.println("Log O)ut	P)urchase	R)eturn");
			}
			
			
			userInput=in.nextLine();
			if(userInput.equals("O")){
				logOut();
			}
			else if(userInput.equals("P")){
				purchase();
			}
			else if(userInput.equals("R")){
				proReturn();
			}
			else if(userInput.equals("St")){
				System.out.println("A)ll	S)pecific");
				userInput=in.nextLine();
				if(userInput.equals("A")){
					s.getAll();
				}
				else if(userInput.equals("S")){
					System.out.println("Enter Product Code");
					userInput=in.nextLine();
					s.getSpecific(userInput);
				}
			}
			else if(userInput.equals("Sa")){
				System.out.println("Enter date (format:dd/MM/yyyy)");
				userInput=in.nextLine();
				rec.getReceipt(userInput);
			}
		}
		else{
			System.out.println("Sorry,your employee ID is not right.Please check.");
			logIn();
		}
	}
	/**
	* method logOut()
	* execute after user chose log out
	**/
	public void logOut(){
		System.out.println("Successful log out.");
		int flag=0;
		
	}
	/**
	* method purchase()
	* enable user to buy product
	**/
	public void purchase()throws Exception{
		
		if(flag==0){
				int lastIndexOfZero=rec.getReceiptId().lastIndexOf("0");
				if(lastIndexOfZero==rec.getReceiptId().length()-1){
					int num=Integer.parseInt(rec.getReceiptId().substring(lastIndexOfZero));
					num++;
					recentReceiptCode=rec.getReceiptId().substring(0,lastIndexOfZero)+String.valueOf(num);
				}
				else{
					int num=Integer.parseInt(rec.getReceiptId().substring(lastIndexOfZero+1));
					num++;
					recentReceiptCode=rec.getReceiptId().substring(0,lastIndexOfZero+1)+String.valueOf(num);
				}
				
			}
		
		System.out.println("Enter Purchase Code");
		
		userInput=in.nextLine();
		if(s.checkProductInfo(userInput)){
			System.out.println("Current Stock: "+s.getProQua());
			System.out.println("Price: "+s.getProPrice());
			System.out.println("How many would you like to purchase?");
			
			amountOfPurchase=Integer.parseInt(in.nextLine());
			s.createTemporaryQua(amountOfPurchase);
			createTransactionDetails();
			createTemporaryReceipt();
			System.out.println("These items have been added");
			
			System.out.println("P)urchase 	C)omplete Transaction");
			
			userInput=in.nextLine();
			if(userInput.equals("P")){
				flag=1;
				purchase();
			}
			else if(userInput.equals("C")){
				System.out.println("Please chose region.");
				choseRegion();
				System.out.println("Transaction Details");
				showTransactionDetails();
				
			}
		}
		else{
			System.out.println("Sorry,we have not this product.Please check the purchase code.");
			purchase();
		}
	}
	/**
	* method proReturn()
	* enable user to return product
	**/
	public void proReturn()throws Exception{
		if(em.getEmpType().equals("Manager")){
			System.out.println("Enter supplier ID");
			userInput=in.nextLine();
			sup.determineSup(userInput);
			
			System.out.println("Enter return amount");
			userInput=in.nextLine();
			s.returnToSup(sup.getSupPro(),Integer.parseInt(userInput));
			sup.returnByManager(Integer.parseInt(userInput));
			s.writeToStockFile();
			sup.writeToSupplierFile();
			System.out.println("Successful return");
		}
		else{
			SimpleDateFormat dfs = new SimpleDateFormat("dd/MM/yyyy");
		Date end = dfs.parse(dfs.format(new Date()));
        long between=0;
		System.out.println("Enter return receipt Id");
		
		userInput=in.nextLine();
		Date begin = dfs.parse(rec.getDate(userInput));
		between = (end.getTime() - begin.getTime());
		if(between<=30){
			System.out.println("Enter return product Id");
			userInput=in.nextLine();
			s.getSpecific(userInput);
			
			System.out.println("Enter amount of return product");
			userInput=in.nextLine();
			s.returnPro(Integer.parseInt(userInput));
		
			s.writeToStockFile();
			System.out.println("Successful return");
		}
		else{
			System.out.println(" Sorry,your purchase days greater than 30 days.");
		}
		}
		
	}
	/**
	* method choseRegion()
	* enable user to chose region to determine tax
	**/
	public void choseRegion(){
		System.out.println("I)reland   C)hina");
		userInput=in.nextLine();
		if(userInput.equals("I")){
			tax=0.1;
		}
		else if(userInput.equals("C")){
			tax=0.2;
		}
			
		
	}
	/**
	* method createTemporaryReceipt()
	* create temporary receipt when user does not make sure purchase
	**/
	public void createTemporaryReceipt(){
		
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		temporaryReceipt.add(df.format(new Date())+","+recentReceiptCode+","+userInput+","+s.getProPrice()+","+String.valueOf(amountOfPurchase)+"\n");
		

	}
	/**
	* method createReceipt()
	* create receipt when user make sure purchase
	**/
	public void createReceipt(){
		for(int i=0;i<temporaryReceipt.size();i++){
			rec.addReceipt(temporaryReceipt.get(i));
		}
		
	}
	/**
	* method createTransactionDetails()
	**/
	public void createTransactionDetails(){
		transactionDetails+=s.getProName()+"\t\t"+amountOfPurchase+"\t"+s.getProPrice()+"\t\t"+amountOfPurchase*Integer.parseInt(s.getProPrice())+"\n";
		subTotle+=amountOfPurchase*Integer.parseInt(s.getProPrice());
		
		
	}
	/**
	* method showTransactionDetails()
	**/
	public void showTransactionDetails()throws Exception{
		System.out.println(transactionDetails+"\n"+"Sub-total: "+subTotle+"\n"+"Tax("+(int)(tax*100)+"%)"+"\t\t"+subTotle*tax+"\n"+"Total\t\t"+(subTotle+subTotle*tax));
		System.out.println("C)ontinue	S)top");
		userInput=in.nextLine();
		if(userInput.equals("C")){
			createReceipt();
			rec.writeToReceiptsFile();
			s.salePro();
			s.writeToStockFile();
			System.out.println("Thank you for your purchase. Your receipt has been printed. ");	
		}
		else if(userInput.equals("S")){
			System.out.println("Log I)n		Log O)ut	P)urchase	R)eturn");
		}
		
	}
}
