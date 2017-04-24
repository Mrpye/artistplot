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

public class gctrl extends PApplet {





Serial port = null;

// select and modify the appropriate line for your operating system
// leave as null to use interactive port (press 'p' in the program)
//String portname = "COM3";
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

public void selectSerialPort()
{
  String result = (String) JOptionPane.showInputDialog(frame,
    "Select the serial port that corresponds to your Arduino board.",
    "Select serial port",
    JOptionPane.QUESTION_MESSAGE,
    null,
    Serial.list(),
    0);
    
  if (result != null) {
    portname = result;
    openSerialPort();
  }
}

public void setup()
{
  
  openSerialPort();
  
}

public void draw()
{
  background(0);  
  fill(255);
  int font =24;
  textSize(font);
  int y = 24, dy = 26;
  text("INSTRUCTIONS", 12, y); y += dy;
  text("p: select serial port", 12, y); y += dy;
  text("1: set speed to 0.001 inches (1 mil) per jog", 12, y); y += dy;
  text("2: set speed to 0.010 inches (10 mil) per jog", 12, y); y += dy;
  text("3: set speed to 0.100 inches (100 mil) per jog", 12, y); y += dy;
  text("arrow keys: jog in x-y plane", 12, y); y += dy;
  text("page up & page down: jog in z axis", 12, y); y += dy;
  text("$: display grbl settings", 12, y); y+= dy;
  text("h: go home", 12, y); y += dy;
  text("0: zero machine (set home to the current location)", 12, y); y += dy;
  text("g: stream a g-code file", 12, y); y += dy;
  text("x: stop streaming g-code (this is NOT immediate)", 12, y); y += dy;
  y = height - dy;
  text("current jog speed: " + speed + " inches per step", 12, y); y -= dy;
  text("current serial port: " + portname, 12, y); y -= dy;

gcode = loadStrings("/home/andrew/projects/artistplot/photo.gcode");
  if (gcode == null) return;
  streaming = true;

  stream();
  


}
/*
void keyPressed()
{
  if (key == '1') speed = 0.001;
  if (key == '2') speed = 0.01;
  if (key == '3') speed = 0.1;
  
  if (!streaming) {
    if (keyCode == LEFT) port.write("G91\nG20\nG00 X-" + speed + " Y0.000 Z0.000\n");
    if (keyCode == RIGHT) port.write("G91\nG20\nG00 X" + speed + " Y0.000 Z0.000\n");
    if (keyCode == UP) port.write("G91\nG20\nG00 X0.000 Y" + speed + " Z0.000\n");
    if (keyCode == DOWN) port.write("G91\nG20\nG00 X0.000 Y-" + speed + " Z0.000\n");
    if (keyCode == KeyEvent.VK_PAGE_UP) port.write("G91\nG20\nG00 X0.000 Y0.000 Z" + speed + "\n");
    if (keyCode == KeyEvent.VK_PAGE_DOWN) port.write("G91\nG20\nG00 X0.000 Y0.000 Z-" + speed + "\n");
    if (key == 'h') port.write("G90\nG20\nG00 X0.000 Y0.000 Z0.000\n");
    if (key == 'v') port.write("$0=75\n$1=74\n$2=75\n");
    //if (key == 'v') port.write("$0=100\n$1=74\n$2=75\n");
    if (key == 's') port.write("$3=10\n");
    if (key == 'e') port.write("$16=1\n");
    if (key == 'd') port.write("$16=0\n");
    if (key == '0') openSerialPort();
    if (key == 'p') selectSerialPort();
    if (key == '$') port.write("$$\n");
  }
  
  if (!streaming && key == 'g') {
    gcode = null; i = 0;
    File file = null; 
    println("Loading file...");
    selectInput("Select a file to process:", "fileSelected", file);
  }
  
  if (key == 'x') streaming = false;
}
*/
public void fileSelected(File selection) {
  if (selection == null) {
    println("Window was closed or the user hit cancel.");
  } else {
    println("User selected " + selection.getAbsolutePath());
    gcode = loadStrings(selection.getAbsolutePath());
    if (gcode == null) return;
    streaming = true;
    stream();
  }
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
}
  public void settings() {  size(1000, 500); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--present", "--window-color=#666666", "--stop-color=#cccccc", "gctrl" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}