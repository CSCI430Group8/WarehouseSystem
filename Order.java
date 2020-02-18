import java.text.DecimalFormat;
import java.util.*;
import java.io.*;
import java.util.GregorianCalendar;


public class Order implements Serializable {
	private static final long serialVersionUID = 1L;
    private String id;
    private GregorianCalendar date;
    ProductList orderedItems;
	
	/*
     * Function:	Order
     * Type:		constructor(generic)
     * Privacy:		public
     * Description:	Order Constructor.
	 */
	Order(GregorianCalendar date, String id, ProductList orderedItems){
        this.id = id;
        this.date = date;
        this.orderedItems = orderedItems;
    }//end constructor
	
	/*
     * Function:	getId
     * Type:		String
     * Privacy:		public
     * Description:	Gets Client ID of order.
	 */
	public String getId(){
		return id;
	}//end getId
	
	/*
     * Function:	getDate
     * Type:		Date
     * Privacy:		public
     * Description:	Gets Order Date.
	 */
	public GregorianCalendar getDate(){
		return date;
	}//end getName
	
	/*
     * Function:	getOrder
     * Type:		ProductList
     * Privacy:		public
     * Description:	Gets product list from order.
	 */
	public ProductList getOrder(){
		return orderedItems;
	}//end getOrder
	
	
	/*
     * Function:	setId
     * Type:		void
     * Privacy:		public
     * Description:	Sets Order ID.
	 */
	public void setId(String id){
		this.id = id;
	}//end setId
	
	/*
     * Function:	setDate
     * Type:		void
     * Privacy:		public
     * Description:	Sets Order Date.
	 */
	public void setDate(GregorianCalendar date){
		this.date = date;
	}//end setDate
	
	/*
     * Function:	setOrderedItems
     * Type:		void
     * Privacy:		public
     * Description:	Sets items ordered in order.
	 */
	public void setOrderedItems(ProductList orderedItems){
		this.orderedItems = orderedItems;
	}//end setOrderedItems
	

    /*
     * Function:	toString
     * Type:		String
     * Privacy:		public
     * Description:	Converts Product to string output.
	 */
    public String toString(){
        return "Date: " + date + " ID: " + id + "List of Products" + orderedItems;
    }//end toString
}