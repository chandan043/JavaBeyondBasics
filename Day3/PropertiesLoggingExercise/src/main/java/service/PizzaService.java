package service;

import java.util.List;

import org.apache.commons.logging.LogFactory;

import com.gl.exception.GlAcademyException;

import model.Foodie;
import model.Pizza;
import validator.PizzaValidator;

public class PizzaService {
	public Double purchasePizza(Foodie foodie) throws GlAcademyException {
	    PizzaValidator pizzaValidator = new PizzaValidator();
	    pizzaValidator.validate(foodie);
	    Double totalCost = this.calculateCost(foodie.getPizzasOrdered());

	    if (totalCost > foodie.getWalletBalance()) {
	        GlAcademyException exception = new GlAcademyException("Service.INSUFFICIENT_WALLET_BALANCE");
	        LogFactory.getLog(this.getClass()).error(exception.getMessage(), exception);
	        throw exception;
	    }

	    LogFactory.getLog(this.getClass()).info("Foodie: " + foodie.getName() + ", Total Cost: " + totalCost);
	    return totalCost;
	}

	
	//return the total cost of all the Pizza objects in the List
	public Double calculateCost(List<Pizza> pizzasOrdered) {
		return pizzasOrdered.stream().mapToDouble(pizza -> pizza.getCost()).sum();
	}
}
