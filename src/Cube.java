import java.util.Arrays;
import java.util.HashMap;
import java.util.function.Function;

public class Cube {
        Face[] faces;

    public Cube(){
        faces = new Face[6];

        // when you're facing yellow, red on top
        HashMap<Integer, Integer> topFaceRelativeOrientation = new HashMap<>();
        //    (what you're referencing -> what you're actually rotating)
        topFaceRelativeOrientation.put(0,5);
        topFaceRelativeOrientation.put(1,1);
        topFaceRelativeOrientation.put(2,0);
        topFaceRelativeOrientation.put(3,3);
        topFaceRelativeOrientation.put(4,5);
        topFaceRelativeOrientation.put(5,4);
        Face topFace = new Face(new int[][]{ {0,0,0}, {0,0,0}, {0,0,0}},"up", topFaceRelativeOrientation);
        faces[0] = topFace;

        // when you're facing green, yellow on top
        HashMap<Integer, Integer> leftFaceRelativeOrientation = new HashMap<>();
        //    (what you're referencing -> what you're actually rotating)
        leftFaceRelativeOrientation.put(0,0);
        leftFaceRelativeOrientation.put(1,2);
        leftFaceRelativeOrientation.put(2,3);
        leftFaceRelativeOrientation.put(3,5);
        leftFaceRelativeOrientation.put(4,4);
        leftFaceRelativeOrientation.put(5,1);
        Face leftFace = new Face(new int[][]{ {1,1,1}, {1,1,1}, {1,1,1}}, "left", leftFaceRelativeOrientation);
        faces[1] = leftFace;

        // when you're facing orange, yellow on top
        HashMap<Integer, Integer> frontFaceRelativeOrientation = new HashMap<>();
        //    (what you're referencing -> what you're actually rotating)
        frontFaceRelativeOrientation.put(0,0);
        frontFaceRelativeOrientation.put(1,1);
        frontFaceRelativeOrientation.put(2,2);
        frontFaceRelativeOrientation.put(3,3);
        frontFaceRelativeOrientation.put(4,4);
        frontFaceRelativeOrientation.put(5,5);
        Face frontFace = new Face(new int[][]{ {2,2,2}, {2,2,2}, {2,2,2}}, "front", frontFaceRelativeOrientation);
        faces[2] = frontFace;

        // when you're facing blue, yellow on top
        HashMap<Integer, Integer> rightFaceRelativeOrientation = new HashMap<>();
        //    (what you're referencing -> what you're actually rotating)
        rightFaceRelativeOrientation.put(0,0);
        rightFaceRelativeOrientation.put(1,5);
        rightFaceRelativeOrientation.put(2,1);
        rightFaceRelativeOrientation.put(3,2);
        rightFaceRelativeOrientation.put(4,4);
        rightFaceRelativeOrientation.put(5,3);
        Face rightFace = new Face(new int[][]{ {3,3,3}, {3,3,3}, {3,3,3}}, "right",rightFaceRelativeOrientation);
        faces[3] = rightFace;

        // when you're facing white, orange on top
        HashMap<Integer, Integer> downFaceRelativeOrientation = new HashMap<>();
        //    (what you're referencing -> what you're actually rotating)
        downFaceRelativeOrientation.put(0,2);
        downFaceRelativeOrientation.put(1,1);
        downFaceRelativeOrientation.put(2,4);
        downFaceRelativeOrientation.put(3,3);
        downFaceRelativeOrientation.put(4,5);
        downFaceRelativeOrientation.put(5,0);
        Face downFace = new Face(new int[][]{ {4,4,4}, {4,4,4}, {4,4,4}}, "down",downFaceRelativeOrientation);
        faces[4] = downFace;

        HashMap<Integer, Integer> backFaceRelativeOrientation = new HashMap<>();
        //    (what you're referencing -> what you're actually rotating)
        backFaceRelativeOrientation.put(0,0);
        backFaceRelativeOrientation.put(1,3);
        backFaceRelativeOrientation.put(2,5);
        backFaceRelativeOrientation.put(3,1);
        backFaceRelativeOrientation.put(4,4);
        backFaceRelativeOrientation.put(5,2);
        Face backFace = new Face(new int[][]{ {5,5,5}, {5,5,5}, {5,5,5}}, "back",backFaceRelativeOrientation);
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

    public int[] arrayCopy(int[] arr){
        int[] copy = new int[3];
        for(int i = 0; i<3; i++){
            copy[i] = arr[i];
        }
        return copy;
    }
    public int[][] faceCopy(int[][] face){
        int[][] copy = new int[3][3];
        for(int row = 0; row<3; row++){
            for(int col = 0; col<3; col++){
                copy[row][col] = face[row][col];
            }
        }
        return copy;
    }

    public Boolean faceCompleted(int face){
        for(int row = 0; row<3; row++){
            for(int col = 1; col<3; col++){
                if((faces[face].getFacelet(row, col)%10) != (faces[face].getFacelet(0, 0)%10)){
                    return false;
                }
            }
        }
        return true;
    }

    public void applyRotations(String[] algorithm){
        for(String s:algorithm){
            if(s.equals("U")){
                upLayerClockwise();
            }
            else if(s.equals("U'")){
                upLayerCounterClockwise();
            }
            else if(s.equals("U2")){
                upLayerClockwise();
                upLayerClockwise();
            }

            else if(s.equals("F")){
                frontLayerClockwise();
            }
            else if(s.equals("F'")){
                frontLayerCounterClockwise();
            }
            else if(s.equals("F2")){
                frontLayerClockwise();
                frontLayerClockwise();
            }

            else if(s.equals("L")){
                leftLayerClockwise();
            }
            else if(s.equals("L'")){
                leftLayerCounterClockwise();
            }
            else if(s.equals("L2")){
                leftLayerClockwise();
                leftLayerClockwise();
            }

            else if(s.equals("R")){
                rightLayerClockwise();
            }
            else if(s.equals("R'")){
                rightLayerCounterClockwise();
            }
            else if(s.equals("R2")){
                rightLayerClockwise();
                rightLayerClockwise();
            }

            else if(s.equals("D")){
                downLayerClockwise();
            }
            else if(s.equals("D'")){
                downLayerCounterClockwise();
            }
            else if(s.equals("D2")){
                downLayerClockwise();
                downLayerClockwise();
            }

            else if(s.equals("B")){
                backLayerClockwise();
            }
            else if(s.equals("B'")){
                backLayerCounterClockwise();
            }
            else if(s.equals("B2")){
                backLayerClockwise();
                backLayerClockwise();
            }
        }
    }
    // works
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
        printCube();
    }
    // works
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
        printCube();
    }
    // works
    public void frontLayerClockwise(){
        System.out.println("front layer clockwise");
        faceClockwise(2);
        //updating side facelets
        int[] topFaceBottomRow = arrayCopy(faces[0].getBottomRowFacelets());
        int[] leftFaceRightColumn = arrayCopy(faces[1].getRightColFacelets());
        int[] rightFaceLeftColumn = arrayCopy(faces[3].getLeftColFacelets());
        int[] bottomFaceTopRow = arrayCopy(faces[4].getTopRowFacelets());

        // hard copies the top face to array to save it
       // System.arraycopy(faces[0].getBottomRowFacelets(), 0, topFaceBottomRow, 0, faces[0].getBottomRowFacelets().length);

        faces[0].setRowInverse(2, leftFaceRightColumn);
        faces[1].setCol(2, bottomFaceTopRow);
        faces[3].setCol(0, topFaceBottomRow);
        faces[4].setRowInverse(0, rightFaceLeftColumn);
        printCube();
    }
    // works
    public void frontLayerCounterClockwise(){
        System.out.println("front layer counter clockwise");
        faceCounterClockwise(2);
        //updating side facelets
        int[] topFaceBottomRow = arrayCopy(faces[0].getBottomRowFacelets());
        int[] leftFaceRightColumn = arrayCopy(faces[1].getRightColFacelets());
        int[] rightFaceLeftColumn = arrayCopy(faces[3].getLeftColFacelets());
        int[] bottomFaceTopRow = arrayCopy(faces[4].getTopRowFacelets());

        faces[0].setRow(2, rightFaceLeftColumn);
        faces[1].setCol(2, topFaceBottomRow);
        faces[3].setCol(0, bottomFaceTopRow);
        faces[4].setRowInverse(0, leftFaceRightColumn);
        printCube();
    }
    // works
    public void rightLayerClockwise(){
        System.out.println("right layer clockwise");
        faceClockwise(3);

        //updating side facelets
        int[] topFaceRightColumn = arrayCopy(faces[0].getRightColFacelets());
        int[] backFaceLeftColumn = arrayCopy(faces[5].getLeftColFacelets());
        int[] downFaceRightCol = arrayCopy(faces[4].getRightColFacelets());
        int[] frontFaceRightCol = arrayCopy(faces[2].getRightColFacelets());

        faces[5].setColInverse(0, topFaceRightColumn);
        faces[0].setCol(2,frontFaceRightCol);
        faces[4].setColInverse(2, backFaceLeftColumn);
        faces[2].setCol(2,downFaceRightCol);
        printCube();
    }
    // works
    public void rightLayerCounterClockwise(){
        System.out.println("right layer counter clockwise");
        faceCounterClockwise(3);

        //updating side facelets
        int[] topFaceRightColumn = arrayCopy(faces[0].getRightColFacelets());
        int[] backFaceLeftColumn = arrayCopy(faces[5].getLeftColFacelets());
        int[] downFaceRightCol = arrayCopy(faces[4].getRightColFacelets());
        int[] frontFaceRightCol = arrayCopy(faces[2].getRightColFacelets());

        faces[5].setColInverse(0,downFaceRightCol);
        faces[0].setColInverse(2,backFaceLeftColumn);
        faces[4].setCol(2, frontFaceRightCol);
        faces[2].setCol(2,topFaceRightColumn);
        printCube();
    }
    // works
    public void leftLayerClockwise(){
        System.out.println("left layer clockwise");
        faceClockwise(1);

        //updating side facelets
        int[] topFaceLeftColumn = arrayCopy(faces[0].getLeftColFacelets());
        int[] backFaceRightColumn = arrayCopy(faces[5].getRightColFacelets());
        int[] downFaceLeftCol = arrayCopy(faces[4].getLeftColFacelets());
        int[] frontFaceLeftCol = arrayCopy(faces[2].getLeftColFacelets());

        faces[0].setColInverse(0, backFaceRightColumn);
        faces[2].setCol(0, topFaceLeftColumn);
        faces[4].setCol(0, frontFaceLeftCol);
        faces[5].setColInverse(2, downFaceLeftCol);
        printCube();
    }
    // works
    public void leftLayerCounterClockwise(){
        System.out.println("left layer counter clockwise");
        faceCounterClockwise(1);

        //updating side facelets
        int[] topFaceLeftColumn = arrayCopy(faces[0].getLeftColFacelets());
        int[] backFaceRightColumn = arrayCopy(faces[5].getRightColFacelets());
        int[] downFaceLeftCol = arrayCopy(faces[4].getLeftColFacelets());
        int[] frontFaceRightCol = arrayCopy(faces[2].getLeftColFacelets());

        faces[0].setCol(0, frontFaceRightCol);
        faces[2].setCol(0, downFaceLeftCol);
        faces[4].setColInverse(0, backFaceRightColumn);
        faces[5].setColInverse(2, topFaceLeftColumn);
        printCube();
    }
    // works
    public void downLayerClockwise(){
        System.out.println("down layer clockwise");
        faceClockwise(4);

        //updating side facelets
        int[] leftFaceBottomRow = arrayCopy(faces[1].getBottomRowFacelets());
        int[] backFaceBottomRow = arrayCopy(faces[5].getBottomRowFacelets());
        int[] rightFaceBottomRow = arrayCopy(faces[3].getBottomRowFacelets());
        int[] frontFaceBottomRow = arrayCopy(faces[2].getBottomRowFacelets());

        faces[1].setRow(2, backFaceBottomRow);
        faces[2].setRow(2, leftFaceBottomRow);
        faces[3].setRow(2, frontFaceBottomRow);
        faces[5].setRow(2, rightFaceBottomRow);
    }
    // works
    public void downLayerCounterClockwise(){
        System.out.println("down layer counter clockwise");
        faceCounterClockwise(4);

        //updating side facelets
        int[] leftFaceBottomRow = arrayCopy(faces[1].getBottomRowFacelets());
        int[] backFaceBottomRow = arrayCopy(faces[5].getBottomRowFacelets());
        int[] rightFaceBottomRow = arrayCopy(faces[3].getBottomRowFacelets());
        int[] frontFaceBottomRow = arrayCopy(faces[2].getBottomRowFacelets());

        faces[1].setRow(2, frontFaceBottomRow);
        faces[2].setRow(2, rightFaceBottomRow);
        faces[3].setRow(2, backFaceBottomRow);
        faces[5].setRow(2, leftFaceBottomRow);
    }

    public void backLayerClockwise(){
        System.out.println("back layer clockwise");
        faceClockwise(5);

        //updating side facelets
        int[] rightFaceRightColumn = arrayCopy(faces[3].getRightColFacelets());
        int[] downFaceBottomRow = arrayCopy(faces[4].getBottomRowFacelets());
        int[] leftFaceLeftColumn = arrayCopy(faces[1].getLeftColFacelets());
        int[] topFaceTopRow = arrayCopy(faces[0].getTopRowFacelets());


    }
    public void backLayerCounterClockwise(){
        System.out.println("back layer counter clockwise");
        faceCounterClockwise(5);

        //updating side facelets
        int[] rightFaceRightColumn = arrayCopy(faces[3].getRightColFacelets());
        int[] downFaceBottomRow = arrayCopy(faces[4].getBottomRowFacelets());
        int[] leftFaceLeftColumn = arrayCopy(faces[1].getLeftColFacelets());
        int[] topFaceTopRow = arrayCopy(faces[0].getTopRowFacelets());

    }

    public void rotateCubeLeft(){
        System.out.println("rotate cube left");
        faceClockwise(0);
        faceCounterClockwise(4);

        int[][] prevFront = faceCopy(faces[2].facelets);
        int[][] prevLeft = faceCopy(faces[1].facelets);
        int[][] prevBack = faceCopy(faces[5].facelets);
        int[][] prevRight = faceCopy(faces[3].facelets);

        faces[1].setFacelets(prevFront);
        faces[2].setFacelets(prevRight);
        faces[5].setFacelets(prevLeft);
        faces[3].setFacelets(prevBack);
        printCube();
    }
    public void rotateCubeRight(){
        System.out.println("rotate cube right");
        faceCounterClockwise(0);
        faceClockwise(4);

        int[][] prevFront = faceCopy(faces[2].facelets);
        int[][] prevLeft = faceCopy(faces[1].facelets);
        int[][] prevBack = faceCopy(faces[5].facelets);
        int[][] prevRight = faceCopy(faces[3].facelets);

        faces[1].setFacelets(prevBack);
        faces[2].setFacelets(prevLeft);
        faces[5].setFacelets(prevRight);
        faces[3].setFacelets(prevFront);
    }
    public void rotateCubeUp(){

    }
    public void rotateCubeDown(){

    }
    public Boolean isCubeSolved(){
        if(faceCompleted(0) && faceCompleted(1) && faceCompleted(2) && faceCompleted(3) && faceCompleted(4) && faceCompleted(5)){
            return true;
        }
        return false;
    }


    public void rotateTopLayerTillYellowInRightSpot(){
        System.out.println("rotate top layer till yellow in right spot");
        while(faces[1].getFacelet(0,2)%10 != 0){
            upLayerClockwise();
        }
    }
    public int numYellowCornerFacelets(){
        System.out.println("num yellow corner facelets");
        int num = 0;
        if(faces[0].getFacelet(0,0)%10 == 0){
            num++;
        }
        if(faces[0].getFacelet(0,2)%10 == 0){
            num++;
        }
        if(faces[0].getFacelet(2,0)%10 == 0){
            num++;
        }
        if(faces[0].getFacelet(2,2)%10 == 0){
            num++;
        }
        return num;
    }
    public void rotateFishDownLeft(){
        System.out.println("rotate fish down left");
        while(faces[0].getFacelet(2,0)%10 != 0){
            upLayerClockwise();
        }
    }
    // step 6 - solve the yellow face
    public void solveYellowFace(){
        System.out.println("solve yellow face");
        String[] solveTopFace = new String[]{"R","U","R'","U","R","U2","R'"};
        while(!faceCompleted(0)) {
            System.out.println("face not completed");
            if (numYellowCornerFacelets() == 0 || numYellowCornerFacelets() == 2) {
                System.out.println("not fish");
                rotateTopLayerTillYellowInRightSpot();
                applyRotations(solveTopFace);
            }
            else if (numYellowCornerFacelets() == 1) {
                System.out.println("fish");
                rotateFishDownLeft();
                applyRotations(solveTopFace);
            }
        }
    }


    public Boolean isCornersMatching(int face){
        System.out.println("is corners matching");
        return faces[face].getFacelet(0, 0)%10 == faces[face].getFacelet(0, 2)%10;
    }
    public void rotateTillLeftFaceCornersMatch(){
        System.out.println("rotate Till Left Face Corners Match");
        while(!isCornersMatching(1)){
            rotateCubeLeft();
        }
    }
    public void rotateTopLayerTillCornerPiecesAreInTheRightPlace(){
        System.out.println("rotate Top Layer Till Corner Pieces Are In The Right Place");
        while(faces[2].getFacelet(0,0)%10 != faces[2].getFacelet(1,0)%10){
            upLayerClockwise();
        }

    }
    // step 7 - position the corners
    public void positionTheCorners(){
        System.out.println("position corners");
        String[] positionCorners = new String[]{"L'","U","R","U'","L","U","R'","R","U","R'","U","R","U2","R'"};
        // two cases:
        // no side faces have matching corners -> perform algorithm, rotate cube till that face is in left, then perform algorithm
        if(!isCornersMatching(1) && !isCornersMatching(2) && !isCornersMatching(3) && !isCornersMatching(5)){
            System.out.println("no matching corners");
            applyRotations(positionCorners);
            rotateTillLeftFaceCornersMatch();
            applyRotations(positionCorners);
        }
        // one of the side faces has matching corners -> rotate cube till that face is in left, then perform algorithm
        else{
            rotateTillLeftFaceCornersMatch();
            applyRotations(positionCorners);
        }
        rotateTopLayerTillCornerPiecesAreInTheRightPlace();
    }


    public Boolean shouldCycleClockWise(){
        if((faces[3].getFacelet(0,1)%10 == faces[2].getFacelet(0,1)%10) ||
                (faces[2].getFacelet(0,1)%10 == faces[1].getFacelet(0,1)%10) ||
                (faces[1].getFacelet(0,1)%10 == faces[5].getFacelet(0,1)%10) ||
                (faces[5].getFacelet(0,1)%10 == faces[3].getFacelet(0,1)%10)){
            System.out.println("should cycle clockwise");
            return true;
        }
        System.out.println("shouldn't cycle clockwise");
        return false;
    }
    public void turnCompletedFaceAway(){
        System.out.println("turn completed face away");
        while(!faceCompleted(5)){
            rotateCubeLeft();
        }
    }
    // Step 8 - position the edges
    public void cycleTopLayerEdges(){
        System.out.println("cycle top layer edges");
        String[] clockWise = new String[]{"F2","U","R'","L","F2","L'","R", "U", "F2"};
        String[] counterClockWise = new String[]{"F2","U'","R'","L","F2","L'","R", "U'", "F2"};
        // three cases:
        // none of the side faces solved -> CCW, turn completed face away then CCW again
        if(!faceCompleted(1) && !faceCompleted(2) && !faceCompleted(3) && !faceCompleted(5)){
            System.out.println("none of the faces completed");
            applyRotations(counterClockWise);
            System.out.println("one of the faces completed");
            turnCompletedFaceAway();
            System.out.println("completed face turned away, cycle edges counterclockwise");
            if(shouldCycleClockWise()){
                turnCompletedFaceAway();
                System.out.println("clockwise");
                applyRotations(clockWise);
            }
            // one of the faces solved other top layer edge pieces need to cycle CCW -> cycle CCW
            else{
                turnCompletedFaceAway();
                System.out.println("counter clockwise");
                while(!isCubeSolved()){
                    applyRotations(counterClockWise);
                }
            }
            System.out.println("cube completed");
        }
        // one of the faces solved other top layer edge pieces need to cycle CW -> cycle CW
        else if(shouldCycleClockWise()){
            turnCompletedFaceAway();
            System.out.println("clockwise");
            applyRotations(clockWise);
        }
        // one of the faces solved other top layer edge pieces need to cycle CCW -> cycle CCW
        else{
            turnCompletedFaceAway();
            System.out.println("counter clockwise");
            while(!isCubeSolved()){
                applyRotations(counterClockWise);
            }
        }
    }




    public static void main(String[] args) {
        Cube cube = new Cube();
        /*
        HashMap<String, Runnable> map = new HashMap<>();
        map.put("F2", () -> {
            cube.frontLayerClockwise();
            cube.frontLayerClockwise();
        });
         */
        /*
        cube.faces[0].setFacelets(new int[][]{{00,10,20},{30,40,50},{60,70,80}});
        cube.faces[1].setFacelets(new int[][]{{01,11,21},{31,41,51},{61,71,81}});
        cube.faces[2].setFacelets(new int[][]{{02,12,22},{32,42,52},{62,72,82}});
        cube.faces[3].setFacelets(new int[][]{{03,13,23},{33,43,53},{63,73,83}});
        cube.faces[4].setFacelets(new int[][]{{04,14,24},{34,44,54},{64,74,84}});
        cube.faces[5].setFacelets(new int[][]{{05,15,25},{35,45,55},{65,75,85}});
         */
        cube.faces[0].setFacelets(new int[][]{  {12,20,35},
                                                {40,50,60},
                                                {70,80,91}});

        cube.faces[1].setFacelets(new int[][]{  {11,22,32},
                                                {41,51,61},
                                                {71,81,91}});

        cube.faces[2].setFacelets(new int[][]{  {13,21,30},
                                                {42,52,62},
                                                {72,82,92}});

        cube.faces[3].setFacelets(new int[][]{  {15,25,30},
                                                {43,53,63},
                                                {73,83,93}});

        cube.faces[4].setFacelets(new int[][]{  {14,24,34},
                                                {44,54,64},
                                                {74,84,94}});

        cube.faces[5].setFacelets(new int[][]{  {13,23,30},
                                                {45,55,65},
                                                {75,85,95}});
        cube.printCube();
        cube.solveYellowFace();
        cube.printCube();
        cube.positionTheCorners();
        cube.printCube();
        cube.cycleTopLayerEdges();
        cube.printCube();
    }
}

