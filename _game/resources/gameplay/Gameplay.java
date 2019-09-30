package resources.gameplay;

import javafx.stage.*;

import library.time.*;
import resources.gameplay.interact.GameplayInteract;
import resources.gameplay.ui.GameplayUI;
import resources.audio.AudioTrack;
import resources.input.InputHandler;


public class Gameplay {


  private Stage _primaryStage;
  private int _width, _height;

  private Bpm _bpm;

  private AudioTrack _audioTrack;
  private GameplayInteract _interact;
  private GameplayUI _ui;
  private InputHandler _input;

  private boolean _update = false;


  public Gameplay(Stage primaryStage, int width, int height, float beatsPerMinute, String track) {
    _primaryStage = primaryStage;
    _width = width;
    _height = height;



    _ui = new GameplayUI(_primaryStage, _width, _height);
    _ui.Load();

    _interact = new GameplayInteract(_ui.GetBackground(), beatsPerMinute);

    _input = new InputHandler(_primaryStage, _interact::OnInput);
    _bpm = new Bpm(beatsPerMinute, _interact::Interact);

    _audioTrack = new AudioTrack(track, this::Activate);
    _audioTrack.Play();
  }

  // Triggers activation
  public void Activate(float length) {
    _update = true;
    _bpm.SetLength(length);
    _bpm.SetQuantize(2);
    _bpm.Start();
    _interact.Load(_bpm.GetBarTime());
  }

  // Only update when activated
  public void Update() {
    if(_update) {
      _bpm.Update();
      _interact.Update();
    }
  }

}
