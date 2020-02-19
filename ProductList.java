import java.text.DecimalFormat;
import java.util.*;
import java.io.*;


public class ProductList implements Serializable {
	private static final long serialVersionUID = 1L;
	private LinkedList<Product> products = new LinkedList<Product>();
	private static ProductList productList;
	
	/*
     * Function:	ProductList
     * Type:		constructor(generic)
     * Privacy:		private
     * Description:	ProductList Constructor.
	 */
	private ProductList() {
	}//end constructor
	
	/*
     * Function:	instance
     * Type:		static ProductList
     * Privacy:		public
     * Description:	Creates instance of ProductList.
	 */
	public static ProductList instance() {
		if (productList == null) {
			return (productList = new ProductList());
		} 
		else {
			return productList;
		}
	}//end instance
	
	/*
     * Function:	insertProduct
     * Type:		boolean
     * Privacy:		public
     * Description:	Inserts Product into ProductList.
	 */
	public boolean insertProduct(Product product) {
		boolean result;
		result = products.add(product);
		return result;
	}//end insertProduct

	/*
     * Function:	getProducts
     * Type:		Iterator
     * Privacy:		public
     * Description:	Returns an iterator for ProductList.
	 */
	public Iterator getProducts(){
		return products.iterator();
	}//end getProducts

	/*
	 * Function:	setProductPrice
	 * Type:		boolean
	 * Privacy:		public
	 * Description:
	 */
	public boolean setProductPrice(String id, double price){
		Boolean entryFound = false;
		for(int i = 0; !entryFound & i < products.size(); i++){
			if(products.get(i).getId().contentEquals(id)){
				entryFound = true;
				products.get(i).setPrice(price);
			}//end if
		}//end for
		return entryFound;//product is found and changed
	}//end setProductName

	/*
	 * Function:	setProductQuantity
	 * Type:		boolean
	 * Privacy:		public
	 * Description:
	 */
	public boolean setProductQuantity(String id, int quantity){
		Boolean entryFound = false;
		for(int i = 0; !entryFound & i < products.size(); i++){
			if(products.get(i).getId().contentEquals(id)){
				entryFound = true;
				products.get(i).setQuantity(quantity);
			}//end if
		}//end for
		return entryFound;//product is found and changed
	}//end setProductQuantity

	/*
	 * Function:	setProductName
	 * Type:		boolean
	 * Privacy:		public
	 * Description:
	 */
	public boolean setProductName(String id, String name){
		Boolean entryFound = false;
		for(int i = 0; !entryFound & i < products.size(); i++){
			if(products.get(i).getId().contentEquals(id)){
				entryFound = true;
				products.get(i).setName(name);
			}//end if
		}//end for
		return entryFound;//product is found and changed
	}//end setProductName

	/*
	 * Function:	writeObject
	 * Type:		void
	 * Privacy:		private
	 * Description:	Writes ProductList to disk.
	 */
	private void writeObject(java.io.ObjectOutputStream output) {
		try {
			output.defaultWriteObject();
			output.writeObject(productList);
		} 
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}//end writeObject
	
	/*
     * Function:	readObject
     * Type:		void
     * Privacy:		private
     * Description:	Reads ProductList from disk.
	 */
	private void readObject(java.io.ObjectInputStream input) {
		try {
			if (productList != null) {
				return;
			} 
			else {
				input.defaultReadObject();
				if (productList == null) {
					productList = (ProductList) input.readObject();
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
	
	/*
     * Function:	toString
     * Type:		String
     * Privacy:		public
     * Description:	Converts ProductList to string output.
	 */
	public String toString() {
		return products.toString();
	}//end toString
}