package design.pattern.builder;

public interface Cook {

    public Pizza prepareDough();
    public Pizza setBase();
    public Pizza spreadSauce();
    public Pizza addToppings();
    public Pizza addExtraToppings();
    public Pizza doBaking();

}
