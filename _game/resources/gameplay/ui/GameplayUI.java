package resources.gameplay.ui;

import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.image.*;
import java.util.ArrayList;

import resources.gameplay.arrows.*;

import library.time.*;
import resources.audio.*;


public class GameplayUI extends library.display.menu.Menu {


  private Image _lanes = new Image("images/lanes.png");
  private ImageView _lanesV = new ImageView(_lanes);

  private Image _selector = new Image("images/selector.png");
  private ImageView _selectorV = new ImageView(_selector);

  private ArrayList<Pane> _layers = new ArrayList<Pane>();

  public GameplayUI(Stage primaryStage, int width, int height) {
    // Call constructor from extended class
    super(primaryStage, width, height);

    _background.setStyle("-fx-background-color: black");

    _title = "Gameplay";
  }

  @Override
  public void Load() {
    super.Load();

    _background.getChildren().add(_lanesV);

    _selectorV.setY(_lanes.getHeight());
    _background.getChildren().add(_selectorV);
  }

  public Pane GetBackground() {
    return _background;
  }

  public int AddLayer() {
    Pane layer = new Pane();
    _background.getChildren().add(layer);
    _layers.add(layer);

    return _layers.size() - 1;
  }

  public Pane GetLayer(int index) {
    return _layers.get(index);
  }

}
