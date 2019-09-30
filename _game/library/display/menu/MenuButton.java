package library.display.menu;

import java.util.function.*;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;

import javafx.geometry.*;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

public class MenuButton {

  private Pane _pane = new Pane();
  private Rectangle _background = new Rectangle();
  private Text _text = new Text();

  private float _fontSize;
  private String _fontFamily = "Helvetica";

  private Consumer<String> _output;
  private String _outputText;

  // Assign necessary variables & append children
  public MenuButton(Consumer<String> output, Pane parent, String text, String outputText) {
    _output = output;
    _outputText = outputText;

    parent.getChildren().add(_pane);

    _pane.getChildren().add(_background);

    _text.setText(text);
    _text.setFill(Color.rgb(0, 0, 255));

    _pane.getChildren().add(_text);
  }

  // Send signal
  public void Activate() {
    try {
      _output.accept(_outputText);
    }
    catch(Exception e)  {
      System.out.println("MenuButton -  Activate: Could not send signal to consumer.");
    }
  }

  // Set general position
  public void SetPosition(float x, float y) {
    _pane.setTranslateX(x);
    _pane.setTranslateY(y);
  }

  // Set text position
  public void SetTextPosition(float x, float y) {
    _text.setTranslateX(x);
    _text.setTranslateY(y);
  }

  // Set font size
  public void SetFont(String font, float fontSize) {
    _fontFamily = font;
    _fontSize = fontSize;

    _text.setFont(Font.font(_fontFamily, _fontSize));
  }

  // Set background size
  public void SetSize(float width, float height) {
    _pane.setTranslateX(_pane.getTranslateX() - (width/2));
    _background.setWidth(width);
    _background.setHeight(height);
  }

  // Set background style
  public void SetBackgroundStyle(String style) {
    try {
      _pane.setStyle(style);
    }
    catch(Exception e)  {
      System.out.println("MenuButton - SetBackgroundStyle: could not set style correctly.");
    }
  }


}
