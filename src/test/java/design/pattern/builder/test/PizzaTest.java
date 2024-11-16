package design.pattern.builder.test;

import design.pattern.builder.Cook;
import design.pattern.builder.HeadChef;
import design.pattern.builder.ItalianCook;

public class PizzaTest {

    public static void main(String[] args) {

        Cook cook = new ItalianCook();
        HeadChef chef = new HeadChef(cook);
        chef.deliverPizza().getPizza();
    }
}
