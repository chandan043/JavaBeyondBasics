package userinterface;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import model.Foodie;
import model.Pizza;
import model.PizzaSize;
import service.PizzaService;

public class PizzaShoppie {
    private static final Log LOGGER = LogFactory.getLog(PizzaShoppie.class);

    public static void main(String[] args) throws FileNotFoundException, IOException {
    	Properties props = new Properties();
    	props.load(PizzaShoppie.class.getClassLoader().getResourceAsStream("configuration.properties"));

        try {

            String name = "Jacob"; // Optional: could also be loaded from properties
            Double balance = 1000d;

            List<Pizza> pizzasOrdered = new ArrayList<>();
            pizzasOrdered.add(new Pizza(PizzaSize.REGULAR));
            pizzasOrdered.add(new Pizza(PizzaSize.MEDIUM));
            pizzasOrdered.add(new Pizza(PizzaSize.LARGE));

            Foodie foodie = new Foodie(name, balance, pizzasOrdered);
            PizzaService pizzaService = new PizzaService();

            Double totalCost = pizzaService.purchasePizza(foodie);
            LOGGER.info(props.getProperty("PizzaShoppe.SUCCESS") + " " + totalCost);
        } catch (Exception e) {
            try {
            	
                String message = props.getProperty(e.getMessage());
                if (message != null) {
                    LOGGER.info(message);
                } else {
                    LOGGER.error("Unexpected error occurred", e);
                }
            } catch (Exception ex) {
                LOGGER.error("Failed to load configuration or map error message", ex);
            }
        }
    }
}
