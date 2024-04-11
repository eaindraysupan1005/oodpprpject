public class cone extends IceCream {

	private String type;
	private double price;
	
	
	public cone(String flavor, double price, String type, String size) {
		super(flavor, size);
		this.type = type;
		this.price = price + 10.0;
		
	}
	
	
	@Override
	public void showDetails(){
		super.showDetails();
		System.out.println("Type: "+ type+ "Price: "+price);
	}
	

}
