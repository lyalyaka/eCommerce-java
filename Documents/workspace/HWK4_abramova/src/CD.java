
public class CD extends Audio {
	@Override
	public int getPrice(){   // override to get the item price and add 2% (Environment Tax)
		return (int) (super.price * 1.02);
	}
}
