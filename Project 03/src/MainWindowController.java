import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.css.PseudoClass;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.util.Callback;

import java.io.File;
import java.util.*;

public class MainWindowController
{
    // TableView
    @FXML private TableView<DataRow> dividedDifferenceTableView;
    @FXML private TableColumn<String, String> xColumn;
    @FXML private TableColumn<String, String> yColumn;
    private ObservableList<DataRow> tableData = FXCollections.observableArrayList();

    // Tabs
    @FXML private TabPane mainWindowTabPane;
    @FXML private Tab project1Tab;
    @FXML private Tab project2Tab;
    @FXML private Tab project3Tab;

    @FXML private ContextMenu songsContextMenu;

    @FXML private Label statusLabel;
    @FXML private Label polynomialLabel;
    @FXML private Label simplifiedPolynomial;
    @FXML private Label lagrangeFormLabel;

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
                new PropertyValueFactory<>("f0"));

        dividedDifferenceTableView.setSelectionModel(null);

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text File", "*.txt")
        );
    }



    private void updateTable()
    {
        dividedDifferenceTableView.refresh();
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
            //FileHandler.getInstance().readFile(file, tableData);
            //dividedDifferenceTableView.setItems(tableData);

            ArrayList<ArrayList<Float>> table = new ArrayList<>();
            FileHandler.getInstance().readFile(file, table);



            PolynomialInterpolation.getInstance().newton(table, 0);

            String x, f0, f1, f2, f3;


            int rows = table.get(0).size() * 2 - 1;

            for(int row = 0; row < rows; row++)
            {
                x = f0 = f1 = f2 = f3 = "";
                if(row == 0)
                {
                    x = String.valueOf(table.get(0).get(0));
                    f0 = String.valueOf(table.get(1).get(0));
                }
                else if(row == 1)
                {
                    f1 = String.valueOf(table.get(2).get(0));
                }
                else if(row == 2)
                {
                    x = String.valueOf(table.get(0).get(1));
                    f0 = String.valueOf(table.get(1).get(1));
                    f2 = String.valueOf(table.get(3).get(0));
                }
                else if(row == 3)
                {
                    f1 = String.valueOf(table.get(2).get(1));
                    f3 = String.valueOf(table.get(4).get(0));
                }
                else if(row == 4)
                {
                    x = String.valueOf(table.get(0).get(2));
                    f0 = String.valueOf(table.get(1).get(2));
                    f2 = String.valueOf(table.get(3).get(1));
                }
                else if(row == 5)
                {
                    f1 = String.valueOf(table.get(2).get(2));
                }
                else if(row == 6)
                {
                    x = String.valueOf(table.get(0).get(3));
                    f0 = String.valueOf(table.get(1).get(3));
                }

                tableData.add(new DataRow(x, f0, f1, f2, f3));
            }





            //PolynomialInterpolation.getInstance().printDividedDifferenceTable(table);



            int iterations = table.get(0).size() - 1;

            for(int i = 0; i < iterations; i++)
            {
                String columnName = "f[";
                for(int j = 0; j < i + 1; j++)
                    columnName += ",";
                columnName += "]";

                TableColumn<DataRow, String> column = new TableColumn<>();
                column.setSortable(false);
                column.setText(columnName);
                column.setMinWidth(100);
                column.setCellValueFactory(
                        new PropertyValueFactory<>("f" + (i + 1)));
                dividedDifferenceTableView.getColumns().add(column);
            }

            dividedDifferenceTableView.setItems(tableData);

            polynomialLabel.setText(PolynomialInterpolation.getInstance().printInterpolatingPolynomial(table));
            lagrangeFormLabel.setText(PolynomialInterpolation.getInstance().lagrange(table));
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
}
