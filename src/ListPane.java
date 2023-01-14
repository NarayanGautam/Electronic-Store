import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;

public class ListPane extends Pane {
    private ListView listOfItems;
    private Label listLabel;

    public ListPane(String title){
        Pane innerPane = new Pane();
        listLabel = new Label(title);
        listLabel.relocate(100,10);

        listOfItems = new ListView();
        listOfItems.relocate(0,30);
        listOfItems.setPrefSize(290,300);

        innerPane.getChildren().addAll(listLabel, listOfItems);
        getChildren().add(innerPane);
    }

    public ListView getListOfItems() { return listOfItems; }
    public void setTitle(String title){ listLabel.setText(title); }
}
