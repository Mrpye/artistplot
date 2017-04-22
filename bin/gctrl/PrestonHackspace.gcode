(Scribbled version of C:\Users\Andrewp\AppData\Local\Temp\ink_ext_XXXXXX.svgUVI0RY @ 1000.00)
( unicorn.py --tab="plotter_setup" --pen-up-angle=50 --pen-down-angle=30 --start-delay=150 --stop-delay=150 --xy-feedrate=1000 --z-feedrate=150 --z-height=0 --finished-height=0 --register-pen=true --x-home=0 --y-home=0 --num-copies=1 --continuous=false --pause-on-layer-change=false C:\Users\Andrewp\AppData\Local\Temp\ink_ext_XXXXXX.svgUVI0RY )
G21 (metric ftw)
G90 (absolute mode)
G92 X0.00 Y0.00 Z0.00 (you are here)

M300 S30 (pen down)
G4 P150 (wait 150ms)
M300 S50 (pen up)
G4 P150 (wait 150ms)
M18 (disengage drives)
M01 (Was registration test successful?)
M17 (engage drives if YES, and continue)

(Polyline consisting of 2 segments.)
G1 X29.31 Y6.81 F1000.00
M300 S30.00 (pen down)
G4 P150 (wait 150ms)
G1 X29.34 Y18.67 F1000.00
G1 X29.34 Y30.51 F1000.00
G1 X26.34 Y30.51 F1000.00
G1 X17.53 Y30.49 F1000.00
G1 X14.39 Y29.70 F1000.00
G1 X11.73 Y27.97 F1000.00
G1 X9.75 Y25.49 F1000.00
G1 X8.68 Y22.45 F1000.00
G1 X8.56 Y20.13 F1000.00
G1 X8.98 Y17.90 F1000.00
G1 X9.91 Y15.84 F1000.00
G1 X11.33 Y14.02 F1000.00
G1 X12.68 Y12.87 F1000.00
G1 X14.18 Y12.00 F1000.00
G1 X15.82 Y11.42 F1000.00
G1 X17.58 Y11.12 F1000.00
G1 X19.67 Y11.08 F1000.00
G1 X21.32 Y11.08 F1000.00
G1 X21.32 Y8.93 F1000.00
G1 X21.32 Y6.79 F1000.00
G1 X25.30 Y6.79 F1000.00
G1 X29.31 Y6.81 F1000.00
G1 X29.31 Y6.81 F1000.00
M300 S50.00 (pen up)
G4 P150 (wait 150ms)

(Polyline consisting of 2 segments.)
G1 X17.62 Y16.16 F1000.00
M300 S30.00 (pen down)
G4 P150 (wait 150ms)
G1 X16.20 Y17.56 F1000.00
G1 X17.14 Y18.48 F1000.00
G1 X18.09 Y19.41 F1000.00
G1 X18.39 Y19.11 F1000.00
G1 X18.73 Y18.82 F1000.00
G1 X19.10 Y19.19 F1000.00
G1 X17.38 Y20.86 F1000.00
G1 X17.01 Y20.52 F1000.00
G1 X17.30 Y20.19 F1000.00
G1 X17.58 Y19.91 F1000.00
G1 X16.64 Y18.98 F1000.00
G1 X15.69 Y18.05 F1000.00
G1 X14.27 Y19.45 F1000.00
G1 X12.85 Y20.84 F1000.00
G1 X13.79 Y21.77 F1000.00
G1 X14.74 Y22.70 F1000.00
G1 X14.88 Y22.57 F1000.00
G1 X15.07 Y22.43 F1000.00
G1 X17.46 Y24.78 F1000.00
G1 X17.32 Y24.97 F1000.00
G1 X17.18 Y25.10 F1000.00
G1 X18.11 Y26.02 F1000.00
G1 X19.06 Y26.93 F1000.00
G1 X20.50 Y25.55 F1000.00
G1 X21.92 Y24.17 F1000.00
G1 X20.98 Y23.24 F1000.00
G1 X20.03 Y22.31 F1000.00
G1 X19.75 Y22.59 F1000.00
G1 X19.41 Y22.87 F1000.00
G1 X18.96 Y22.43 F1000.00
G1 X20.67 Y20.76 F1000.00
G1 X21.11 Y21.18 F1000.00
G1 X20.82 Y21.53 F1000.00
G1 X20.54 Y21.82 F1000.00
G1 X21.48 Y22.74 F1000.00
G1 X22.43 Y23.67 F1000.00
G1 X23.86 Y22.26 F1000.00
G1 X25.28 Y20.85 F1000.00
G1 X24.34 Y19.93 F1000.00
G1 X23.40 Y19.01 F1000.00
G1 X23.25 Y19.15 F1000.00
G1 X23.07 Y19.29 F1000.00
G1 X20.66 Y16.93 F1000.00
G1 X20.80 Y16.75 F1000.00
G1 X20.95 Y16.60 F1000.00
G1 X20.01 Y15.68 F1000.00
G1 X19.06 Y14.76 F1000.00
G1 X17.62 Y16.16 F1000.00
G1 X17.62 Y16.16 F1000.00
M300 S50.00 (pen up)
G4 P150 (wait 150ms)


(end of print job)
M300 S50.00 (pen up)
G4 P150 (wait 150ms)
M300 S255 (turn off servo)
G1 X0 Y0 F1000.00
G1 Z0.00 F150.00 (go up to finished level)
G1 X0.00 Y0.00 F1000.00 (go home)
M18 (drives off)