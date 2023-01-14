import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ElectronicStoreApp extends Application {
    private ElectronicStore model;
    private ElectronicStoreView view;

    public ElectronicStoreApp() {
        this.model = ElectronicStore.createStore();
        this.view = new ElectronicStoreView(model);
    }

    public void start(Stage primaryStage) {
        view.getStoreStock().getListOfItems().setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                handleAddButtonSelect();
            }
        });

        view.getAddButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                handleAdd();
            }
        });

        view.getCurrentCart().getListOfItems().setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                handleCartButtonSelect();
            }
        });

        view.getRemoveFromCartButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                handleRemove();
            }
        });

        view.getCompleteSale().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                handleCompleteSale();
            }
        });

        view.getResetButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // when the reset button is clicked, reset the app and its model and view to initial state
                ElectronicStoreApp app = new ElectronicStoreApp();
                app.start(primaryStage);
            }
        });

        primaryStage.setTitle("Electronic Store Application - " + model.getName());
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(view, 800, 400)); // Set size of window
        primaryStage.show();

        view.update();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void handleAddButtonSelect(){
        // get the selected items index, and if something is selected, enable the add to cart button
        int selectedStoreStockIndex = view.getStoreStock().getListOfItems().getSelectionModel().getSelectedIndex();
        view.getAddButton().setDisable(selectedStoreStockIndex < 0);
    }

    public void handleCartButtonSelect(){
        // get the selected cart items index, and if something is selected, enable the remove from cart button
        int selectedCartItemIndex = view.getCurrentCart().getListOfItems().getSelectionModel().getSelectedIndex();
        view.getRemoveFromCartButton().setDisable(selectedCartItemIndex < 0);
    }

    public void handleAdd(){
        // get the selected item, and if there is enough of that item in stock, update that items cart quantity and update the view
        Product item = (Product) view.getStoreStock().getListOfItems().getSelectionModel().getSelectedItem();
        if (item != null && item.getCartQuantity() < item.getStockQuantity()) {
            item.addCartQuantity();
            if (item.getCartQuantity() == item.getStockQuantity())
                view.update();
        } else{
            view.update();
        }

        // add the item to the models cart, and update the views cart
        model.addToCart(item);
        view.updateCart(model.getCart());
        // handle the add buttons toggle on/of
        handleAddButtonSelect();
    }

    public void handleRemove(){
        // get the selected cart items index
        int selectedIndex = view.getCurrentCart().getListOfItems().getSelectionModel().getSelectedIndex();
        if (selectedIndex < 0)
            return;

        // since the cart is stored as a string array, we create a product array of items that stores the cart products
        Product[] items = model.getCart().toArray(new Product[0]);
        // get the item to remove from the cart
        Product itemToRemove = items[selectedIndex];

        // update the items to removes cart quantity, and update the model and view
        if (itemToRemove != null){
            if (itemToRemove.getCartQuantity() > 0)
                itemToRemove.removeCartQuantity();
            if (itemToRemove.getCartQuantity() == 0)
                model.getCart().remove(itemToRemove);
            view.updateCart(model.getCart());
        }
        view.update();
        System.out.println("Removed " + itemToRemove + " from the cart");
        // handle the remove from carts toggle on/off
        handleCartButtonSelect();
    }

    public void handleCompleteSale(){
        // increment the number of sales in the model, and update the views store summary pane
        model.addSale();
        view.getStoreSummaryPane().setNumberOfSales(model.getNumberOfSales());
        model.setRevenue(model.getRevenue() + model.getCartRevenue());
        view.getStoreSummaryPane().setRevenue(model.getRevenue());
        view.getStoreSummaryPane().setRevenueOverSales(model.getNumberOfSales(), model.getRevenue());

        // sell the products in the cart and reset the products cart quantity to 0
        for (Product p: model.getCart()){
            p.sellUnits(p.getCartQuantity());
            p.resetCart();
        }
        // clear the models cart and update the views
        model.getCart().clear();
        view.update();
        view.updateCart(model.getCart());
        // handle the remove from carts toggle on/off
        handleCartButtonSelect();
    }
}
