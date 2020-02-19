import java.util.*;
import java.io.*;

public class Userinterface {
	//Menu Choices
    static final int EXIT = 0,
                     ADD_CLIENTS_PRODUCTS_SUPPLIES = 1,
                     EDIT_CLIENTS_PRODUCTS_SUPPLIES = 2,
                     ACCEPT_CLIENT_ORDERS = 3,
                     ACCEPT_CLIENT_PAYMENT = 4,
                     ACCEPT_SHIPMENT = 5,
                     ADD_ITEM_TO_CART = 6,
    //Query Choices
                     LIST_CLIENT_TRANSACTIONS = 7,
                     LIST_SUPPLIER_PRICES = 8,
                     LIST_NEGATIVE_BALANCES = 9,
                     LIST_BACKORDERS = 10,
                     LIST_PURCHASE_PRICES = 11,
                     LIST_INVENTORY = 12,
					 LIST_ALL_CLIENTS = 13,
					 LIST_ALL_SUPPLIERS = 14,
                     LIST_CUSTOMER_TRANSACTIONS = 15,
					 SAVE = 16,
					 RETRIEVE = 17,
                     HELP = 18;
					
    //Add Choices
  	static final int CLIENT = 1,
  					 PRODUCT = 2,
  					 SUPPLIER = 3;

  	//product choices
  	static final int  PRODUCT_NAME = 1,
                      PRODUCT_PRICE = 2,
                      PRODUCT_QUANTITY = 3;

    //customer choices
    static final int  CLIENT_NAME = 1,
                      CLIENT_PHONE = 2,
                      CLIENT_ADDRESS = 3,
                      CLIENT_BALANCE = 4;

    //supplier choices
    static final int  SUPPLIER_NAME = 1,
					  SUPPLIER_PHONE = 2,
					  SUPPLIER_ADDRESS = 3;
					 
	transient Scanner inputScanner = new Scanner(System.in);//create scanner for input
	private static Userinterface userinterface;
	private static Warehouse warehouse;


	/*
     * Function:	Userinterface
     * Type:		constructor(generic)
     * Privacy:		private
     * Description:	Constructor for Userinterface class. This is made private because
     * 				it is using the singleton methodology to make sure only one
     * 				Userinterface can be initialized at a time
     */
	private Userinterface() {
		char input;
		System.out.print("Would you like to load a save? (Y/N)");
		input = inputScanner.next().charAt(0);
    	if(input == ('y') || input == ('Y'))
    		retrieve();
		else {
			warehouse = Warehouse.instance();
		}
	}
	
	/*
     * Function:	instance
     * Type:		Userinterface
     * Privacy:		public
     * Description:	This is the singleton for Userinterface, which, if were to
     * 				try an initialize a second Userinterface class it would
     * 				restrict access to the constructor and only return a copy
     *  			of the current Userinterface class.
     */
	public static Userinterface instance() {
		if (userinterface == null) {
			return userinterface = new Userinterface();
		} 
		else {
			return userinterface;
		}
	}
	
	/*Business Processes*/

