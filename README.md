# ApexStratRoulette

## Description

A simple Java application made for rolling random strats for Apex Legends, which you can use when you feel bored or want to try something new. By "strat roulette" I mean a randomized selection of characters, weapons, and a strategy for your squad to follow. Of course, interpreting and following the rolled strat is entirely up to you. I've included my personal interpretation of the default strats below.

It requires a properly set up Java 8. It might work on other Java versions, but I didn't test.

### Please, do not use a random strat when playing with random teammates!

## Usage

To launch the program you can use the start.bat file, simply create a shortcut to it and use that. Do not move the file from it's spot. The file executes a `java StratRoulette` command. This will open the program window.

On the left side of the window you can choose what things should the program roll, then clicking the `Roll!` button will randomize and display the strat on the right.

## Expanding the pools

The program pulls it's data from a set of text files in it's resources folder. Each file contains a simple list of items for the program to choose from when rolling for that particular thing, separated into lines. Those lists can be easily modified to include your own strats, remove guns you really don't like, and so on. Simply add your own items, one per line, or remove existing ones. The program ignores empty lines.

## Default strats

Below is my personal interpretation of the default strats provided with the program:

* Explosives - You have to pick up every single grenade you find, this includes dropping other items to make inventory space. This mean you'll be forced to use grenades liberally or risk having your entire inventory filled.
* Supressive fire - Don't spare the triggers, favour automatic weapons if possible. This one is quite loose.
* Package hunting - You have to go for every Care Package you can.
* Ship hunting - You have to go for every Supply Ship.
* Finishers - You can only execute downed enemies by performing a finisher.
* Aggression - Play aggressively.
* Switch on empty - Whenever your magazine runs dry, you have to switch to your other weapon.

__Created by Atloas, TheRealAtloas on Origin.__
