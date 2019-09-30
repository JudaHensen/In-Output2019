package library.display.animation;

import javafx.animation.*;
import java.util.function.*;

public class CustomAnimationTimer extends AnimationTimer
{
	private Consumer<Long> handleMethod = null;

	// Assign handleMethod
	public CustomAnimationTimer(Consumer<Long> methodReference)
	{
		handleMethod = methodReference;
	}

	// Execute handleMethod every frame
	public void handle(long currentNanoTime)
	{
		handleMethod.accept(currentNanoTime);
	}

	// Change handleMethod
	public void setHandle(Consumer<Long> methodReference)
	{
		handleMethod = null;
		handleMethod = methodReference;
	}

}
