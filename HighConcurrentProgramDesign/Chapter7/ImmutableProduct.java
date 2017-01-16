package HighConcurrentProgramDesign.Chapter7;

/**
 * Created by Taocr on 2017/1/14.
 */
public final class ImmutableProduct {
    private final String no ;
    private final String name;
    private final double price;

    public ImmutableProduct(String no, String name, double price) {
        this.no = no;
        this.name = name;
        this.price = price;
    }

    public String getNo() {
        return no;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
