import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import java.io.File;
import javax.swing.border.LineBorder;

public class MainFrame extends JFrame {


    private static final long serialVersionUID = -8424734531682214227L;
    private JPanel contentPane;

    private DefaultListModel<String> dlm;

    public MainFrame() {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("Couldn't set system look&feel, fallback");
        }

        dlm = new DefaultListModel<>();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 350, 600);
        setLocationRelativeTo(null);

        setTitle("Custom test importer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu mainMenu = new JMenu("Main");
        mainMenu.setFont(new Font("Courier New", Font.PLAIN, 14));
        menuBar.add(mainMenu);

        JMenuItem setConfigItem = new JMenuItem("Set config");
        setConfigItem.addActionListener(e -> {
            new ConfigDialog(false);
        });
        setConfigItem.setFont(new Font("Courier New", Font.PLAIN, 14));
        mainMenu.add(setConfigItem);

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> {
            Main.INSTANCE.configHandler.save();
            dispose();
        });

        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.addActionListener(e -> {
        });
        aboutItem.setFont(new Font("Courier New", Font.PLAIN, 14));
        mainMenu.add(aboutItem);
        exitItem.setFont(new Font("Courier New", Font.PLAIN, 14));
        mainMenu.add(exitItem);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JPanel panel = new JPanel();
        panel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        contentPane.add(panel, BorderLayout.SOUTH);
        panel.setLayout(new BorderLayout(0, 0));


        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(e -> {
            dlm.clear();
            for (String s : Main.INSTANCE.networkHandler.refresh()) {
                dlm.addElement(s);
            }
        });
        refreshButton.setFont(new Font("Courier New", Font.PLAIN, 14));
        panel.add(refreshButton, BorderLayout.WEST);

        JLabel statusLabel = new JLabel("Status");
        statusLabel.setFont(new Font("Courier New", Font.PLAIN, 14));
        statusLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(statusLabel, BorderLayout.CENTER);

        JList list = new JList(dlm);
        list.setFont(new Font("Courier New", Font.PLAIN, 18));
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        contentPane.add(list, BorderLayout.CENTER);
        JButton importButton = new JButton("Import");
        importButton.addActionListener(e -> {
            if (list.getSelectedIndex() != -1) {
                File out = Main.INSTANCE.networkHandler.downloadExercise(dlm.getElementAt(list.getSelectedIndex()));
                Main.INSTANCE.importHandler.importCustomTest(out);
                statusLabel.setText("[Y] " + out.getName());
            }
        });
        importButton.setFont(new Font("Courier New", Font.PLAIN, 14));
        panel.add(importButton, BorderLayout.EAST);
        dlm.clear();
        for (String s : Main.INSTANCE.networkHandler.refresh()) {
            dlm.addElement(s);
        }

        setVisible(true);

    }

}
