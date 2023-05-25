import java.util.HashMap;

public class Face {
    //properties
    int[][] facelets;
    String orientation;
    //constructor
    public Face(int[][] facelets, String orientation){
        this.facelets = facelets;
        this.orientation = orientation;
    }
    //getters and setters

    public String getOrientation() {
        return orientation;
    }
    public int[][] getFacelets() {
        return facelets;
    }
    public void setFacelets(int[][] facelets) {
        this.facelets = facelets;
    }
    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public void updateFacelet(int row, int col, int newVal){
        facelets[row][col] = newVal;
    }
    public int getFacelet(int row, int col){
        return facelets[row][col];
    }

    public int[] getRightColFacelets(){
        int[] rightCol = new int[3];
        for(int row = 0; row<3; row++){
            rightCol[row] = facelets[row][2];
        }
        return rightCol;
    }
    public int[] getLeftColFacelets(){
        int[] leftCol = new int[3];
        for(int row = 0; row<3; row++){
            leftCol[row] = facelets[row][0];
        }
        return leftCol;
    }
    public int[] getTopRowFacelets(){
        return facelets[0];
    }
    public int[] getBottomRowFacelets(){
        return facelets[2];
    }
    public void setCol(int col, int[] arr){
        for(int row = 0; row<3; row++){
            facelets[row][col] = arr[row];
        }
    }
    public void setColInverse(int col, int[] arr){
        int counter = 0;
        for(int row = 2; row>=0; row--){
            facelets[row][col] = arr[counter];
            counter ++;
        }
    }
    public void setRow(int row, int[] facelets){
        this.facelets[row] = facelets;
    }
    public void setRowInverse(int row, int[] arr){
        int counter = 0;
        for(int col = 2; col>=0; col--){
            facelets[row][col] = arr[counter];
            counter ++;
        }
    }
}
