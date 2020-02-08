import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Warehouse implements Serializable {

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
        System.out.println("Dummy Method.");
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

        private void display(){
            System.out.print("ID: " + id + " Name: " + name + " Quantity: " + quantity);
        }

        private String id,
                    name;
        private int quantity;
    }
}
