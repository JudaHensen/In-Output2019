package resources.gameplay.arrows;

import javafx.scene.layout.*;

import resources.gameplay.arrows.Arrow;
import java.util.ArrayList;
import library.time.*;


public class ArrowManager {


  private Time _time = new Time();
  private ArrayList<Arrow> _arrows = new ArrayList<Arrow>();

  private Pane _background;
  private float _barTime;

  private float _hitDistance;

  public ArrowManager(Pane background, float barTime, float hitDistance) {
    _background = background;
    _barTime = barTime;
    _hitDistance = hitDistance;
  }

  public void Update() {
    _time.Update();

    // update arrow position
    for(int i = 0; i < _arrows.size(); i++) {
      _arrows.get(i).Update( (float) _time.GetDeltaTime() );
    }
    // remove if out of boundarie
    for(int i = _arrows.size()-1; i > -1; i--) {
      if(_arrows.get(i).GetY() > 1000) {
        _arrows.get(i).Remove();
        _arrows.remove(i);
      }
    }
  }

  public void AddArrow(Arrow.Direction direction) {

        float speed = 768 / ( _barTime );
        Arrow arr = new Arrow(_background, direction, speed);

        _arrows.add(arr);
  }

  public void RemoveArrow(int index) {
    try {
      _arrows.get(index).Remove();
      _arrows.remove(index);
    }
    catch(Exception e) {
      System.out.println("Out of boundaries? index: " + index);
    }
  }

  public int GetHit(Arrow.Direction direction, float yReference) {
    int hit = -1;

    for(int i = 0; i < _arrows.size(); i++) {
      if(_arrows.get(i).GetDirection() ==  direction &&
        _arrows.get(i).GetDistance(yReference) <= _hitDistance) {
        hit = i;
        break;
      }
    }
    return hit;
  }




}
