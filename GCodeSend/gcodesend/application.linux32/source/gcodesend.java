import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.awt.event.KeyEvent; 
import javax.swing.JOptionPane; 
import processing.serial.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class gcodesend extends PApplet {

 //<>//




Serial port = null;

// select and modify the appropriate line for your operating system
// leave as null to use interactive port (press 'p' in the program)
//String portname = null;
//String portname = Serial.list()[0]; // Mac OS X
String portname = "/dev/ttyUSB0"; // Linux
//String portname = "COM6"; // Windows

boolean streaming = false;
float speed = 0.001f;
String[] gcode;
int i = 0;

public void openSerialPort()
{
  if (portname == null) return;
  if (port != null) port.stop();
  
  port = new Serial(this, portname, 57600);
  
  port.bufferUntil('\n');
}



public void setup()
{
  
  openSerialPort();
 

}

public void draw()
{}

public void keyPressed()
{

  
  if (key == 'x'){
    streaming = false;
    exit();
  }
}

public void run(String selection) {
 
    println("User selected " + selection);
    gcode = loadStrings(selection);
    if (gcode == null) return;
    streaming = true;
    stream();
  
}


public void stream()
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


public void serialEvent(Serial p)
{
  String s = p.readStringUntil('\n');
  println(s.trim());
  
  if (s.trim().startsWith("ok")) stream();
  if (s.trim().startsWith("error")) stream(); // XXX: really?
  if (s.trim().startsWith("Y range is from")) run("/home/pi/projects/artistplot/photo.gcode"); // XXX: really?
   
}
  public void settings() {  size(500, 250); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "gcodesend" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
