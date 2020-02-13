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
    private List<Product> cart;
	
	/*Client Constructor*/
    Client(String id, String name, String phone, String address){
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.balance = 0.00;//will always start with empty balance
    }//end constructor

	Client(String id, String name, String phone, String address, double balance){
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.balance = balance;
	}//end constructor
	
	/*Gets Client Balance*/
	public double getBalance(){
		return balance;
	}//end getBalance
	
	/*Gets Client ID*/
	public String getId(){
		return id;
	}//end getId
	
	/*Gets Client Name*/
	public String getName(){
		return name;
	}//end getName
	
	/*Gets Client Phone Number*/
	public String getPhone(){
		return phone;
	}//end getPhone
	
	/*Gets Client Address*/
	public String getAddress(){
		return address;
	}//end getAddress
	
	/*Sets Client Balance*/
	public void setBalance(double balance){
		this.balance = balance;
	}//end setBalance
	
	/*Sets Client ID*/
	public void setId(String id){
		this.id = id;
	}//end setId
	
	/*Sets Client Name*/
	public void setName(String name){
		this.name = name;
	}//end setName
	
	/*Sets Client Phone Number*/
	public void setPhone(String phone){
		this.phone = phone;
	}//end setPhone

	/*Sets Client Address*/
	public void setAddress(String address){
		this.address = address;
	}//end setAddress

    /*Converts Client to string output*/
    public String toString(){
        return "ID: " + id + " Name: " + name + " Balance: " + balance + " Phone Number: " + phone + " Address: " + address;
    }//end display
}