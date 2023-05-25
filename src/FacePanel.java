import javax.swing.*;

public class FacePanel extends JPanel {
    private JLabel label;

    public FacePanel(String labelStr, int[][] face) {
        JPanel facePanel = new JPanel();
        facePanel.setLayout(new BoxLayout(facePanel, BoxLayout.Y_AXIS));

        label = new JLabel(labelStr);
        facePanel.add(label);

// top row
        JPanel topRow = new JPanel();
        topRow.setLayout(new BoxLayout(topRow, BoxLayout.X_AXIS));
        JButton topleft = new JButton("" + face[0][0]);
        JButton topmiddle = new JButton("" + face[0][1]);
        JButton topright = new JButton("" + face[0][2]);
        topRow.add(topleft);
        topRow.add(topmiddle);
        topRow.add(topright);
//middle row
        JPanel middleRow = new JPanel();
        middleRow.setLayout(new BoxLayout(middleRow, BoxLayout.X_AXIS));
        JButton middleleft = new JButton("" + face[1][0]);
        JButton middlemiddle = new JButton("" + face[1][1]);
        JButton middleright = new JButton("" + face[1][2]);
        middleRow.add(middleleft);
        middleRow.add(middlemiddle);
        middleRow.add(middleright);
//bottom row
        JPanel bottomRow = new JPanel();
        bottomRow.setLayout(new BoxLayout(bottomRow, BoxLayout.X_AXIS));
        JButton bottomleft = new JButton("" + face[2][0]);
        JButton bottommiddle = new JButton("" + face[2][1]);
        JButton bottomright = new JButton("" + face[2][2]);
        bottomRow.add(bottomleft);
        bottomRow.add(bottommiddle);
        bottomRow.add(bottomright);

        JPanel facelets = new JPanel();
        facelets.setLayout(new BoxLayout(facelets, BoxLayout.Y_AXIS));

        facelets.add(topRow);
        facelets.add(middleRow);
        facelets.add(bottomRow);

        facePanel.add(facelets);
        this.add(facePanel);

    }

}
