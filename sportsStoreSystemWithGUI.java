package sportsStoreSystem;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.lang.String;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JTextArea;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * this class is sport store system with GUI
 * @author Qi Meiyu
 *
 */
public class sportsStoreSystemWithGUI extends JFrame{
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
	static int amountOfPurchase;
	int formerReceiptCode=0;
	/**
	*the data filed flag 
	*/
	int flag=0,k=0;
	/**
	*the data filed transactionDetails
	*/

	String transactionDetails="";
	String transaction="product name"+"        "+"amount        "+"unit cost        "+"total cost        ";
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
	String recentReceiptCode="",text="";
	/**
	*the data filed text field jt
	*/
	private JTextField jt = new JTextField(8);
	/**
	*the data filed text field jt2
	*/
	private JTextField jt2 = new JTextField(8);
	/**
	*the data filed arraylist temporaryReceipt
	*/
	ArrayList<String> temporaryReceipt = new ArrayList<String>();
	/**
	*the data filed button jbtOk
	*/
	private JButton jbtOk = new JButton("OK");
	/**
	*the data filed button jbtCancel
	*/
	private JButton jbtCancel = new JButton("Cancel");
	/**
	*the data filed button jbtSubmit
	*/
	private JButton jbtSubmit = new JButton("Submit");
	/**
	*the data filed button jbtLogIn
	*/
	private static JButton jbtLogIn = new JButton("LogIn");
	/**
	*the data filed button jbtLogOut
	*/
	private static JButton jbtLogOut = new JButton("LogOut");
	/**
	*the data filed button jbtReturn
	*/
	private static JButton jbtReturn = new JButton("Return");
	/**
	*the data filed button jbtPurchase
	*/
	private static JButton jbtPurchase = new JButton("Purchase");
	/**
	*the data filed button jbtStock
	*/
	private JButton jbtStock = new JButton("Stock");
	/**
	*the data filed button jbtSales
	*/
	private JButton jbtSales = new JButton("Sales");
	/**
	*the data filed button jbtAll
	*/
	private JButton jbtAll = new JButton("All");
	/**
	*the data filed button jbtSpecific
	*/
	private JButton jbtSpecific = new JButton("Specific");
	/**
	*the data filed button jbtCompleteTransaction
	*/
	private JButton jbtCompleteTransaction = new JButton("CompleteTransaction");
	/**
	*the data filed button jbtIreland 
	*/
	private JButton jbtIreland = new JButton("Ireland");
	/**
	*the data filed button jbtChina 
	*/
	private JButton jbtChina = new JButton("China");

	/**
	 * the constructor to create a frame
	 */
	public sportsStoreSystemWithGUI(){
		setTitle("Sports Store System");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(200,200);
		setVisible(true);

		jbtLogIn.addActionListener(new logInListener());
		
	}
	/**
	 * main method 
	 * passing files'name as parameter to other class 
	 * @param args
	 */
	public static void main(String [] args){
	
		for(int i=0;i<4;i++){
			if(args[i].equals("stock.csv")){
			s = new stock(args[i]);
			}
			else if(args[i].equals("employees.csv")){
				em = new employees(args[i]);
			}
			else if(args[i].equals("receipts.csv")){
				rec = new receipts(args[i]);
			}
			else if(args[i].equals("suppliers.csv")){
				sup = new suppliers(args[i]);
			}
		}
		JFrame frame = new sportsStoreSystemWithGUI();
		frame.setLayout(new GridLayout(1,1,2,2));
		frame.add(jbtLogIn);
	
	}
	
