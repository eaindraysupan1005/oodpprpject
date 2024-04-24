public class Cone extends IceCream {

	private String type;
	private int count;
		
	public Cone(String flavor, double price, int count) {
		super(flavor, price);	
		this.count = count;
		this.type = "cone";
	}
	
	
	@Override
	public String toString(){
		return super.toString()+ ", "+ type + ", "+ count + " servings";
	}
	

}
