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
	*this class used to get or change infomation of receipts from receipts.csv file
	*/
public class receipts {

	/**
	*the private data filed br 
	*/
	private BufferedReader br = null;
	/**
	*the private data filed list 
	*/
	private List<String> list = new ArrayList<String>();
	/**
	*the private data filed rowNumOfReceipts and colNumOfReceipts
	*/
	private int rowNumOfReceipts,colNumOfReceipts;
	/**
	*the private data filed newReceipt 
	*/
	private ArrayList<String> newReceipt = new ArrayList<String>();
	/**
	*the private data filed receipt array 
	*/
	private String  receipt[][];
	
	/**
     * the no-arg constructor
     **/

	public receipts() {

    }
	/**
	*the constructor with a String parameter fileName
	*this constructor read from csv file and put information to an array
	*throw exception if file does not exist 
	**/
    public receipts(String fileName) {
           
            try {
				br = new BufferedReader(new FileReader(fileName));
			
            String stemp;
            while ((stemp = br.readLine()) != null) {
                    list.add(stemp);
            }
			
			
            rowNumOfReceipts = getRowNum();
            colNumOfReceipts = getColNum();
			
			receipt= new String[rowNumOfReceipts][colNumOfReceipts];
			for(int k=0;k<rowNumOfReceipts;k++){
                for(int j=0;j<colNumOfReceipts;j++){
                	 receipt[k][j]=getString(k, j);
							 
                    }
				}
				
			
			for(int i=0;i<rowNumOfReceipts;i++){
				newReceipt.add(receipt[i][0]+","+receipt[i][1]+","+receipt[i][2]+","+receipt[i][3]+","+receipt[i][4]+"\n");
					
			}	
			
            } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			/*for(int i=0;i<newReceipt.size();i++)
				System.out.print(newReceipt.get(i));
			
			for(int i=0;i<rowNum;i++)
				for(int j=0;j<colNum;j++)
					System.out.print(receipt[i][j]+",");
						System.out.println();
				
		*/
    }
	/**
	*method getReceiptId()
	*@return receipts ID
	**/
	public String getReceiptId(){
		return receipt[rowNumOfReceipts-1][1];
	}
	public void addReceipt(String rec){
		newReceipt.add(rec);
		
					
	}
	/**
	*method getDate()
	*@param recId
	*@return receipts date
	**/
	public String getDate(String recId){
		String recDate="";
		for(int i=1;i<rowNumOfReceipts;i++){
			if(receipt[i][1].equals(recId)){
				recDate=receipt[i][0];
			}
		}
		return recDate;
	}
	/**
	*method getReceipt()
	*@param recDate
	**/
	public void getReceipt(String recDate){
	//	System.out.println(newReceipt.get(0));
		for(int i=1;i<newReceipt.size();i++){
			if(newReceipt.get(i).contains(recDate)){
				System.out.println(newReceipt.get(i));
			}
			else 
				System.out.println("Sorry,"+recDate+" has not receipt");
		}
		
	}
	/**
	* method writeToReceiptsFile()
	* write information to receipts.csv file when receipts finished
	**/
	public void writeToReceiptsFile() throws Exception{
		
		File file = new File("receipts.csv");
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
		for(int i=0;i<newReceipt.size();i++){
			bw.write(newReceipt.get(i));
			
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
     * get specific char
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
