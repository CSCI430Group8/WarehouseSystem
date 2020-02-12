import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Supplier implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id,
            name,
            phone,
            address;
			
    Supplier(String id, String name, String phone, String address){
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
    }
	
	/*Gets Supplier ID*/
	public String getId(){
		return id;
	}
	
	/*Gets Supplier Name*/
	public String getName(){
		return name;
	}
	
	/*Gets Supplier Phone Number*/
	public String getPhone(){
		return phone;
	}
	
	/*Gets Supplier Address*/
	public String getAddress(){
		return address;
	}
	
	/*Sets Supplier ID*/
	public void setId(String id){
		this.id = id;
	}
	
	/*Sets Supplier Name*/
	public void setName(String name){
		this.name = name;
	}
	
	/*Sets Supplier Phone Number*/
	public void setPhone(String phone){
		this.phone = phone;
	}
	
	/*Sets Supplier Address*/
	public void setAddress(String address){
		this.address = address;
	}

    /*Display is being used to test additions, not using iterator*/
    public void display(){
        System.out.print("ID: " + id + " Name: " + name + " Phone Number: " + phone + " Address: " + address);
    }
}