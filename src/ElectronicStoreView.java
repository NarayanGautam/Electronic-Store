import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ElectronicStoreView extends Pane{
    private ElectronicStore model;
    private StoreSummaryPane storeSummaryPane;
    private ListPane storeStock;
    private ListPane currentCart;
    private Button reset;
    private Button addToCart;
    private Button removeFromCart;
    private Button completeSale;

    public Button getAddButton() { return addToCart; }
    public Button getRemoveFromCartButton() { return removeFromCart; }
    public Button getCompleteSale() { return completeSale; }
    public Button getResetButton() { return reset; }
    public ListPane getStoreStock() { return storeStock; }
    public ListPane getCurrentCart() { return currentCart; }
    public StoreSummaryPane getStoreSummaryPane() { return storeSummaryPane; }

    public ElectronicStoreView(ElectronicStore initModel){
        model = initModel;

        storeSummaryPane = new StoreSummaryPane();
        storeSummaryPane.relocate(0,0);

        reset = new Button("Reset Store");
        reset.relocate(20,335);
        reset.setPrefSize(145,50);

        storeStock = new ListPane("Store Stock:");
        storeStock.relocate(185,0);

        addToCart = new Button("Add to Cart");
        addToCart.relocate(260,335);
        addToCart.setPrefSize(145,50);
        addToCart.setDisable(true);

        String cartTitle = "Current Cart ($0.00):";
        currentCart = new ListPane(cartTitle);
        currentCart.relocate(495,0);

        removeFromCart = new Button("Remove from Cart");
        removeFromCart.relocate(495,335);
        removeFromCart.setPrefSize(145,50);
        removeFromCart.setDisable(true);

        completeSale = new Button("Complete Sale");
        completeSale.relocate(640,335);
        completeSale.setPrefSize(145,50);
        completeSale.setDisable(true);

        // set default values for the store summary pane and store stock
        storeSummaryPane.getNumberOfSales().setText("" + 0);
        storeSummaryPane.getRevenue().setText("" + model.getRevenue());
        storeSummaryPane.getRevenueOverSales().setText("N/A");
        storeSummaryPane.getMostPopularItems().setItems(FXCollections.observableArrayList(model.mostPopularItems()));
        storeStock.getListOfItems().setItems(FXCollections.observableArrayList(model.getStock()));

        getChildren().addAll(storeSummaryPane, reset, storeStock, currentCart, addToCart, removeFromCart, completeSale);

    }

    // update the store stock and most popular items
    public void update() {
        // get a list of the current store stock
        List<Product> realtimeStock = new ArrayList<Product>(List.of(model.getStock()));
        // a list of the stores stock quantities
        List<Integer> stockQuantities = new ArrayList<Integer>();

        // update the stock quantities and if the product is out of stock or cart quantity equals the store stock, remove it from the real time stock
        for (Product p: model.getStock()){
            stockQuantities.add(p.getStockQuantity());
            if (p.getSoldQuantity() >= model.MAX_PRODUCTS || p.getCartQuantity() == p.getStockQuantity()){
                realtimeStock.remove(p);
            }
        }
        // update the store stock and most popular item listviews
        storeStock.getListOfItems().setItems(FXCollections.observableArrayList(realtimeStock));
        System.out.println("Store stock inventory: \n" + stockQuantities);
        storeSummaryPane.getMostPopularItems().setItems(FXCollections.observableArrayList(model.mostPopularItems()));
    }

    public void updateCart(Set<Product> cartProducts){
        // create an array list to append cart quantities to the set of products in the cart
        ArrayList<String> items = new ArrayList<String>();

        // append cart quantities with their product
        for (Product p: model.getCart()){
            String toAdd = p.getCartQuantity() + "x " + p;
            items.add(toAdd);
        }
        // update current cart listview
        currentCart.getListOfItems().setItems(FXCollections.observableArrayList(items));

        // update current cart title with new cart revenue
        String cartTitle = "Current Cart ($" + model.getCartRevenue() +"):";
        currentCart.setTitle(cartTitle);

        // update complete sale buttons toggle on/off
        if (cartProducts.size() > 0)
            completeSale.setDisable(false);
        else
            completeSale.setDisable(true);
    }
}
