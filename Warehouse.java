import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class Warehouse implements Serializable {
    static final int EXIT = 0,
            CLIENT = 1,
            PRODUCT = 2,
            SUPPLIER = 3;
    
    Scanner inputScanner = new Scanner(System.in);//create scanner for input
    

    private static Warehouse warehouse;
    private List<Client> Clients;
    private List<Supplier> suppliers;
    private List<Product> inventory;

    /*
     * Function:	Warehouse
     * Type:		constructor(generic)
     * Privacy:		private
     * Description:	Constructor for Warehouse class. This is made private because
     * 				it is using the singleton methodology to make sure only one
     * 				Warehouse can be initialized at a time
     */
    private Warehouse(){
        Clients = new ArrayList<Client>();
        suppliers = new ArrayList<Supplier>();
        inventory = new ArrayList<Product>();
    }

    /*
     * Function:	getInstance
     * Type:		Warehouse
     * Privacy:		public
     * Description:	This is the singleton for Warehouse, which, if were to
     * 				try an initialize a second Warehouse class it would
     * 				restrict access to the constructor and only return a copy
     *  			of the current Warehouse class.
     */
    public static Warehouse getInstance(){
        if (warehouse == null){
            warehouse = new Warehouse();
        }

        return warehouse;
    }



    /*Business Processes*/

    /*
     * Function:	addClientsProductsSupplier
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

            System.out.println(EXIT + ".) Go Back\n" +
                    CLIENT + ".) Add Client\n"+
                    PRODUCT + ".) Add Product\n"+
                    SUPPLIER + ".) Add Suppliers\n");

            boolean entryFound = false;
            input = inputScanner.nextInt();

            switch(input){
                case EXIT:
                    break;
                case CLIENT:
                    System.out.print("\nClient ID: ");
                    id = inputScanner.next();
                    System.out.print("\nName: ");
                    name = inputScanner.next();
                    System.out.print("\nPhone Number: ");
                    phone = inputScanner.next();
                    System.out.print("\nAddress: ");
                    address = inputScanner.next();
                    Client dummyClient = new Client(id, name, phone, address);

                    /*Check if ID is already found in system. Can't have different items of same ID*/
                    for(int i = 0; !entryFound & i < Clients.size(); i++) {//search for item by id
                        if(Clients.get(i).getId().contentEquals(dummyClient.getId()))//if item is found
                           entryFound = true;
                    }//end for
                    if(entryFound) {
                        System.out.println("ID is already present in system; Item not added.");
                    } else {//When the Product is not listed
                        Clients.add(dummyClient);
                    }
                    break;
                case PRODUCT:
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
                    
                    for(int i = 0; !entryFound & i < inventory.size(); i++) {//search for item by id
                        if(inventory.get(i).getId().contentEquals(dummyProduct.getId())) {//if item is found
                            entryFound = true;
                            inventory.get(i).setQuantity(inventory.get(i).getQuantity() + dummyProduct.getQuantity());
                        }//end if
                    }//end for
                    if(!entryFound)//When the Product is not listed
                        inventory.add(dummyProduct);


                    break;
                case SUPPLIER:
                    System.out.print("\nSupplier ID: ");
                    id = inputScanner.next();
                    System.out.print("\nName: ");
                    name = inputScanner.next();
                    System.out.print("\nPhone Number: ");
                    phone = inputScanner.next();
                    System.out.print("\nAddress: ");
                    address = inputScanner.next();
                    Supplier dummySupplier = new Supplier(id, name, phone, address);

                    /*Check if ID is already found in system. Can't have different items of same ID*/
                    for(int i = 0; !entryFound & i < suppliers.size(); i++) {//search for item by id
                        if(suppliers.get(i).getId().contentEquals(dummySupplier.getId()))//if item is found
                            entryFound = true;
                    }//end for
                    if(entryFound) {
                        System.out.println("ID is already present in system; Item not added.");
                    } else {//When the Product is not listed
                        suppliers.add(dummySupplier);
                    }//end if-else
                    break;
                default:
                    System.out.println("Not a valid input.\n");
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
    	Boolean entryFound = false,
    			inputVerification = false;
    	char input;
    	int ClientIndex = 0;
    	double inputCredit = 0.0;
    	
    	
    	/*Check if Client exists (by id)*/
    	for(int i = 0; !entryFound & i < Clients.size(); i++) {
    		if(Clients.get(i).getId().contentEquals(id)){
    			entryFound = true;
    			ClientIndex = i;
    		}//end if
    	}//end for
    	
    	if(entryFound) {
    		while(!inputVerification) {
    			System.out.print("\nInput Amount to Deposit: ");
    		
    		
    			inputCredit = Math.round(inputScanner.nextDouble() * 100.0) / 100.0;
    		 
    			System.out.print("\nDid you want to deposit $" + inputCredit + " into " + Clients.get(ClientIndex).getName() + "'s account? (Y/N)");
    			
    			input = inputScanner.next().charAt(0);
    			
    			if(input == ('y') && input == ('Y'))//we have broken the verification cycle
    				inputVerification = true;
    		 
    			
    		}//end while
    		
    		Clients.get(ClientIndex).setBalance(Clients.get(ClientIndex).getBalance() + inputCredit);
    		System.out.print("A credit of " + inputCredit + " has been deposited into " + Clients.get(ClientIndex).getName() + "'s account." );
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
        for(int i = 0; i < Clients.size(); i++) {//search through all Clients
        	if(Clients.get(i).getBalance() < 0)//If Client balance is negative
        		System.out.println("\nID: " + Clients.get(i).getId()
        							+"\nName: " + Clients.get(i).getName()
        							+"\nBalance: $" + Clients.get(i).getBalance());
        	
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
        for(int i = 0; i < inventory.size(); i++){
            inventory.get(i).display();
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
}