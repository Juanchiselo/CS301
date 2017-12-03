import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.css.PseudoClass;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.*;

public class MainWindowController
{
    // TableView
    @FXML private TableView<InterpolationNode> dividedDifferenceTableView;
    @FXML private TableColumn<String, String> xColumn;
    @FXML private TableColumn<String, String> yColumn;
    private ObservableList<InterpolationNode> tableData = FXCollections.observableArrayList();

    // Tabs
    @FXML private TabPane mainWindowTabPane;
    @FXML private Tab project1Tab;
    @FXML private Tab project2Tab;
    @FXML private Tab project3Tab;

    @FXML private ContextMenu songsContextMenu;

    @FXML private Label statusLabel;

    File file;
    FileChooser fileChooser = new FileChooser();

    private final PseudoClass errorClass = PseudoClass.getPseudoClass("error");
    private ArrayList<Error> errors = new ArrayList<>();
    private ArrayList<TextField> textFields = new ArrayList<>();

    public void initialize()
    {
        xColumn.setCellValueFactory(
                new PropertyValueFactory<>("x"));

        yColumn.setCellValueFactory(
                new PropertyValueFactory<>("y"));

        dividedDifferenceTableView.setSelectionModel(null);


        // Right click context menu.
        dividedDifferenceTableView.addEventHandler(MouseEvent.MOUSE_CLICKED, t ->
        {
            if(t.getButton() == MouseButton.SECONDARY)
                songsContextMenu.show(dividedDifferenceTableView, t.getScreenX() , t.getScreenY());
        });

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text File", "*.txt")
        );
    }



    private void updateTable()
    {
        dividedDifferenceTableView.refresh();
    }

    /**
     * The event handler for the Logout button in the MainWindow scene.
     */
    public void onLogout()
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("Logout");
        alert.setContentText("Are you sure you want to logout?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK)
        {
            Main.stage.setResizable(false);
        }
    }

    /**NAVIGATION HANDLERS**/
    public void onGoToHome()
    {
        mainWindowTabPane.getSelectionModel().select(project1Tab);
    }

    public void onGoToStore()
    {
        mainWindowTabPane.getSelectionModel().select(project2Tab);
    }

    public void onGoToProjectProject3()
    {
        mainWindowTabPane.getSelectionModel().select(project3Tab);
    }

    public void onLocateFile()
    {
        fileChooser.setTitle("Locate File");
        file = fileChooser.showOpenDialog(Main.stage);

        if(file != null)
        {
            FileHandler.getInstance().readFile(file, tableData);
            dividedDifferenceTableView.setItems(tableData);

            int iterations = (tableData.size() / 2) - 1;

            for(int i = 0; i < iterations; i++)
            {
                String columnName = "f[";
                for(int j = 0; j < i + 1; j++)
                    columnName += ",";
                columnName += "]";



                TableColumn<InterpolationNode, Float> column = new TableColumn<>();
                column.setSortable(false);
                column.setText(columnName);
                column.setMinWidth(100);
                dividedDifferenceTableView.getColumns().add(column);
            }
        }
    }

    /**
     * Displays the status messages located in the status bar.
     * @param type - The type of the status message.
     * @param message - The message to display.
     */
    public void setStatus(String type, String message)
    {
        if(type.equals("ERROR"))
            statusLabel.setStyle("-fx-text-fill: red");
        else
            statusLabel.setStyle("-fx-text-fill: white");

        statusLabel.setText(type + ": " + message);
    }

    /**
     * Displays error messages in the form of alerts.
     * @param title - The title of the alert.
     * @param errorMessage - The error message.
     */
    private void alertUser(String title, String errorMessage, String type)
    {
        Alert alert = new Alert(Alert.AlertType.NONE);

        switch (type)
        {
            case "INFORMATION":
                alert = new Alert(Alert.AlertType.INFORMATION);
                break;
            case "ERROR":
                alert = new Alert(Alert.AlertType.ERROR);
                break;
            case "CONFIRMATION":
                alert = new Alert(Alert.AlertType.CONFIRMATION);
        }

        alert.setTitle(title);
        alert.setHeaderText(title);
        alert.setContentText(errorMessage);
        alert.showAndWait();
    }
}
