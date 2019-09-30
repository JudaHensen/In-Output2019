package library.display.menu;

// Scene & Layout
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;

// Utility
import java.util.function.*;
import java.util.ArrayList;

// Cursom classes
import library.display.menu.MenuButton;


public class Menu {


  protected Stage _primaryStage;
  protected Pane _background = new Pane();
  protected Scene _scene;
  // Stage sizes
  protected int _width, _height;
  // Application Title
  protected String _title;

  protected ArrayList<MenuButton> _buttons = new ArrayList<MenuButton>();

  // Setup necessary variables
  public Menu(Stage primaryStage, int width, int height) {
    _primaryStage = primaryStage;
    _width = width;
    _height = height;

    _scene = new Scene(_background, _width, _height);
  }

  public void Load() {
    try {
      _primaryStage.setScene(_scene);
      _primaryStage.setTitle(_title);
      _primaryStage.show();
    }
    catch(Exception e) {
      System.out.println("Menu - Load: Something is wrong here, make sure you declare all needed variables");
    }
  }

  public void Update() {

  }

  public void AddButton(Consumer<String> output, Pane parent, String text, String outputText) {
    try {
      MenuButton button = new MenuButton(output, parent, text, outputText);
      _buttons.add(button);
    }
    catch(Exception e) {
      System.out.println("Menu - AddButton: Could not add button.");
    }
  }

  public void RemoveButton(int index) {
    try {
      _buttons.remove(index);
    }
    catch(Exception e) {
      System.out.println("Menu - RemoveButton: Could not remove button, does this index exist? \nIndex: " + index);
    }
  }

  public void ActivateButton(int index) {
    try {
      _buttons.get(index).Activate();
    }
    catch(Exception e) {
      System.out.println("Menu - ActivateButton: Could not activate button, does this index exist? \nIndex: " + index);
    }
  }


}
