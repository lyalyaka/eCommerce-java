
public class Readable extends Item {
	protected String authorName;  //useless
	protected String name;
	protected Integer qty;
	protected String type;
	
	public Readable(){}; //to avoid error implicit super constructor is undefined for default constructor
	
	public Readable(String[] r, String type){
		super.sNo = Integer.parseInt(r[0]);
		this.name = r[1];
		this.authorName = r[2];
		super.price = Integer.parseInt(r[3].trim());
		this.qty = Integer.parseInt(r[4].trim());
		this.type = type;
	}
	
	public String getInfo(){
		return super.sNo + " " + this.name + " " + this.authorName + " " + super.price + " " + this.qty;
	}
	
	public int getSNo(){
		return super.sNo;
	}
	
	@Override
	public int getPrice(){
		return super.price;
	}
}
		
