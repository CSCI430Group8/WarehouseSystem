import java.text.DecimalFormat;
import java.util.*;
import java.io.*;


public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
    private String id,
            name;
    private int quantity;
    private double price;
	
	/*
     * Function:	Product
     * Type:		constructor(generic)
     * Privacy:		public
     * Description:	Product Constructor.
	 */
	Product(String id, String name, double price, int quantity){
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }//end constructor
	
	/*
     * Function:	getId
     * Type:		String
     * Privacy:		public
     * Description:	Gets Product ID.
	 */
	public String getId(){
		return id;
	}//end getId
	
	/*
     * Function:	getName
     * Type:		String
     * Privacy:		public
     * Description:	Gets Product Name.
	 */
	public String getName(){
		return name;
	}//end getName
	
	/*
     * Function:	getQuantity
     * Type:		int
     * Privacy:		public
     * Description:	Gets Product Quantity.
	 */
	public int getQuantity(){
		return quantity;
	}//end getQuantity
	
	/*
     * Function:	getPrice
     * Type:		double
     * Privacy:		public
     * Description:	Gets Product Price.
	 */
	public double getPrice(){
		return price;
	}//end getPrice
	
	/*
     * Function:	setId
     * Type:		void
     * Privacy:		public
     * Description:	Sets Product ID.
	 */
	public void setId(String id){
		this.id = id;
	}//end setId
	
	/*
     * Function:	setName
     * Type:		void
     * Privacy:		public
     * Description:	Sets Product Name.
	 */
	public void setName(String name){
		this.name = name;
	}//end setName
	
	/*
     * Function:	setQuantity
     * Type:		void
     * Privacy:		public
     * Description:	Sets Product Quantity.
	 */
	public void setQuantity(int quantity){
		this.quantity = quantity;
	}//end setQuantity
	
	/*
     * Function:	setPrice
     * Type:		void
     * Privacy:		public
     * Description:	Sets Product Price.
	 */
	public void setPrice(double price){
		this.price = price;
	}//end setPrice

    /*
     * Function:	toString
     * Type:		String
     * Privacy:		public
     * Description:	Converts Product to string output.
	 */
    public String toString(){
        return "ID: " + id + " Name: " + name + " Price Per Item: $" + price + " Quantity: " + quantity;
    }//end toString
}