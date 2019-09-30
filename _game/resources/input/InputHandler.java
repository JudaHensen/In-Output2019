package resources.input;

import javafx.stage.*;
import javafx.scene.input.*;
import javafx.event.EventHandler;
import java.util.function.*;


public class InputHandler {
  

  private Consumer<String> _output;
  private EventHandler<KeyEvent> ReceiveInput = this::ReceiveInput;

  public InputHandler(Stage stage, Consumer<String> output) {
    stage.addEventHandler(KeyEvent.KEY_PRESSED, ReceiveInput);
    _output = output;
  }

  public void ReceiveInput(KeyEvent e) {
    _output.accept( e.getCode().toString());
  }

}
