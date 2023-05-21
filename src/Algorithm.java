public class Algorithm {
    // solving for pieces, not stickers
    // solve each layer individually
    // solving the cube can be broken down into 8 steps
    // step 1 - making the daisy
    // step 2 - create white cross
        // rotate white edge piece until side facelet matches a center-piece
        // rotate that whole side twice
        // do that for every white petal of the daisy
        // when this is finished the white cross will remain facing down for the rest of the solve
    // step 3 - solve the bottom layer
        // find the white facelet on any side face, top corner, and rotate that layer until the adjacent color matches a center
        // if its to the left of the center, perform the left trigger
        // if its to the right of the center, perform the right trigger
        // edge case 1: white facelet is adjacent to the white face
        // edge case 2: white facelet is on the top face
    // step 4
    // step 5
    // step 6
    // step 7
    // step 8 - swapping the top layer edge pieces
        // face the completed side face away from you
        // check if the side pieces need to be rotated clockwise or counterclockwise
        //  clockwise - F2, U, R', L, F2, L', R, U, F2
        // counterClockwise - F2, U', R', L, F2, L', R, U', F2



    //edit
    public static void main(String[] args) {
        Cube cube = new Cube();
        cube.faces[0].setFacelets(new int[][]{{0,0,0},{0,0,0},{0,0,0}});
        cube.faces[1].setFacelets(new int[][]{{1,3,1},{1,1,1},{1,1,1}});
        cube.faces[2].setFacelets(new int[][]{{2,2,2},{2,2,2},{2,2,2}});
        cube.faces[3].setFacelets(new int[][]{{3,5,3},{3,3,3},{3,3,3}});
        cube.faces[4].setFacelets(new int[][]{{4,4,4},{4,4,4},{4,4,4}});
        cube.faces[5].setFacelets(new int[][]{{5,1,5},{5,5,5},{5,5,5}});
        cube.printCube();

    }
}
