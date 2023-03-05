var x1;
var y1= 450;
var x2 = 450;
var y2;
var x3 = 450;
var value;
var i = 0;
var j = 0;
var totalX = 0;
var totalY = 0;


function setup(){
  createCanvas(700,500);
  background(255);
}

function draw(){

  //vertical();
  //horizontal();
}

function mouseDragged(){
  background(255);
  fill(255);
  rect(25, 25, mouseX, mouseY );
  vertical();
  horizontal();
  fill(random(0, 256), random(0, 256),256);
  text(i * j, 660,460);
  
  
}


function vertical(){
  i = 0;
  for(x1 = 25; x1 < mouseX; x1 += 25){
  line(x1, 25, x1, mouseY+25);
  fill(random(0, 256), random(0, 256),256);
   text(i += 1, x1 + 5,mouseY + 25);
  //if(mouseX > pmouseX){
  //} else {
  //  i -= 1;
  //  //rect(x1 + 5,mouseY + 25,x1+30,mouseY + 50);
  //}
  }
 
}

function horizontal(){
  j=0;
  for(y2 = 25; y2 < mouseY; y2 += 25){
  line(25,y2,mouseX+25,y2);
  fill(random(0, 256), random(0, 256),256);
  text(j+=1, mouseX+10,y2+10);
  }
  
}