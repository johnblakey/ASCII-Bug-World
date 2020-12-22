# ASCII-Bug-World
Terminal Application

## Predator - Prey Simulator
Turn based simulation with plants, ants, and spiders interacting on a grid displayed with ASCII art.

Plants are eaten by ants and spiders hunt the ants. Plants can grow to adjacent squares.

### Run in terminal
Recommend running in a terminal larger than the grid area calculated and displayed.

Application takes 5 integer arguments on the command line:
1. Turns: Total number of turns to be run
2. Grid Size: Length of one side of the square grid to be created
3. Ants: Total number of ants to be placed randomly
4. Spiders: Total number of spiders to be placed randomly
5. Plants: Total number of plants to be placed randomly

#### Linux Example Command To Start Simulation
> $ java -cp /home/path/to/github/project/ASCII-Bug-World/out/production/PredatorVsPrey/ com.github.johnblakey.bug.world.GameEngine 1000 20 8 5 40

This runs the simulation with 1000 turns on a grid of 20 by 20. Starting population is 20 ants, 8 spiders, and 40 plants.
