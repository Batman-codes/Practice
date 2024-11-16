package design.pattern.builder;

public class HeadChef {

    private Cook cook;

    public HeadChef(Cook cook){
       this.cook = cook;
    }

    public Pizza deliverPizza(){
        cook.prepareDough();
        cook.setBase();
        cook.spreadSauce();
        cook.addToppings();
        cook.addExtraToppings();
        return cook.doBaking();
    }


}
