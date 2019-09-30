package resources.menu.song.select;

import javafx.stage.*;

public class SongSelect extends library.display.menu.Menu {

  public SongSelect(Stage primaryStage, int width, int height) {
    // Call constructor from extended class
    super(primaryStage, width, height);

    _background.setStyle("-fx-background-color: black");

    _title = "Song selection";
  }

}
