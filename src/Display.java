import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Display implements ActionListener{

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
        Cube cube = new Cube();
        JLabel instructions = new JLabel("set up the cube configuration (what color each facelet is)" +
                " by clicking each button and choosing a color");
        frame.add(instructions, BorderLayout.NORTH);

        JButton button2 = new JButton("Solve Cube");
        frame.add(button2, BorderLayout.SOUTH);

        JLabel rotations = new JLabel("rotations to solve the cube");
        frame.add(rotations, BorderLayout.WEST);

        JPanel controlPanel = new JPanel();
        JLabel label = new JLabel("Rotation control");
        controlPanel.add(label);
        JButton upLayerClockwise = new JButton("upLayerClockwise");
        //upLayerClockwise.addActionListener(this);
        controlPanel.add(upLayerClockwise);
        frame.add(controlPanel, BorderLayout.EAST);


        JPanel cubePanel = new JPanel();
        cubePanel.setLayout(new BoxLayout(cubePanel, BoxLayout.Y_AXIS));
        FacePanel up = new FacePanel("up", cube.faces[0].facelets);
        cubePanel.add(up);

        JPanel leftForwardRightBack = new JPanel();
        leftForwardRightBack.setLayout(new BoxLayout(leftForwardRightBack, BoxLayout.X_AXIS));
        FacePanel left = new FacePanel("left",cube.faces[1].facelets);
        FacePanel forward = new FacePanel("forward",cube.faces[2].facelets);
        FacePanel right = new FacePanel("right",cube.faces[3].facelets);
        FacePanel behind = new FacePanel("behind",cube.faces[5].facelets);
        leftForwardRightBack.add(left);
        leftForwardRightBack.add(forward);
        leftForwardRightBack.add(right);
        leftForwardRightBack.add(behind);
        cubePanel.add(leftForwardRightBack);

        FacePanel bottom = new FacePanel("bottom",cube.faces[4].facelets);
        cubePanel.add(bottom);


        frame.add(cubePanel, BorderLayout.CENTER);

        // 4. Size the frame
        frame.setSize(550, 150);

        // 5. Show the frame
        frame.setVisible(true);
    }



    public static void main(String[] args) {
        border();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}