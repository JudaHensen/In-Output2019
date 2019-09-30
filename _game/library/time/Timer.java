package library.time;

public class Timer extends Time {

  private Long _time;

  public Timer() {
    _time = 0L;
  }

  @Override
  public void Update() {
    _deltaTime = System.currentTimeMillis() - _previousTime;
    _time += _deltaTime;

    _previousTime = System.currentTimeMillis();
  }

  public void Reset() {
    _time = 0L;
  }

  public long GetDeltaTime() { return _deltaTime; }
  public long GetTime() { return _time; }

}
