# AI-Methods-Project
3 Jug Problem

Run Programme:

1)Download the JugProblemSource.class file. Through the command line
of a your system, go to the directory where the JugProblemSource.class
file is located.

2)Type in the command java JugProblemSource into the command line,
and press enter.

3)You will now be prompted to enter the capacity for jugA.
Please enter a positive integer. Entering anything other than this will
return a message indicating that the input is incorrect and you will be
prompted again until a valid input is entered.
You will then be prompted to enter the capacity of jugB and then
JugC.

4)After valid input is received (positive integers) the programme will run
and display how many distinct states can be obtained from the three
input jug capacities, as well as the run time.

I implemented a high-level depth first search algorithm of the following 3 jug Problem:

Consider the following water-jug problem. There are 3 jugs that have capacities of A
gallons, B gallons, and C gallons. A pump with an unlimited supply of water is available which
can be used to fill the jugs. Water may be poured from one jug to another or to the ground.
None of the jugs has any measuring marks on it. In the start state, all the three jugs are empty.
This is denoted (0, 0, 0); the first number is the amount of water in the A-gallon jug, the middle
number is the amount of water in the B-gallon jug, and the last number is the amount of water
in the C-gallon jug. For example, if A=8, B=5, and C=3, a possible next state is (8, 0, 0) which
is reached by pouring water into the 8-gallon jug. From the state (8, 0, 0) a possible next state
is (5, 0, 3) which is reached by pouring water from the 8-gallon jug to the 3-gallon jug. From
the state (5, 0, 3) a possible next state is (0, 0, 3) which is reached by pouring water from the
8-gallon jug to the ground. 
