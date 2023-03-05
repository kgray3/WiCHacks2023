color bg = color(255, 255, 255);

void setup(){
  size(700, 700);
  background(bg);
}

int radius = 200;
int divisor = 10;
int filled = 2;

void draw(){
  translate(width/2,height/2);
  background(bg);
  stroke(0);
  strokeWeight(2);
  circle(0, 0, radius*2);
  
  fill(0);
  textSize(14);
  if(filled > divisor)
       filled = divisor;
  text("showing: " + filled + " / " + divisor, 100, 200);
  
  drawFilled();
  drawDivisions();
}

void drawFilled() {
  float lastAngle = 0;
  for (int i = 0; i < divisor; i++) {
    if(i < filled)
      fill(100);
    else
      fill(255);
    arc(0, 0, radius*2, radius*2, lastAngle, lastAngle+radians(360/divisor));
    lastAngle += radians(360/divisor);
  }
}

void drawDivisions(){
  stroke(0);
  strokeWeight(2);
  rotate(PI/2);
  for(int i = 0; i < divisor; i++){ //<>//
    rotate(radians(360/divisor));
    line(0,0,0,-200);
  }
  rotate(3*PI/2);
}

void mouseDragged(){
 if(keyPressed){
   if(key == 'd'){
     if(divisor < 1)
       divisor = 1;
     else
       divisor = mouseX / 25;
   } else if(key == 'f'){
     if(filled > divisor)
       filled = divisor;
     else if(filled < 0)
       filled = 0;
     else
       filled = mouseX / 25;
   }
 }
}
