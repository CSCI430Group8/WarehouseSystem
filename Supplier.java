import java.text.DecimalFormat;
import java.util.*;
import java.io.*;

public class Supplier implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id,
            name,
            phone,
            address;
	
	/*Supplier Constructor*/	
    Supplier(String id, String name, String phone, String address){
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
    }//end constuctor
	
	/*Gets Supplier ID*/
	public String getId(){
		return id;
	}//end getId
	
	/*Gets Supplier Name*/
	public String getName(){
		return name;
	}//end getName
	
	/*Gets Supplier Phone Number*/
	public String getPhone(){
		return phone;
	}//end getPhone
	
	/*Gets Supplier Address*/
	public String getAddress(){
		return address;
	}//end getAddress
	
	/*Sets Supplier ID*/
	public void setId(String id){
		this.id = id;
	}//end setId
	
	/*Sets Supplier Name*/
	public void setName(String name){
		this.name = name;
	}//end setName
	
	/*Sets Supplier Phone Number*/
	public void setPhone(String phone){
		this.phone = phone;
	}//end setPhone
	
	/*Sets Supplier Address*/
	public void setAddress(String address){
		this.address = address;
	}//end setAddress

    /*Converts Supplier to string output*/
    public String toString(){
        return "ID: " + id + " Name: " + name + " Phone Number: " + phone + " Address: " + address;
    }//end toString
}