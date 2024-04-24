public class Cup extends IceCream{
 
	private String type;
	private int count;	
	
	public Cup(String flavor, double price,int count) {
		super(flavor, price);			
		this.count = count;
		this.type = "cup";
	}

    @Override
	public String toString(){
		return super.toString()+ ", "+ type + ", "+ count + " servings";
	} 
}
