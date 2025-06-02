package com.globallogic;
import java.util.*;
import java.util.stream.Collectors;
import java.time.*;

class Sales{
	String productName;
	double saleAmount;
	String customerID;
	LocalDate dateOfSale;
	
	public Sales(String productName, double saleAmount,String customerID,LocalDate dateOfSale) {
		this.productName = productName;
		this.saleAmount = saleAmount;
		this.customerID = customerID;
		this.dateOfSale = dateOfSale;
	}
	public String getSalesData() {
		return productName+" "+saleAmount+" "+customerID+" "+dateOfSale;
	}
}

//ix
class SaleSummary {
    String productName;
    double totalAmount;

    public SaleSummary(String productName, double totalAmount) {
        this.productName = productName;
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return productName + " -> Total Sales: " + totalAmount;
    }
}


public class Stream2 {

	public static void main(String[] args) {
		List<Sales> sale = new ArrayList<>();
		sale.add(new Sales("Smartphone", 299.99, "C001", LocalDate.of(2022, 12, 15)));
	    sale.add(new Sales("Smartphone", 399.99, "C002", LocalDate.of(2023, 1, 10)));
	    sale.add(new Sales("Laptop", 899.99, "C001", LocalDate.of(2023, 2, 5)));
	    sale.add(new Sales("Tablet", 150.00, "C003", LocalDate.of(2023, 3, 12)));
	    sale.add(new Sales("Smartphone", 250.00, "C004", LocalDate.of(2023, 1, 20)));
	    sale.add(new Sales("Laptop", 999.99, "C005", LocalDate.of(2022, 11, 30)));
	    sale.add(new Sales("Tablet", 75.00, "C006", LocalDate.of(2023, 2, 28)));
	    
//	    i) Filter out all sales that occurred after a certain date, say January 1, 2023.
	    sale.stream().filter(x-> x.dateOfSale.isAfter(LocalDate.of(2023,1, 1))).forEach(x->System.out.println(x.getSalesData()));
	    System.out.println("--------------------------");
//	    ii) Map the sales to their corresponding product names.
	    sale.stream().map(x -> x.productName).forEach(System.out::println);
	    System.out.println("--------------------------");
//	    iii) Calculate the total sales amount for a specific product, e.g., "Smartphone".
	    double s2 = sale.stream().filter(x->x.productName.equals("Smartphone")).mapToDouble(x->x.saleAmount).sum();
		System.out.println(s2);
		System.out.println("--------------------------");
//		iv) Sort the sales by sale amount in descending order.		
		sale.stream().sorted((a, b) -> Double.compare(b.saleAmount, a.saleAmount)) // Descending order
	    .forEach(x -> System.out.println(x.getSalesData()));
		System.out.println("--------------------------");
//		v) Collect the distinct customer IDs from all sales.
		List<String> distinctCId = sale.stream()
			    .map(x -> x.customerID)
			    .distinct()
			    .collect(Collectors.toList());
		System.out.println(distinctCId);
		System.out.println("--------------------------");
//		vi) Group the sales by product name.
		Map<String, List<Sales>> groupedByProduct = sale.stream()
			    .collect(Collectors.groupingBy(s -> s.productName));

			groupedByProduct.forEach((product, salesList) -> {
			    System.out.println("Product: " + product);
			    salesList.forEach(s -> System.out.println("  " + s.getSalesData()));
			});
		System.out.println("--------------------------");
//		vii) Filter the sales to include only those with a sale amount greater than $100, then calculate the total
//		sales amount for those sales.
		double tsum1 = sale.stream().filter(x->x.saleAmount > 100).mapToDouble(s->s.saleAmount).sum();
		System.out.println(tsum1);
		System.out.println("--------------------------");
//		viii) Perform one of the previous operations using parallel streams for potentially improved
//		performance.
		double tsum2 = sale.parallelStream().filter(x->x.saleAmount > 100).mapToDouble(s->s.saleAmount).sum();
		System.out.println(tsum2);
		System.out.println("--------------------------");
//		ix) Map the sales to a new object SaleSummary, which contains the product name and the total sales
//		amount for that product.
		List<SaleSummary> summaries = sale.stream()
			    .collect(Collectors.groupingBy(s -> s.productName, Collectors.summingDouble(s -> s.saleAmount)))
			    .entrySet().stream()
			    .map(entry -> new SaleSummary(entry.getKey(), entry.getValue()))
			    .collect(Collectors.toList());

			summaries.forEach(System.out::println);

		System.out.println("--------------------------");
//		x) Calculate the average sales amount across all sales.
		OptionalDouble average = sale.stream()
			    .mapToDouble(s -> s.saleAmount)
			    .average();
		System.out.println(average);
		System.out.println("--------------------------");
	}

}