	/**
	 * when user click log in this listener executed
	 * @author Qi Meiyu
	 *
	 */
	class logInListener implements ActionListener{
			public void actionPerformed(ActionEvent e){
				JFrame frame2 = new sportsStoreSystemWithGUI();
				frame2.setLayout(new GridLayout(3,1,2,2));
				frame2.add(new JLabel("Enter Employee ID"));
				frame2.add(jt);
				frame2.add(jbtSubmit);
				
				jbtSubmit.addActionListener(new SubmitListener());
				
				
			}
	}
	/**
	 * when user click log out this listener executed
	 * @author Qi Meiyu
	 *
	 */
	class logOutListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			JFrame frame5 = new sportsStoreSystemWithGUI();
			frame5.add(new JLabel("Successful log out."));
		}
		
	}
	/**
	 * when user click return this listener executed
	 * @author Qi Meiyu
	 *
	 */
	class proReturnListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try{
				execute();
			}
			catch(Exception ex){
				System.out.println("exception1");
			}
		}
			public  void execute() throws Exception{
				JFrame frame6 = new sportsStoreSystemWithGUI();

				if(em.getEmpType().equals("Manager")){
					System.out.println("manager");
					JButton jbtSubmit7 = new JButton("Submit");
				frame6.setLayout(new GridLayout(5,1,2,2));
				jt.setText("");
				jt2.setText("");
				
				frame6.add(new JLabel("Enter supplier ID"));
				frame6.add(jt);
				frame6.add(new JLabel("Enter return amount"));
				frame6.add(jt2);
				frame6.add(jbtSubmit7);
				jbtSubmit7.addActionListener(new SubmitListener1());
				
			}
			else{
		
				frame6.setLayout(new GridLayout(3,1,2,2));
				jt.setText("");
				JButton jbtSubmit5 = new JButton("Submit");
				frame6.add(new JLabel("Enter return receipt Id"));
				frame6.add(jt);
				frame6.add(jbtSubmit5);
				jbtSubmit5.addActionListener(new SubmitListener5());
			}
		}
	}
	/**
	 * when user click purchase this listener executed
	 * @author Qi Meiyu 
	 *
	 */
	class purchaseListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
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
			
			JFrame frame12 = new sportsStoreSystemWithGUI();
			frame12.setLayout(new GridLayout(3,1,2,2));
			JButton jbtSubmit1 = new JButton("Submit");
			jt.setText("");
			frame12.add(new JLabel("Enter Purchase Code"));
			frame12.add(jt);
			frame12.add(jbtSubmit1);
			jbtSubmit1.addActionListener(new SubmitListener3());
			
		}	
	}	
	/**
	 * when user click completeTransactionListener this listener executed
	 * @author Qi Meiyu
	 *
	 */
	class completeTransactionListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			JFrame frame16 = new sportsStoreSystemWithGUI();
			frame16.setLayout(new GridLayout(3,1,2,2));
			frame16.add(new JLabel("Please chose region."));
			frame16.add(jbtIreland);
			frame16.add(jbtChina);
			
			jbtIreland.addActionListener(new IrelandTaxListener());
			jbtChina.addActionListener(new ChinaTaxListener());
			
			//frame16.add(new JLabel("Transaction Details"));
			//frame16.add(new JLabel(transactionDetails+"\n"+"Sub-total: "+subTotle+"\n"+"Tax("+(int)(tax*100)+"%)"+"\t\t"+subTotle*tax+"\n"+"Total\t\t"+(subTotle+subTotle*tax)));
			
		}
				
	}
	/**
	 * when user choose Ireland as region this listener executed
	 * @author Qi Meiyu
	 *
	 */
	class IrelandTaxListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			tax=0.1;
			JFrame frame19 = new sportsStoreSystemWithGUI();
			frame19.setLayout(new GridLayout(8,1,5,5));
			frame19.add(new JLabel("Transaction Details"));
			frame19.add(new JLabel(transaction));
			frame19.add(new JLabel(transactionDetails));
			frame19.add(new JLabel("Sub-total: "+subTotle));
			frame19.add(new JLabel("Tax("+(int)(tax*100)+"%)"+"\t\t"+subTotle*tax));
			frame19.add(new JLabel("Total\t\t"+(subTotle+subTotle*tax)));
			frame19.add(jbtOk);
			frame19.add(jbtCancel);
			
			jbtOk.addActionListener(new OkListener());
			jbtCancel.addActionListener(new CancelListener());
		}
	}
