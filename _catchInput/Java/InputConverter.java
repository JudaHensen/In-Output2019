import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import java.util.Enumeration;

// simulate keys
import java.awt.Robot;
import java.awt.event.KeyEvent;

import java.util.HashMap;


public class InputConverter implements SerialPortEventListener {
	SerialPort serialPort;

	private static final String PORT_NAMES[] = {
			"COM10(Arduino/Genuino Uno)",
      "COM1",
      "COM2",
      "COM3",
      "COM4",
      "COM5",
      "COM8",
      "COM9",
      "COM10"
	};


	private BufferedReader input;

	private OutputStream output;

	private static final int TIME_OUT = 2000;

	private static final int DATA_RATE = 9600;

  private static HashMap<String, Integer> inputValues = new HashMap<String, Integer>();

  private static Robot keyboard;

	public void initialize() {
    try {
      keyboard = new Robot();
    }
    catch(Exception e) {
      System.err.println(e.toString());
    }

    inputValues.put("16", KeyEvent.VK_ENTER);
    inputValues.put("128", KeyEvent.VK_SPACE);
    inputValues.put("1", KeyEvent.VK_LEFT);
    inputValues.put("4", KeyEvent.VK_RIGHT);
    inputValues.put("8", KeyEvent.VK_UP);
    inputValues.put("2", KeyEvent.VK_DOWN);

		CommPortIdentifier portId = null;
		Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();


		while (portEnum.hasMoreElements()) {
			CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
			for (String portName : PORT_NAMES) {
				if (currPortId.getName().equals(portName)) {
					portId = currPortId;
					break;
				}
			}
		}
		if (portId == null) {
			System.out.println("Could not find COM port.");
			return;
		}

		try {
			serialPort = (SerialPort) portId.open(this.getClass().getName(),
					TIME_OUT);

			serialPort.setSerialPortParams(DATA_RATE,
					SerialPort.DATABITS_8,
					SerialPort.STOPBITS_1,
					SerialPort.PARITY_NONE);

			input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
			output = serialPort.getOutputStream();

			serialPort.addEventListener(this);
			serialPort.notifyOnDataAvailable(true);
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}


	public synchronized void close() {
		if (serialPort != null) {
			serialPort.removeEventListener();
			serialPort.close();
		}
	}

	public synchronized void serialEvent(SerialPortEvent oEvent) {
		if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
			try {
				String inputLine=input.readLine();
				//System.out.println(inputLine);

        // Check if input is found in inputValues
        if(inputValues.containsKey(inputLine)) {
          Integer val = inputValues.get(inputLine);
          keyboard.keyPress(val);
          keyboard.keyRelease(val);
        }


			} catch (Exception e) {
				//System.err.println(e.toString()); // << returns if input stream is empty
			}
		}
	}

	public static void main(String[] args) throws Exception {
		InputConverter main = new InputConverter();
		main.initialize();
		Thread t=new Thread() {
			public void run() {
				try {Thread.sleep(1000000);} catch (InterruptedException ie) {}
			}
		};
		t.start();
		System.out.println("Started");
	}
}
