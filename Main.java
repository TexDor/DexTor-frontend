import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.stream.IntStream;

public class Main {

    private static Font f1, f2, f3;

    private static final Color bg0 = new Color(200, 215, 250); // Baby Blue
    private static final Color bg1 = new Color(160, 180, 240); // Darker Blue
    private static final Color bg2 = new Color(100, 140, 210); // Darker Blue
    private static final Color fg0 = new Color(40, 50, 130); // Very Dark Blue

    private static final Color bg3 = new Color(230, 190, 250); // Light pink

    public static void main(String[] args) {

        // Font setup
        f1 = new Font("SansSerif", Font.PLAIN, 18);
        f2 = new Font("SansSerif", Font.ITALIC, 16);

        // Window creation
        JFrame frame = new JFrame("TexDor");
        frame.setSize(1440, 810);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        // Background panel
        JPanel bgPane = new JPanel(new BorderLayout());
        bgPane.setBackground(bg0);
        frame.add(bgPane);

        // Inventory Panel
        JPanel invPane = new JPanel(new BorderLayout());
        invPane.setBackground(bg1);
        invPane.setPreferredSize(new Dimension(300, 810));
        bgPane.add(invPane, BorderLayout.LINE_END);
        JTextField invSearch = GetNewTextField(bg1, Color.black);
        JPanel invTopPane = new JPanel(new BorderLayout());
        invTopPane.add(invSearch, BorderLayout.NORTH);
        invTopPane.add(GetNewSeparator(false), BorderLayout.SOUTH);
        invPane.add(invTopPane, BorderLayout.NORTH);
        DefaultListModel<String> invLM = new DefaultListModel<>();
        // Sample code for placeholder items.
        List<InvItem> invItems = IntStream.rangeClosed(1, 50)
                .mapToObj(i -> new InvItem("Item" + i, 10.99f + (i % 5))).toList();
        invItems.stream().map(invItem -> invItem.Name).forEach(invLM::addElement);
        // End sample code
        JList<String> invItemList = new JList<>();
        invItemList.setModel(invLM);
        invItemList.setFont(f2);
        invItemList.setBackground(bg1);
        JScrollPane invSP = new JScrollPane(invItemList);
        invSP.setBorder(BorderFactory.createEmptyBorder());
        invPane.add(invSP, BorderLayout.CENTER);
        invSearch.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String s = invSearch.getText().toLowerCase();
                invLM.clear();
                invItems.stream().filter(i -> i.Name.toLowerCase().contains(s))
                        .map(invItem -> invItem.Name).forEach(invLM::addElement);
            }
        });
        invPane.add(GetNewSeparator(true), BorderLayout.BEFORE_LINE_BEGINS);

        JPanel orderPane = new JPanel();
        orderPane.setLayout(new BoxLayout(orderPane, BoxLayout.Y_AXIS));
        bgPane.add(orderPane, BorderLayout.CENTER);
        // Sample code for placeholder items.
        List<OrderItem> odrItems = IntStream.rangeClosed(1,30).mapToObj(i ->
                new OrderItem("Linda", "Morrow", bg0, f1, f2, GetNewButton(bg3, Color.BLACK, "Fulfill"))).toList();
        odrItems.forEach(orderPane::add);
        // End sample code

        JPanel dlvryPane = new JPanel();
        dlvryPane.setLayout(new BoxLayout(dlvryPane, BoxLayout.Y_AXIS));
        dlvryPane.setBackground(bg1);
        dlvryPane.setPreferredSize(new Dimension(300, 810));
        bgPane.add(dlvryPane, BorderLayout.LINE_START);


        frame.setVisible(true);
        frame.revalidate();
        frame.repaint();
    }

    private static JTextField GetNewTextField(Color bg, Color fg) {
        JTextField tf = new JTextField();
        tf.setBackground(bg);
        tf.setForeground(fg);
        tf.setBorder(BorderFactory.createEmptyBorder());
        tf.setFont(f1);
        return tf;
    }

    private static JButton GetNewButton(Color bg, Color fg, String text) {
        JButton b = new JButton(text);
        b.setBackground(bg);
        b.setForeground(fg);
        b.setFocusPainted(false);
        //b.setBorder(BorderFactory.createEmptyBorder());
        b.setPreferredSize(new Dimension(100, 30));
        return b;
    }

    private static JSeparator GetNewSeparator(boolean vertical) {
        JSeparator sep;
        if (vertical) sep = new JSeparator(SwingConstants.VERTICAL);
        else sep = new JSeparator();
        sep.setBackground(fg0);
        sep.setForeground(bg2);
        return sep;
    }

}