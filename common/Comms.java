package common;

import java.io.*;
import java.util.*;

public class Comms implements Serializable{

	private static final long serialVersionUID = 1033940525824106637L;		//generated serial ID
	
	/**
	 * method to send message from client to server
	 * @param order
	 * @return 
	 */
	public static File sendClientMessage(Order order) {
		
		Integer i = StaffItemsManager.getOrders().size();
		File file = new File(System.getProperty("java.io.tmpdir") + "Order" + i + ".txt");
		try {
			OutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos); 	        
			oos.writeObject(order);
			oos.close();
        } catch(IOException e){
            e.printStackTrace();
        }
		return file;
	}
	
	/**
	 * method to send list of dishes from the server to the client
	 * @param dishes
	 */
	public static void sendDishes(List<Dish> dishes) {
		
		List<Dish> tempDishes = dishes;

		try {
			OutputStream fos = new FileOutputStream(new File
					(System.getProperty("java.io.tmpdir") + "\\Menu.txt"));
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(tempDishes);
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//method to send list of postcodes from server to client
	public static void sendPostcodes(List<Postcode> postcodes) {
		
		List<Postcode> tempPostcodes = postcodes;

		try {
			OutputStream fos = new FileOutputStream(new File
					(System.getProperty("java.io.tmpdir") + "\\Postcodes.txt"));
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(tempPostcodes);
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * method to send list of users from server to client
	 * @param users
	 */
	public static void sendUsers(List<User> users) {
		List<User> tempUsers = users;

		try {
			OutputStream fos = new FileOutputStream(new File
					(System.getProperty("java.io.tmpdir") + "\\Users.txt"));
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(tempUsers);
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * method to get list of dishes from client 
	 * @return List<Dish>
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static List<Dish> messageDishes() throws IOException, ClassNotFoundException {
		InputStream fis = new FileInputStream(new File
				(System.getProperty("java.io.tmpdir") + "\\Menu.txt"));
		ObjectInputStream ois = new ObjectInputStream(fis);		
		return (List<Dish>) ois.readObject();
	}
	
	/**
	 * method to get list of postcodes from client
	 * @return List<Postcode>
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static List<Postcode> messagePostcodes() throws IOException, ClassNotFoundException {
		InputStream fis = new FileInputStream(new File
				(System.getProperty("java.io.tmpdir") + "\\Postcodes.txt"));
		ObjectInputStream ois = new ObjectInputStream(fis);		
		return (List<Postcode>) ois.readObject();
	}
	
	/**
	 * method to get order from client
	 * @return order
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Order messageOrder(File order) throws IOException, ClassNotFoundException {
		InputStream fis = new FileInputStream(order);
		ObjectInputStream ois = new ObjectInputStream(fis);
		return (Order) ois.readObject();
	}
	
	/**
	 * method to get list of users from client
	 * @return List<User>
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static List<User> messageUsers() throws IOException, ClassNotFoundException {
		InputStream fis = new FileInputStream(new File
				(System.getProperty("java.io.tmpdir") + "\\Users.txt"));
		ObjectInputStream ois = new ObjectInputStream(fis);
		return (List<User>) ois.readObject();
	}
}