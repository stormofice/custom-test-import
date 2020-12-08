import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ConfigDialog extends JDialog {

    private static final long serialVersionUID = -4904339877368892275L;
    private final JPanel contentPanel = new JPanel();

    public ConfigDialog(boolean fS) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("Couldn't set system look&feel, fallback");
        }
        setTitle("Config");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBounds(100, 100, 324, 220);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        FlowLayout fl_contentPanel = new FlowLayout(FlowLayout.CENTER, 1000, 5);
        contentPanel.setLayout(fl_contentPanel);

        JLabel staticFileChooser = new JLabel("Base folder");
        staticFileChooser.setFont(new Font("Courier New", Font.PLAIN, 18));
        contentPanel.add(staticFileChooser);

        JButton fileChooserButton = new JButton("Choose base folder");
        fileChooserButton.addActionListener(e -> {

            JFileChooser jfc = new JFileChooser();
            jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            jfc.setMultiSelectionEnabled(false);

            int result = jfc.showDialog(this, "Choose");
            if (result == JFileChooser.APPROVE_OPTION) {
                Main.INSTANCE.configHandler.setBaseFolder(jfc.getSelectedFile());
                fileChooserButton.setText(jfc.getSelectedFile().getName());
            }

        });
        fileChooserButton.setFont(new Font("Courier New", Font.PLAIN, 18));
        contentPanel.add(fileChooserButton);

        JLabel staticImportChooser = new JLabel("Import type");
        staticImportChooser.setFont(new Font("Courier New", Font.PLAIN, 18));
        contentPanel.add(staticImportChooser);

        JComboBox importBox = new JComboBox(new String[]{"BASE_FOLDER", "SEARCH_PUBLIC_TEST", "SEARCH_MAIN_FILE"});
        importBox.setSelectedItem(Main.INSTANCE.configHandler.getImportType().toString());
        importBox.addPropertyChangeListener(evt -> {
            Main.INSTANCE.configHandler.setImportType(ImportType.valueOf((String) importBox.getSelectedItem()));
        });
        importBox.setFont(new Font("Courier New", Font.PLAIN, 18));
        contentPanel.add(importBox);

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Courier New", Font.PLAIN, 14));
        cancelButton.addActionListener(e -> {
            dispose();
        });
        cancelButton.setActionCommand("Cancel");
        if (!fS)
            buttonPane.add(cancelButton);
        getRootPane().setDefaultButton(cancelButton);

        JButton saveButton = new JButton("Save");
        saveButton.setFont(new Font("Courier New", Font.PLAIN, 14));
        saveButton.addActionListener(e -> {
            Main.INSTANCE.configHandler.save();
            dispose();
            if (fS)
                Main.INSTANCE.mainFrame = new MainFrame();
        });
        saveButton.setActionCommand("Save");
        buttonPane.add(saveButton);

        setLocationRelativeTo(null);
        setVisible(true);

    }

}
