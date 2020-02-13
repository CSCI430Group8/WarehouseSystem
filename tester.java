import java.util.*;
import java.io.*;
import java.util.Scanner;

public class tester {
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
                     LIST_CUSTOMER_TRANSACTIONS = 13,
					 SAVE = 14,
					 RETRIEVE = 15,
                     HELP = 16;
					
	//Add Choices
	static final int CLIENT = 1,
					 PRODUCT = 2,
					 SUPPLIER = 3;

	//product choices
	static final int PRODUCT_ID = 1,
                    PRODUCT_NAME = 2,
                    PRODUCT_PRICE = 3,
                    PRODUCT_QUANTITY = 4;

    //customer choices
    static final int CLIENT_ID = 1,
                    CLIENT_NAME = 2,
                    CLIENT_PHONE = 3,
                    CLIENT_ADDRESS = 4,
                    CLIENT_BALANCE = 5;

    //supplier choices
    static final int SUPPLIER_ID = 1,
            SUPPLIER_NAME = 2,
            SUPPLIER_PHONE = 3,
            SUPPLIER_ADDRESS = 4;
					 
	transient Scanner inputScanner = new Scanner(System.in);//create scanner for input
	private static tester Tester;
	private static Warehouse warehouse;


	/*
     * Function:	tester
     * Type:		constructor(generic)
     * Privacy:		private
     * Description:	Constructor for tester class. This is made private because
     * 				it is using the singleton methodology to make sure only one
     * 				tester can be initialized at a time
     */
	private tester() {
		char input;
		System.out.println("Would you like to load a save? (Y/N)");
		input = inputScanner.next().charAt(0);
    	if(input == ('y') || input == ('Y'))
    		retrieve();
		else {
			warehouse = Warehouse.instance();
		}
	}
	
