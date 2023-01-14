import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class StoreSummaryPane extends Pane {
    private TextField numberOfSales;
    private TextField revenue;
    private TextField revenueOverSales;
    private ListView mostPopularItems;

    public StoreSummaryPane() {
        Pane innerPane = new Pane();

        Label summary = new Label("Store Summary:");
        summary.relocate(30,10);

        Label salesLabel = new Label("# Sales:");
        salesLabel.relocate(30,35);
        numberOfSales = new TextField();
        numberOfSales.relocate(75,30);
        numberOfSales.setPrefSize(100, 30);

        Label revenueLabel = new Label("Revenue:");
        revenueLabel.relocate(20,70);
        revenue = new TextField();
        revenue.relocate(75,65);
        revenue.setPrefSize(100, 30);

        Label revenuePerSaleLabel = new Label("$ / Sale:");
        revenuePerSaleLabel.relocate(25,105);
        revenueOverSales = new TextField();
        revenueOverSales.relocate(75,100);
        revenueOverSales.setPrefSize(100, 30);

        Label popularItems = new Label("Most Popular Items:");
        popularItems.relocate(30,135);
        mostPopularItems = new ListView();
        mostPopularItems.relocate(10,160);
        mostPopularItems.setPrefSize(165,170);

        innerPane.getChildren().addAll(summary, salesLabel, numberOfSales, revenueLabel, revenue,
                revenuePerSaleLabel, revenueOverSales, popularItems, mostPopularItems);

        getChildren().addAll(innerPane);

    }

    public TextField getNumberOfSales() { return numberOfSales; }
    public TextField getRevenue() { return revenue; }
    public TextField getRevenueOverSales() { return revenueOverSales; }
    public ListView getMostPopularItems() { return mostPopularItems; }
    public void setNumberOfSales(int sales) { numberOfSales.setText("" + sales);}
    public void setRevenue(double saleRevenue) { revenue.setText("" + saleRevenue); }
    public void setRevenueOverSales(int sales, double salesRevenue) {
        double revPerSale = Math.round((salesRevenue/sales) * 100.0)/100.0;
        revenueOverSales.setText("" + revPerSale);
    }
}
