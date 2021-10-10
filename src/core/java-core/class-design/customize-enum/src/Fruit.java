public enum Fruit {

    APPLE("apple"),

    BANANA("banana"),

    DRAGON_FRUIT("dragon_fruit");

    private String name;

    Fruit(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public String getNameToCorrespondType(Fruit fruit) {
        return fruit.getName();
    }

}
