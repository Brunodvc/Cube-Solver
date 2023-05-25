import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FacePanel extends JPanel {
    private JLabel label;
    private JPanel topRow, middleRow, bottomRow;
    private JButton[][] buttons;
    private static Color[] numToColor = {Color.YELLOW, Color.GREEN, Color.ORANGE, Color.BLUE, Color.WHITE, Color.RED};
    private static ActionListener listener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton btn = (JButton) e.getSource();
            Color clr = btn.getBackground();
            if (clr.equals(Color.RED)) {
                btn.setBackground(Color.BLUE);
            }
            if (clr.equals(Color.BLUE)) {
                btn.setBackground(Color.GREEN);
            }
            if (clr.equals(Color.GREEN)) {
                btn.setBackground(Color.YELLOW);
            }
            if (clr.equals(Color.YELLOW)) {
                btn.setBackground(Color.ORANGE);
            }
            if (clr.equals(Color.ORANGE)) {
                btn.setBackground(Color.WHITE);
            }
            if (clr.equals(Color.WHITE)) {
                btn.setBackground(Color.RED);
            }

        }
    };

    private void setPanelColor(JPanel panel) {
        for (Component c : panel.getComponents()) {
            JButton btn = (JButton) c;
            btn.setBackground(Color.RED);
        }
    }

    public void update(int[][] face) {
        System.out.println("facePanel updated");
        for (int r = 0; r < face.length; r++) {
            for (int c = 0; c < face[0].length; c++) {
//                System.out.println("face num: " + face[r][c] + ", color: " + numToColor[face[r][c]]);
                buttons[r][c].setBackground(numToColor[face[r][c]]);
                buttons[r][c].setForeground(numToColor[face[r][c]]);
                buttons[r][c].updateUI();
            }
        }
        topRow.updateUI();
        middleRow.updateUI();
        bottomRow.updateUI();
        updateUI();
    }

    public FacePanel(String labelStr, int[][] face) {
        super();
//        JPanel facePanel = new JPanel();
//        facePanel.
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        label = new JLabel(labelStr);
        add(label);

// top row
        topRow = new JPanel();
        topRow.setLayout(new BoxLayout(topRow, BoxLayout.X_AXIS));
//        JButton topleft = new JButton("" + face[0][0]);
//        JButton topmiddle = new JButton("" + face[0][1]);
//        JButton topright = new JButton("" + face[0][2]);
//        topRow.add(topleft);
//        topRow.add(topmiddle);
//        topRow.add(topright);
//middle row
        middleRow = new JPanel();
        middleRow.setLayout(new BoxLayout(middleRow, BoxLayout.X_AXIS));
//        JButton middleleft = new JButton("" + face[1][0]);
//        JButton middlemiddle = new JButton("" + face[1][1]);
//        JButton middleright = new JButton("" + face[1][2]);
//        middleRow.add(middleleft);
//        middleRow.add(middlemiddle);
//        middleRow.add(middleright);
//bottom row
        bottomRow = new JPanel();
        bottomRow.setLayout(new BoxLayout(bottomRow, BoxLayout.X_AXIS));
//        JButton bottomleft = new JButton("" + face[2][0]);
//        JButton bottommiddle = new JButton("" + face[2][1]);
//        JButton bottomright = new JButton("" + face[2][2]);
//        bottomRow.add(bottomleft);
//        bottomRow.add(bottommiddle);
//        bottomRow.add(bottomright);
        buttons = new JButton[3][3];
        for (int r = 0; r < face.length; r++) {
            for (int c = 0; c < face[0].length; c++) {
                JButton button = new JButton();
                button.setBackground(numToColor[face[r][c]]);

                if (r == 0 ) {
                    topRow.add(button);
                } else if (r == 1) {
                    middleRow.add(button);
                } else {
                    bottomRow.add(button);
                }
                buttons[r][c] = button;
            }
        }

        JPanel facelets = new JPanel();
        facelets.setLayout(new BoxLayout(facelets, BoxLayout.Y_AXIS));

        facelets.add(topRow);
        facelets.add(middleRow);
        facelets.add(bottomRow);
        setPanelColor(topRow);
        setPanelColor(middleRow);
        setPanelColor(bottomRow);

        add(facelets);
    }

}
