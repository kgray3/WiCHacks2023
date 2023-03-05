var previousX;
var previousY;
var previousHeight;
var rcount = 0;
var lcount = 0;
var total = 0;

function setup() {
  createCanvas(700, 500);
  background(255);
  stroke(10);
  line(350, 0, 350, 700);
}

function draw() {
  //needed for mousePressed() to work
}

function mousePressed() {
  
   strokeWeight(2);
  nextX = mouseX;
   nextY = mouseY;
   nextWidth = random(25, 50);
   nextHeight = random(10, 30);
  fill(random(0, 256), random(0, 256),256);
  stroke(random(100, 140), random(40, 80), 0);
  rect(nextX, nextY, nextWidth, nextHeight);
  
 
 if(nextX < width/2){
   fill(250,255,255);
   rect(35, 425,40,30);
   fill(random(0, 256), random(0, 256),256);
   textSize(20);
   rcount += 1
  text(rcount, 50,height-50);
 } else {
   fill(250,255,255);
   rect(635, 425,40,30);
   fill(random(0, 256), random(0, 256),256);
   textSize(20);
   lcount += 1
  text(lcount, width-50,height-50);
   
 }
 
 total = lcount + rcount;
 
 fill(250,255,255);
 rect(329,425,40,30);
 fill(random(0, 256), random(0, 256),256);
 text(total,343,450);
 
 
  
}