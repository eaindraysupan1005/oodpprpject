public class cone extends IceCream {
	private String type;
	
	
	public cone(String flavor, double price) {
		super.flavor = flavor;
		super.price = price;
		this.type = "cone";
		
	}
	
	
	@Override
	public String toString(){
	
        return "Flavor: " + flavor + ", Type: " + type + ", Price: $" + price;
    
	}
	

}
