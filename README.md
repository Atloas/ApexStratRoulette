# ApexStratRoulette

A simple command line program for rolling strats in Apex Legends.

The program requires Java 8 to run.

To run it, navigate to the folder containing it and type:

    java StratRoulette options

where options is a space separated list of things the program is supposed to roll:

legends

weapons

strats

solo - will roll for just one player.


For example:

    java StratRoulette weapons strats

This will tell the entire team what weapons and strats strategies to use, but won't restrict your legend choice.

An empty options string will result in a full roll:

    java StratRoulette


The legend, weapon, and strats lists are easily expandable: you just need to add whatever you want to the text files, one item per line.