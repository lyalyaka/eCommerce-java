
public class Ebook extends Readable {
	@Override
	public int getPrice(){   // override and only call the parent’s getPrice() to get the base price
		return super.getPrice();
	}
}