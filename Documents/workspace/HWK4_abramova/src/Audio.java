
public class Audio extends Item {
	protected String artistName;
	protected String name;
	protected Integer qty;
	protected String type;
	
	public Audio(){}; //to avoid error implicit super constructor is undefined for default constructor
	
	public Audio(String[] a, String type){
		super.sNo = Integer.parseInt(a[0]);
		this.name = a[1];
		this.artistName = a[2];
		super.price = Integer.parseInt(a[3].trim());
		this.qty = Integer.parseInt(a[4].trim());
		this.type = type;
	}

	public String getInfo(){
		return super.sNo + " " + this.name + " " + this.artistName + " " + super.price + " " + this.qty;
	}
	
	public int getSNo(){
		return super.sNo;
	}
	
	@Override
	public int getPrice(){
		return super.price;
	}
}