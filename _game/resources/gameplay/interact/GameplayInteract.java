package resources.gameplay.interact;

import javafx.scene.layout.*;

import resources.gameplay.arrows.*;
import java.util.ArrayList;
import library.time.*;


public class GameplayInteract {


  private ArrowManager _arrowManager;
  private int _maximumInputs = 2;
  private Pane _background;

  private float _defaultDistance = 25;
  private float _distance;

  public GameplayInteract(Pane background, float beatsPerMinute) {
    _background = background;
    _distance = _defaultDistance * (beatsPerMinute / 60);

    System.out.println(_distance);
  }

  public void Load(float barTime) {
    _arrowManager = new ArrowManager(_background, barTime, _distance);
  }

  public void Update() {
    _arrowManager.Update();
  }

  public void Interact(int bar, int note) {

    Arrow.Direction dir1 = Arrow.Direction.getRandomDirection();

    _arrowManager.AddArrow( dir1 );

    if(Math.random() > .9) {
      Arrow.Direction dir2 = Arrow.Direction.getRandomDirection();
      while(dir1 == dir2) {
        dir2 = Arrow.Direction.getRandomDirection();
      }
      _arrowManager.AddArrow( dir2 );
    }

  }

  public void OnInput(String input) {
    int hitIndex;

    switch(input) {
      case "LEFT":
        hitIndex = _arrowManager.GetHit(Arrow.Direction.LEFT, 675);
        break;
      case "UP":
        hitIndex = _arrowManager.GetHit(Arrow.Direction.UP, 675);
        break;
      case "DOWN":
        hitIndex = _arrowManager.GetHit(Arrow.Direction.DOWN, 675);
        break;
      case "RIGHT":
        hitIndex = _arrowManager.GetHit(Arrow.Direction.RIGHT, 675);
        break;
      default: hitIndex = -1; break;
    }

    if(hitIndex >= 0) {
      System.out.println("hit?: true, " + hitIndex );
      _arrowManager.RemoveArrow(hitIndex);
    }
    else {
      System.out.println("hit?: false");
    }

  }


}
