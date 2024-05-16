import processing.core.PApplet;

public class Sketch extends PApplet {

  // Related arrays for the (x, y) coordinates of the snowflakes
  float[] snowX = new float[42];
  float[] snowY = new float[42];
  int snowDiameter = 10;
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
    }
  }

  public void draw() {
    background (0);

    // Draw snow
    snow();

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

    // If the blue player circle hits a snowflake, the player will lose a life
    for (int i = 0; i < snowX.length; i++) {
      if (dist(playerX, playerY, snowX[i], snowY[i]) < snowDiameter/2 + 25)
      playerLives -= 1;
    }
}
  
  // All other defined methods are written below:
  public void snow() {
    fill (255);
    for (int i = 0; i < snowX.length; i++) {
      circle(snowX[i], snowY[i], snowDiameter);

      snowY[i]+= snowSpeed;
      
      // Reset snowflakes
      if (snowY[i] > height) {
        snowY[i] = 0;
      }
    }
  }

  public void playerLives() {
    // Add three squares in the top right to indicate player lives
    fill (255, 0, 0);
    for (int i = 0; i < playerLives; i++) {
    square(310 + i * 30, 10, 20);
    }
  }
}