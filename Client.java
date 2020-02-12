import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Client implements Serializable {
	private static final long serialVersionUID = 1L;
    private double balance;
    private String id,
            name,
            phone,
            address;
    private List<Product> cart;
	
    Client(String id, String name, String phone, String address){
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.balance = 0.00;//will always start with empty balance
    }
	
	/*Gets Client Balance*/
	public double getBalance(){
		return balance;
	}
	
	/*Gets Client ID*/
	public String getId(){
		return id;
	}
	
	/*Gets Client Name*/
	public String getName(){
		return name;
	}
	
	/*Gets Client Phone Number*/
	public String getPhone(){
		return phone;
	}
	
	/*Gets Client Address*/
	public String getAddress(){
		return address;
	}
	
	/*Sets Client Balance*/
	public void setBalance(double balance){
		this.balance = balance;
	}
	
	/*Sets Client ID*/
	public void setId(String id){
		this.id = id;
	}
	
	/*Sets Client Name*/
	public void setName(String name){
		this.name = name;
	}
	
	/*Sets Client Phone Number*/
	public void setPhone(String phone){
		this.phone = phone;
	}

    /*Display is being used to test additions, not using iterator*/
    public void display(){
        System.out.print("ID: " + id + " Name: " + name + " Balance: " + balance + " Phone Number: " + phone + " Address: " + address);
    }
}