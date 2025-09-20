package firelack.rosestrades.cosmetics;

public class Cosmetic {
    private final String id;
    private final String name;
    private final int price;

    public Cosmetic(String id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public int getPrice() { return price; }
}
