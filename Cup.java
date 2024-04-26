public class Cup extends IceCream{
 
	private String type;
	private int count;	
	
	public Cup(String flavor, double price,int count) {
		super(flavor, price);			
		this.count = count;
		this.type = "cup";
	}

    public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString(){
		return super.toString()+ ", "+ type + ", "+ count + " servings";
	} 
}
