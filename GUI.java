import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class GUI extends JFrame {
  private JTextField xDataField;

  GUI(ANNAgent annAgent) {
    super(annAgent.getLocalName());

    JPanel p = new JPanel();
    p.setLayout(new GridLayout(3, 2));

    p.add(new JLabel("Pixels separated by commas:"));
    xDataField = new JTextField("", 50);
    p.add(xDataField);
    getContentPane().add(p, BorderLayout.CENTER);

    JButton addButton = new JButton("Predict");
    addButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ev) {
        try {
          String xData = xDataField.getText().trim();
          annAgent.predict(xData);
          xDataField.setText("");
        } catch (Exception e) {
          System.out.println(e.getMessage());
        }
      }
    });
    p = new JPanel();
    p.add(addButton);
    getContentPane().add(p, BorderLayout.SOUTH);

    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        annAgent.doDelete();
      }
    });

    setResizable(false);
  }

  public void showGui() {
    pack();
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int centerX = (int) screenSize.getWidth() / 2;
    int centerY = (int) screenSize.getHeight() / 2;
    setLocation(centerX - getWidth() / 2, centerY - getHeight() / 2);
    super.setVisible(true);
  }
}