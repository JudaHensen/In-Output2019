package resources.gameplay.arrows;

import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.image.*;

import java.util.Random;


public class Arrow {


  public enum Direction {LEFT, RIGHT, UP, DOWN;

    public static Direction getRandomDirection() {
      Random random = new Random();
      return values()[random.nextInt(values().length)];
    }
  }

  private Pane _parent;
  private Direction _direction;
  private float _speed;

  private Image _img = new Image("images/Arrow.png");
  private ImageView _imgV = new ImageView(_img);


  public Arrow(Pane parent, Direction dir, float speed) { // <<<<<<< moet beter kunnen
    _parent = parent;
    _direction = dir;
    _speed = speed;

    _parent.getChildren().add(_imgV);

    float sectW = (1366 - 500) / 4;

    _imgV.setY(- (_img.getWidth()*1.3) );

    switch(_direction) {
      case LEFT:
        _imgV.setX(250 + (sectW * .5) - _img.getWidth()/2 );
        break;
      case UP:
        _imgV.setX(250 + (sectW * 1.5) - _img.getWidth()/2 );
        _imgV.setRotate(90);
        break;
      case DOWN:
        _imgV.setX(250 + (sectW * 2.5) - _img.getWidth()/2 );
        _imgV.setRotate(270);
        break;
      case RIGHT:
        _imgV.setX(250 + (sectW * 3.5) - _img.getWidth()/2 );
        _imgV.setRotate(180);
        break;
    }
  }


  // Update arrow y position
  public void Update(float deltaT) {
    _imgV.setY(_imgV.getY() + (_speed * deltaT) );
  }

  public float GetDistance(float yReference) {
    float distance = yReference - (float) (_imgV.getY() + _img.getWidth()/2);

    if(distance < 0)System.out.println("Distance: " + -distance);
    else System.out.println("Distance: " + distance);

    if(distance < 0) return -distance;
    return distance;
  }

  public void Remove() {
    _parent.getChildren().remove(_imgV);
  }

  public float GetY() {
    return (float) _imgV.getY();
  }

  public Direction GetDirection() {
    return _direction;
  }

}
