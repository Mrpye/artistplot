 //<>//
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import processing.serial.*;

Serial port = null;

// select and modify the appropriate line for your operating system
// leave as null to use interactive port (press 'p' in the program)
//String portname = null;
//String portname = Serial.list()[0]; // Mac OS X
String portname = "/dev/ttyUSB0"; // Linux
//String portname = "COM6"; // Windows

boolean streaming = false;
float speed = 0.001;
String[] gcode;
int i = 0;

void openSerialPort()
{
  if (portname == null) return;
  if (port != null) port.stop();
  
  port = new Serial(this, portname, 57600);
  
  port.bufferUntil('\n');
}



void setup()
{
  size(500, 250);
  openSerialPort();
 

}

void draw()
{}

void keyPressed()
{

  
  if (key == 'x'){
    streaming = false;
    exit();
  }
}

void run(String selection) {
 
    println("User selected " + selection);
    gcode = loadStrings(selection);
    if (gcode == null) return;
    streaming = true;
    stream();
  
}


void stream()
{
  if (!streaming) return;
  
  while (true) {
    if (i == gcode.length) {
      streaming = false;
      exit();
      return;
    }
    
    if (gcode[i].trim().length() == 0) i++;
    else break;
  }
  
  println(gcode[i]);
  port.write(gcode[i] + '\n');
  i++;
}


void serialEvent(Serial p)
{
  String s = p.readStringUntil('\n');
  println(s.trim());
  
  if (s.trim().startsWith("ok")) stream();
  if (s.trim().startsWith("error")) stream(); // XXX: really?
  if (s.trim().startsWith("Y range is from")) run("/home/pi/projects/artistplot/photo.gcode"); // XXX: really?
   
}