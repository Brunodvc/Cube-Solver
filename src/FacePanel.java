import javax.swing.*;

public class FacePanel extends JPanel {
    private JLabel label;

    public FacePanel(String labelStr) {
        JPanel facePanel = new JPanel();
        facePanel.setLayout(new BoxLayout(facePanel, BoxLayout.Y_AXIS));

        label = new JLabel(labelStr);
        facePanel.add(label);

// top row
        JPanel topRow = new JPanel();
        topRow.setLayout(new BoxLayout(topRow, BoxLayout.X_AXIS));
        JButton topleft = new JButton("top left");
        JButton topmiddle = new JButton("top middle");
        JButton topright = new JButton("top right");
        topRow.add(topleft);
        topRow.add(topmiddle);
        topRow.add(topright);
//middle row
        JPanel middleRow = new JPanel();
        middleRow.setLayout(new BoxLayout(middleRow, BoxLayout.X_AXIS));
        JButton middleleft = new JButton("middle left");
        JButton middlemiddle = new JButton("middle middle");
        JButton middleright = new JButton("middle right");
        middleRow.add(middleleft);
        middleRow.add(middlemiddle);
        middleRow.add(middleright);
//bottom row
        JPanel bottomRow = new JPanel();
        bottomRow.setLayout(new BoxLayout(bottomRow, BoxLayout.X_AXIS));
        JButton bottomleft = new JButton("bottom left");
        JButton bottommiddle = new JButton("bottom middle");
        JButton bottomright = new JButton("bottom right");
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
