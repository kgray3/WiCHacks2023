package javabackend;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Map.Entry;

import org.jfugue.midi.MidiFileManager;
import org.jfugue.pattern.Pattern;

/*
 * Class to create an MP3 music representation of a math equation.
 */
public class MP3Creator {

    public static HashMap<Integer, Integer> xHashMap = new HashMap<>();
    public static HashMap<Integer,Boolean> pHashMap = new HashMap<>();
    //public static HashMap<Integer, Boolean> exploredIndices = new HashMap<>();
    public static void main(String[] args) {
        int[] xArr = {2,2,2,3,4};
        int[] yArr = {-12,-10,4,3,-4};

        Point[] arr = createPoints(xArr, yArr);
        String[][] musicPatterns = createJFugueMusicString(arr);




        Pattern song = generateMusicPattern(musicPatterns);

       generateMP3(song);

        
        
    }


    /*
     * Method to create Point objects out of two arrays of x coordinates and
     * y coordinates, respectively.
     */
    public static Point[] createPoints(int[] xArr, int[] yArr) {
        Point[] pointArr = new Point[xArr.length];
        for(int i = 0; i < xArr.length; i++) {

            if(xHashMap.containsKey(xArr[i])) {
                xHashMap.put(xArr[i], xHashMap.get(xArr[i]) + 1);
            } else {
                xHashMap.put(xArr[i], 1);
            }

            Point p = new Point(xArr[i], yArr[i]);
            pointArr[i] = p;
            if(i == xArr.length - 1) {
                pointArr[i].setDuration(pointArr[i-1].getDuration());
            } else {
                pointArr[i].setDuration(Math.abs(pointArr[i].getX() - xArr[i+1])/4);
            }
        }

        return pointArr;
    }


    // create alternate music string 


    /*
     * Method to create a valid JFugue music string based on the Points generated.
     */
    public static String[][] createJFugueMusicString(Point[] pArr) {
        

        int[] xArr = new int[pArr.length];

        Set<Integer> xSet = new HashSet<Integer>();
        ArrayList<Point> pSet = new ArrayList<>();

        for(int a = 0; a < pArr.length; a++) {
            xSet.add(pArr[a].getX()); 
            
        }

        int counter = 0;
        for(Point p : pArr) {
            if(!pHashMap.containsKey(p.getX())) {
                pSet.add(p);
                pHashMap.put(p.getX(),true);
            }
        }

        System.out.println(xSet.toArray());

        String[][] musicArr = new String[getMusicPatternCount(pArr)][pSet.toArray().length];

        int pArrIndex = 0;

        for(int cols = 0; cols < pSet.toArray().length; cols ++) {
                for(int rows = 0; rows < getMusicPatternCount(pArr); rows++) {
                    System.out.print("X: " + pSet.get(cols).getX());
                    System.out.println(" Count: " + xHashMap.get(pSet.get(cols).getX()));
                    if(xHashMap.get(pSet.get(cols).getX()) > 1 && rows < xHashMap.get(pSet.get(cols).getX())) {
                        musicArr[rows][cols] = pArr[pArrIndex].getNoteRepresentation();//pSet.get(cols).getNoteRepresentation();
                        pArrIndex++;
                    }else if (xHashMap.get(pSet.get(cols).getX()) == 1 && rows == 0){
                        musicArr[rows][cols] = pSet.get(cols).getNoteRepresentation();
                        pArrIndex++;
                    } 
                    
                    else {
                        musicArr[rows][cols] = "R/" + pSet.get(cols).getDuration(); 
                    }
                }
            
        }

        // ArrayList<String> musicPatterns = new ArrayList<>();

        // int iterations = 0;

        // int musicStringFreq = getMusicPatternCount(pArr);



        return musicArr;

    }

    public static int arrayFrequencyCount(int x, int[] arr) {
        int freq = 0;
        for(int element : arr ) {
            if(element == x) {
                freq++;
            }
        }
        return freq;
    }

    public static int getMusicPatternCount(Point[] arr) {
        int max = 1;

        int[] xArr = new int[arr.length];
        for( int i = 0; i < xArr.length; i++) {
            xArr[i] = arr[i].getX();
        }
        for(Entry<Integer, Integer> entry: xHashMap.entrySet()) {
            if(entry.getValue() > max) {
                max = entry.getValue();
            }
        }
        return max;
    }

    public static Pattern generateMusicPattern(String[][] musicArr) {

        ArrayList<String> patterns = new ArrayList<>();
        String musicString = "";
        for(int rows = 0; rows < musicArr.length; rows++) {
            for(int cols = 0; cols < musicArr[rows].length; cols++) {
                musicString = musicString += musicArr[rows][cols] + " ";
            }
            patterns.add(musicString);
            System.out.println(musicString);
            musicString = "";
        }

        String finalMusicString = "";

        for(int i = 0; i < patterns.size(); i ++) {
            finalMusicString = finalMusicString + "V" + i + " " + patterns.get(i) + " ";
        }

        Pattern pattern = new Pattern(finalMusicString);

        return pattern;

    }

    public static void generateMP3(Pattern p) {

        try {
            File filePath = new File("wichacks/javabackend/MIDI/result.midi");
            MidiFileManager.savePatternToMidi(p, filePath);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }





}
