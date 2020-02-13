import java.text.DecimalFormat;
import java.util.*;
import java.io.*;


public class ClientList implements Serializable {
	private static final long serialVersionUID = 1L;
	private List clients = new LinkedList();
	private static ClientList clientList;
	
	/*ClientList Constructor*/
	private ClientList() {
	}//end constructor
	
	/*Creates an instance of ClientList*/
	public static ClientList instance() {
		if (clientList == null) {
			return (clientList = new ClientList());
		} 
		else {
			return clientList;
		}
	}//end instance
	
	/*Inserts a Client into ClientList*/
	public boolean insertClient(Client client) {
		clients.add(client);
		return true;
	}//end insertClient

	/*Returns an iterator for ClientList*/
	public Iterator getClients(){
		return clients.iterator();
	}//end getClients
  
	/*Writes ClientList to disk*/
	private void writeObject(java.io.ObjectOutputStream output) {
		try {
			output.defaultWriteObject();
			output.writeObject(clientList);
		} 
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}//end writeObject
	
	/*Reads ClientList from disk*/
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
	
	/*Converts ClientList to string output*/
	public String toString() {
		return clients.toString();
	}//end toString
}