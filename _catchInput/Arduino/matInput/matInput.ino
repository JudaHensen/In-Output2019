#include <Psx.h>               
#include <math.h>               
                                                         
//#define Cross 512
//#define Circle 1024
//#define Triangle 2048
//#define Square 256
//#define L1 8192
//#define L2 32768
//#define L3 4096
//#define L4 20480

// Assign pins to wires
// Red wire = 3.3v, Black wire = ground
#define dataPin 8 // Brown wire
#define cmndPin 9 // Orange wire
#define attPin 10 // Yellow wire
#define clockPin 11 // Blue wire

#define MaximumInputs 2
#define Start 16
#define Select 128
#define Left 1
#define Right 4
#define Up 8
#define Down 2

int values[] = {Select, Start, Up, Right, Down, Left};
int size = 6;

int previousKeys[MaximumInputs] {0, 0};
int currentKeys[MaximumInputs] {0, 0};

Psx Psx;                                                  

int data = 0;                                   

void setup()
{
  Psx.setupPins(dataPin, cmndPin, attPin, clockPin, 10);  
                                                                                                                                                      
  Serial.begin(9600);
}

void loop()
{
  data = Psx.read();                                                                                                                         
  switch(data) {
    case Start:
      currentKeys[0] = Start;
      if(currentKeys[0] != previousKeys[0]) sendKey(Start);
      break;
    case Select:
      currentKeys[0] = Select;
      if(currentKeys[0] != previousKeys[0]) sendKey(Select);
      break;
    case Left:
      currentKeys[0] = Left;
      if(currentKeys[0] != previousKeys[0]) sendKey(Left);
      break;
    case Right:
      currentKeys[0] = Right;
      if(currentKeys[0] != previousKeys[0]) sendKey(Right);
      break;
    case Up:
      currentKeys[0] = Up;
      if(currentKeys[0] != previousKeys[0]) sendKey(Up);
      break;
    case Down:
      currentKeys[0] = Down;
      if(currentKeys[0] != previousKeys[0]) sendKey(Down);
      break;
    default:
      checkMultipleInputs();
      break; 
  } 

  checkKeyReleases();
  // Update previous keys
  previousKeys[0] = currentKeys[0];
  previousKeys[1] = currentKeys[1];
  
  delay(38);
}

void checkMultipleInputs() {
  // Check if input is less or equal to the highest 2 values.
  if(data <= values[0] + values[1]) {
    // Scroll through values
    for(int i = 0; i < size; i++) {
      // Check if value has a "rest" value.
      if(data > values[i]) {
        
        currentKeys[0] = values[i];
        if(currentKeys[0] != previousKeys[0]) sendKey(values[i]);
        // Check if second key is can be found.
         int temp = data % values[i];
         
        for(int c = i; c < size; c++) {
          if(temp == values[c]) { 
            
            currentKeys[1] = values[c];
            if(currentKeys[1] != previousKeys[1]) sendKey(values[c]);
            break;
          }
          else currentKeys[1] = 0;
        }
        break;
      }
      else currentKeys[0] = 0;
    }    
  } 
  else {
    currentKeys[0] = 0;
    currentKeys[1] = 0;
  } 
}


void checkKeyReleases() {
  for(int i = 0; i < MaximumInputs; i++) {
    if(currentKeys[i] != previousKeys[i]) releaseKey(previousKeys[i]);
  }
}

void sendKey( int val) {
 Serial.println(val); // << will catch via java
}






//
