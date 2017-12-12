package sportsStoreSystem;


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;

/**
	*@author Qi Meiyu
	*this class used to get or change infomation of stock from stock.csv file
	*/
public class stock {

	/**
	*the private data filed br 
	*/
	private BufferedReader br = null;
	
	/**
	*the private data filed list 
	*/
	private List<String> list = new ArrayList<String>();
	
	/**
	*the private data filed  rowNumOfStock and colNumOfStock
	*/
	private int rowNumOfStock,colNumOfStock;
	
	/**
	*the private data filed  recentStockRow
	*/
	private int recentStockRow;
	
	/**
	*the private data filed stock array
	*/
	private String stock[][]={{"0"},{"0"}};
	
	/**
	*the private data filed temporaryQua array  
	*/
	ArrayList<String> temporaryQua = new ArrayList<String>();
	
	/**
	*the private data filed temporaryID array
	**/
	ArrayList<String> temporaryID = new ArrayList<String>();
	
	/**
     * the no-arg constructor
     **/
	public stock() {

    }
	
	/**
	*the constructor with a String parameter fileName
	*this constructor read from csv file and put information to an array
	*throw exception if file does not exist 
	**/
    public stock(String fileName){
           
            try {
				br = new BufferedReader(new FileReader(fileName));
			
            String stemp;
            while ((stemp = br.readLine()) != null) {
                    list.add(stemp);
            }
			
			
            rowNumOfStock = getRowNum();
            colNumOfStock = getColNum();
			
            stock= new String[rowNumOfStock][colNumOfStock];
			for(int k=0;k<rowNumOfStock;k++){
                for(int j=0;j<colNumOfStock;j++){
                	stock[k][j]=getString(k, j);
							 
                    }
				}
            } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    }
    /**
     * TODO:check if product is in stock
     * @param proCode
     * @return inStock
     **/
    public boolean checkProductInfo(String proCode){
    	boolean inStock = false;
    	
    	for(int k=1;k<rowNumOfStock;k++){
    		if(stock[k][0].equals(proCode)){
    		
    			recentStockRow=k;
    			inStock = true;
    			
    		}
    	}
    	return inStock;
    	
    }
    /**
	*method getAll()
	*TODO:get all product information in stock
	**/
	
	public void getAll(){
		for(int k=1;k<rowNumOfStock;k++){
                for(int j=0;j<colNumOfStock;j++){				
					System.out.print(stock[k][j]+"\t");
					if(j!=0&&j%3==0)
						System.out.println();
							 
                    }
				}
	}
	
	/**
	*method getSpecific()
	*TODO:get a specific product information in stock
	**/
	
	public void getSpecific(String id){
		for(int k=0;k<rowNumOfStock;k++){
				if(stock[k][0].equals(id)){
					recentStockRow=k;
					System.out.println(stock[k][0]+"\t"+stock[k][1]+"\t"+stock[k][2]+"\t"+stock[k][3]);
				}
		}
	}
	/**
	*method getProCode()
	*@return product code
	**/
	
    public String getProCode(){
    	
    	return stock[recentStockRow][0];
    	
    }
    /**
	*method getProName()
	*@return product name
	**/
	
    public String getProName(){
    	
    	return stock[recentStockRow][1];
    
    }
    /**
	*method getProPrice()
	*@return product price
	**/
	
    public String getProPrice(){
    	
    	return stock[recentStockRow][2];
    
    }
    /**
	*method getProQua()
	*@return product quatity
	**/
	
	public String getProQua(){
	
	return stock[recentStockRow][3];
	
	}
	
	/**
	 *mothod returnPro()
	 * TODO:to increase quantity if customer returns product
	 * */
	public void returnPro(int returnQua){
		int qua = Integer.parseInt(stock[recentStockRow][3]);
		stock[recentStockRow][3] = String.valueOf(qua+returnQua);
		
	}
	
	public void returnToSup(String supProName,int amount){
		for(int k=0;k<rowNumOfStock;k++){
				if(stock[k][1].equals(supProName)){
					recentStockRow=k;
				}
		}
		int quaAfterReturnSup = Integer.parseInt(stock[recentStockRow][3])-amount;
		stock[recentStockRow][3]=String.valueOf(quaAfterReturnSup);
	}
	/**
	 * method salePro()
	 * TODO:to reduce quantity if product sale
	 * */
	public void salePro(){
		//int qua = Integer.parseInt(stock[recentStockRow][3]);
		for(int i =0;i<temporaryID.size();i++){
			for(int j=0;j<rowNumOfStock;j++){
				if(temporaryID.get(i).equals(stock[j][0]))
					stock[j][3]=temporaryQua.get(i);
			}
		}
		//stock[recentStockRow][3] = String.valueOf(temporaryQua);
		
	}
	/**
	* method createTemporaryQua()
	* TODO: create a temporary quantity if user does not make sure receipt
	*@param amount
	**/
	public void createTemporaryQua(int amount){
		int qua = Integer.parseInt(stock[recentStockRow][3]);
		qua-=amount;
		temporaryID.add(stock[recentStockRow][0]);
		temporaryQua.add(String.valueOf(qua));
		
	}
	/**
	* method writeToStockFile()
	* write information to stock.csv file when receipts finished
	**/
	public void writeToStockFile() throws Exception{
		
		File file = new File("stock.csv");
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
		for(int i=0;i<rowNumOfStock;i++){
			for(int j=0;j<colNumOfStock;j++){
				
					if(j%3==0&&j!=0){ 
						bw.write(stock[i][j]);
						bw.write("\n");
					}
					else{
						bw.write(stock[i][j]+",");
					}
					 
				
			}
			
		}
		
		bw.close();
	}
    public List getList() {
    			return list;
    	}
   
    	public int getRowNum() {
    			return list.size();
    	}
    
    	public int getColNum() {
    			if (!list.toString().equals("[]")) {
                    if (list.get(0).toString().contains(",")) {
                            	return list.get(0).toString().split(",").length;
                    	} else if (list.get(0).toString().trim().length() != 0) {
                    			return 1;
                    	} else {
                            return 0;
                    	}
    				}
    			else {
                    return 0;
    				}
    }
    /**
     *
     * @param index
     */
    	public String getRow(int index) {
            if (this.list.size() != 0) {
                    return (String) list.get(index);
            } else {
                    return null;
            }
    }
    /**
     * 
     * @param index
     */
    	public String getCol(int index) {
    			if (this.getColNum() == 0) {
    					return null;
    			}
    			StringBuffer sb = new StringBuffer();
    			String tmp = null;
    			int colnum = this.getColNum();
    			if (colnum > 1) {
    					for (Iterator it = list.iterator(); it.hasNext();) {
    							tmp = it.next().toString();
    							sb = sb.append(tmp.split(",")[index] + ",");
    					}
    			} else {
    					for (Iterator it = list.iterator(); it.hasNext();) {
    							tmp = it.next().toString();
    							sb = sb.append(tmp + ",");
    					}
    			}
    			String str = new String(sb.toString());
    			str = str.substring(0, str.length() - 1);
    			return str;
    	}
    /**
     * get speific char
     * @param row
     * @param col
     */
    	public String getString(int row, int col) {
    			String temp = null;
    			int colnum = this.getColNum();
    			if (colnum > 1) {
    					temp = list.get(row).toString().split(",")[col];
    			} else if(colnum == 1){
                    	temp = list.get(row).toString();
    			} else {
                    	temp = null;
    			}
    			return temp;
    	}
}
