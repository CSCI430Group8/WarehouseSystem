import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.io.*;


public class Order implements Serializable {
	private static final long serialVersionUID = 1L;
    private String id,
			date;
    private LinkedList<Product> orderedItems = new LinkedList<Product>();
	transient DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
	LocalDateTime now;
	
	/*
     * Function:	Order
     * Type:		constructor(generic)
     * Privacy:		public
     * Description:	Order Constructor.
	 */
	Order(String id, LinkedList<Product> orderedItems){
		now = LocalDateTime.now();
		this.id = id;
        this.date = dtf.format(now);
        this.orderedItems = orderedItems;
    }//end constructor

	/*
	 * Function:	Order
	 * Type:		constructor(generic)
	 * Privacy:		public
	 * Description:	Order Constructor.
	 */
	Order(String id){
		now = LocalDateTime.now();
		this.id = id;
		this.date = dtf.format(now);
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
	public String getDate(){
		return date;
	}//end getName
	
	/*
     * Function:	getOrder
     * Type:		Iterator
     * Privacy:		public
     * Description:	Gets product list from order.
	 */
	
	
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
	public void setDate(String date){
		this.date = date;
	}//end setDate
	
	/*
     * Function:	setOrderedItems
     * Type:		void
     * Privacy:		public
     * Description:	Sets items ordered in order.
	 */
	public void setOrderedItems(LinkedList<Product> orderedItems){
		this.orderedItems = orderedItems;
	}//end setOrderedItems

	/*
	 * Function:	addToOrder
	 * Type:		boolean
	 * Privacy:		public
	 * Description:	Sets items ordered in order.
	 */
	public void addToCart(Product product){
		this.orderedItems.add(product);
	}//end setOrderedItems

    /*
     * Function:	toString
     * Type:		String
     * Privacy:		public
     * Description:	Converts Product to string output.
	 */
    public String toString(){
        return "Date: " + date + " ID: " + id + " List of Products: " + " Ordered Items: " + orderedItems;
    }//end toString
}