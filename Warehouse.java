import java.text.DecimalFormat;
import java.util.*;
import java.io.*;



public class Warehouse implements Serializable {
    transient Scanner inputScanner = new Scanner(System.in);//create scanner for input

    private static Warehouse warehouse;
	private ClientList clients;
	private SupplierList suppliers;
	private ProductList inventory;
	private BackorderList backorders;

    /*
     * Function:	Warehouse
     * Type:		constructor(generic)
     * Privacy:		private
     * Description:	Constructor for Warehouse class. This is made private because
     * 				it is using the singleton methodology to make sure only one
     * 				Warehouse can be initialized at a time
     */
    private Warehouse(){
        clients = ClientList.instance();
        suppliers = SupplierList.instance();
        inventory = ProductList.instance();
        backorders = BackorderList.instance();
    }//end constructor

    /*
     * Function:	instance
     * Type:		Warehouse
     * Privacy:		public
     * Description:	This is the singleton for Warehouse, which, if were to
     * 				try an initialize a second Warehouse class it would
     * 				restrict access to the constructor and only return a copy
     *  			of the current Warehouse class.
     */
    public static Warehouse instance(){
        if (warehouse == null){
            warehouse = new Warehouse();
        }
        return warehouse;
    }//end instance
	
	/*
     * Function:	getClients
     * Type:		Iterator
     * Privacy:		public
     * Description:	This returns an iterator for the ClientList that allows
					for traversal through the different Clients within the
					list for the tester.
     */
	public Iterator getClients() {
		return clients.getClients();
	}//end getClients
	
	/*
     * Function:	getSuppliers
     * Type:		Iterator
     * Privacy:		public
     * Description:	This returns an iterator for the SupplierList that allows
					for traversal through the different Suppliers within the
					list for the tester.
     */
	public Iterator getSuppliers() {
		return suppliers.getSuppliers();
	}//end getSuppliers

	/*
     * Function:	getProducts
     * Type:		Iterator
     * Privacy:		public
     * Description:	This returns an iterator for the ProductList that allows
					for traversal through the different Products within the
					list for the tester.
     */
	public Iterator getProducts() {
		return inventory.getProducts();
	}//end getProducts
	
	/*
     * Function:	getBackorders
     * Type:		Iterator
     * Privacy:		public
     * Description:	This returns an iterator for the BackorderList that allows
					for traversal through the different backorders within the
					list for the tester.
     */
	public Iterator getBackorders() {
		return backorders.getBackorders();
	}//end getProducts
	
	/*
     * Function:	addBackorders
     * Type:		void
     * Privacy:		public
     * Description:	Add an order to the backorder list.
     */
	public void addBackorders(String id, LinkedList<Product> orderedItems) {
		backorders.addBackorder(id, orderedItems);
	}//end getProducts
	
	/*
     * Function:	addClient
     * Type:		Client
     * Privacy:		public
     * Description:	This adds a Client to the ClientList, and then it returns 
					if the Client that was added.
     */
	public Client addClient(String id, String name, String phone, String address) {
		Client client = new Client(id, name, phone, address);
		if (clients.insertClient(client)) {
			return (client);
		}
		return null;
	}//end addClient
	
	/*
     * Function:	addClient(with balance)
     * Type:		Client
     * Privacy:		public
     * Description:	This adds a Client to the ClientList, and then it returns
					if the Client that was added. Balance option has been added to method for direct creation.
     */
	public Client addClient(String id, String name, String phone, String address, double balance) {
		Client client = new Client(id, name, phone, address, balance);
		if (clients.insertClient(client)) {
			return (client);
		}
		return null;
	}//end addClient
	
	/*
     * Function:	addSupplier
     * Type:		Supplier
     * Privacy:		public
     * Description:	This adds a Supplier to the SupplierList, and then it returns 
					if the Supplier that was added.
     */
	public Supplier addSupplier(String id, String name, String phone, String address) {
		Supplier supplier = new Supplier(id, name, phone, address);
		if (suppliers.insertSupplier(supplier)) {
			return (supplier);
		}
		return null;
	}//end addSupplier
	
	/*
     * Function:	addProduct
     * Type:		Product
     * Privacy:		public
     * Description:	This adds a Product to the ProductList, and then it returns 
					if the Product that was added.
     */
	public Product addProduct(String id, String name, double price, int quantity) {
		Product product = new Product(id, name, price, quantity);
		if (inventory.insertProduct(product)) {
			return (product);
		}
		return null;
	}//end addProduct
	
