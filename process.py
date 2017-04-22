import pygame.camera
import pygame.image
from subprocess import call
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
call(["sudo", "potrace","--svg","photo.pbm"])