    /*
     * Function:	addClientProductsSupplier
     * Type:		void
     * Privacy:		public
     * Description:	Once called will use ask which of the 3 types you will
     * 				want to add; Clients, Products, or suppliers. Once specified,
     * 				You will be asked to enter all of the information for the
     * 				type you wish to enter. After you have entered the information you
     * 				will be asked to verify this data, if it is not correct you will
     * 				be asked to retype the data. After this has happened, if you are
     * 				entering a Product you will be asked if you wish to enter another.
     * 
     */
    public void addClientProductsSupplies(){

        int input = EXIT + 1;//arbitrary non-exit number

        while(input != EXIT) {

            int quantity = 0;
			
            double price = 0;
			
            String name,
                    id,
                    phone,
                    address;

            System.out.println("\nWhat would you like to add?\n" + 
					EXIT + ".) Go Back\n" +
                    CLIENT + ".) Add Client\n"+
                    PRODUCT + ".) Add Product\n"+
                    SUPPLIER + ".) Add Supplier\n");

            boolean entryFound = false,
					result = false;
            input = inputScanner.nextInt();
			

            switch(input){
                case EXIT:
                    break;
                case CLIENT:
					entryFound = false;
                    System.out.print("\nClient ID: ");
                    id = inputScanner.next();
                    System.out.print("\nName: ");
                    name = inputScanner.next();
                    System.out.print("\nPhone Number: ");
                    phone = inputScanner.next();
                    System.out.print("\nAddress: ");
                    address = inputScanner.next();
                    Client dummyClient = new Client(id, name, phone, address);

					Iterator allClients = warehouse.getClients();
                    /*Check if ID is already found in system. Can't have different items of same ID*/
					while (!entryFound && allClients.hasNext()){//search for item by id
						Client nextClient = (Client)(allClients.next());
                        if(nextClient.getId().contentEquals(dummyClient.getId()))//if item is found
                           entryFound = true;
                    }//end while
                    if(entryFound) {
                        System.out.println("ID is already present in system; Item not added.");
                    } else {//When the Client is not listed
                        dummyClient = warehouse.addClient(id, name, phone, address);
						if (dummyClient == null) {
							System.out.println("Could not add client, try again.");
						}
                    }
                    break;
                case PRODUCT:
					entryFound = false;
                    /*Inputs for Product*/
                    System.out.print("\nProduct ID: ");
                    id = inputScanner.next();
                    System.out.print("\nProduct Name: ");
                    name = inputScanner.next();
                    System.out.print("\nProduct Price: ");
                    price = Math.round(inputScanner.nextDouble() * 100.0) / 100.0;//rounds to 2 decimals
                    System.out.print("\nProduct Quantity: ");
                    quantity = inputScanner.nextInt();

                    Product dummyProduct = new Product(id,name,price,quantity);//create dummy entry based on inputs
					
					Product nextProduct = new Product("","",1,1);
					Iterator allProducts = warehouse.getProducts();
                    /*Check if ID is already found in system. Must update the quantity if found*/
					while (!entryFound && allProducts.hasNext()){//search for item by id
						nextProduct = (Product)(allProducts.next());
                        if(nextProduct.getId().contentEquals(dummyProduct.getId())) {//if item is found
                            entryFound = true;
							allProducts.remove();
							quantity = nextProduct.getQuantity() + dummyProduct.getQuantity();
                            dummyProduct = warehouse.addProduct(nextProduct.getId(),nextProduct.getName(),nextProduct.getPrice(),quantity);
							if (dummyProduct != null) {
								System.out.println("Product quantity update successful.");
							}
                        }//end if
                    }//end while
					if(entryFound) {
                        System.out.println("ID is already present in system; Item quantity updated.");
                    } else {//When the Product is not listed
                        dummyProduct = warehouse.addProduct(id, name, price, quantity);
						if (dummyProduct == null) {
							System.out.println("Could not add product, try again.");
						}
                    }
                    break;
                case SUPPLIER:
					entryFound = false;
                    System.out.print("\nSupplier ID: ");
                    id = inputScanner.next();
                    System.out.print("\nName: ");
                    name = inputScanner.next();
                    System.out.print("\nPhone Number: ");
                    phone = inputScanner.next();
                    System.out.print("\nAddress: ");
                    address = inputScanner.next();
                    Supplier dummySupplier = new Supplier(id, name, phone, address);

					Iterator allSuppliers = warehouse.getSuppliers();
                    /*Check if ID is already found in system. Can't have different suppliers of same ID*/
					while (!entryFound && allSuppliers.hasNext()){//search for supllier by id
						Supplier nextSupplier = (Supplier)(allSuppliers.next());
                        if(nextSupplier.getId().contentEquals(dummySupplier.getId()))//if item is found
                            entryFound = true;
                    }//end while
                    if(entryFound) {
                        System.out.println("ID is already present in system; Item not added.");
                    } else {//When the Product is not listed
                        dummySupplier = warehouse.addSupplier(id, name, phone, address);
						if (dummySupplier == null) {
							System.out.println("Could not add supplier, try again.");
						}
                    }//end if-else
                    break;
                default:
                    System.out.println("Not a valid input.\n");
                    break;
            }//end switch
        }//end while
    }//end method

    
    /*
     * Function:	editClientProductsSupplier
     * Type:		void
     * Privacy:		public
     * Description:	Once called will use ask which of the 3 types you will
     * 				want to add; Clients, Products, or suppliers. Once specified,
     * 				You will be asked to specify which field on the type that you would
     *              like to edit.
     *
     */
    public void editClientProductsSupplies(){
        int input = EXIT + 1;//arbitrary non-exit number

        while(input != EXIT) {
            System.out.println("\nWhich would you like to edit?\n" + 
					EXIT + ".) Go Back\n" +
                    CLIENT + ".) Edit Client\n"+
                    PRODUCT + ".) Edit Product\n"+
                    SUPPLIER + ".) Edit Supplier\n");
            String id;
            input = inputScanner.nextInt();

            switch(input) {
                case EXIT:
                    break;
                case CLIENT:
                    System.out.print("Input the id of the item to edit: ");
                    id = inputScanner.next();

                    System.out.println("\nWhich value would you like to edit? " +
                            "\n" + CLIENT_NAME + ".) Name" +
                            "\n" + CLIENT_PHONE + ".) Phone Number" +
                            "\n" + CLIENT_ADDRESS + ".) Address" +
                            "\n" + CLIENT_BALANCE + ".) Balance");

                    input = inputScanner.nextInt();

                    switch (input) {
                        case CLIENT_NAME:
                            System.out.println("Input the new name.");
                            if(warehouse.setClientName(id, inputScanner.next()))
                                System.out.println("Name has been changed.");
                            else
                                System.out.println("ID not found, name not changed.");
                            break;
                        case CLIENT_PHONE:
                            System.out.println("Input the new phone number.");
                            if(warehouse.setClientPhone(id, inputScanner.next()))
                                System.out.println("Phone number has been changed.");
                            else
                                System.out.println("ID not found, phone number not changed.");
                            break;
                        case CLIENT_ADDRESS:
                            System.out.println("Input the new address.");
                            if(warehouse.setClientAddress(id, inputScanner.next()))
                                System.out.println("Address has been changed.");
                            else
                                System.out.println("ID not found, address not changed.");
                            break;
                        case CLIENT_BALANCE:
                            System.out.println("Input the new balance.");
                            if(warehouse.setClientBalance(id, Math.round(inputScanner.nextDouble() * 100.0) / 100.0))//round to 2 decimal places
                                System.out.println("Balance has been changed.");
                            else
                                System.out.println("ID not found, balance not changed.");
                            break;
                        default:
                            break;
                    }//end switch
                    break;
                case PRODUCT:
                    System.out.print("Input the id of the item to edit: ");
                    id = inputScanner.next();


                    System.out.println("\nWhich value would you like to edit? " +
                            "\n" + PRODUCT_NAME + ".) Name" +
                            "\n" + PRODUCT_PRICE + ".) Price" +
                            "\n" + PRODUCT_QUANTITY + ".) Quantity");

                    input = inputScanner.nextInt();

                    switch (input) {
                        case PRODUCT_NAME:
                            System.out.println("Input the new name.");
                            if(warehouse.setProductName(id, inputScanner.next()))
                                System.out.println("Name has been changed.");
                            else
                                System.out.println("ID not found, name not changed.");
                            break;
                        case PRODUCT_PRICE:
                            System.out.println("Input the new price.");
                            if(warehouse.setProductPrice(id, Math.round(inputScanner.nextDouble() * 100.0) / 100.0))//round to 2 decimal places
                                System.out.println("Price has been changed.");
                            else
                                System.out.println("ID not found, price not changed.");
                            break;
                        case PRODUCT_QUANTITY:
                            System.out.println("Input the new quantity.");
                            if(warehouse.setProductQuantity(id, inputScanner.nextInt()))
                                System.out.println("Quantity has been changed.");
                            else
                                System.out.println("ID not found, quantity not changed.");
                            break;
                        default:
                            break;
                    }//end switch

                    break;
                case SUPPLIER:
                    System.out.print("\nInput the id of the item to edit: ");
                    id = inputScanner.next();

                    System.out.println("Which value would you like to edit? " +
                            "\n" + SUPPLIER_NAME + ".) Name" +
                            "\n" + SUPPLIER_PHONE + ".) Phone Number" +
                            "\n" + SUPPLIER_ADDRESS + ".) Address");

                    input = inputScanner.nextInt();

                    switch (input) {
                        case SUPPLIER_NAME:
                            System.out.println("Input the new name.");
                            if(warehouse.setSupplierName(id, inputScanner.next()))
                                System.out.println("Name has been changed.");
                            else
                                System.out.println("ID not found, name not changed.");
                            break;
                        case SUPPLIER_PHONE:
                            System.out.println("Input the new phone number.");
                            if(warehouse.setSupplierPhone(id, inputScanner.next()))
                                System.out.println("Phone number has been changed.");
                            else
                                System.out.println("ID not found, phone number not changed.");
                            break;
                        case SUPPLIER_ADDRESS:
                            System.out.println("Input the new address.");
                            if(warehouse.setSupplierAddress(id, inputScanner.next()))
                                System.out.println("Address has been changed.");
                            else
                                System.out.println("ID not found, address not changed.");
                            break;
                        default:
                            break;
                    }//end switch

                    break;
                default:
                    break;
            }//end switch
        }//end while
    }//end method
    
