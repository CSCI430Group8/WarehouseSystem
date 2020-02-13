import java.text.DecimalFormat;
import java.util.*;
import java.io.*;


public class SupplierList implements Serializable {
	private static final long serialVersionUID = 1L;
	private List suppliers = new LinkedList();
	private static SupplierList supplierList;
	
	/*SupplierList Constructor*/
	private SupplierList() {
	}//end constructor
	
	/*Creates an instance of SupplierList*/
	public static SupplierList instance() {
		if (supplierList == null) {
			return (supplierList = new SupplierList());
		} 
		else {
			return supplierList;
		}
	}//end instance
	
	/*Inserts Supplier into SupplierList*/
	public boolean insertSupplier(Supplier supplier) {
		suppliers.add(supplier);
		return true;
	}//end insertSupplier

	/*Returns an iterator for SupplierList*/
	public Iterator getSuppliers(){
		return suppliers.iterator();
	}//end getSuppliers
  
	/*Writes SupplierList to disk*/
	private void writeObject(java.io.ObjectOutputStream output) {
		try {
			output.defaultWriteObject();
			output.writeObject(supplierList);
		} 
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}//end writeObejct
	
	/*Reads SupplierList from disk*/
	private void readObject(java.io.ObjectInputStream input) {
		try {
			if (supplierList != null) {
				return;
			} 
			else {
				input.defaultReadObject();
				if (supplierList == null) {
					supplierList = (SupplierList) input.readObject();
				} 
				else {
					input.readObject();
				}
			}
		} 
		catch(IOException ioe) {
			ioe.printStackTrace();
		} 
		catch(ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
	}//end readObject
	
	/*Converts SupplierList to string output*/
	public String toString() {
		return suppliers.toString();
	}//end toString
}