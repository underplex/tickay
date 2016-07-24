## Synopsis

The Tickay library provides classes and interfaces for simulating the board game Ticket to Ride, both the original base version (called here "America") and the Europe version. (No claim of copyright is made on those games or any other Days of Wonder product and this code was not developed in consulation with them.)

Classes implementing either AmericaStrategy or EuroStrategy represent a player using a strategy in the game. Using classes Specification<AmericaStrategy> or Specification<EuroStrategy>, you can specify how many games the simulator should run and what strategies to use in the game. For each run of the simulation, a SimulationLog object is produced that can be output to an XML file.

In sum, strategies can be simulated and then evaluated by parsing and analyzing the XML data produced by the games played.

## API Reference

Reference docs live in the docs folder.

## Tests

As of January 2016, the com.underplex.tickay.test package includes a test suite that uses JUnit to run several tests
of the game engine. 

## License

GPL 2.0
