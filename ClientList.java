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