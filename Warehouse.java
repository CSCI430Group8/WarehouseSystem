import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class Warehouse implements Serializable {
	static final int EXIT = 0,
					CLIENT = 1,
					PRODUCT = 2,
					SUPPLIER = 3;
	
    private static Warehouse warehouse;
    private List<customer> customers;
    private List<manufacturer> suppliers;
    private List<product> inventory;

    private Warehouse(){
        customers = new ArrayList<customer>();
        suppliers = new ArrayList<manufacturer>();
        inventory = new ArrayList<product>();
    }

    public static Warehouse getInstance(){//singleton
        if (warehouse == null){
            warehouse = new Warehouse();
        }

        return warehouse;
    }



    /*Business Processes*/


    public void addClientProductsSupplies(){
    	Scanner inputScanner = new Scanner(System.in);//create scanner for input
    	int input = EXIT + 1, //arbitrary non-exit number
    		quantity = 0;
    	String name,
    			id;
    	boolean productFound = false;
    	
    	
    	System.out.println("Method in progress.");
    	while(input != EXIT) {
    		System.out.println(EXIT + ".) Go Back\n" +
    				CLIENT + ".) Add Client\n"+
    				PRODUCT + ".) Add Product\n"+
    				SUPPLIER + ".) Add Suppliers\n");
    		input = inputScanner.nextInt();
        
    		switch(input){
    			case EXIT:
    				break;
    			case CLIENT:
    				System.out.println("Dummy Method.");
    				break;
    			case PRODUCT:
    				product dummyProduct = new product("123","testProduct",5);
    				for(int i = 0; productFound == false && i < inventory.size(); i++) {//search for item by id
    					if(inventory.get(i).id.contentEquals(dummyProduct.id)) {//if item is found
    						productFound = true;
    						inventory.get(i).quantity += dummyProduct.quantity;
    					}
    				}
    				if(!productFound)//When the product is not listed
    					inventory.add(dummyProduct);

    				
    				break;
    			case SUPPLIER:
    				System.out.println("Dummy Method.");
    				break;
    			default:
    				System.out.println("Not a valid input.\n");
    				break;
    		}
    	}
        
        //inputScanner.close();
    }

    public void acceptClientOrders(){
        System.out.println("Dummy Method.");
    }

    public void acceptCustomerPayment(){
        System.out.println("Dummy Method.");
    }
    public void acceptShipments(){
        System.out.println("Dummy Method.");
    }
    public void addItemsToCart(){
        System.out.println("Dummy Method.");
    }



    /*Queries*/

    public void listClientTransactions(){
        System.out.println("Dummy Method.");
    }
    public void listManufacturerPrices(){
        System.out.println("Dummy Method.");
    }
    public void listNegativeBalances(){
        System.out.println("Dummy Method.");
    }
    public void listBackorders(){
        System.out.println("Dummy Method.");
    }
    public void listPurchasePrices(){
        System.out.println("Dummy Method.");
    }
    public void listInventory(){
        for(int i = 0; i < inventory.size(); i++){
            inventory.get(i).display();
            System.out.println();
        }
    }
    public void listCustomerTransactions(){
        System.out.println("Dummy Method.");
    }


    /*Customer*/
    private class customer{
        customer(String id, String name, String phone, String address){
            this.id = id;
            this.name = name;
            this.phone = phone;
            this.address = address;
        }

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

        private String id,
                    name,
                    phone,
                    address;
    }


    /*Product*/
    private class product{
        product(String id, String name){//if not inputting quantity then assumed to be 1
            this.id = id;
            this.name = name;
            this.quantity = 1;
        }

        product(String id, String name, int quantity){
            this.id = id;
            this.name = name;
            this.quantity = quantity;
        }

        /*Display is being used to test additions to inventory, not using iterator*/
        private void display(){
            System.out.print("ID: " + id + " Name: " + name + " Quantity: " + quantity);
        }

        private String id,
                    name;
        private int quantity;
    }
}
