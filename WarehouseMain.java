import java.io.*;
import java.util.Scanner;

public class WarehouseMain {
    //Business Processes
    static final int EXIT = 0,
                     ADD_CLIENTS_PRODUCTS_SUPPLIES = 1,
                     ACCEPT_CLIENT_ORDERS = 2,
                     ACCEPT_CUSTOMER_PAYMENT = 3,
                     ACCEPT_SHIPMENT = 4,
                     ADD_ITEM_TO_CART = 5,
    //Queries
                     LIST_CLIENT_TRANSACTIONS = 6,
                     LIST_MANUFACTURER_PRICES = 7,
                     LIST_NEGATIVE_BALANCES = 8,
                     LIST_BACKORDERS = 9,
                     LIST_PURCHASE_PRICES = 10,
                     LIST_INVENTORY = 11,
                     LIST_CUSTOMER_TRANSACTIONS = 12,
                     HELP = 13;

    public static void main(String [] args) {
        Scanner inputScanner = new Scanner(System.in);//create scanner for input
        int input = EXIT + 1; //Arbitrary non-zero(non-exit) number
        Warehouse warehouse = Warehouse.getInstance();//Singleton, only allows for 1 warehouse

        while(input != EXIT){//Keep looping until user wishes to exit
            System.out.println(EXIT + ".) Exit\n" +
                    ADD_CLIENTS_PRODUCTS_SUPPLIES + ".) Add Clients, Products, or Supplies\n"+
                    ACCEPT_CLIENT_ORDERS + ".) Accept Client Order\n"+
                    ACCEPT_CUSTOMER_PAYMENT + ".) Accept Customer Payment\n"+
                    ACCEPT_SHIPMENT + ".) Accept Shipment\n"+
                    ADD_ITEM_TO_CART + ".) Add Items to Cart\n"+
                    LIST_CLIENT_TRANSACTIONS + ".) List Client Transactions\n"+
                    LIST_MANUFACTURER_PRICES + ".) List Manufacturer Prices\n"+
                    LIST_NEGATIVE_BALANCES + ".) List Customers with Negative Balances\n"+
                    LIST_BACKORDERS + ".) List Backordered Items\n"+
                    LIST_PURCHASE_PRICES + ".) List Price of Items from Suppliers\n"+
                    LIST_INVENTORY + ".) List Current Inventory\n"+
                    LIST_CUSTOMER_TRANSACTIONS + ".) List Customer Transactions by Date\n"+
                    HELP + ".) Help" );
            input = inputScanner.nextInt();

            switch(input){
                case EXIT:
                    break;
                case ADD_CLIENTS_PRODUCTS_SUPPLIES:
                    warehouse.addClientProductsSupplies();
                    break;
                case ACCEPT_CLIENT_ORDERS:
                    warehouse.acceptClientOrders();
                    break;
                case ACCEPT_CUSTOMER_PAYMENT:
                    warehouse.acceptCustomerPayment();
                    break;
                case ACCEPT_SHIPMENT:
                    warehouse.acceptShipments();
                    break;
                case ADD_ITEM_TO_CART:
                    warehouse.addItemsToCart();
                    break;
                case LIST_CLIENT_TRANSACTIONS:
                    warehouse.listClientTransactions();
                    break;
                case LIST_MANUFACTURER_PRICES:
                    warehouse.listManufacturerPrices();
                    break;
                case LIST_NEGATIVE_BALANCES:
                    warehouse.listNegativeBalances();
                    break;
                case LIST_BACKORDERS:
                    warehouse.listBackorders();
                    break;
                case LIST_PURCHASE_PRICES:
                    warehouse.listPurchasePrices();
                    break;
                case LIST_INVENTORY:
                    warehouse.listInventory();
                    break;
                case LIST_CUSTOMER_TRANSACTIONS:
                    warehouse.listCustomerTransactions();
                    break;
                case HELP:
                    break;
                case -1:
                    warehouse.test();
                    break;
                default:
                    System.out.println(input + " is not a valid action. Try typing " + HELP + " for help.");
                    break;
            }//end switch
            System.out.println();//spacing for ui
        }//end while
    }//end main
}//end class
