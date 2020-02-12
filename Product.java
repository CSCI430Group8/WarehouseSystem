import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/*Product Class*/
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
    private String id,
            name;
    private int quantity;
    private double price;
	
	Product(String id, String name, double price, int quantity){
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
	
	/*Gets Product ID*/
	public String getId(){
		return id;
	}
	
	/*Gets Product Name*/
	public String getName(){
		return name;
	}
	
	/*Gets Product Quantity*/
	public int getQuantity(){
		return quantity;
	}
	
	/*Gets Product Price*/
	public double getPrice(){
		return price;
	}
	
	/*Sets Product ID*/
	public void setId(String id){
		this.id = id;
	}
	
	/*Sets Product Name*/
	public void setName(String name){
		this.name = name;
	}
	
	/*Sets Product Quantity*/
	public void setQuantity(int quantity){
		this.quantity = quantity;
	}
	
	/*Sets Product Price*/
	public void setPrice(double price){
		this.price = price;
	}

    /*Display is being used to test additions to inventory, not using iterator*/
    public void display(){
        System.out.print("ID: " + id + " Name: " + name + " Price Per Item: $" + price + " Quantity: " + quantity);
    }
}