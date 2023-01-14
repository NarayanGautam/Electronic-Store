//Class representing an electronic store
//Has an array of products that represent the items the store can sell

import java.util.*;

public class ElectronicStore {
    public final int MAX_PRODUCTS = 10; //Maximum number of products the store can have
    private int curProducts;
    private String name;
    private Product[] stock; //Array to hold all products
    private double revenue;
    private Set<Product> cart; // set that holds all the unique items added to the cart
    private int numberOfSales; // keep track of the number of sales completed

    public ElectronicStore(String initName) {
        revenue = 0.0;
        name = initName;
        stock = new Product[MAX_PRODUCTS];
        curProducts = 0;
        cart = new HashSet<Product>();
        numberOfSales = 0;
    }

    public String getName() {
        return name;
    }

    public Product[] getStock() {
        Product[] items = new Product[curProducts];
        for (int i=0; i<curProducts; i++)
            items[i] = stock[i];
        return items;
    }

    public ArrayList<Product> mostPopularItems(){
        // create a treeset to sort the products by quantity sold
        TreeSet<Product> sortedPopularItems = new TreeSet<Product>(List.of(getStock()));
        ArrayList<Product> popularItems = new ArrayList<Product>();

        // add the first 3 items in the treeset to the popular items arraylist
        int count = 0;
        for (Product p: sortedPopularItems){
            if (count < 3){
                popularItems.add(p);
                count++;
            } else {
                break;
            }
        }
        return popularItems;
    }

    public double getRevenue() { return revenue; }
    public void setRevenue(Double rev) { revenue = rev; }

    //Adds a product and returns true if there is space in the array
    //Returns false otherwise
    public boolean addProduct(Product newProduct) {
        if (curProducts < MAX_PRODUCTS) {
            stock[curProducts] = newProduct;
            curProducts++;
            return true;
        }
        return false;
    }

    public void addToCart(Product item){ cart.add(item); } // adds selected item to the cart
    public Set<Product> getCart(){ return cart; }
    // get the total of the items in the cart
    public double getCartRevenue(){
        double cartRevenue =0;
        for (Product p: cart)
            cartRevenue += p.getPrice() * p.getCartQuantity();
        return Math.round(cartRevenue * 100.0)/100.0;
    }
    public void addSale(){ numberOfSales++; } // when a sale is complete, number of sales is incremented
    public int getNumberOfSales() { return numberOfSales; }

    public static ElectronicStore createStore() {
        ElectronicStore store1 = new ElectronicStore("Watts Up Electronics");
        Desktop d1 = new Desktop(100, 10, 3.0, 16, false, 250, "Compact");
        Desktop d2 = new Desktop(200, 10, 4.0, 32, true, 500, "Server");
        Laptop l1 = new Laptop(150, 10, 2.5, 16, true, 250, 15);
        Laptop l2 = new Laptop(250, 10, 3.5, 24, true, 500, 16);
        Fridge f1 = new Fridge(500, 10, 250, "White", "Sub Zero", false);
        Fridge f2 = new Fridge(750, 10, 125, "Stainless Steel", "Sub Zero", true);
        ToasterOven t1 = new ToasterOven(25, 10, 50, "Black", "Danby", false);
        ToasterOven t2 = new ToasterOven(75, 10, 50, "Silver", "Toasty", true);
        store1.addProduct(d1);
        store1.addProduct(d2);
        store1.addProduct(l1);
        store1.addProduct(l2);
        store1.addProduct(f1);
        store1.addProduct(f2);
        store1.addProduct(t1);
        store1.addProduct(t2);
        return store1;
    }
} 