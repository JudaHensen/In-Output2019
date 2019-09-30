package resources.audio;

import java.util.function.*;

import javafx.util.Duration;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;


public class AudioTrack {
  

  private String _path;
  private MediaPlayer _mediaPlayer;
  private Consumer<Float> _output;


  public AudioTrack(String path, Consumer<Float> output) {
    _path = path;
    _output = output;
  }

  public void Play() {
    try{
      Media sound = new Media(new File(_path).toURI().toString());
      _mediaPlayer = new MediaPlayer(sound);
      _mediaPlayer.play();
    }
    catch (Exception e) {
      System.out.println("AudioTrack - Play: " + e.toString());
    }

    try {
      _mediaPlayer.setOnPlaying( () -> {
        _output.accept( (float) _mediaPlayer.getTotalDuration().toSeconds() );
      }
      );
    }
    catch (Exception e) {
      System.out.println("Please check if you have a playback device available!");
    }
  }

  public void Stop() {
    _mediaPlayer.stop();
  }

}
