package javabackend;

public class Point {

    // maybe keep a hash of duplicate notes or something.
    char[] cMajor = {'C', 'D', 'E', 'F', 'G', 'A', 'B'};
    public int xCoord;
    public int yCoord;
    public char pitch; // calculated via modding y coordinate with length of pitch array
    public double duration;
    public String noteRepresentation;
    public char octave;
    
    public Point(int x, int y) {
        xCoord = x;
        yCoord = y;

        int cMajorIndex;
        if(y<0) {
            cMajorIndex = Math.abs(8 + y) % 8;
        } else {
            cMajorIndex = y % 8;
        }
        //cMajorIndex = Math.abs(y) % 8;

        pitch = cMajor[cMajorIndex];

        duration = 0; /// default val - change later.

        // set octave based on where y value falls
        if( yCoord < -8) {
            octave = '3';
        } else if ( yCoord < 0 && yCoord >= -8) {
            octave = '4';
        } else if ( yCoord >= 0 && yCoord < 8 ) {
            octave = '5';
        } else {
            octave = '6';
        }

        noteRepresentation = "" + pitch + octave + duration;


    }

    public int getX() {
        return xCoord;
    }

    public int getY() {
        return yCoord;
    }

    public char getPitch() {
        return pitch;
    }

    public double getDuration() {
        return duration;
    }

    public char getOctave() {
        return octave;
    }

    public String getNoteRepresentation() {
        return noteRepresentation;
    }

    public void setX(int x) {
        this.xCoord = x;
    }

    public void setY(int y) {
        this.yCoord = y;
    }

    public void setPitch(char pitch) {
        this.noteRepresentation = "" + pitch + octave + "/" + duration;
        this.pitch = pitch;
    }

    public void setDuration(double duration) {
        this.noteRepresentation = "" + pitch + octave + "/" + duration;
        this.duration = duration;
    }

    public void setOctave(char octave) {
        this.noteRepresentation = "" + pitch + octave + "/" + duration;
        this.octave = octave;
    }

    public void setNoteRepresentation(String s) {
        this.noteRepresentation = s;
    }
}