/**
 * when user click Ok this listener executed
 * @author Qi Meiyu
 *
 */
	class OkListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			try{
				execute();
			}
			catch(Exception ex){
				System.out.println("exception2");
			}
			
			
		}
		public void execute(){
			createReceipt();
			try{
				rec.writeToReceiptsFile();
			}
			catch(Exception ex){
				System.out.println("exception3");
			}
			s.salePro();
			try{
				s.writeToStockFile();
			}
			catch(Exception ex){
				System.out.println("exception4");
			}
			JFrame frame21 = new sportsStoreSystemWithGUI();
			frame21.setLayout(new GridLayout(1,1,5,5));
			frame21.add(new JLabel("Thank you for your purchase. Your receipt has been printed."));
		}
			
		public void createReceipt(){
		for(int i=0;i<temporaryReceipt.size();i++){
			rec.addReceipt(temporaryReceipt.get(i));
		}
		
	}
	}
	/**
	 * this listener executed when user click Ok after they chose China as region 
	 */
	class chinaOkListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			try{
				execute();
			}
			catch(Exception ex){
				System.out.println("exception2");
			}
			
			
		}
		public void execute(){
			createReceipt();
			
			try{
				rec.writeToReceiptsFile();
			}
			catch(Exception ex){
				System.out.println("exception3");
			}
			s.salePro();
			try{
				s.writeToStockFile();
			}
			catch(Exception ex){
				System.out.println("exception4");
			}
			JFrame frame21 = new sportsStoreSystemWithGUI();
			frame21.setLayout(new GridLayout(1,1,5,5));
			frame21.add(new JLabel("Thank you for your purchase. Your receipt has been printed."));
		}
			
		public void createReceipt(){
		for(int i=0;i<temporaryReceipt.size();i++){
			rec.addReceipt(temporaryReceipt.get(i));
		}
		
	}
	}
	/**
	 * when user click Cancel this listener executed
	 * @author Qi Meiyu
	 *
	 */
	class CancelListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			JFrame frame22 = new sportsStoreSystemWithGUI();
			frame22.setLayout(new GridLayout(1,1,5,5));
			frame22.add(new JLabel("your receipt has been canceled"));
		}
	}
	/**
	 * when user chose China as region this listener executed
	 * @author Qi Meiyu
	 *
	 */
	class ChinaTaxListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			tax=0.2;
		
			JFrame frame20 = new sportsStoreSystemWithGUI();
			 JButton jbtOk2 = new JButton("OK");
			frame20.setLayout(new GridLayout(8,1,5,5));
			frame20.add(new JLabel("Transaction Details"));
			frame20.add(new JLabel(transaction));
			frame20.add(new JLabel(transactionDetails));
			frame20.add(new JLabel("Sub-total: "+subTotle));
			frame20.add(new JLabel("Tax("+(int)(tax*100)+"%)"+"\t\t"+subTotle*tax));
			frame20.add(new JLabel("Total\t\t"+(subTotle+subTotle*tax)));
			frame20.add(jbtOk2);
			frame20.add(jbtCancel);
			
			jbtOk2.addActionListener(new chinaOkListener());
			jbtCancel.addActionListener(new CancelListener());
		}
	}
	/**
	 * when manager want to get information of receipts this listener executed
	 * @author Qi Meiyu
	 *
	 */
	class salesInfoListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			JFrame frame17 = new sportsStoreSystemWithGUI();
			JButton jbtSubmit9 = new JButton("Submit");
			jt.setText("");
			frame17.setLayout(new GridLayout(3,1,2,2));
			frame17.add(new JLabel("Enter date (format:dd/MM/yyyy)"));
			frame17.add(jt);
			frame17.add(jbtSubmit9);
			
			jbtSubmit9.addActionListener(new SubmitListener9());
		}
	}
	/**
	 * when manager want to get information of stock this listener executed
	 * @author Qi Meiyu
	 *
	 */
	class stockInfoListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			JFrame frame18 = new sportsStoreSystemWithGUI();
			frame18.setLayout(new GridLayout(2,1,2,2));
			frame18.add(jbtAll);
			frame18.add(jbtSpecific);
			
			jbtAll.addActionListener(new AllListener());
			jbtSpecific.addActionListener(new SpecificListener());
			
		}
		
	}
	/**
	 * when manager want to get all products'information this listener executed
	 * @author Qi Meiyu
	 *
	 */
	class AllListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			s.getAll();
		}
		
	}
	/**
	 * when manager want to get a specific products'infomation this listener executed
	 * @author Qi Meiyu
	 *
	 */
	class SpecificListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			JFrame frame21 = new sportsStoreSystemWithGUI();
			frame21.setLayout(new GridLayout(3,1,2,2));	
			jt.setText("");
			JButton jbtSubmit8 = new JButton("Submit");
			frame21.add(new JLabel("Enter Product Code"));
			frame21.add(jt);
			frame21.add(jbtSubmit8);
			jbtSubmit8.addActionListener(new SubmitListener8());
		}
		
	}
	/**
	 * when user click submit this listener executed
	 * @author Qi Meiyu
	 *
	 */
	class SubmitListener implements ActionListener{
		public void actionPerformed(ActionEvent e){	
			if(em.checkEmployees(jt.getText())){
				JFrame frame3 = new sportsStoreSystemWithGUI();
				if(em.getEmpType().equals("Manager")){
					frame3.setLayout(new GridLayout(6,1,2,2));
					frame3.add(new JLabel("Welcome "+em.getEmpName()));
					frame3.add(jbtLogOut);
					frame3.add(jbtPurchase);
					frame3.add(jbtReturn);
					frame3.add(jbtStock);
					frame3.add(jbtSales);
					jbtSales.addActionListener(new salesInfoListener());
					jbtStock.addActionListener(new stockInfoListener());
					jbtLogOut.addActionListener(new logOutListener());
					jbtReturn.addActionListener(new proReturnListener());
					jbtPurchase.addActionListener(new purchaseListener());
				}	
	
				else {
					frame3.setLayout(new GridLayout(4,1,2,2));
					frame3.add(new JLabel("Welcome "+em.getEmpName()));
					frame3.add(jbtLogOut);
					frame3.add(jbtPurchase);
					frame3.add(jbtReturn);
					jbtLogOut.addActionListener(new logOutListener());
					jbtReturn.addActionListener(new proReturnListener());
					jbtPurchase.addActionListener(new purchaseListener());
				}
			}
			else{
					JFrame frame4 = new sportsStoreSystemWithGUI();
					frame4.add(new JLabel("Sorry,your employee ID is not right.Please check."));
			}
	
		}
	}
	/**
	 * when user click submit7 this listener executed
	 * @author Qi Meiyu
	 *
	 */
	class SubmitListener1 implements ActionListener{
		public void actionPerformed(ActionEvent e){
			sup.determineSup(jt.getText());
			s.returnToSup(sup.getSupPro(),Integer.parseInt(jt2.getText()));
			sup.returnByManager(Integer.parseInt(jt2.getText()));
			try{
							s.writeToStockFile();
						}
						catch(Exception ex){
							System.out.println("exception5");
						}
						try{
							sup.writeToSupplierFile();
						}
						catch(Exception ex){
							System.out.println("exception6");
						}
						
						JFrame frame7 = new sportsStoreSystemWithGUI();
						frame7.add(new JLabel("Successful return"));
						
		}
	}
	/**
	 * when user click submit6 this listener executed
	 * @author Qi Meiyu
	 *
	 */
	class SubmitListener2 implements ActionListener{
		public void actionPerformed(ActionEvent e){
			s.getSpecific(jt.getText());
			s.returnPro(Integer.parseInt(jt2.getText()));
					try{
							s.writeToStockFile();
						}
						catch(Exception ex){
							System.out.println("exception7");
						}
						
						JFrame frame10 = new sportsStoreSystemWithGUI();
						frame10.add(new JLabel("Successful return"));
				
		}
	}
	/**
	 * when user click submit1 this listener executed
	 * @author Qi Meiyu
	 *
	 */
	
	class SubmitListener3 implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(s.checkProductInfo(jt.getText())){
				JFrame frame14 = new sportsStoreSystemWithGUI();
						frame14.setLayout(new GridLayout(5,1,2,2));
						//JTextField jt7 = new JTextField(8);
						jt.setText("");
						JButton jbtSubmit2 = new JButton("Submit");
						frame14.add(new JLabel("Current Stock: "+s.getProQua()));
						frame14.add(new JLabel("Price: "+s.getProPrice()));
						frame14.add(new JLabel("How many would you like to purchase?"));
						frame14.add(jt);
						frame14.add(jbtSubmit2);
						jbtSubmit2.addActionListener(new SubmitListener4());
			}
			else{
				JFrame frame13 = new sportsStoreSystemWithGUI();
				frame13.add(new JLabel("Sorry,we have not this product.Please check the purchase code."));
			}
			
						
		}
	}
	/**
	 * when user click submit2 this listener executed
	 * @author Qi Meiyu
	 *
	 */
	
	class SubmitListener4 implements ActionListener{
		public void actionPerformed(ActionEvent e){
			SubmitListener4 a = new SubmitListener4();
		    			amountOfPurchase=Integer.parseInt(jt.getText());
						s.createTemporaryQua(amountOfPurchase);
						a.createTransactionDetails();
						a.createTemporaryReceipt();
						
						JFrame frame15 = new sportsStoreSystemWithGUI();
						frame15.setLayout(new GridLayout(3,1,2,2));
						frame15.add(new JLabel("These items have been added"));
						frame15.add(jbtPurchase);
						frame15.add(jbtCompleteTransaction);
						
						jbtPurchase.addActionListener(new purchaseListener());
						jbtCompleteTransaction.addActionListener(new completeTransactionListener());
			
		}
		/**
		 * a method createTemporaryReceipt
		 */
		public void createTemporaryReceipt(){
		
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		temporaryReceipt.add(df.format(new Date())+","+recentReceiptCode+","+s.getProCode()+","+s.getProPrice()+","+String.valueOf(amountOfPurchase)+"\n");
		

		}
		/**
		 * a method createTransactionDetails
		 */
		public void createTransactionDetails(){	
		
		transactionDetails+=s.getProName()+"        		"+amountOfPurchase+"        		"+s.getProPrice()+"        		"+amountOfPurchase*Integer.parseInt(s.getProPrice())+"					";
		subTotle+=amountOfPurchase*Integer.parseInt(s.getProPrice());
		
		
		}
		
		
	}
	/**
	 * when user click submit5 this listener executed
	 * @author Qi Meiyu
	 *
	 */
	
	class SubmitListener5 implements ActionListener{
		public void actionPerformed(ActionEvent e){
			SimpleDateFormat dfs = new SimpleDateFormat("dd/MM/yyyy");
			Date end = null;
			try {
				end = dfs.parse(dfs.format(new Date()));
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			long between=0;
			Date begin = null;
			try {
				begin = dfs.parse(rec.getDate(jt.getText()));
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			between = (end.getTime() - begin.getTime());
			if(between<=30){
				JFrame frame9 = new sportsStoreSystemWithGUI();
				JButton jbtSubmit6 = new JButton("Submit");
				frame9.setLayout(new GridLayout(5,1,2,2));
				jt.setText("");
				frame9.add(new JLabel("Enter return product Id"));
				frame9.add(jt);
				frame9.add(new JLabel("Enter amount of return product"));
				frame9.add(jt2);
				frame9.add(jbtSubmit6);

				jbtSubmit6.addActionListener(new SubmitListener2());
			}
			else{
				JFrame frame11 = new sportsStoreSystemWithGUI();
				frame11.add(new JLabel(" Sorry,your purchase days greater than 30 days."));
			}
		}
	}
	/**
	 * when user click submit8 this listener executed
	 * @author Qi Meiyu
	 *
	 */
	
	class SubmitListener8 implements ActionListener{
		public void actionPerformed(ActionEvent e){
	
			s.getSpecific(jt.getText());
		
		}
	}				
	/**
	 * when user click submit9 this listener executed
	 * @author Qi Meiyu
	 *
	 */
	
	class SubmitListener9 implements ActionListener{
		public void actionPerformed(ActionEvent e){
			
			rec.getReceipt(jt.getText());
		}
	}
}
