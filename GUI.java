import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame implements ActionListener{
    private final JButton[] buttons = new JButton[Board.TOTAL_POSITIONS];
    private final Controller controller;

    public GUI(Controller controller) {
        this.controller = controller;
        setName("Tic-Tac-Toe");
        setSize(300, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3));
        setButtons();
    }

    public void setButtons() {
        for (int i = 0; i < Board.TOTAL_POSITIONS; i++) {
            JButton btn = new JButton();
            btn.setFocusable(false);
            btn.setFont(new Font("Arial", Font.BOLD, 60));
            btn.setActionCommand(Integer.toString(i));
            btn.addActionListener(this);
            buttons[i] = btn;
            add(btn);
        }
    }

    public void displayMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void updateBoard(Cell[] marks) {
        for (int i = 0; i < Board.TOTAL_POSITIONS; i++) {
            switch (marks[i]) {
                case CROSS -> buttons[i].setText("X");
                case NOUGHT -> buttons[i].setText("O");
                case EMPTY -> buttons[i].setText("");
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int position = Integer.parseInt(e.getActionCommand());
        controller.handleBoardClick(position);
    }
}
