package main.builderpattern;

public class Drink {


    private String brand;
    private boolean hasIce;
    private boolean hasSoda;
    private String ounce;


    public Drink(Builder builder) {
        brand = builder.brand;
        hasIce = builder.addIce;
        hasSoda = builder.addSoda;
        ounce = builder.ounce;
    }


    public static class Builder {

        private String brand;
        private boolean addSoda;
        private boolean addIce;
        private String ounce;

        public Builder(String brand) {
            this.brand = brand;
        }

        public Builder withSoda(boolean addSoda) {
            this.addSoda = addSoda;
            return this;
        }

        public Builder withIce(boolean addIce) {
            this.addIce = addIce;
            return this;
        }

        public Builder size(String ounce) {
            this.ounce = ounce;
            return this;
        }

        public Drink build() {
            return new Drink(this);
        }
    }

    @Override
    public String toString() {
        return "Drink{" +
                "brand='" + brand + '\'' +
                ", withIce=" + (hasIce ? "Yes" : "No") +
                ", withSoda=" + (hasSoda ? "Yes" : "No") +
                ", ounce='" + ounce + '\'' +
                '}';
    }
}
