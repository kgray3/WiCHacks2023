color bg = color(255, 255, 255);

void setup(){
  size(700, 700);
  background(bg);
}

int rangemin = -30;
int rangemax = 30;
boolean drawn = false;

int p = 20;
int subintervals = 20;
float side = 0.5; // 0 for lefthand, 0.5 for midpoint, 1 for righthand

void draw(){
  translate(width/2,height/2);
  
  if(!drawn){
    background(bg);
    drawCoordinatePlane();
    drawFunction();
    float area = drawIntervals(subintervals);
    
    fill(255,150);
    noStroke();
    rect(95, 170, 250, 90);
    fill(0);
    textSize(14);
    text("function: 1/10 x^2", 100, 200);
    text("subintervals: " + subintervals, 100, 220);
    text("approximated area: " + area, 100, 240);
  }
}

// x*x + 2
// 4*x - x*x
// x*x*x - 3
// .1*x*x
float function(float x){
  return 4*x - x*x;
}

float drawIntervals(int subintervals){
  float area = 0;
  float range = rangemax - rangemin;
  float rectw = range / subintervals;
  for(int i = 0; i < subintervals; i++){
    fill(0,0,255,100);
    stroke(0);
    strokeWeight(1);
    //println(i*rectw);
    rect((rangemin + (i*rectw))*p,0,rectw*p,-p*function(rangemin + (i*rectw) + side));
    area = area + (rectw*function(rangemin + (i*rectw) + side));
  }
  
  return area;
}

void drawFunction(){
  float x = rangemin;
  float y;
  for(float i = rangemin; i < rangemax; i = i + .01){
    y = function(x);
    //println("x: " + x + ", y: " + y);
    fill(0);
    stroke(0);
    point(x*p,y*-p);
    x = i;
  }
  drawn = true; 
}

void drawCoordinatePlane(){
  stroke(100);
  line(-(width/2), 0, width/2, 0);
  line(0,-(height/2), 0, height/2);
  for(int i = -(width/2); i < width/2; i = i + p)
    line(i,5,i,-5);
  for(int i = -(height/2); i < height/2; i = i + p)
    line(5,i,-5,i);
}

void mouseDragged(){
 if(keyPressed){
   if(key == 'z'){
     p = mouseX / 25;
   } else if(key == 's'){
     subintervals = mouseX / 5;
   }
 }
 drawn = false;
}

void mouseReleased(){
  drawn = true;
}