    /*
     * Function:	acceptClientOrders
     * Type:		void
     * Privacy:		public
     * Description:	Client would like to purchase the items in their cart.
     * 				Takes the items in their cart and removes them from inventory
     * 				then charges the price of the items to their stock.
     */
    public void acceptClientOrders(){
        System.out.println("Dummy Method.");
        System.out.println("Method modified for testing backorders / orders");
        System.out.println("Adds a dummy order to a dummy cart and then adds to backorder");
        String id = "1";//using test client's id
        LinkedList<Product> dummyCart = new LinkedList<Product>();
        Iterator items = warehouse.getProducts();
        Product dummyProduct = new Product("1","1",1,1);
        
        /*Create the dummy cart with testing product*/
        dummyCart.add(dummyProduct);


        
        /*Find the item ID*/
        /*subtract ordered items from inventory*/
        /*For testing purposes, a backorder will be triggered purposely*/
        warehouse.addBackorders(id, dummyCart);

    }//end method

    /*
     * Function:	acceptClientPayment
     * Type:		void
     * Privacy:		public
     * Description:	Take the Clients payment. Once payment is confirmed
     * 				add the amount taken from payment to their account.
     * 				(Rounds up!)
     */
    public void acceptClientPayment(){
    	System.out.print("\nInput Client ID: ");
    	String id = inputScanner.next();
    	boolean entryFound = false,
				result = false,
    			inputVerification = false;
    	char input;
    	int ClientIndex = 0;
    	double inputCredit = 0.0;
		Client nextClient = new Client("", "", "", ""),
			   dummyClient = new Client("", "", "", "");
    	
    	
    	/*Check if Client exists (by id)*/
		Iterator allClients = warehouse.getClients();
        /*Check if ID is already found in system. Can't have different items of same ID*/
		while (!entryFound && allClients.hasNext()){//search for item by id
			nextClient = (Client)(allClients.next());
    		if(nextClient.getId().contentEquals(id)){
    			entryFound = true;
				allClients.remove();
    		}//end if
    	}//end while
    	
    	if(entryFound) {
    		while(!inputVerification) {
    			System.out.print("\nInput Amount to Deposit: ");
    			inputCredit = Math.round(inputScanner.nextDouble() * 100.0) / 100.0;
    			System.out.print("\nDid you want to deposit $" + inputCredit + " into " + nextClient.getName() + "'s account? (Y/N)");
    			input = inputScanner.next().charAt(0);
    			
    			if(input == ('y') || input == ('Y'))//we have broken the verification cycle
    				inputVerification = true;
    			
    		}//end while
    		
    		dummyClient = warehouse.addClient(nextClient.getId(),nextClient.getName(),nextClient.getPhone(),
                                nextClient.getAddress(),nextClient.getBalance() + inputCredit);
			if (dummyClient != null) {
				System.out.println("Deposit successful.");
			}
    		System.out.print("A credit of " + inputCredit + " has been deposited into " + nextClient.getName() + "'s account.\n" );
    	}//end if
		else {
			System.out.print("\nEntry not found.\n");
		}//end if
    }//end method
    
