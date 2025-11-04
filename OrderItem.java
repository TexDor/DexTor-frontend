import javax.swing.*;
import java.awt.*;

public class OrderItem extends JPanel {
    String FName, LName;

    OrderItem(String fName, String lName, Color c, Font f, Font f2, JButton b) {
        FName = fName;
        LName = lName;
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        setPreferredSize(new Dimension(840, 50));
        setBackground(c);

        // Displays first name and last initial.
        JLabel nameLbl = new JLabel(fName + " " + lName.toCharArray()[0] + ".  ");
        nameLbl.setFont(f);
        add(nameLbl);

        // Displays a limited preview of the items in this order
        JLabel itemLbl = new JLabel("2xitem15, 1xitem40, 3xitem3, 5xitem2, 1xitem33, 2xitem30"); // Placeholder
        itemLbl.setPreferredSize(new Dimension(400, 25));
        itemLbl.setFont(f2);
        itemLbl.setForeground(Color.darkGray);
        add(itemLbl);

        add(b);
        setVisible(true);
    }
}
