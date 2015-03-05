package core;

import java.awt.Point;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public class Cell
{
  Rectangle rect;
  Point position;
  Cell parent = null;
  boolean isDoor = false;
  int exitID = 0;
  int fScore = 0;
  int gScore = 0;
  int hScore = 0;
  int offsetY = 200;
  int offsetX = 200;
  
  Cell(int x, int y, int x2, int y2)
  {
    this.position = new Point(x, y);
    this.cellType = 0;
    this.rect = new Rectangle(x2 - this.offsetX, y2 - this.offsetY, 32.0F, 32.0F);
  }
  
  public int getCellType()
  {
    return this.cellType;
  }
  
  public void setExitID(int n)
  {
    this.exitID = n;
  }
  
  public int getExitID()
  {
    return this.exitID;
  }
  
  public void setCellType(int cellType)
  {
    this.cellType = cellType;
  }
  
  public boolean isFree()
  {
    return this.cellType == 0;
  }
  
  public void setWall()
  {
    this.cellType = 1;
  }
    
  public void setOffsets(int offsetX, int offsetY)
  {
    this.offsetX = offsetX;
    this.offsetY = offsetY;
  }
  
  public void setFollower()
  {
    this.cellType = 4;
  }
  
  public void setPlayer()
  {
    this.cellType = 5;
  }
  
  public void setTarget()
  {
    this.cellType = 2;
  }
  
  public boolean isWall()
  {
    return this.cellType == 1;
  }
  
  public boolean isFollower()
  {
    return this.cellType == 4;
  }
  
  public boolean isPlayer()
  {
    return this.cellType == 5;
  }
  
  public void setFree()
  {
    this.cellType = 0;
  }
  
  public float getCentreX()
  {
    return this.rect.getCenterX();
  }
  
  public float getCentreY()
  {
    return this.rect.getCenterY();
  }
  
  public float getX()
  {
    return this.rect.getX();
  }
  
  public float getY()
  {
    return this.rect.getY();
  }
  
  public void setParent(Cell parent, Cell target)
  {
    this.parent = parent;
    if ((this.position.x != parent.position.x) && (this.position.y != parent.position.y)) {
      parent.gScore += 14;
    } else {
      parent.gScore += 14;
    }
    this.hScore = ((Math.abs(this.position.x - target.position.x) + Math.abs(this.position.y - target.position.y)) * 10);
    this.fScore = (this.gScore + this.hScore);
  }
  
  public void setParentIfBetter(Cell parent, Cell target)
  {
    int tempgScore;
    if ((this.position.x != parent.position.x) && (this.position.y != parent.position.y)) {
      tempgScore = parent.gScore + 14;
    } else {
      tempgScore = parent.gScore + 14;
    }
    int temphScore = (Math.abs(this.position.x - target.position.x) + Math.abs(this.position.y - target.position.y)) * 10;
    int tempfScore = tempgScore + temphScore;
    if (tempfScore < this.fScore)
    {
      this.fScore = tempfScore;
      this.gScore = tempgScore;
      this.hScore = temphScore;
      this.parent = parent;
    }
  }
  
  public int getFScore()
  {
    return this.fScore;
  }
  
  public void renderExits(Graphics g)
  {
    g.fill(this.rect);
  }
  
  public String toString()
  {
    return "Cell [position=" + this.position + ", fScore=" + this.fScore + "]";
  }
  
  public void setInspected()
  {
    this.inspected = true;
  }
  
  public boolean isTarget()
  {
    return this.cellType == 2;
  }
  
  int cellType = 0;
  static final int FREE = 0;
  static final int WALL = 1;
  static final int TARGET = 2;
  static final int FOLLOWER = 4;
  static final int PLAYER = 5;
  static final int NEXT = 6;
  private boolean inspected = false;
  
  public void showCellType(Graphics g)
  {
    g.drawString(""+cellType, rect.getX(), rect.getY());
  }
}
