import processing.core.PApplet;

public class Sketch extends PApplet {
	
  // snowflake variables
  float[] snowY;
  float[] snowX;
  float[] snowSpeed;
  double snowfallSpeed = 1;
  int[] snowColor; 
  boolean[] ballHideStatus;

  // blue player variables
  int playerX; 
  int playerY; 
  int playerLives = 3;

  // game over variable
  boolean gameOver = false;
  
  public void settings() 
  {
    size(800,600);
  }

  /** 
   * Called once at the beginning of execution.  Add initial set up
   * values here i.e background, stroke, fill etc.
   */
  public void setup() 
  {
    // setting up arrays
    snowY = new float[25];
    snowX = new float[25];
    snowSpeed = new float[25];
    snowColor = new int[25];
    ballHideStatus = new boolean[25];

    // giving snow random speeds and positions
    for (int i = 0; i < 25; i++)
    {
      snowY[i] = random(-height, 0);
      snowX[i] = random(width);
      snowSpeed[i] = random (1,4);
      snowColor[i] = color(255,255,255);
      ballHideStatus[i] = false; 
    }

    // player spawn point
    playerY = 500;
    playerX = 400;

  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() 
  {
    {
    // background
    background(0, 0, 0);

    // drawing snow
    for (int i = 0; i < 25; i++)
    {
      if (!ballHideStatus[i])
      {
        fill(snowColor[i]);
        ellipse(snowX[i], snowY[i], 30, 30);
        snowY[i] += snowSpeed[i] * snowfallSpeed;

        if (snowY[i] > height)
        {
          snowY[i] = random(0);
          snowX[i] = random(width);
        }
      }
      
    // collision detection between player and snow
      if (!ballHideStatus[i] && dist(playerX, playerY, snowX[i], snowY[i]) < 20)
      {
        playerLives--;
        ballHideStatus[i] = true;

        if (playerLives <= 0)
        {
          gameOver = true;
        }
      }
    }

    // draw blue player
    fill(0, 0, 255);
    ellipse(playerX, playerY, 25,25);

    // player health count
    fill (255, 0, 0);
    for (int i = 0; i < playerLives; i++)
    {
      rect (675 + i * 35, 25, 25, 25);
    }


    }

    // this is what happens when you have 0 lives left, the game ends
    if (gameOver)
    {
      background(255,255,255);
      textSize(80);
      fill(0,0,0);
      text("Game Over! ;(", 200, 250);
    }
  }
  
  // when the mouse is presed and the cursor is close to a snowflake, it is deleted
  public void mousePressed()
  {
    for (int i = 0; i < 25; i ++)
    {
      if (!ballHideStatus[i] && dist(mouseX, mouseY, snowX[i], snowY[i]) < 25)
      {
        ballHideStatus[i] = true;
      }

    }
  }

  public void keyPressed ()
  {
    // player movement
    if (key == 'w')
    {
      playerY -= 5;
    }
    else if (key == 'a')
    {
      playerX -= 5;
    }
    else if (key == 's')
    {
      playerY += 5;
    }
    else if (key == 'd')
    {
      playerX += 5;
    }

        // snow speed controls
    if (keyCode == UP)
    {
      snowfallSpeed = 0.5;
    }
    else if (keyCode == DOWN)
    {
      snowfallSpeed = 1.5;
    }
  }

}
