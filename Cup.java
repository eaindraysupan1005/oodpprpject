public class Cup extends IceCream{
 
	private String type;
	private int count;	
	
	public Cup(String flavor, double price, String type, int count) {
		super(flavor, price);
		this.type = type;			
		this.count = count;
	}

    @Override
	public String toString(){
		return super.toString()+ ", "+ type + ", "+ count + " servings";
	} 

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
