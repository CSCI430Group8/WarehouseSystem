import java.text.DecimalFormat;
import java.util.*;
import java.io.*;


public class ClientList implements Serializable {
	private static final long serialVersionUID = 1L;
	private LinkedList<Client> clients = new LinkedList<Client>();
	private static ClientList clientList;
	
	/*
     * Function:	ClientList
     * Type:		constructor(generic)
     * Privacy:		private
     * Description:	ClientList Constructor.
	 */
	private ClientList() {
	}//end constructor
	
	/*
     * Function:	instance
     * Type:		static ClientList
     * Privacy:		public
     * Description:	Creates an instance of ClientList.
	 */
	public static ClientList instance() {
		if (clientList == null) {
			return (clientList = new ClientList());
		} 
		else {
			return clientList;
		}
	}//end instance
	
	/*
     * Function:	insertClient
     * Type:		boolean
     * Privacy:		public
     * Description:	Inserts a Client into ClientList.
	 */
	public boolean insertClient(Client client) {
		boolean result;
		result = clients.add(client);
		return result;
	}//end insertClient

	/*
     * Function:	getClients
     * Type:		Iterator
     * Privacy:		public
     * Description:	Returns an iterator for ClientList*/
	public Iterator getClients(){
		return clients.iterator();
	}//end getClients

	/*
	 * Function:	setClientBalance
	 * Type:		boolean
	 * Privacy:		public
	 * Description:
	 */
	public boolean setClientBalance(String id, double balance){
		Boolean entryFound = false;
		for(int i = 0; !entryFound & i < clients.size(); i++){
			if(clients.get(i).getId().contentEquals(id)){
				entryFound = true;
				clients.get(i).setBalance(balance);
			}//end if
		}//end for
		return entryFound;//product is found and changed
	}//end setClientBalance

	/*
	 * Function:	setClientName
	 * Type:		boolean
	 * Privacy:		public
	 * Description:
	 */
	public boolean setClientName(String id, String name){
		Boolean entryFound = false;
		for(int i = 0; !entryFound & i < clients.size(); i++){
			if(clients.get(i).getId().contentEquals(id)){
				entryFound = true;
				clients.get(i).setName(name);
			}//end if
		}//end for
		return entryFound;//product is found and changed
	}//end setClientName

	/*
	 * Function:	setClientPhone
	 * Type:		boolean
	 * Privacy:		public
	 * Description:
	 */
	public boolean setClientPhone(String id, String phone){
		Boolean entryFound = false;
		for(int i = 0; !entryFound & i < clients.size(); i++){
			if(clients.get(i).getId().contentEquals(id)){
				entryFound = true;
				clients.get(i).setPhone(phone);
			}//end if
		}//end for
		return entryFound;//product is found and changed
	}//end setClientPhone

	/*
	 * Function:	setClientAddress
	 * Type:		boolean
	 * Privacy:		public
	 * Description:
	 */
	public boolean setClientAddress(String id, String address){
		Boolean entryFound = false;
		for(int i = 0; !entryFound & i < clients.size(); i++){
			if(clients.get(i).getId().contentEquals(id)){
				entryFound = true;
				clients.get(i).setAddress(address);
			}//end if
		}//end for
		return entryFound;//product is found and changed
	}//end setClientAddress

	/*
	 * Function:	getClientBalance
	 * Type:		Double
	 * Privacy:		public
	 * Description:	returns a balance from a client, if the id does not exist then return 0
	 */
	public double getClientBalance(String id) {
		Boolean entryFound = false;
		double clientBalance = 0;
		for(int i = 0; !entryFound & i < clients.size(); i++){
			if(clients.get(i).getId().contentEquals(id)){
				entryFound = true;
				clientBalance = clients.get(i).getBalance();
			}//end if
		}//end for
		return clientBalance;
	}//end getClients

	/*
	 * Function:	writeObject
	 * Type:		void
	 * Privacy:		private
	 * Description:	Writes ClientList to disk.
	 */
	private void writeObject(java.io.ObjectOutputStream output) {
		try {
			output.defaultWriteObject();
			output.writeObject(clientList);
		} 
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}//end writeObject
	
	/*
     * Function:	readObject
     * Type:		void
     * Privacy:		private
     * Description:	Reads ClientList from disk.
	 */
	private void readObject(java.io.ObjectInputStream input) {
		try {
			if (clientList != null) {
				return;
			} 
			else {
				input.defaultReadObject();
				if (clientList == null) {
					clientList = (ClientList) input.readObject();
				} 
				else {
					input.readObject();
				}
			}
		} 
		catch(IOException ioe) {
			ioe.printStackTrace();
		} 
		catch(ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
	}//end readObject
	
	/*
     * Function:	toString
     * Type:		String
     * Privacy:		public
     * Description:	Converts ClientList to string output
	 */
	public String toString() {
		return clients.toString();
	}//end toString
}