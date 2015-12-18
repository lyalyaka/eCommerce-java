import java.io.*;
import java.util.*;

public class UserInterface {
	
	private int currentPage = 1; // the page number (P1...P10)
	String[] readableFiles = {"Books.txt", "Ebooks.txt"};
	String[] audioFiles = {"CDs.txt", "MP3.txt"};
	LinkedList<Readable> readableList = new LinkedList<Readable>();
	LinkedList<Audio> audioList = new LinkedList<Audio>();
	Scanner scanner = new Scanner(System.in);
	ShoppingCart user;
	String[] ar = {};
	String str, type;
	int scanned, purchaseSNo, purchaseQty;
	int confirmationID = 1000;
	
	public int getCurrentPage(){
		return this.currentPage;
	}
	
	public int changeCurrentPage() {// This method is for page navigation. It should change to current page and show the content.
		boolean dontStop = true;
		
		while(dontStop){
			switch (currentPage){
		      //DONE
				case 1: //P1
		            System.out.println("\n1.Sign in\n2.Sign up\nChoose your option: ");
		            scanned = scanner.nextInt();
		            currentPage = (scanned == 1) ? 3 : (scanned == 2) ? 2 : 1;
		        	break;
		      //DONE
		        case 2: //P2   Add a user
		        	System.out.println("Choose your username:");
		        	user = new ShoppingCart(scanner.next(), "new");
		        	currentPage = 1;
		            break;
		      //DONE
		        case 3: //P3 - P4
		        	System.out.println("Enter your username: ");
		        	String userName = scanner.next();
		        	if( getUsers(userName) ){
		        		user = new ShoppingCart(userName);
		        		System.out.printf("Hello %s\n\n", userName);
		        		currentPage = 4;
		        	}
		        	else{
		        		System.out.println("No Access");
		        		currentPage = 1;
		        	}
		        	break;
		      //DONE	
		        case 4: //P5
		        	System.out.println("\n1.View Items By Category \n2.View Shopping Cart \n3.Sign Out \n4.View Previous Orders \nChoose your option: ");
		        	scanned = scanner.nextInt();
		        	currentPage = (scanned == 1) ? 5 : (scanned == 2) ? 6 : (scanned == 4) ? 8 : 4;
		        	if(scanned == 3) dontStop = false;
		        	break;
		      //DONE
		        case 5: //P6 Display readables or audio
		        	System.out.println("\n1.Readables \n2.Audio \nChoose your option:\n\nPress -1 to return to the previous menu");	        	
		        	scanned = scanner.nextInt();
		        	currentPage = (scanned == 1) ? showReadables() : (scanned == 2) ? showAudioProducts() : (scanned == -1) ? 4 : 5;
		        	break;
		      //DONE	
		        case 6: //P7 Shopping Cart
		        	user.printShoppingCart();
		        	currentPage = 4;
		        	break;
		        	
		        case 7: //P10 Checkout
		        	user.printCheckOut("U" + confirmationID);
		        	confirmationID++;
		        	System.out.println("Items shipped to " + user.getUsername());
		        	break;
		        	
		        case 8: //P11 Previous Orders
		        	
		      }
		}
		scanner.close();
		return 0;
	}
		
	
	public void getReadables(){
		try {
			for(String filename: readableFiles){
				BufferedReader in = new BufferedReader(new FileReader(filename));
				while ((str = in.readLine()) != null){
		        	ar = str.split(",");
		        	String type = (filename.equals("Books.txt")) ? "Book" : "eBook";
		        	readableList.add(new Readable(ar, type));
				}
		    in.close();
			}   
	    } catch (IOException e) {
	        System.out.println("File Read Error");
	    }
	}
	
	
	public void getAudioProducts(){
		try {    
			for(String filename: audioFiles){
				BufferedReader in = new BufferedReader(new FileReader(filename));
				while ((str = in.readLine()) != null){
		        	ar = str.split(",");
		        	type = (filename.equals("CDs.txt")) ? "CD" : "MP3";
		        	audioList.add(new Audio(ar, type));
		        }
		        in.close();
			} 
	    } catch (IOException e) {
	        System.out.println("File Read Error");
	    }
	}
	
	
	public boolean getUsers(String name){
		boolean foundIt = false;
		try {
            BufferedReader in = new BufferedReader(new FileReader("Users.txt"));
            while ((str = in.readLine()) != null)
	        	if (str.equals(name)) foundIt = true;
            in.close();
	    } catch (IOException e) {
            System.out.println("File Read Error");
        }
		return foundIt;
	}
	
	
	public int showReadables(){
		System.out.printf("%s\t %-29s %-9s %-15s %-15s %s\n", "SNo", "Name of the Book", "Author", "Price($)", "Quantity", "Type");
		for(Readable r: readableList)
			System.out.printf("%d\t %-29s %-9s %-15.0f %-15d %s\n", r.sNo, r.name, r.authorName, r.price, r.qty, r.type);
    	System.out.println("\nChoose your option: \nPress -1 to return to the previous menu");
    	scanned = scanner.nextInt();
    	if(scanned == -1) return 5;
    	purchaseSNo = scanned;
    	System.out.println("Enter quantity: ");
		purchaseQty = scanner.nextInt();
    	for(Readable r: readableList){
    		if(r.getSNo() == purchaseSNo){
    			user.addContentShopCart(purchaseSNo, purchaseQty, r.name, r.getPrice(), r.type);
    			r.qty--;
    			break;
    		}
    	}
    	System.out.println("\nPress -2 to Continue Shopping or Press 0 to CheckOut:");
    	scanned = scanner.nextInt();
    	return (scanned == 0) ? 7 : 5;
	}
	
	
	public int showAudioProducts(){
		System.out.printf("%s\t %-29s %-9s %-15s %-15s %s\n",  "SNo", "Name", "Artist", "Price($)", "Quantity", "Type");
		for(Audio a: audioList)
			System.out.printf("%d\t %-29s %-9s %-15.0f %-15d %s\n", a.sNo, a.name, a.artistName, a.price, a.qty, a.type);
		System.out.println("\nChoose your option: \nPress -1 to return to the previous menu");
		scanned = scanner.nextInt();
    	if(scanned == -1) return 5;
    	purchaseSNo = scanned;
    	System.out.println("Enter quantity: ");
		purchaseQty = scanner.nextInt();
    	for(Audio a: audioList){
    		if(a.getSNo() == purchaseSNo){
    			user.addContentShopCart(purchaseSNo, purchaseQty, a.name, a.getPrice(), a.type);
    			a.qty--;
    			break;
    		}
    	}
    	System.out.println("\nPress -2 to Continue Shopping or Press 0 to CheckOut:");
    	scanned = scanner.nextInt();
    	return (scanned == 0) ? 7 : 5;
	}
}
