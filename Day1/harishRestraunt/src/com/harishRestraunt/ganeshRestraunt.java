package com.harishRestraunt;
import java.util.*;

//menuItems
class menuItems{
	String name;
	String category;
	double price;
	
	//setter methoid
	public menuItems(String name,String category,double price) {
		this.name = name;
		this.category = category;
		this.price = price;
	}
	
	//getter method
	public String getmenuItems() {
		return " Name:"+" "+ name +" "+"category"+ category +" "+ "price"+ price; 		
	}
}


@FunctionalInterface
interface CategorizerMenuItem{
	public String getCategory(String item,ArrayList<menuItems> menu);
}

@FunctionalInterface
interface ApplySpecialOffer1{
	public int getDiscount(String item,ArrayList<menuItems> menu,HashMap<String,Integer> discountData);
}


public class ganeshRestraunt {
	public static void main(String args[]) {
		ArrayList<menuItems> menu = new ArrayList<>();
		HashMap<String,Integer> discountData = new HashMap<>();
		
		menu.add(new menuItems("pizza","Main Course",250));
		menu.add(new menuItems("Soda","Drink",30));
		menu.add(new menuItems("cake","Desert",50));
		discountData.put("Main Course",20);
		discountData.put("Drink",10);
		discountData.put("Desert",5);
		
		
		CategorizerMenuItem obj = (String item,ArrayList<menuItems> menuMap) -> {
			
			for(menuItems data : menuMap ) {
				if(data.name == item) {
					return data.category;
				}
			}
			return "Invalid item";
		};
		
		ApplySpecialOffer1 obj2 = (String item,ArrayList<menuItems> menuMap,HashMap<String,Integer> discountDataMap)->{
			String category = "/0";
			for(menuItems data : menuMap ) {
				if(data.name == item) {
					category =  data.category;
				}
			}
			if(category == "/0")
				return 0;
			
			return discountDataMap.get(category);
		};
		
		System.out.println(obj.getCategory("cake",menu));
		System.out.println(obj2.getDiscount("cake",menu,discountData));
		
	}
}
