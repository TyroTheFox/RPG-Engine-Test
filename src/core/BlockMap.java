package core;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class BlockMap
{
  public static TiledMap tmap;
  public static int mapWidth;
  public static int mapHeight;
  Cell[][] grid;
  private Cell temp;
  
  public BlockMap(TiledMap map)
    throws SlickException
  {
    tmap = map;
    mapWidth = tmap.getWidth() * tmap.getTileWidth();
    mapHeight = tmap.getHeight() * tmap.getTileHeight();
    this.grid = new Cell[tmap.getWidth()][tmap.getHeight()];
  }
  
  public void generateMap(int a, int b)
  {
    for (int x = 0; x < tmap.getWidth(); x++) {
      for (int y = 0; y < tmap.getHeight(); y++)
      {
        int tileID = tmap.getTileId(x, y, 0);
        this.temp = new Cell(x, y, a + x * 32, b + y * 32);
        if (tileID == 1)
        {
          this.grid[x][y] = this.temp;
          this.grid[x][y].cellType = 1;
        }
        else
        {
          this.grid[x][y] = this.temp;
          this.grid[x][y].cellType = 0;
        }
      }
    }
  }
  
  public void updateMap(int a, int b)
  {
    this.grid = new Cell[tmap.getWidth()][tmap.getHeight()];
    for (int x = 0; x < tmap.getWidth(); x++) {
      for (int y = 0; y < tmap.getHeight(); y++)
      {
        int tileID = tmap.getTileId(x, y, 0);
        this.temp = new Cell(x, y, a + x * 32, b + y * 32);
        if (tileID == 1)
        {
          this.grid[x][y] = this.temp;
          this.grid[x][y].cellType = 1;
        }
        else
        {
          this.grid[x][y] = this.temp;
          this.grid[x][y].cellType = 0;
        }
      }
    }
  }
  
  public TiledMap getMap()
  {
    return tmap;
  }
  
  public int getWidth()
  {
    return mapWidth;
  }
  
  public int getHeight()
  {
    return mapHeight;
  }
  
  public Cell[][] getGrid()
  {
    return this.grid;
  }
}
