## About
This repo is a basis of a guest lecture held at NTNU Trondheim in the course TDT4165 Programming Languages.

The goal is to show of as much as possible of Scala's base features in 2 hours and give motivations for why thinking of types and domains is useful and important.

## Scala
- ~20 years old
- Attempts to fuse object oriented and functional programing
- Runs on JVM
- Has pioneered many of the features we see spreading the last years.
- Version 3 released "recently". Embraces braceless syntax and is compatible with 2.

## Scala at FINN.no
Most of the teams using it is embracing the more functional style.
Some things we really like:
- Errors at compile time instead of at run time
- Incredibly powerful type system
- Concise and regular language structure
- Very nice library support for doing Functional Programming
- Leverage existing tools from JVM eco system
- Ability to drop down to procedural when desired

## Mini-FINN
A barebone classified ads service using the console

#### Setup for Intellij
- Install the scala plugin (Preferences -> Plugins -> Search 'scala' -> Restart Intellij)
- Open project in Intellij
- Choose project JDK 1.8 (only tested on java8), leave rest as defaults

#### Setup for VsCode (and others)
- Install the plugin "Metals"

#### Running
- Press the play button in Main.scala

### Prelude 
*Read*: Syntax

### Task 0: Introduction
- We have a small service for inputting and outputting Ads.
- It uses the console to select mode (input, output, exit).
- We "mock" a database using a mutable Map

### Task 1: ADT
*Read*: ADTExample

*Problem*: 
- The modus variable in AdServer is implemented as a string and such has an infinite domain
- The id of ads is easily mistaken for other ids
- Our ads are just strings, make them have both a price and some text.

*Task:* 
- Replace Modus with an Sum Type with a limited domain.
- Introduce a opaque type for AdId
- Introduce a Product Type for Ad

### Task 2: Modeling
*Read*: ModelingExample

*Problem*: The database accepts strings and not product types

*Task:* Implement an ADT for two kinds of Ad and parse them from string

- CarAd, with fields regNr (registration number) and price
- JobAd, with field company

Make sure we can output Ads to the console in the same format as it is input

### Task 3: Option
*Read*: OptionExample

*Problem*: UnknownAdType and Unknown mode are easily mistaken for good results
 
*Task:* Get rid of Unknown types. Also upgrade the database to be able to signal missing element.

### Task 4: Either / disjuctions
*Read*: EitherExample

*Problem*: The user gets no feedback on what went wrong if we could not handle the input

*Task:* Switch previous tasks use of Options for unknowns in favor of error-signaling types

### Task 5: Recursion
*Read*: RecursionExample

*Problem*: The app uses a while loop mutating a var. Eg it has state.

*Task:* Remove state in AdServer by making the run method tail-recursive

### Task 6: IO
*Read*: IOExample

*Problem*: There are untracked side effects in accessing console and database

*Task:* Wrap up console and database effects in IO and compose program as IO. 

### Bonus 7: State
*Read*: RefExample

*Problem*: The database is concrete for each server. We want to be able to safely use it concurrently with N servers/users.

*Task:* Wrap up the mutability in Database and pass a database instance into server on startup.