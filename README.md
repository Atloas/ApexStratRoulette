# ApexStratRoulette

A simple command line program for rolling strats in Apex Legends.

The program requires Java 11 to run (though I'll probably go to an earlier version at some point).

To run it, navigate to the folder containing it and type:

    java StratRoulette options

where options is a space separated list of things the program is supposed to roll:

legends

weapons

extra - extra strategies, like hunting packages.

team - will roll for all 3 people.


For example:

    java StratRoulette team weapons extra

This will tell the entire team what weapons and extra strategies to use, but won't restrict your legend choice.

An empty options string will result in a full roll:

    java StratRoulette