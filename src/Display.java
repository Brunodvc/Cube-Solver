import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Display {
    private Cube cube;
    public FacePanel up, left, right, bottom, forward, behind;

    // JFrame BorderLayout Example
    public Display() {
        // 1. Create the frame (the window that will pop up)
        JFrame frame = new JFrame("Cube-Solver");
        // This line is optional, because BorderLayout is the default
        // layouts for JFrames
//        frame.setLayout(new BorderLayout());

        // 2. Choose what happens when you click the exit button
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 3. Create components and put them in the frame
       // cube = new Cube(this);
        JLabel instructions = new JLabel("set up the cube configuration (what color each facelet is)" +
                " by clicking each button and choosing a color");
        frame.add(instructions, BorderLayout.NORTH);

        JButton button2 = new JButton("Solve Cube");
        frame.add(button2, BorderLayout.SOUTH);

        JLabel rotations = new JLabel("rotations to solve the cube");
        frame.add(rotations, BorderLayout.WEST);

        ActionListener listen = e -> {
            if (e.getActionCommand().equals("upLayerClockwise")) {
                cube.upLayerClockwise();
            }
        };

        JPanel controlPanel = new JPanel();
        JLabel label = new JLabel("Rotation control");
        controlPanel.add(label);
        JButton upLayerClockwise = new JButton("upLayerClockwise");
        upLayerClockwise.addActionListener(listen);
        //upLayerClockwise.addActionListener(this);
        controlPanel.add(upLayerClockwise);
        frame.add(controlPanel, BorderLayout.EAST);


        JPanel cubePanel = new JPanel();
        cubePanel.setLayout(new BoxLayout(cubePanel, BoxLayout.Y_AXIS));
        up = new FacePanel("up", cube.faces[0].facelets);
        cubePanel.add(up);

        JPanel leftForwardRightBack = new JPanel();
        leftForwardRightBack.setLayout(new BoxLayout(leftForwardRightBack, BoxLayout.X_AXIS));
        left = new FacePanel("left",cube.faces[1].facelets);
        forward = new FacePanel("forward",cube.faces[2].facelets);
        right = new FacePanel("right",cube.faces[3].facelets);
        behind = new FacePanel("behind",cube.faces[5].facelets);
        leftForwardRightBack.add(left);
        leftForwardRightBack.add(forward);
        leftForwardRightBack.add(right);
        leftForwardRightBack.add(behind);
        cubePanel.add(leftForwardRightBack);

        bottom = new FacePanel("bottom",cube.faces[4].facelets);
        cubePanel.add(bottom);

        cube.printCube();
        frame.add(cubePanel, BorderLayout.CENTER);

        // 4. Size the frame
        frame.setSize(550, 150);

        // 5. Show the frame
        frame.setVisible(true);
    }



    public static void main(String[] args) {
        new Display();

    }
}