import processing.core.PApplet;

public class Sketch extends PApplet {

  // Related arrays for the (x, y) coordinates of the snowflakes
  float[] snowX = new float[42];
  float[] snowY = new float[42];
  boolean[] ballHideStatus = new boolean[42];
  int snowDiameter = 30;
  float snowSpeed = 1;
  float playerX = 50;
  float playerY = 350;
  int playerLives = 3;

  public void settings() {
    size(400, 400);
  }

  public void setup() {
    background(0);

    // Generate random x- and y-values for snowflakes
    for (int i = 0; i < snowX.length; i++) {
      snowX[i] = random(width);
      snowY[i] = random(height); 
      ballHideStatus[i] = false;
    }
  }

  public void draw() {
    background (0);

    // Draw the snowflakes
    snow();

    // If they the UP key is pressed, the speed of the snow goes faster, if the DOWN key is pressed, the speed of the snow goes slower
    if (keyPressed) {  
      if (keyCode == UP) {
        snowSpeed ++;
      }
      else if (keyCode == DOWN) {
        snowSpeed --;
      }
    }

    // Add a blue player circle
    fill (0, 0, 255);
    ellipse (playerX, playerY, 50, 50);

    // If WASD keys are used, move accordingly
    if (keyPressed) {
      if (key == 'w'){
        playerY -= 2;
      }
      else if (key == 'a'){
        playerX -= 2;
      }
      else if (key == 's'){
        playerY += 2;
      }
      else if (key == 'd'){
        playerX += 2;
      }
    }

    playerLives();

    // If the player collides with a snowflake, they will lose a life
    for (int i = 0; i < snowX.length; i++) {
      if (!ballHideStatus[i] && dist(playerX, playerY, snowX[i], snowY[i]) < snowDiameter / 2 + 25) {
        playerLives--;
        snowX[i] = random(width); 
        snowY[i] = random(height);
      }
    }

    // If the player has no more lives, the screen clears to white
    if (playerLives <= 0) {
      background (255);
    }
  }
  
  // All other defined methods are written below:

  /**
   * Draws snowflakes falling at random locations.
   * 
   * @author H. Ye
   */
  public void snow() {
    fill (255);
    for (int i = 0; i < snowX.length; i++) {
      if (!ballHideStatus[i]) {
        circle(snowX[i], snowY[i], snowDiameter);
        snowY[i]+= snowSpeed;
      
      // Reset snowflakes
        if (snowY[i] > height) {
          snowY[i] = 0;
          }
        }
      }
    }

  /**
   * Draws three squares to indicate the amount of lives the player has.
   * 
   * @author H. Ye
   */
  public void playerLives() {
    // Add three squares in the top right to indicate player lives
    fill (255, 0, 0);
    for (int i = 0; i < playerLives; i++) {
      square (310 + (i * 30), 10, 20);
    }
  }

  /**
   * When the mouse is clicked on the snowflakes, they disappear.
   * 
   * @author H. Ye
   */
  
  public void mouseClicked() {
    // The mouse can click on snowflakes to make them disappear
    for (int i = 0; i < snowX.length; i++) {
      if (!ballHideStatus[i] && dist(mouseX, mouseY, snowX[i], snowY[i]) < snowDiameter/2) {
        ballHideStatus[i] = true;
      }
    }
  }
}