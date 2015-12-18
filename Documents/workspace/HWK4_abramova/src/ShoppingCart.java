import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

public class ShoppingCart extends User {
 
	LinkedList<Content> contentList = new LinkedList<Content>();
	class Content{
		private int SNo, qty, price;
		private String name, type;
		Date date;
		
		public Content(int purchaseSNo, int purchaseQty, String purchaseName, int purchasePrice, String purchaseType){
			this.SNo = purchaseSNo;
			this.qty = purchaseQty;
			this.name = purchaseName;
			this.type = purchaseType;
			this.date = new Date();
		}
	}
	
	public void addContentShopCart(int purchaseSNo, int purchaseQty, String purchaseName, int purchasePrice, String purchaseType){
		contentList.add(new Content(purchaseSNo, purchaseQty, purchaseName, purchasePrice, purchaseType));
		System.out.println(purchaseQty + purchaseName + " " + purchaseType + "s sucessfully added to your cart.");
	}
 
	public ShoppingCart(String name){
		super(name);
	}

	public ShoppingCart(String name, String newUser){
		super(name, "new");
	}
	
	public LinkedList<Content> getContent(){ // return the content of the shopping cart
		return contentList;
	}
	
	public void printShoppingCart(){
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		for(Content c: contentList) System.out.println(c.SNo + "," + c.name + "," + dateFormat.format(c.date)  + "," + c.qty);
	}	
	
	public void printCheckOut(String confirmationID){
		int totalPrice=0;
		System.out.println("Billing Information:");
		System.out.printf("%-20s %-10s %s\n", "Name", "Quantity", "Price");
		for(Content c: contentList) System.out.printf("%-20s %-10s %s\n", c.name, c.qty, totalPrice += c.price);
		System.out.printf("%-20s %-10s %d\n", "HST (13%)", (int)(totalPrice * 0.13));
		System.out.printf("%-20s %-10s %d\n", "Shipping (10%)", (int)(totalPrice * 0.1));
		System.out.printf("%-20s %-10s %s\n", "", "", "___________");
		System.out.printf("%-20s %-10s %d\n", "Total:", "", totalPrice *= 1.23);
		System.out.println("\nAre you sure you  want to pay? yes or no: ");
		System.out.println("Confirmation ID: " + confirmationID);
	}
}

