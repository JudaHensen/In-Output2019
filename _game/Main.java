// Application
import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

// Custom classes
import library.display.*;
import library.display.animation.*;
import library.time.*;

// import resources.menu.main.*;
import resources.gameplay.Gameplay;


public class Main extends Application {


  private int _appWidth = 1366,
              _appHeight = 768;

  private Pane _mainPane = new Pane();
  private Scene _mainScene = new Scene(_mainPane, _appWidth, _appHeight);

  // private MainMenu _mainMenu;
  private CustomAnimationTimer _runTime = new CustomAnimationTimer(this::Update);
  private Gameplay _gameplay;

  public static void main(String[] args)
  {
    launch(args);
  }

  public void start(Stage primaryStage) {
    // _mainMenu = new MainMenu(primaryStage, _appWidth, _appHeight);
    // _mainMenu.Load();
    primaryStage.setResizable(false);

    _gameplay = new Gameplay(primaryStage, _appWidth, _appHeight, 145f, "audio/test.mp3");

    _runTime.start();
  }

  public void Update(long nanoTime) {
    _gameplay.Update();
  }

}
