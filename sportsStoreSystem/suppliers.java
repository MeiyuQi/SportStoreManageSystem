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
	*this class used to get or change infomation of suppliers from suppliers.csv file
	*/
public class suppliers {
	/**
	*the private data filed br 
	*/
	private BufferedReader br = null;
	/**
	*the private data filed list 
	*/
	private List<String> list = new ArrayList<String>();
	/**
	*the private data filed rowNumOfSuppliers and colNumOfSuppliers;
	*/
	private int rowNumOfSuppliers,colNumOfSuppliers;
	/**
	*the private data filed  suppliers array
	*/
	private String suppliers[][]={{"0"},{"0"}};
	/**
	*the data filed recentSupplierRow 
	*/
	int recentSupplierRow;
	/**
     * the no-arg constructor
     **/
	public suppliers() {

    }
	
	/**
	*the constructor with a String parameter fileName
	*this constructor read from csv file and put information to an array
	*throw exception if file does not exist 
	**/
    public suppliers(String fileName) {
           
            try {
				br = new BufferedReader(new FileReader(fileName));
			
            String stemp;
            while ((stemp = br.readLine()) != null) {
                    list.add(stemp);
            }
			
			
            rowNumOfSuppliers = getRowNum();
            colNumOfSuppliers = getColNum();
            suppliers= new String[rowNumOfSuppliers][colNumOfSuppliers];
			for(int k=0;k<rowNumOfSuppliers;k++){
                for(int j=0;j<colNumOfSuppliers;j++){
                	suppliers[k][j]=getString(k, j);
					}
				}
            } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    }
    /**
	* method determineSup()
	* @param supId
	* TODO: manager determine to return product to which supplier
	**/
	public void determineSup(String supId){
		
		for(int i=1;i<rowNumOfSuppliers;i++){
			if(suppliers[i][0].equals(supId)){
				
				recentSupplierRow=i;
				
			}
			
		}
		
	}
	/**
	*method returnByManager()
	*@param amount
	**/
	public void returnByManager(int amount){
		int quaAfterReturn = Integer.parseInt(suppliers[recentSupplierRow][4])+amount;
		suppliers[recentSupplierRow][4]=String.valueOf(quaAfterReturn);
		
	}
	/**
	*method getSupID()
	*@return supplier ID
	**/
    public String getSupID(){
    	
    	return suppliers[recentSupplierRow][0];
    	
    }
    /**
	*method getSupName()
	*@return supplier name
	**/
    public String getSupName(){
    	
    	return suppliers[recentSupplierRow][1];
    
    }
    /**
	*method getSupPro()
	*@return product supplied by supplier
	**/
    public String getSupPro(){
    	//shu zu yue jie 
    	return suppliers[recentSupplierRow][2];
    
    }
    /**
	*method getSupPrice()
	*@return price of product supplied by supplier
	**/
	public String getSupPrice(){
	
	return suppliers[recentSupplierRow][3];
	
	}
	/**
	*method getSupQua()
	*@return product quatity supplied by supplier
	**/
	public String getSupQua(){
	
	return suppliers[recentSupplierRow][4];
	
	}
	
	
	/**
	* method writeToSupplierFile()
	* write information to suppliers.csv file when return by manager finished
	**/
	public void writeToSupplierFile() throws Exception{
		
		File file = new File("suppliers.csv");
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
		for(int i=0;i<rowNumOfSuppliers;i++){
			for(int j=0;j<colNumOfSuppliers;j++){
				
					if(j%4==0&&j!=0){ 
						bw.write(suppliers[i][j]);
						bw.write("\n");
					}
					else{
						bw.write(suppliers[i][j]+",");
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
     *get specific char
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
