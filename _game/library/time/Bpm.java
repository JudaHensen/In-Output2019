package library.time;

import library.time.Timer;
import java.util.function.*;


public class Bpm {


  private float _bpm;
  private Timer _timer;
  // Current bar & note
  private int _bar, _note;
  private int _previousNote = 1;

  private float _songLength;

  // Time in ms
  private float _barTime, _noteTime;
  private int _quantize = 4;

  private BiConsumer<Integer, Integer> _output;

  public Bpm(float value, BiConsumer<Integer, Integer> output) {
    _bpm = value;
    _output = output;

    Calculate();
  }

  public void Start() {
    _timer = new Timer();
  }

  public void Reset() {
    _timer.Reset();
    Calculate();
    _bar = 1;
    _note = 1;
    _previousNote = 1;
  }

  private void Calculate() {
    _barTime = (60/ (_bpm / 4)) * 1000L;
    _noteTime = _barTime / _quantize;
  }


  public void Update() {
    _timer.Update();

    if(_timer.GetTime()/1000 < _songLength) {

      _bar = 1 + (int)Math.floor( _timer.GetTime() / _barTime );
      _note = 1 + (int)Math.floor((_timer.GetTime() % _barTime) / _noteTime);

      if(_note != _previousNote) {
        //Send signal
        _output.accept(_bar, _note);
        _previousNote = _note;
      }
    }
  }

  public void SetBpm(float value) {
    _bpm = value;
    Calculate();
  }

  public float GetBpm() { return _bpm; }

  public void SetQuantize(int value) {
    _quantize = value;
    Calculate();
  }

  public int GetQuantize() { return _quantize; }

  public void SetLength(float value) {
    _songLength = value;
  }

  public float GetBarTime() {
    return _barTime;
  }

  public float GetNoteTime() {
    return _noteTime;
  }

}