    /*
     * Function:	acceptShipment
     * Type:		void
     * Privacy:		public
     * Description:	
     */
    public void acceptShipments(){
        System.out.println("Dummy Method.");
    }//end method
    
    /*
     * Function:	addItemsToCart
     * Type:		void
     * Privacy:		public
     * Description:	Adds Products from inventory into a client's cart.
     * 				The client will be specified using their ID.
     */
    public void addItemsToCart(){
        System.out.println("Dummy Method.");
        
    }//end method

    /*Queries*/

    /*
     * Function:	listClientTransactions
     * Type:		void
     * Privacy:		public
     * Description:	
     */
    public void listClientTransactions(){
        System.out.println("Dummy Method.");
    }//end method
    
    /*
     * Function:	listSupplierPrices
     * Type:		void
     * Privacy:		public
     * Description:	
     */
    public void listSupplierPrices(){
        System.out.println("Dummy Method.");
    }//end method
    
    /*
     * Function:	listNegativeBalances
     * Type:		void
     * Privacy:		public
     * Description:	Searches through every client and checks their current balance.
     * 				If a client's balance is below $0.00 this will display a list of
     * 				these clients.
     */
    public void listNegativeBalances(){
		Iterator allClients = warehouse.getClients();
		while (allClients.hasNext()){//search for item by id
			Client nextClient = (Client)(allClients.next());
        	if(nextClient.getBalance() < 0)//If Client balance is negative
        		System.out.println("\nID: " + nextClient.getId()
        							+"\nName: " + nextClient.getName()
        							+"\nBalance: $" + nextClient.getBalance());
        	
        }//end while
    }//end method
    
