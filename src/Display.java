import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Display {

    // JFrame BorderLayout Example
    public static void border() {
        // 1. Create the frame (the window that will pop up)
        JFrame frame = new JFrame("Cube-Solver");
        // This line is optional, because BorderLayout is the default
        // layouts for JFrames
//        frame.setLayout(new BorderLayout());

        // 2. Choose what happens when you click the exit button
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 3. Create components and put them in the frame
        JLabel instructions = new JLabel("set up the cube configuration (what color each facelet is)" +
                " by clicking each button and choosing a color");
        frame.add(instructions, BorderLayout.NORTH);

        JButton button2 = new JButton("Solve Cube");
        frame.add(button2, BorderLayout.SOUTH);

        JLabel rotations = new JLabel("rotations to solve the cube");
        frame.add(rotations, BorderLayout.WEST);

        JLabel rotationControl = new JLabel("Rotation control");
        frame.add(rotationControl, BorderLayout.EAST);


        JPanel cubePanel = new JPanel();
        cubePanel.setLayout(new BoxLayout(cubePanel, BoxLayout.Y_AXIS));
        FacePanel up = new FacePanel("up");
        cubePanel.add(up);

        JPanel leftForwardRight = new JPanel();
        leftForwardRight.setLayout(new BoxLayout(leftForwardRight, BoxLayout.X_AXIS));
        FacePanel left = new FacePanel("left");
        FacePanel forward = new FacePanel("forward");
        FacePanel right = new FacePanel("right");
        leftForwardRight.add(left);
        leftForwardRight.add(forward);
        leftForwardRight.add(right);
        cubePanel.add(leftForwardRight);

        FacePanel bottom = new FacePanel("bottom");
        cubePanel.add(bottom);
        FacePanel behind = new FacePanel("behind");
        cubePanel.add(behind);

        frame.add(cubePanel, BorderLayout.CENTER);

        // 4. Size the frame
        frame.setSize(550, 150);

        // 5. Show the frame
        frame.setVisible(true);
    }



    public static void main(String[] args) {
        border();
    }

}