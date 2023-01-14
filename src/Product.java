import java.util.Objects;

//Base class for all products the store will sell
public abstract class Product implements Comparable<Product>{
    private double price;
    private int stockQuantity;
    private int soldQuantity;
    private int cartQuantity;

    public Product(double initPrice, int initQuantity) {
        price = initPrice;
        stockQuantity = initQuantity;
        cartQuantity = 0;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public int getSoldQuantity() {
        return soldQuantity;
    }

    public double getPrice() {
        return price;
    }

    //Returns the total revenue (price * amount) if there are at least amount items in stock
    //Return 0 otherwise (i.e., there is no sale completed)
    public double sellUnits(int amount) {
        if (amount > 0 && stockQuantity >= amount) {
            stockQuantity -= amount;
            soldQuantity += amount;
            return price * amount;
        }
        return 0.0;
    }

    public boolean equals(Object x){
        if (!(x instanceof Product))
            return false;
        return this.toString().equalsIgnoreCase(x.toString());
    }

    public int hashCode(){
        return this.toString().hashCode();
    }

    // sorts by most quantity sold
    public int compareTo(Product p){
        int compare = p.getSoldQuantity() - this.soldQuantity;
        if (!(compare == 0))
            return compare;
        return -1;
    }

    public void addCartQuantity() { cartQuantity++; }
    public void removeCartQuantity() { cartQuantity--; }
    public int getCartQuantity() { return cartQuantity; }
    public void resetCart() { cartQuantity = 0;}
}