import java.util.*;

public class JugProblemSource{
    /*
     * stack -> This data structure is an ArrayList which stores arrays of type integer. This ArrayList stores all the states yet to be visted and expanded by using 
     Depth First Search. This data structure is used in the same way a stack data structure is used by adding states to the one end of the ArrayList,
     as well as removing elements from the same end of the ArrayList, imitating a stack.
     
     * states_visited -> This data structure is an ArrayList which stores arrays of type integer. This ArrayList stores all the states visited throuhg
     the depth first search traversal of states. Once a state is removed from the top of the stack data structure it is added to this data structure.
     
     * root -> This array of type integer stores the starting state (0,0,0).

     * capacities -> This array which stores elements of type integer stores the provided 3 capacities of jug A,B and C. 
     This array is passed into the getNeighbours method where it is used to calculate the states when filling jugs, and pouring jugs into
     other jugs, where the capicity of a given jug is used for a calculation.

     * state -> This array which stores elements of type integer is used to create a new copy of an array of a state in memory location when 
     determing neighboruing states of a given node. This is so that a single state represented as an array of integers can
     be manipulated in diffferent ways without changing the orignial state(represented as an array).
    */
    public static void main(String[] args){
        int capacityA = 0;
        int capacityB = 0;
        int capacityC = 0;
        Boolean x = true;

        do{
            Scanner myscanner = new Scanner(System.in);
            try{
                System.out.println("Enter a positive integer for the capacity for jug A:");
                capacityA = myscanner.nextInt();//grab integer input from terminal
                x = false;
            }
            catch(Exception e){
                System.out.println("Sorry, please enter a positive integer");
            }
        }while(x == true);//continue asking for input from user until valid input provided
        x = true;

        do{
            Scanner myscanner = new Scanner(System.in);
            try{
                System.out.println("Enter a positive integer for the capacity for jug B:");
                capacityB= myscanner.nextInt();
                x = false;
            }
            catch(Exception e){
                System.out.println("Sorry, please enter a positive integer");
            }
        }while(x == true);
        x = true;

        do{
            Scanner myscanner = new Scanner(System.in);
            try{
                System.out.println("Enter a positive integer for the capacity for jug C:");
                capacityC = myscanner.nextInt();
                x = false;
            }
            catch(Exception e){
                System.out.println("Sorry, please enter a positive integer");
            }
        }while(x == true);

        long startTime = System.currentTimeMillis();
        DepthFirstSearch(capacityA, capacityB, capacityC);//run method
        long endTime = System.currentTimeMillis();
        System.out.println("The time taken to run the programme in milli seconds is:" + (endTime-startTime));
    }

    //method to check if a state exists in an arrayList of states
    public static boolean contains(ArrayList<int[]> list, int[] state){
        for(int[] visited_state : list){
            if(Arrays.equals(visited_state,state)){
                return true;
            }
        }
        return false;
    }

    public static void DepthFirstSearch(int A, int B, int C){
        ArrayList<int[]> stack = new ArrayList<int[]>();
        ArrayList<int[]> states_visited = new ArrayList<int[]>();
        int[] root = {0,0,0};
        int[] capacities = {A,B,C};
        stack.add(root);//start depth first search by adding the starting state to stack
        while(stack.size() != 0 ){
            //pop state from top of stack
            int[] node = stack.get(stack.size()-1);
            stack.remove(stack.size()-1);
            if(!(contains(states_visited, node))){//only add unique states to states_visited ArrayList
                states_visited.add(node);
            }
            ArrayList<int[]> neighbours = getNeighbours(node, capacities);
            if(neighbours.size()>0){
                for(int i = 0;i<neighbours.size();i++){
                    //add neighbours to top of the stack if it has not already been a state visited
                    if(!(contains(states_visited, neighbours.get(i)))){
                        stack.add(neighbours.get(i));
                    }
                }
            }
        }
        System.out.println("The number of possible states is: " + states_visited.size());
    }

    //method used to fill a certain jug to its capacity
    public static int[] FillJug(int[] node, int jug, int capacity){ 
        //create new array in memeory location and copy elements over from input (to edit input array without changing it)
        int[] state = new int[3];
        state[0] = node[0];
        state[1] = node[1];
        state[2] = node[2];
        state[jug] = capacity;
        return state;
    }

    //method used to empty a certain jug
    public static int[] EmptyJug(int[] node, int jug){
        int[] state = new int[3];
        state[0] = node[0];
        state[1] = node[1];
        state[2] = node[2];
        state[jug] = 0;
        return state;
    }

    //method used to return a state where one jug has been poured into another
    public static int[] PoorJug(int[] node, int PouringJug, int jug2, int capacityjug2){
        int[] state = new int[3];
        state[0] = node[0];
        state[1] = node[1];
        state[2] = node[2];
        int totalGallons = state[PouringJug] + state[jug2];
        if(state[PouringJug] >= (capacityjug2 - state[jug2])){
            //set the jug being poured to the difference between the amount in the pouring jug, and the remaing amount in the second jug 
            state[PouringJug] = state[PouringJug] - (capacityjug2 - state[jug2]);
        }else{
            //all gallons used up in pouring jug otherwise
            state[PouringJug] = 0;
        }

        if(totalGallons >= capacityjug2){
            state[jug2] = capacityjug2; //set jug being poured into its capacity if pouring into the jug will go over the capacity
        }else{
            state[jug2] = totalGallons;
        }
        return state;
    }

    //method to return ArrayList of neighbouring states of a given node/state
    public static ArrayList<int[]> getNeighbours(int[] node, int[] jugs){
        ArrayList<int[]> neighbour_states = new ArrayList<int[]>();
        //set capacities of all 3 jugs to new variables
        int A = jugs[0];
        int B = jugs[1];
        int C = jugs[2];

        //set indexes of the 3 jugs for increased readability
        int jugA = 0;
        int jugB = 1;
        int jugC = 2;

        //fill the jugs if they are not full already, and append each reulting state to neighbour_states
        if (node[jugA] != A){
            neighbour_states.add(FillJug(node, jugA, A));
        }
        if (node[jugB] != B){
            neighbour_states.add(FillJug(node, jugB, B));
        }
        if (node[jugC] != C){
            neighbour_states.add(FillJug(node, jugC, C));
        }

        //if a jug is not empty find the neighbouring states by emptying the jug, and pouring the jug into the other two if possible
        if (node[jugA] != 0){
            neighbour_states.add(EmptyJug(node, jugA));
            if(node[jugB] != B){
                neighbour_states.add(PoorJug(node, jugA, jugB, B));//if jug B is not full add state by pouring jug A into jug B
            }
            if(node[jugC] != C){
                neighbour_states.add(PoorJug(node, jugA, jugC, C));//if jug C is not full add state by pouring jug A into jug C
            }
        }

        if (node[jugB] != 0){
            neighbour_states.add(EmptyJug(node, jugB));
            if(node[jugA] != A){
                neighbour_states.add(PoorJug(node, jugB, jugA, A));
            }
            if(node[jugC] != C){
                neighbour_states.add(PoorJug(node, jugB, jugC, C));
            }
        }

        if (node[jugC] != 0){
            neighbour_states.add(EmptyJug(node, jugC));
            if(node[jugA] != A){
                neighbour_states.add(PoorJug(node, jugC, jugA, A));
            }
            if(node[jugB] != B){
                neighbour_states.add(PoorJug(node, jugC, jugB, B));
            }
        }

        return neighbour_states;
    }
}