    /*
     * Function:	listBackorders
     * Type:		void
     * Privacy:		public
     * Description:	
     */
    public void listBackorders(){
        Iterator allBackorders = warehouse.getBackorders();
        while (allBackorders.hasNext()) {//List all backorders
        	Order nextBackorder = (Order)(allBackorders.next());
        	System.out.println(nextBackorder.toString());
        }
    }//end method
    
    /*
     * Function:	listPurchasePrices
     * Type:		void
     * Privacy:		public
     * Description:	
     */
    public void listPurchasePrices(){
        System.out.println("Dummy Method.");
    }//end method
    
    /*
     * Function:	listInventory
     * Type:		void
     * Privacy:		public
     * Description:	
     */
    public void listInventory(){
		Iterator allProducts = warehouse.getProducts();
		while (allProducts.hasNext()){
			Product nextProduct = (Product)(allProducts.next());
            System.out.println(nextProduct.toString());
            System.out.println();
        }//end while
    }//end method
	
	/*
     * Function:	listAllClients
     * Type:		void
     * Privacy:		public
     * Description:	
     */
    public void listAllClients(){
		Iterator allClients = warehouse.getClients();
		while (allClients.hasNext()){
			Client nextClient = (Client)(allClients.next());
            System.out.println(nextClient.toString());
            System.out.println();
        }//end while
    }//end method
	
	/*
     * Function:	listAllSuppliers
     * Type:		void
     * Privacy:		public
     * Description:	
     */
    public void listAllSuppliers(){
		Iterator allSuppliers = warehouse.getSuppliers();
		while (allSuppliers.hasNext()){
			Supplier nextSupplier = (Supplier)(allSuppliers.next());
            System.out.println(nextSupplier.toString());
            System.out.println();
        }//end while
    }//end method
    
    /*
     * Function:	listAllBackorders
     * Type:		void
     * Privacy:		public
     * Description:	
     */
    public void listAllBackorders(){
		Iterator allBackorders = warehouse.getBackorders();
		while (allBackorders.hasNext()){
			Order nextBackorder = (Order)(allBackorders.next());
            System.out.println(nextBackorder.toString());
            System.out.println();
        }//end while
    }//end method
    
    /*
     * Function:	listCustomerTransactions
     * Type:		void
     * Privacy:		public
     * Description:	
     */
    public void listCustomerTransactions(){
        System.out.println("Dummy Method.");
    }//end method	


	/*
     * Function:	save
     * Type:		void
     * Privacy:		public
     * Description:	Outputs data into a file called WarehouseData and
					saves it for future use.
     */
	private void save() {
		if (warehouse.save()) {
			System.out.println("The warehouse has been successfully saved in the file WarehouseData. \n" );
		} 
		else {
			System.out.println("There has been an error in saving the warehouse. \n" );
		}
	}//end save

