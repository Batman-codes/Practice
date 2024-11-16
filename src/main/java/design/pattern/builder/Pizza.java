package design.pattern.builder;

public class Pizza {

    private String dough;
    private String base;
    private String sauce;
    private String  toppings;
    private String extraToppings;
    private String bake;

    public String getDough() {
        return dough;
    }

    public void setDough(String dough) {
        this.dough = dough;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getSauce() {
        return sauce;
    }

    public void setSauce(String sauce) {
        this.sauce = sauce;
    }

    public String getToppings() {
        return toppings;
    }

    public void setToppings(String toppings) {
        this.toppings = toppings;
    }

    public String getExtraToppings() {
        return extraToppings;
    }

    public void setExtraToppings(String extraToppings) {
        this.extraToppings = extraToppings;
    }

    public String getBake() {
        return bake;
    }

    public void setBake(String bake) {
        this.bake = bake;
    }

    public void getPizza(){
        System.out.println("dough" + dough  + "," + "base" + base  + "," + "sauce" + sauce  + ","  + "extraToppings" + extraToppings );
    }
}
