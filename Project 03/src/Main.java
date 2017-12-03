import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.io.IOException;


public class Main extends Application
{
	public static MainWindowController mainWindowController;
	public static Scene mainWindow;
	public static Stage stage;

	@Override
	public void start(Stage primaryStage) throws IOException
	{
		// Loads the FXML for the MainWindow Scene and creates the Scene.
		FXMLLoader loader = new FXMLLoader(getClass().getResource("layouts/MainWindow.fxml"));
		mainWindow = new Scene(loader.load(), 1280, 720);
		mainWindowController = loader.getController();

		// Saves a reference of the Stage object so
		// the Controller class can access it.
		// It also sets the stage.
		stage = primaryStage;
		stage.getIcons().add(new Image(Main.class.getResourceAsStream("Drawable/Icon.png")));
		stage.setTitle("CS 301 Projects");
		stage.setResizable(false);
		stage.setScene(mainWindow);
		stage.show();
	}
	
	public static void main(String[] args)
	{
		launch(args);
	}
}
