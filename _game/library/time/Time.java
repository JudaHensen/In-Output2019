package library.time;

import java.lang.*;

public class Time {

  protected Long _startTime, _previousTime, _deltaTime;

  public Time() {
    _startTime = System.currentTimeMillis();
    _previousTime = System.currentTimeMillis();
  }

  public void Update() {
    // Calculate deltaTime
    _deltaTime = System.currentTimeMillis() - _previousTime;
    // Update previousTime
    _previousTime = System.currentTimeMillis();
  }

  public Long GetDeltaTime() {
    return _deltaTime;
  }

}
