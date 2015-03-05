package core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * Room Class
 * Creates each location within the game
 * @author Kieran
 *
 */

class Room 
{
	// The Room's description.
    private String description;
    
    // The Room's exits.
    private ArrayList<Cell[]> exit;
    private HashMap<Cell[], Room> exitMap;
    //List of Locations upon New Entrance
    private HashMap<Room, Cell> entranceLocationList;
    //World Map
    private String map;
    //State
    private int state;
    
    /**
     * Initialises Class
     * @param map
     * @param state
     */
    public Room(String map, int state){
        exit = new ArrayList<Cell[]>();
        exitMap = new HashMap<Cell[], Room>();
        entranceLocationList = new HashMap<Room, Cell>();
        this.map = map;
        this.state = state;
    }
    
     /**
      * Adds and Exit to the room
      * @param c
      */
    public void addExit(Cell[] c){
    	exit.add(c);
    }
    
    /**
     * Returns an Exit
     * @return
     */
    public ArrayList<Cell[]> getExit() 
    {
        return exit;
    }
    
    /**
     * Returns an Exit
     * @param i
     * @return
     */
    public Cell[] getExit(int i) 
    {
        return exit.get(i);
    }
    
    /**
     * Adds a Neighbour to this Room
     * 
     * @param cells
     * @param room
     */
    public void addNeighbour(Cell[] cells, Room room){
    	exitMap.put(cells, room);
    }
    
    /**
     * Returns a Neightbour to this Room
     * @return
     */
    public HashMap<Cell[], Room> getNeighbour(){
    	return exitMap;
    }
    
    /**
     * Adds an Entrance Location when you move into the Room from another
     * @param room
     * @param cell
     */
    public void addEntranceLocation(Room room, Cell cell){
    	entranceLocationList.put(room, cell);
    }
    
    /**
     * Returns the Entrance Location List
     * @return
     */
    public HashMap<Room, Cell> getLocationList(){
    	return entranceLocationList;
    }
    
    /**
     * Finds the Rooms Neighbour
     * @param cell
     * @return
     */
    public Room findNeighbour(Cell cell){
    	Room temp = null;
    	
    	for(int i = 0; i < exit.size(); i++){
        	for(int g = 0; g < exit.get(i).length; g++){
	    		if(exit.get(i)[g].isDoor && (exit.get(i)[g].position.x == cell.position.x 
	    				&& exit.get(i)[g].position.y == cell.position.y)){
	    			temp = exitMap.get(exit.get(i));
	    		}
        	}
    	}    	
    	return temp;
    }
    
	/**
	 * Returns an Entrance Location
	 * @param room
	 * @return
	 */
	public Cell findEntranceLocation(Room room){
		Cell temp = new Cell(0, 0, 0, 0);
		
		temp = entranceLocationList.get(room);
		
		return temp;
	}
    
	/**
	 * Returns the Room's map
	 * @return
	 */
    public String getMap(){
    	return map;
    }
    
    /**
     * For Slick State Swapping
     * @return
     */
    public int getState(){
    	return state;
    }
    
}
