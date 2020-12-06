package myCode;


import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ArtistUI extends JPanel {

    private JTextField nameField = new JTextField(45);
    private JTextField genreField = new JTextField(45);
    private JTextField descriptionField = new JTextField(145);
    private JTextField discographyField = new JTextField(145);

    private JButton createButton = new JButton("New...");
    private JButton updateButton = new JButton("Update");
    private JButton deleteButton = new JButton("Delete");
    private JButton firstButton = new JButton("First");
    private JButton prevButton = new JButton("Previous");
    private JButton nextButton = new JButton("Next");
    private JButton lastButton = new JButton("Last");
    //... updateButton, deleteButton, firstButton, prevButton, nextButton,
    //...lastButton
    private artistController bean = new artistController();
    
    public ArtistUI() {
        setBorder(new TitledBorder
                (new EtchedBorder(),"Artist Details"));
        setLayout(new BorderLayout(5, 5));
        add(initFields(), BorderLayout.NORTH);
        add(initButtons(), BorderLayout.CENTER);
        setFieldData(bean.moveFirst());
    }

    private JPanel initButtons() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 3, 3));
        panel.add(createButton);
        createButton.addActionListener( new ButtonHandler());
        panel.add(updateButton);
        updateButton.addActionListener(new ButtonHandler());
        panel.add(deleteButton);
        deleteButton.addActionListener(new ButtonHandler());
        panel.add(firstButton);
        firstButton.addActionListener(new ButtonHandler());
        panel.add(prevButton);
        prevButton.addActionListener(new ButtonHandler());
        panel.add(nextButton);
        nextButton.addActionListener(new ButtonHandler());
        panel.add(lastButton);
        lastButton.addActionListener(new ButtonHandler());
        return panel;
    }

    private JPanel initFields() {
        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout());
        panel.add(new JLabel("Name"), "align label");
        panel.add(nameField, "wrap");
        //nameField.setEnabled(false);
        panel.add(new JLabel("Genre"), "align label");
        panel.add(genreField, "wrap");
        panel.add(new JLabel("Description"), "align label");
        panel.add(descriptionField, "wrap");
        panel.add(new JLabel("Discography"), "align label");
        panel.add(discographyField, "wrap");
        return panel;
    }

    private artists getFieldData() {
        artists p = new artists();
        p.setName(nameField.getText());
        p.setGenre(genreField.getText());
        p.setDescription(descriptionField.getText());
        p.setDiscography(discographyField.getText());
        return p;
    }

    private void setFieldData(artists p) {
        nameField.setText(String.valueOf(p.getName()));
        genreField.setText(p.getGenre());
        descriptionField.setText(p.getDescription());
        discographyField.setText(p.getDiscography());
    }

    private boolean isEmptyFieldData() {
        return (nameField.getText().trim().isEmpty()
                && genreField.getText().trim().isEmpty()
                && descriptionField.getText().trim().isEmpty()
                && discographyField.getText().trim().isEmpty());
    }

    private class ButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            artists p = getFieldData();
            switch (e.getActionCommand()) {
                case "Save" -> {
                    if (isEmptyFieldData()) {
                        JOptionPane.showMessageDialog(null,
                                "Cannot create an empty record");
                        return;
                    }
                    if (bean.create(p) != null)
                        JOptionPane.showMessageDialog(null,
                                "New artists created successfully.");
                    createButton.setText("New...");
                }
                case "New..." -> {
                    p.setName("");
                    p.setGenre("");
                    p.setDescription("");
                    p.setDiscography("");
                    setFieldData(p);
                    createButton.setText("Save");
                }
                case "Update" -> {
                    if (isEmptyFieldData()) {
                        JOptionPane.showMessageDialog(null,
                                "Cannot update an empty record");
                        return;
                    }
                    if (bean.update(p) != null)
                        JOptionPane.showMessageDialog(
                                null, "The artist  :" + String.valueOf(p.getName()
                                        + " was updated successfully"));
                }
                case "Delete" -> {
                    if (isEmptyFieldData()) {
                        JOptionPane.showMessageDialog(null,
                                "Cannot delete an empty record");
                        return;
                    }
                    p = bean.getCurrent();
                    bean.delete();
                    JOptionPane.showMessageDialog(
                            null, "The artists  :"
                                    + String.valueOf(p.getName()
                                    + " was deleted successfully"));
                }
                case "First" -> setFieldData(bean.moveFirst());
                case "Previous" -> setFieldData(bean.movePrevious());
                case "Next" -> setFieldData(bean.moveNext());
                case "Last" -> setFieldData(bean.moveLast());
                default -> JOptionPane.showMessageDialog(null,
                        "invalid command");
            }
        }
    }
}
