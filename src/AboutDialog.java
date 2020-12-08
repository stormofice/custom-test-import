import java.awt.*;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.net.URL;

public class AboutDialog extends JDialog {

    private static final long serialVersionUID = 5064838844825981988L;
    private final JPanel contentPanel = new JPanel();

    public AboutDialog() {
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setTitle("About");
        setBounds(100, 100, 450, 253);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(0, 0));

        JLabel textLabel = new JLabel("<html>custom test importer<br>v1<br><br> Made by ea8dbc5b07#4557</html>");
        textLabel.setFont(new Font("Courier New", Font.BOLD, 24));
        contentPanel.add(textLabel, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        contentPanel.add(panel, BorderLayout.SOUTH);
        panel.setLayout(new BorderLayout(0, 0));

        JButton repoButton = new JButton("Repository");
        repoButton.addActionListener(e -> {
            try {
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().browse(new URL("https://github.com/stormofice/custom-test-import").toURI());
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        repoButton.setForeground(new Color(0, 153, 255));
        repoButton.setFont(new Font("Courier New", Font.BOLD, 18));
        panel.add(repoButton, BorderLayout.WEST);

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> dispose());
        closeButton.setFont(new Font("Courier New", Font.BOLD, 18));
        panel.add(closeButton, BorderLayout.EAST);

        setLocationRelativeTo(null);
        setVisible(true);
    }

}
