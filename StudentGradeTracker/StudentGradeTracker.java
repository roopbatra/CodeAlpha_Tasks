import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class StudentGradeTracker extends JFrame {

    JTextField idField, nameField, marksField;
    JTable table;
    DefaultTableModel model;

    ArrayList<String> ids = new ArrayList<>();
    ArrayList<String> names = new ArrayList<>();
    ArrayList<Integer> marks = new ArrayList<>();

    public StudentGradeTracker() {

        setTitle("Student Grade Tracker");
        setSize(700,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel top = new JPanel();

        idField = new JTextField(5);
        nameField = new JTextField(10);
        marksField = new JTextField(5);

        JButton addBtn = new JButton("Add");

        top.add(new JLabel("ID"));
        top.add(idField);
        top.add(new JLabel("Name"));
        top.add(nameField);
        top.add(new JLabel("Marks"));
        top.add(marksField);
        top.add(addBtn);

        add(top, BorderLayout.NORTH);

        model = new DefaultTableModel(new String[]{"ID","Name","Marks"},0);
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel bottom = new JPanel();

        JButton deleteBtn = new JButton("Delete");
        JButton summaryBtn = new JButton("Summary");

        JLabel avg = new JLabel("Avg: ");
        JLabel high = new JLabel("High: ");
        JLabel low = new JLabel("Low: ");

        bottom.add(deleteBtn);
        bottom.add(summaryBtn);
        bottom.add(avg);
        bottom.add(high);
        bottom.add(low);

        add(bottom, BorderLayout.SOUTH);

        addBtn.addActionListener(e -> {
            try {
                String id = idField.getText();
                String name = nameField.getText();
                int m = Integer.parseInt(marksField.getText());

                ids.add(id);
                names.add(name);
                marks.add(m);

                model.addRow(new Object[]{id,name,m});

                idField.setText("");
                nameField.setText("");
                marksField.setText("");

            } catch(Exception ex) {
                JOptionPane.showMessageDialog(this,"Invalid Input");
            }
        });

        deleteBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if(row != -1) {
                ids.remove(row);
                names.remove(row);
                marks.remove(row);
                model.removeRow(row);
            }
        });

        summaryBtn.addActionListener(e -> {

            if(marks.size() == 0) return;

            int max = marks.get(0);
            int min = marks.get(0);
            int sum = 0;

            for(int m : marks) {
                sum += m;
                if(m > max) max = m;
                if(m < min) min = m;
            }

            avg.setText("Avg: " + (sum/marks.size()));
            high.setText("High: " + max);
            low.setText("Low: " + min);
        });
    }

    public static void main(String[] args) {
        new StudentGradeTracker().setVisible(true);
    }
}