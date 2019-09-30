package resources.menu.main;

import javafx.stage.*;

public class MainMenu extends library.display.menu.Menu {

  public MainMenu(Stage primaryStage, int width, int height) {
    // Call constructor from extended class
    super(primaryStage, width, height);

    _background.setStyle("-fx-background-color: black");

    _title = "Main Menu";
  }

}
