public class Cup extends IceCream{

	private String type;
	private double price;
	
	
	public Cup(String flavor, double price, String type, String size) {
		super(flavor, price);
		this.type = type;
		this.price = price + 10.0;
		
	}

    @Override
	public String toString(){
		return super.toString()+" type: "+type+" price: "+price;
	}
}