	/*
     * Function:	retrieve
     * Type:		void
     * Privacy:		public
     * Description:	Retrieves data from file called WarehouseData and
					sets all of the data into the warehouse database.
     */
	private void retrieve() {
		try {
			Warehouse tempWarehouse = Warehouse.retrieve();
			if (tempWarehouse != null) {
				System.out.println("The warehouse has been successfully retrieved from the file WarehouseData. \n" );
				warehouse = tempWarehouse;
			} 
			else {
				System.out.println("File doesnt exist; creating new warehouse. \n" );
				warehouse = Warehouse.instance();
			}
		} 
		catch(Exception cnfe) {
			cnfe.printStackTrace();
		}
	}//end retrieve

	/*
     * Function:	process
     * Type:		void
     * Privacy:		public
     * Description:	Goes through the menu until the user decides to exit.
     */
	public void process() {
        int input = EXIT + 1; //Arbitrary non-zero(non-exit) number

        while(input != EXIT){//Keep looping until user wishes to exit
            System.out.println("What would you like to do?\n" +
					EXIT + ".) Exit\n" +
                    ADD_CLIENTS_PRODUCTS_SUPPLIES + ".) Add Clients, Products, or Suppliers\n"+
                    EDIT_CLIENTS_PRODUCTS_SUPPLIES + ".) Edit Clients, Products, or Suppliers\n"+
                    ACCEPT_CLIENT_ORDERS + ".) Accept Client Order\n"+
                    ACCEPT_CLIENT_PAYMENT + ".) Accept Client Payment\n"+
                    ACCEPT_SHIPMENT + ".) Accept Shipment\n"+
                    ADD_ITEM_TO_CART + ".) Add Items to Cart\n"+
                    LIST_CLIENT_TRANSACTIONS + ".) List Client Transactions\n"+
                    LIST_SUPPLIER_PRICES + ".) List Supplier Prices\n"+
                    LIST_NEGATIVE_BALANCES + ".) List Clients with Negative Balances\n"+
                    LIST_BACKORDERS + ".) List Backordered Items\n"+
                    LIST_PURCHASE_PRICES + ".) List Price of Items from Suppliers\n"+
                    LIST_INVENTORY + ".) List Current Inventory\n"+
					LIST_ALL_CLIENTS + ".) List All Clients\n"+
					LIST_ALL_SUPPLIERS + ".) List All Suppliers\n"+
                    LIST_CUSTOMER_TRANSACTIONS + ".) List Customer Transactions by Date\n"+
					SAVE + ".) Save \n" +
					RETRIEVE + ".) Retrieve \n" +
                    HELP + ".) Help");
            input = inputScanner.nextInt();

            switch(input){
                case EXIT:
                    break;
                case ADD_CLIENTS_PRODUCTS_SUPPLIES:
                    addClientProductsSupplies();
                    break;
                case EDIT_CLIENTS_PRODUCTS_SUPPLIES:
                    editClientProductsSupplies();
                    break;
                case ACCEPT_CLIENT_ORDERS:
                    acceptClientOrders();
                    break;
                case ACCEPT_CLIENT_PAYMENT:
                    acceptClientPayment();
                    break;
                case ACCEPT_SHIPMENT:
                    acceptShipments();
                    break;
                case ADD_ITEM_TO_CART:
                    addItemsToCart();
                    break;
                case LIST_CLIENT_TRANSACTIONS:
                    listClientTransactions();
                    break;
                case LIST_SUPPLIER_PRICES:
                    listSupplierPrices();
                    break;
                case LIST_NEGATIVE_BALANCES:
                    listNegativeBalances();
                    break;
                case LIST_BACKORDERS:
                    listBackorders();
                    break;
                case LIST_PURCHASE_PRICES:
                    listPurchasePrices();
                    break;
                case LIST_INVENTORY:
                    listInventory();
                    break;
				case LIST_ALL_CLIENTS:
                    listAllClients();
                    break;
				case LIST_ALL_SUPPLIERS:
                    listAllSuppliers();
                    break;
                case LIST_CUSTOMER_TRANSACTIONS:
                    listClientTransactions();
                    break;
				case SAVE:
					save();
                    break;
				case RETRIEVE:
					retrieve();
                    break;
                case HELP:
                    break;
                default:
                    System.out.println(input + " is not a valid action. Try typing " + HELP + " for help.");
                    break;
            }//end switch
            System.out.println();//spacing for ui
        }//end while
    }//end process
	
	public static void main(String[] s) {
		Userinterface.instance().process();
	}//end main
}//end class
