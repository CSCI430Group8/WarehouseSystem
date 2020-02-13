import java.text.DecimalFormat;
import java.util.*;
import java.io.*;


/*Product Class*/
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
    private String id,
            name;
    private int quantity;
    private double price;
	private List<Product> WaitList;
	
	/*Product Constructor*/
	Product(String id, String name, double price, int quantity){
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }//end constructor
	
	/*Gets Product ID*/
	public String getId(){
		return id;
	}//end getId
	
	/*Gets Product Name*/
	public String getName(){
		return name;
	}//end getName
	
	/*Gets Product Quantity*/
	public int getQuantity(){
		return quantity;
	}//end getQuantity
	
	/*Gets Product Price*/
	public double getPrice(){
		return price;
	}//end getPrice
	
	/*Sets Product ID*/
	public void setId(String id){
		this.id = id;
	}//end setId
	
	/*Sets Product Name*/
	public void setName(String name){
		this.name = name;
	}//end setName
	
	/*Sets Product Quantity*/
	public void setQuantity(int quantity){
		this.quantity = quantity;
	}//end setQuantity
	
	/*Sets Product Price*/
	public void setPrice(double price){
		this.price = price;
	}//end setPrice

    /*Converts Product to string output*/
    public String toString(){
        return "ID: " + id + " Name: " + name + " Price Per Item: $" + price + " Quantity: " + quantity;
    }//end toString
}