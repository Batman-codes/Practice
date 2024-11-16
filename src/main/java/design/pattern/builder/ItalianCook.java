package design.pattern.builder;

public class ItalianCook implements Cook{

    private Pizza pizza;
    public ItalianCook(){
        pizza = new Pizza();
    }

    @Override
    public Pizza prepareDough() {
        pizza.setDough("Preparing Italian Dough");
        return this.pizza;
    }

    @Override
    public Pizza setBase() {
        pizza.setBase("Setting Italian Base");
        return this.pizza;
    }

    @Override
    public Pizza spreadSauce() {
        pizza.setSauce("Adding Italian Sauce");
        return this.pizza;
    }

    @Override
    public Pizza addToppings() {
        pizza.setToppings("Adding Italian Toppings");
        return this.pizza;
    }

    @Override
    public Pizza addExtraToppings() {
        pizza.setExtraToppings("Adding Extra Toppings");
        return this.pizza;
    }

    @Override
    public Pizza doBaking() {
        pizza.setBake("Doing Italian Baking");
        return this.pizza;
    }
}