	/*
     * Function:	instance
     * Type:		tester
     * Privacy:		public
     * Description:	This is the singleton for tester, which, if were to
     * 				try an initialize a second tester class it would
     * 				restrict access to the constructor and only return a copy
     *  			of the current tester class.
     */
	public static tester instance() {
		if (Tester == null) {
			return Tester = new tester();
		} 
		else {
			return Tester;
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

            System.out.println("\n" + EXIT + ".) Go Back\n" +
                    CLIENT + ".) Add Client\n"+
                    PRODUCT + ".) Add Product\n"+
                    SUPPLIER + ".) Add Suppliers\n");

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
                    
					Iterator allProducts = warehouse.getProducts();
                    /*Check if ID is already found in system. Must update the quantity if found*/
					while (!entryFound && allProducts.hasNext()){//search for item by id
						Product nextProduct = (Product)(allProducts.next());
                        if(nextProduct.getId().contentEquals(dummyProduct.getId())) {//if item is found
                            entryFound = true;
                            result = warehouse.updateQuantityForProduct(nextProduct.getQuantity() + dummyProduct.getQuantity(), dummyProduct.getId());
							if (result) {
								System.out.println("Product quantity update successful.");
							}
                        }//end if
                    }//end for
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
                    }//end for
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
            int quantity = 0;

            double price = 0;

            String name,
                    id,
                    phone,
                    address;

            System.out.println("\n" + EXIT + ".) Go Back\n" +
                    CLIENT + ".) Edit Client\n"+
                    PRODUCT + ".) Edit Product\n"+
                    SUPPLIER + ".) Edit Suppliers\n");

            boolean entryFound = false,
                    result = false;
            input = inputScanner.nextInt();
            Iterator iterator;
            String iteratorString;

            switch(input) {
                case EXIT:
                    break;
                case CLIENT:
                    System.out.print("Input the id of the item to edit.");
                    id = inputScanner.next();

                    System.out.println("\n" + CLIENT_ID + ".) ID" +
                            "\n" + CLIENT_NAME + ".) Name" +
                            "\n" + CLIENT_PHONE + ".) Phone Number" +
                            "\n" + CLIENT_ADDRESS + ".) Address" +
                            "\n" + CLIENT_BALANCE + ".) Balance");

                    input = inputScanner.nextInt();

                    Iterator allClients = warehouse.getClients();
                    Client nextClient = new Client("","","","");//dummy initialization

                    while(!entryFound & allClients.hasNext()){
                        nextClient = (Client)(allClients.next());
                        if(nextClient.getId().contentEquals(id)){
                            entryFound = true;
                            allClients.remove();//Remove because it's easier to add a modified version
                        }
                    }

                    if(entryFound) {
                        switch (input) {
                            case CLIENT_ID:
                                System.out.println("What would you like the new id to be?");
                                nextClient.setId(inputScanner.next());

                                break;
                            case CLIENT_NAME:
                                System.out.println("What would you like the new name to be?");
                                nextClient.setName(inputScanner.next());

                                break;
                            case CLIENT_PHONE:
                                System.out.println("What would you like the new phone number to be?");
                                nextClient.setPhone(inputScanner.next());
                                break;
                            case CLIENT_ADDRESS:
                                System.out.println("What would you like the new address to be?");
                                nextClient.setAddress(inputScanner.next());
                                break;
                            case CLIENT_BALANCE:
                                System.out.println("What would you like the new balance to be?");
                                nextClient.setBalance(inputScanner.nextDouble());
                                break;
                            default:
                                break;
                        }
                        /*add to list after editing desired value.*/
                        warehouse.addClient(nextClient.getId(),nextClient.getName(),nextClient.getPhone(),
                                nextClient.getAddress(),nextClient.getBalance());
                    } else {
                        System.out.println("Could not find ID");
                    }

                    break;
                case PRODUCT:
                    System.out.print("Input the id of the item to edit.");
                    id = inputScanner.next();


                    System.out.println("\n" + PRODUCT_ID + ".) ID" +
                            "\n" + PRODUCT_NAME + ".) Name" +
                            "\n" + PRODUCT_PRICE + ".) Price" +
                            "\n" + PRODUCT_QUANTITY + ".) Quantity");

                    input = inputScanner.nextInt();

                    Iterator allProducts = warehouse.getProducts();
                    Product nextProduct = new Product("","",1,1);//dummy initialization

                    while(!entryFound & allProducts.hasNext()){
                        nextProduct = (Product)(allProducts.next());
                        if(nextProduct.getId().contentEquals(id)){
                            entryFound = true;
                            allProducts.remove();//Remove because it's easier to add a modified version
                        }
                    }

                    if(entryFound) {
                        switch (input) {
                            case PRODUCT_ID:
                                System.out.println("What would you like the new id to be?");
                                nextProduct.setId(inputScanner.next());

                                break;
                            case PRODUCT_NAME:
                                System.out.println("What would you like the new name to be?");
                                nextProduct.setName(inputScanner.next());

                                break;
                            case PRODUCT_PRICE:
                                System.out.println("What would you like the new price to be?");
                                nextProduct.setPrice((inputScanner.nextDouble() * 100.0) / 100.0);


                                break;
                            case PRODUCT_QUANTITY:
                                System.out.println("What would you like the new quantity to be?");
                                nextProduct.setQuantity(inputScanner.nextInt());
                                break;
                            default:
                                break;
                        }
                        /*add to list after editing desired value.*/
                        warehouse.addProduct(nextProduct.getId(),nextProduct.getName(),nextProduct.getPrice(),
                                nextProduct.getQuantity());
                    } else {
                        System.out.println("Could not find ID");
                    }

                    break;
                case SUPPLIER:
                    System.out.print("Input the id of the item to edit.");
                    id = inputScanner.next();

                    System.out.println("\n" + SUPPLIER_ID + ".) ID" +
                            "\n" + SUPPLIER_NAME + ".) Name" +
                            "\n" + SUPPLIER_PHONE + ".) Phone Number" +
                            "\n" + SUPPLIER_ADDRESS + ".) Address");

                    input = inputScanner.nextInt();

                    Iterator allSuppliers = warehouse.getSuppliers();
                    Supplier nextSupplier = new Supplier("","","","");//dummy initialization

                    while(!entryFound & allSuppliers.hasNext()){
                        nextSupplier = (Supplier)(allSuppliers.next());
                        if(nextSupplier.getId().contentEquals(id)){
                            entryFound = true;
                            allSuppliers.remove();//Remove because it's easier to add a modified version
                        }
                    }

                    if(entryFound) {
                        switch (input) {
                            case SUPPLIER_ID:
                                System.out.println("What would you like the new id to be?");
                                nextSupplier.setId(inputScanner.next());

                                break;
                            case SUPPLIER_NAME:
                                System.out.println("What would you like the new name to be?");
                                nextSupplier.setName(inputScanner.next());

                                break;
                            case SUPPLIER_PHONE:
                                System.out.println("What would you like the new phone number to be?");
                                nextSupplier.setPhone(inputScanner.next());


                                break;
                            case SUPPLIER_ADDRESS:
                                System.out.println("What would you like the new address to be?");
                                nextSupplier.setAddress(inputScanner.next());
                                break;
                            default:
                                break;
                        }
                        /*add to list after editing desired value.*/
                        warehouse.addSupplier(nextSupplier.getId(),nextSupplier.getName(),nextSupplier.getPhone(),
                                nextSupplier.getAddress());
                    } else {
                        System.out.println("Could not find ID");
                    }

                default:
                    break;
            }
        }
    }


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
		Client client = new Client("0", "0", "0", "0");
    	
    	
    	/*Check if Client exists (by id)*/
		Iterator allClients = warehouse.getClients();
        /*Check if ID is already found in system. Can't have different items of same ID*/
		while (!entryFound && allClients.hasNext()){//search for item by id
			Client nextClient = (Client)(allClients.next());
    		if(nextClient.getId().contentEquals(id)){
    			entryFound = true;
				client = nextClient;
    		}//end if
    	}//end for
    	
    	if(entryFound) {
    		while(!inputVerification) {
    			System.out.print("\nInput Amount to Deposit: ");
    			inputCredit = Math.round(inputScanner.nextDouble() * 100.0) / 100.0;
    			System.out.print("\nDid you want to deposit $" + inputCredit + " into " + client.getName() + "'s account? (Y/N)");
    			input = inputScanner.next().charAt(0);
    			
    			if(input == ('y') || input == ('Y'))//we have broken the verification cycle
    				inputVerification = true;
    			
    		}//end while
    		
    		result = warehouse.updateBalanceForClient(client.getBalance() + inputCredit, client.getId());
			if (result) {
				System.out.println("Deposit successful.");
			}
    		System.out.print("A credit of " + inputCredit + " has been deposited into " + client.getName() + "'s account." );
    	}//end if
		else {
			System.out.print("\nEntry not found.");
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
        	
        }//end for
    }//end method
    
    /*
     * Function:	listBackorders
     * Type:		void
     * Privacy:		public
     * Description:	
     */
    public void listBackorders(){
        System.out.println("Dummy Method.");
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
        }//end for
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
			System.out.println(" The warehouse has been successfully saved in the file WarehouseData. \n" );
		} 
		else {
			System.out.println(" There has been an error in saving the warehouse. \n" );
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
				System.out.println(" The warehouse has been successfully retrieved from the file WareHouseData. \n" );
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
            System.out.println(EXIT + ".) Exit\n" +
                    ADD_CLIENTS_PRODUCTS_SUPPLIES + ".) Add Clients, Products, or Supplies\n"+
                    EDIT_CLIENTS_PRODUCTS_SUPPLIES + ".) Edit Clients, Products, or Supplies\n"+
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
		tester.instance().process();
	}//end main
}//end class
