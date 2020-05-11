package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.*;

public class Main extends Application {
	private ArrayList al;
	private BinarySearchTree bst;
	private LinkedList ll;
	private GUI control;
	public Main() {
		al = new ArrayList();
		bst = new BinarySearchTree();
		ll = new LinkedList();
		control = new GUI(al,bst,ll);
	}
	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GUI.fxml"));
		fxmlLoader.setController(control);
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Algorithms Race");
		primaryStage.setResizable(false);
		primaryStage.show();
	}
}
