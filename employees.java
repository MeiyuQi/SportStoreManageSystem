package sportsStoreSystem;


import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;
import java.util.List;
	/**
	*@author Qi Meiyu
	*this class used to get infomation of employees from employees.csv file
	*/

public class employees {
	
	/**
	*the private data filed br 
	*/
	private BufferedReader br = null;
	/**
	*the private data filed list 
	*/
	
	private List<String> list = new ArrayList<String>();
	
	/**
	*the private data filed rowNum and colNum
	*/
	private int rowNum,colNum;
	/**
	*the private data filed recentEmployeeRow
	*/
	private int recentEmployeeRow;
	/**
	*the private data filed employees 
	*/
	
	private String employees[][]={{"0"},{"0"}};
	
	 /**
     * the no-arg constructor
     */
	public employees() {

    }
	/**
	*the constructor with a String parameter fileName
	*this constructor read from csv file and put information to an array
	*throw exception if file does not exist 
	*/
	
    public employees(String fileName){
           
            try {
				br = new BufferedReader(new FileReader(fileName));
			
            String stemp;
            while ((stemp = br.readLine()) != null) {
                    list.add(stemp);
            }
			
			
            rowNum = getRowNum();
            colNum = getColNum();
			
			employees = new String[rowNum][colNum];
			for(int k=0;k<rowNum;k++){
                for(int j=0;j<colNum;j++){
                    employees[k][j]=getString(k, j);
							 
                    }
				}
			
            } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
	/*		for(int i=0;i<rowNum;i++)
				for(int j=0;j<colNum;j++)
					System.out.print(employees[i][j]+",");
						System.out.println();
				
		*/
    }
    /**
     * TODO:check if user is employee
     * @param eID
     * @return isEmployees
     */
    public boolean checkEmployees(String eID){
    	boolean isEmployees = false;
    	for(int k=1;k<rowNum;k++){
    		if(employees[k][1].equals(eID)){
    			isEmployees = true;
    			recentEmployeeRow=k;
    		}
    	}
    	
    	return isEmployees;
    }
    /**
	 *method getEmpName()
     * @return employee name
     */
    
	
    public String getEmpName(){
		
		return employees[recentEmployeeRow][0];
	}
	
	/**
	 *method getEmpType()
     * @return employee type
     */
    
	 public String getEmpType(){
		
		return employees[recentEmployeeRow][2];
	}
    
	/**
	 *method getList()
     * @return list
     */
    
    public List getList() {
    			return list;
    	}
	/**
	 *method getRowNum()
     * @return the length of list
     */
    
    	public int getRowNum() {
    			return list.size();
    	}
	/**
	 *method getColNum()
     * @return column of csv file
     */
    
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
     * get specfic char
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

