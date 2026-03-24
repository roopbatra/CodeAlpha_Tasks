import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class HotelReservationSystem extends JFrame {

    JTextField nameField, roomField;
    JComboBox<String> typeBox;

    JTable table;
    DefaultTableModel model;

    ArrayList<String> names = new ArrayList<>();
    ArrayList<String> rooms = new ArrayList<>();
    ArrayList<String> types = new ArrayList<>();

    public HotelReservationSystem() {

        setTitle("Hotel Reservation System");
        setSize(700,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel top = new JPanel();

        nameField = new JTextField(10);
        roomField = new JTextField(5);
        typeBox = new JComboBox<>(new String[]{"Standard","Deluxe","Suite"});

        JButton bookBtn = new JButton("Book");

        top.add(new JLabel("Name"));
        top.add(nameField);
        top.add(new JLabel("Room"));
        top.add(roomField);
        top.add(typeBox);
        top.add(bookBtn);

        add(top, BorderLayout.NORTH);

        model = new DefaultTableModel(new String[]{"Name","Room","Type"},0);
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel bottom = new JPanel();

        JButton cancelBtn = new JButton("Cancel");

        bottom.add(cancelBtn);

        add(bottom, BorderLayout.SOUTH);

        bookBtn.addActionListener(e -> {

            String name = nameField.getText();
            String room = roomField.getText();
            String type = typeBox.getSelectedItem().toString();

            if(name.equals("") || room.equals("")) {
                JOptionPane.showMessageDialog(this,"Fill fields");
                return;
            }

            names.add(name);
            rooms.add(room);
            types.add(type);

            model.addRow(new Object[]{name,room,type});

            JOptionPane.showMessageDialog(this,"Booked!");

            nameField.setText("");
            roomField.setText("");
        });

        cancelBtn.addActionListener(e -> {
            int row = table.getSelectedRow();

            if(row != -1) {
                names.remove(row);
                rooms.remove(row);
                types.remove(row);
                model.removeRow(row);
            }
        });
    }

    public static void main(String[] args) {
        new HotelReservationSystem().setVisible(true);
    }
}