package library.display;

import java.util.function.*;
// Custom classes
import library.time.Timer;

public class FrameRate {

  private float _limit;
  private Timer _timer;


  private Consumer<String> _output;

  public FrameRate(float limit, Consumer<String> output) {
    _limit = limit;
    _timer = new Timer();

    _output = output;
  }

  public void Update() {
    _timer.Update();

    if( _timer.GetTime() >= (1/_limit)*1000 ) {
      _timer.Reset();

      // Activate function
      _output.accept("idk");
    }
  }

  public void SetLimit(float limit) { _limit = limit; }

  public float GetLimit() { return _limit; }

}
