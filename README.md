# OUTDATED

# ApexStratRoulette

### Description

A simple command line program for rolling strats in Apex Legends.

The program requires Java 8 to run.

### Usage

To run it, navigate to the folder containing it and type:

`java StratRoulette`

This will roll the Legends, weapons, and a strat for a team of 3. This default behaviour can be modified by addding a list of space separated options to the end of the command. Those options are:

- `nolegends`

- `noweapons`

- `nostrat`

- `solo`

- `duo`

- `quad`

- `ammo` - Will roll ammo types in stead of specific weapons.

- `types` - Will roll weapon types in stead of specific weapons.

For example:

`java StratRoulette nostrat ammo`

This will tell a team of 3 which Legends to use, and what ammo types should their guns be, but won't roll a strat.

The pools of items the program pulls from can be easily expanded by modifying corresponding text files.

**Make sure to only use this program if the entire team aggrees upon it, do not do it with randoms.**
