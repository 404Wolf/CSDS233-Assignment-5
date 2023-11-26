# CSDS 233 Assignment 5
## Wolf Mermelstein

# Design Notes
* I changed `runSimulation` to be a static method that automatically instantiates the `CaseCashSystem`. This ensures that upon each simulation a fresh instance of `CaseCashSystem` is used for the simulation.
* I've decided to make `Student` a public subclass of `CaseCashSystem`, but with a private constructor. It is proper practice to not expose instances of private classes outside of those classes, so making the entire `Student` class private is unideal. By making its constructor private you must use the `CaseCashSystem` superclass to create instances of `Student`s.
* I created a separate `org.sort` package and tests where I implemented quicksort and mergesort. Both quicksort and mergesort implement a sort interface I made, which is a class with a single static public method that sorts `List`s in-place.