public class Cube {
        Face[] faces;

    public Cube(){
        faces = new Face[6];
        Face topFace = new Face(new int[][]{ {0,0,0}, {0,0,0}, {0,0,0}},"up");
        faces[0] = topFace;
        Face leftFace = new Face(new int[][]{ {1,1,1}, {1,1,1}, {1,1,1}}, "left");
        faces[1] = leftFace;
        Face frontFace = new Face(new int[][]{ {2,2,2}, {2,2,2}, {2,2,2}}, "front");
        faces[2] = frontFace;
        Face rightFace = new Face(new int[][]{ {3,3,3}, {3,3,3}, {3,3,3}}, "right");
        faces[3] = rightFace;
        Face downFace = new Face(new int[][]{ {4,4,4}, {4,4,4}, {4,4,4}}, "down");
        faces[4] = downFace;
        Face backFace = new Face(new int[][]{ {5,5,5}, {5,5,5}, {5,5,5}}, "back");
        faces[5] = backFace;
    }
    // the below mappings might need to be updated
    // 0 -> yellow(up)
    // 1 -> green(left)
    // 2 -> orange(front)
    // 3 -> blue(right)
    // 4 -> white(down)
    // 5 -> red(back)
    public void scrambleCube(){
        // I can't just put random colored facelets in random locations because that could lead to an impossible cube
    }
    public void printCube(){
        System.out.println("                    ****----Cube----****");
        printFace(0);
        printRow(1, 0);
        System.out.print("    |");
        printRow(2, 0);
        System.out.print("    |");
        printRow(3, 0);
        System.out.print("    |");
        printRow(5, 0);
        System.out.println();
        printRow(1, 1);
        System.out.print("    |");
        printRow(2, 1);
        System.out.print("    |");
        printRow(3, 1);
        System.out.print("    |");
        printRow(5, 1);
        System.out.println();
        printRow(1, 2);
        System.out.print("    |");
        printRow(2, 2);
        System.out.print("    |");
        printRow(3, 2);
        System.out.print("    |");
        printRow(5, 2);
        System.out.println();
        System.out.println();
        printFace(4);
    }
    public void printFace(int face){
        for(int row = 0; row<3; row++){
            System.out.print("                    ");
            for(int col = 0; col<3; col++){
                System.out.print("    " + faces[face].getFacelet(row,col));
            }
            System.out.println("");
        }
        System.out.println(" ");
    }
    public void printRow(int face, int row){
        for(int col = 0; col<3; col++){
            System.out.print("    " + faces[face].getFacelet(row, col));
        }
    }
    // I'm rotating layers, so I have to update the main face by rotating all the values by 90 degrees
    // and then updating all the side facelets by moving the three adjacent side facelets into the face next to them.
    // This is hard to explain just with writing
    // For the 90-degree rotation of the top face, I have to perform a matrix transposition, then switch the columns
    // the transposition is just switching every row and colomn
    // up face clockwise
    public void faceClockwise(int face){
    //transposition - code taken from https://www.javatpoint.com/rotate-matrix-by-90-degrees-in-java
        int temp = 0;
        for(int i=0; i<3; i++) {
            for(int j=i; j<3; j++) {
                //checks if i is not equal to j because in transpose matrix diagonal elements will not swap
                if(i!=j) {
                    //swapping elements
                    temp = faces[face].getFacelet(i,j);
                    faces[face].facelets[i][j]=faces[face].facelets[j][i];
                    faces[face].facelets[j][i]=temp;
                }
            }
        }
        //row reversal
        int temp2 = 0;
        for(int row = 0; row<3; row++){
            temp2 = faces[face].getFacelet(row, 0);
            faces[face].updateFacelet(row,0,faces[face].getFacelet(row, 2));
            faces[face].updateFacelet(row,2, temp2);
        }
    }
    public void faceCounterClockwise(int face){
        int temp = 0;
        for(int i=0; i<3; i++) {
            for(int j=i; j<3; j++) {
                //checks if i is not equal to j because in transpose matrix diagonal elements will not swap
                if(i!=j) {
                    //swapping elements
                    temp = faces[face].getFacelet(i,j);
                    faces[face].facelets[i][j]=faces[face].facelets[j][i];
                    faces[face].facelets[j][i]=temp;
                }
            }
        }
        //column reversal
        int temp2 = 0;
        for(int col = 0; col<3; col++){
            temp2 = faces[face].getFacelet(0, col);
            faces[face].updateFacelet(0,col,faces[face].getFacelet(2, col));
            faces[face].updateFacelet(2,col, temp2);
        }
    }
    public void upLayerClockwise(){
        System.out.println("upLayer clockwise");
        faceClockwise(0);

        //updating all the side facelets
        int[] leftFaceTopRow = faces[1].getTopRowFacelets();
        int[] frontFaceTopRow = faces[2].getTopRowFacelets();
        int[] rightFaceTopRow = faces[3].getTopRowFacelets();
        int[] backFaceTopRow = faces[5].getTopRowFacelets();

        faces[1].setRow(0, frontFaceTopRow);
        faces[2].setRow(0, rightFaceTopRow);
        faces[3].setRow(0,backFaceTopRow);
        faces[5].setRow(0, leftFaceTopRow);
    }
    public void upLayerCounterClockwise(){
        System.out.println("upLayer counter clockwise");
        faceCounterClockwise(0);

        //updating all the side facelets
        int[] leftFaceTopRow = faces[1].getTopRowFacelets();
        int[] frontFaceTopRow = faces[2].getTopRowFacelets();
        int[] rightFaceTopRow = faces[3].getTopRowFacelets();
        int[] backFaceTopRow = faces[5].getTopRowFacelets();

        faces[1].setRow(0, backFaceTopRow);
        faces[2].setRow(0, leftFaceTopRow);
        faces[3].setRow(0,frontFaceTopRow);
        faces[5].setRow(0, rightFaceTopRow);
    }
    public void frontLayerClockwise(){
        System.out.println("front layer clockwise");
        faceClockwise(2);
        //updating side facelets
        int[] topFaceBottomRow = new int[3];//faces[0].getBottomRowFacelets();
        System.out.println(topFaceBottomRow[0] + "," + topFaceBottomRow[1] + "," + topFaceBottomRow[2]);
        int[] leftFaceRightColumn = faces[1].getRightColFacelets();
        System.out.println(topFaceBottomRow[0] + "," + topFaceBottomRow[1] + "," + topFaceBottomRow[2]);
        int[] rightFaceLeftColumn = faces[3].getLeftColFacelets();
        System.out.println(topFaceBottomRow[0] + "," + topFaceBottomRow[1] + "," + topFaceBottomRow[2]);
        int[] bottomFaceTopRow = faces[4].getTopRowFacelets();
        System.out.println(topFaceBottomRow[0] + "," + topFaceBottomRow[1] + "," + topFaceBottomRow[2]);

        // hard copies the top face to array to save it
        System.arraycopy(faces[0].getBottomRowFacelets(), 0, topFaceBottomRow, 0, faces[0].getBottomRowFacelets().length);

        faces[0].setRowFromCol(2, leftFaceRightColumn);
        System.out.println(topFaceBottomRow[0] + "," + topFaceBottomRow[1] + "," + topFaceBottomRow[2]);
        faces[1].setColFacelets(2, bottomFaceTopRow);
        System.out.println(topFaceBottomRow[0] + "," + topFaceBottomRow[1] + "," + topFaceBottomRow[2]);
        faces[3].setColFacelets(0, topFaceBottomRow);
        faces[4].setRowFromCol(0, rightFaceLeftColumn);
    }
    public void frontLayerCounterClockwise(){
        System.out.println("front layer clockwise");
        faceCounterClockwise(2);
        //updating side facelets
    }
    // down face clockwise
    // left face clockwise
    // right face clockwise
    // forward face clockwise
    // back face clockwise

    // up face counter-clockwise
    // down face counter-clockwise
    // left face counter-clockwise
    // right face counter-clockwise
    // forward face counter-clockwise
    // back face counter-clockwise
    public static void main(String[] args) {
        Cube cube = new Cube();
        cube.faces[0].setFacelets(new int[][]{{5,9,10},{12,23,34},{45,8,19}});
        cube.printCube();
        cube.upLayerClockwise();
        cube.printCube();
        cube.upLayerCounterClockwise();
        cube.printCube();
        cube.frontLayerClockwise();
        cube.printCube();
    }
}

