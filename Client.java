import java.text.DecimalFormat;
import java.util.*;
import java.io.*;


public class Client implements Serializable {
	private static final long serialVersionUID = 1L;
    private double balance;
    private String id,
            name,
            phone,
            address;
    private LinkedList<Product> cart = new LinkedList<Product>();
	
	/*
     * Function:	Client
     * Type:		constructor(generic)
     * Privacy:		public
     * Description:	Client Constructor.
	 */
    Client(String id, String name, String phone, String address){
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.balance = 0.00;//will always start with empty balance
    }//end constructor
    
    /*
     * Function:	Client
     * Type:		constructor(including balance)
     * Privacy:		public
     * Description:	Client Constructor.
	 */
    Client(String id, String name, String phone, String address, double balance){
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.balance = balance;
	}//end constructor
	
	/*
     * Function:	getBalance
     * Type:		double
     * Privacy:		public
     * Description:	Gets Client Balance.
	 */
	public double getBalance(){
		return balance;
	}//end getBalance
	
	/*
     * Function:	getId
     * Type:		String
     * Privacy:		public
     * Description:	Gets Client ID.
	 */
	public String getId(){
		return id;
	}//end getId
	
	/*
     * Function:	getName
     * Type:		String
     * Privacy:		public
     * Description:	Gets Client Name.
	 */
	public String getName(){
		return name;
	}//end getName
	
	/*
     * Function:	getPhone
     * Type:		String
     * Privacy:		public
     * Description:	Gets Client Phone Number.
	 */
	public String getPhone(){
		return phone;
	}//end getPhone
	
	/*
     * Function:	getAddress
     * Type:		String
     * Privacy:		public
     * Description:	Gets Client Address.
	 */
	public String getAddress(){
		return address;
	}//end getAddress
	
	/*
     * Function:	setBalance
     * Type:		void
     * Privacy:		public
     * Description:	Sets Client Balance.
	 */
	public void setBalance(double balance){
		this.balance = balance;
	}//end setBalance
	
	/*
     * Function:	setId
     * Type:		void
     * Privacy:		public
     * Description:	Sets Client ID.
	 */
	public void setId(String id){
		this.id = id;
	}//end setId
	
	/*
     * Function:	setName
     * Type:		void
     * Privacy:		public
     * Description:	Sets Client Name.
	 */
	public void setName(String name){
		this.name = name;
	}//end setName
	
	/*
     * Function:	setPhone
     * Type:		void
     * Privacy:		public
     * Description:	Sets Client Phone Number.
	 */
	public void setPhone(String phone){
		this.phone = phone;
	}//end setPhone
	
	/*
     * Function:	insertToCart
     * Type:		boolean
     * Privacy:		public
     * Description:	Inserts Product into Cart.
	 */
	public boolean insertToCart(Product product) {
		boolean result;
		result = cart.add(product);
		return result;
	}//end insertToCart
	
	/*Sets Client Address*/
	public void setAddress(String address){
		this.address = address;
	}//end setAddress

    /*
     * Function:	toString
     * Type:		String
     * Privacy:		public
     * Description:	Converts Client to string output.
	 */
    public String toString(){
        return "ID: " + id + " Name: " + name + " Balance: " + balance + " Phone Number: " + phone + " Address: " + address;
    }//end display
}