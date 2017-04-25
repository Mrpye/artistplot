import pygame.camera
import pygame.image
from subprocess import call
import unicornlib
import fileinput
#import serial
#import threading

print "taking picture"

pygame.camera.init()
cam = pygame.camera.Camera(pygame.camera.list_cameras()[0])
cam.start()

img = cam.get_image()
pygame.image.save(img, "photo.bmp")
pygame.camera.quit()

print "Converting "
call(["mkbitmap", "photo.bmp"])

print "convert to svg "
call(["potrace","--svg","-P 40mmx40mm","-W 40mm","-H40mm","--tight","photo.pbm"])

#code to remove the pt
print "fix pt "
f =fileinput.FileInput("photo.svg", inplace=True)
for line in f:
	#print(line.replace('pt"', '"'))
	if line.startswith("<g transform="):
		print('<g transform="translate(50.000000,63.385773) scale(0.008858,-0.011925)"')
	else:    
		print(line.replace('pt"', '"'))
	
f.close()

print "build gcode"
e = unicornlib.MyEffect()
e.affect(['--tab="plotter_setup"', '--pen-up-angle=50', '--pen-down-angle=30', '--start-delay=150', '--stop-delay=150', '--xy-feedrate=3500', '--z-feedrate=150', '--z-height=0', '--finished-height=0', '--register-pen=true', '--x-home=0', '--y-home=0', '--num-copies=1', '--continuous=false', '--pause-on-layer-change=false', 'photo.svg'])

file = open("photo.gcode","w") 
for c in e.context.codes:
	file.write(c + '\n') 

 
file.close() 

print "plot "
#sudo chmod +x gctrl
call(["/home/andrew/projects/artistplot/application.linux64/gcodesend"])