	/*
     * Function:	addToClientCart
     * Type:		boolean
     * Privacy:		public
     * Description:	This finds the Client using their ID and then 
					inserts a Product into their Cart.
	 */
	public boolean addToClientCart(Product product, String id) {
		boolean entryFound = false;
		Iterator allClients = clients.getClients();
		while (!entryFound && allClients.hasNext()){//search for item by id
			Client nextClient = (Client)(allClients.next());
                if(nextClient.getId().contentEquals(id)) {//if item is found
                    entryFound = true;
                    nextClient.insertToCart(product);
                }//end if
        }//end while
		
		return entryFound;
	}//end addToClientCart
	
	/*
     * Function:	removeClient
     * Type:		boolean
     * Privacy:		public
     * Description:	This removes a Client from the ClientList, and then it returns 
					if the Client that was removed.
     */
	public boolean removeClient(String id) {
		boolean entryFound = false;
		Iterator allClients = warehouse.getClients();
        while(!entryFound & allClients.hasNext()){
            Client nextClient = (Client)(allClients.next());
            if(nextClient.getId().contentEquals(id)){
                entryFound = true;
                allClients.remove();
            }
        }
		return entryFound;
	}//end removeClient
	
	/*
     * Function:	removeSupplier
     * Type:		boolean
     * Privacy:		public
     * Description:	This removes a Supplier from the SupplierList, and then it returns 
					if the Supplier that was removed.
     */
	public boolean removeSupplier(String id) {
		boolean entryFound = false;
		Iterator allSuppliers = warehouse.getSuppliers();
        while(!entryFound & allSuppliers.hasNext()){
            Supplier nextSupplier = (Supplier)(allSuppliers.next());
            if(nextSupplier.getId().contentEquals(id)){
                entryFound = true;
                allSuppliers.remove();//Remove because it's easier to add a modified version
            }
        }
		return entryFound;
	}//end removeSupplier
	
	/*
     * Function:	removeProduct
     * Type:		boolean
     * Privacy:		public
     * Description:	This removes a Product from the ProductList, and then it returns 
					if the Product that was removed.
     */
	public boolean removeProduct(String id) {
		boolean entryFound = false;
		Iterator allProducts = warehouse.getProducts();
        while(!entryFound & allProducts.hasNext()){
            Product nextProduct = (Product)(allProducts.next());
            if(nextProduct.getId().contentEquals(id)){
                entryFound = true;
                allProducts.remove();
            }
        }
		return entryFound;
	}//end removeProduct
	
	/*
     * Function:	retrieve
     * Type:		Warehouse
     * Privacy:		public
     * Description:	Retrieves data from file called WarehouseData and
					sets all of the data into the warehouse database.
     */
	public static Warehouse retrieve() {
		try {
			FileInputStream file = new FileInputStream("WarehouseData");
			ObjectInputStream input = new ObjectInputStream(file);
			input.readObject();
			return warehouse;
		} 
		catch(IOException ioe) {
			ioe.printStackTrace();
			return null;
		} 
		catch(ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
			return null;
		}
	}//end retrieve
	
	/*
     * Function:	save
     * Type:		boolean
     * Privacy:		public
     * Description:	Outputs data into a file called WarehouseData and
					saves it for future use.
     */
	public static boolean save() {
		try {
			FileOutputStream file = new FileOutputStream("WarehouseData");
			ObjectOutputStream output = new ObjectOutputStream(file);
			output.writeObject(warehouse);
			return true;
		} 
		catch(IOException ioe) {
			ioe.printStackTrace();
			return false;
		}
	}//end save
	
	/*
     * Function:	writeObject
     * Type:		void
     * Privacy:		private
     * Description:	Writes Warehouse class to disk.
     */
	private void writeObject(java.io.ObjectOutputStream output) {
		try {
			output.defaultWriteObject();
			output.writeObject(warehouse);
		} 
		catch(IOException ioe) {
			System.out.println(ioe);
		}
	}//end writeObject
	
	/*
     * Function:	readObject
     * Type:		void
     * Privacy:		private
     * Description:	Reads Warehouse class from disk.
     */
	private void readObject(java.io.ObjectInputStream input) {
		try {
			input.defaultReadObject();
			if (warehouse == null) {
				warehouse = (Warehouse) input.readObject();
			} 
			else {
				input.readObject();
			}
		} 
		catch(IOException ioe) {
			ioe.printStackTrace();
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
	}//end readObject
	
	/*
     * Function:	toString
     * Type:		boolean
     * Privacy:		private
     * Description:	Converts Warehouse into string output.
     */
	public String toString() {
		return clients + "\n" + suppliers + "/n" + inventory;
	}//end toString
}