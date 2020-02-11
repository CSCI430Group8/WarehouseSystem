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
    private List<customer> customers;
    private List<manufacturer> suppliers;
    private List<product> inventory;

    /*
     * Function:	Warehouse
     * Type:		constructor(generic)
     * Privacy:		private
     * Description:	Constructor for Warehouse class. This is made private because
     * 				it is using the singleton methodology to make sure only one
     * 				Warehouse can be initialized at a time
     */
    private Warehouse(){
        customers = new ArrayList<customer>();
        suppliers = new ArrayList<manufacturer>();
        inventory = new ArrayList<product>();
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
     * 				want to add; Clients, products, or suppliers. Once specified,
     * 				You will be asked to enter all of the information for the
     * 				type you wish to enter. After you have entered the information you
     * 				will be asked to verify this data, if it is not correct you will
     * 				be asked to retype the data. After this has happened, if you are
     * 				entering a product you will be asked if you wish to enter another.
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
                    customer dummyCustomer = new customer(id, name, phone, address);

                    /*Check if ID is already found in system. Can't have different items of same ID*/
                    for(int i = 0; !entryFound & i < customers.size(); i++) {//search for item by id
                        if(customers.get(i).id.contentEquals(dummyCustomer.id))//if item is found
                           entryFound = true;
                    }//end for
                    if(entryFound) {
                        System.out.println("ID is already present in system; Item not added.");
                    } else {//When the product is not listed
                        customers.add(dummyCustomer);
                    }
                    break;
                case PRODUCT:
                    /*Inputs for product*/
                    System.out.print("\nProduct ID: ");
                    id = inputScanner.next();
                    System.out.print("\nProduct Name: ");
                    name = inputScanner.next();
                    System.out.print("\nProduct Price: ");
                    price = Math.round(inputScanner.nextDouble() * 100.0) / 100.0;//rounds to 2 decimals
                    System.out.print("\nProduct Quantity: ");
                    quantity = inputScanner.nextInt();

                    product dummyProduct = new product(id,name,price,quantity);//create dummy entry based on inputs
                    
                    for(int i = 0; !entryFound & i < inventory.size(); i++) {//search for item by id
                        if(inventory.get(i).id.contentEquals(dummyProduct.id)) {//if item is found
                            entryFound = true;
                            inventory.get(i).quantity += dummyProduct.quantity;
                        }//end if
                    }//end for
                    if(!entryFound)//When the product is not listed
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
                    manufacturer dummyManufacturer = new manufacturer(id, name, phone, address);

                    /*Check if ID is already found in system. Can't have different items of same ID*/
                    for(int i = 0; !entryFound & i < suppliers.size(); i++) {//search for item by id
                        if(suppliers.get(i).id.contentEquals(dummyManufacturer.id))//if item is found
                            entryFound = true;
                    }//end for
                    if(entryFound) {
                        System.out.println("ID is already present in system; Item not added.");
                    } else {//When the product is not listed
                        suppliers.add(dummyManufacturer);
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
     * Function:	acceptCustomerPayment
     * Type:		void
     * Privacy:		public
     * Description:	Take the customers payment. Once payment is confirmed
     * 				add the amount taken from payment to their account.
     * 				(Rounds up!)
     */
    public void acceptCustomerPayment(){
    	System.out.print("\nInput Client ID: ");
    	String id = inputScanner.next();
    	Boolean entryFound = false,
    			inputVerification = false;
    	char input;
    	int customerIndex = 0;
    	Double inputCredit = 0.0;
    	
    	
    	/*Check if customer exists (by id)*/
    	for(int i = 0; !entryFound & i < customers.size(); i++) {
    		if(customers.get(i).id.contentEquals(id)){
    			entryFound = true;
    			customerIndex = i;
    		}//end if
    	}//end for
    	
    	if(entryFound) {
    		while(!inputVerification) {
    			System.out.print("\nInput Amount to Deposit: ");
    		
    		
    			inputCredit = Math.round(inputScanner.nextDouble() * 100.0) / 100.0;
    		 
    			System.out.print("\nDid you want to deposit $" + inputCredit + " into " + customers.get(customerIndex).name + "'s account? (Y/N)");
    			
    			input = inputScanner.next().charAt(0);
    			
    			if(input == ('y') && input == ('Y'))//we have broken the verification cycle
    				inputVerification = true;
    		 
    			
    		}//end while
    		
    		customers.get(customerIndex).balance += inputCredit;
    		System.out.print("A credit of " + inputCredit + " has been deposited into " + customers.get(customerIndex).name + "'s account." );
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
     * Description:	Adds products from inventory into a client's cart.
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
     * Function:	listManufacturerPrices
     * Type:		void
     * Privacy:		public
     * Description:	
     */
    public void listManufacturerPrices(){
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
        for(int i = 0; i < customers.size(); i++) {//search through all customers
        	if(customers.get(i).balance < 0)//If customer balance is negative
        		System.out.println("\nID: " + customers.get(i).id
        							+"\nName: " + customers.get(i).name
        							+"\nBalance: $" + customers.get(i).balance);
        	
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


    /*Customer*/
    private class customer{
        customer(String id, String name, String phone, String address){
            this.id = id;
            this.name = name;
            this.phone = phone;
            this.address = address;
            this.balance = 0.00;//will always start with empty balance
        }

        /*Display is being used to test additions, not using iterator*/
        private void display(){
            System.out.print("ID: " + id + " Name: " + name + " Balance: " + balance + " Phone Number: " + phone + " Address: " + address);
        }

        private double balance;
        private String id,
                name,
                phone,
                address;
        private List<product> cart;

    }


    /*Manufacturer*/
    private class manufacturer{
        manufacturer(String id, String name, String phone, String address){
            this.id = id;
            this.name = name;
            this.phone = phone;
            this.address = address;
        }

        /*Display is being used to test additions, not using iterator*/
        private void display(){
            System.out.print("ID: " + id + " Name: " + name + " Phone Number: " + phone + " Address: " + address);
        }

        private String id,
                name,
                phone,
                address;
    }


    /*Product*/
    private class product{
        product(String id, String name, double price, int quantity){
            this.id = id;
            this.name = name;
            this.price = price;
            this.quantity = quantity;
        }

        /*Display is being used to test additions to inventory, not using iterator*/
        private void display(){
            System.out.print("ID: " + id + " Name: " + name + " Price Per Item: $" + price + " Quantity: " + quantity);
        }

        private String id,
                name;
        private int quantity;
        private double price;
    }
}