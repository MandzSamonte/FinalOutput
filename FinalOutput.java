import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.applet.Applet;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

class FinalOutput extends JFrame {

    int total = 0;

    public FinalOutput(){
        setVisible(false);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JComboBox cbbProductType = new JComboBox();

        JLabel lblTotal = new JLabel();

        DefaultTableModel tableModel = new DefaultTableModel();
        String[] productColumnIdentifiers = {"Name", "Price" };
        tableModel.setColumnIdentifiers(productColumnIdentifiers);

        DefaultTableModel customerTableModel = new DefaultTableModel();
        String[] recieptColumnIdentifiers = {"Name","Price", "QTY"};
        customerTableModel.setColumnIdentifiers(recieptColumnIdentifiers);

        JTable tblProductList = new JTable(tableModel);
        JTable tblCustomerOrder = new JTable(customerTableModel);

        String[] productType = {"Rice Meals", "Noodles", "Hotdog Sandwich", "Fries and Side", "Chicken", "Burger", "Breakfast", "Beverages and Desert"};
        String[][] productFilePath = {
                {"Rice Meals", "/Users/johnmandysamonte/Library/Preferences/IdeaIC2019.3/scratches/FinalOutput/Products/Rice Meals.txt"},
                {"Noodles", "/Users/johnmandysamonte/Library/Preferences/IdeaIC2019.3/scratches/FinalOutput/Products/Noodles.txt"},
                {"Hotdog Sandwich", "/Users/johnmandysamonte/Library/Preferences/IdeaIC2019.3/scratches/FinalOutput/Products/Hotdog Sandwich.txt"},
                {"Fries and Side", "/Users/johnmandysamonte/Library/Preferences/IdeaIC2019.3/scratches/FinalOutput/Products/Fries and Side.txt"},
                {"Chicken", "/Users/johnmandysamonte/Library/Preferences/IdeaIC2019.3/scratches/FinalOutput/Products/Chicken.txt"},
                {"Burger", "/Users/johnmandysamonte/Library/Preferences/IdeaIC2019.3/scratches/FinalOutput/Products/Burger.txt"},
                {"Breakfast", "/Users/johnmandysamonte/Library/Preferences/IdeaIC2019.3/scratches/FinalOutput/Products/Breakfast.txt"},
                {"Beverages and Desert", "/Users/johnmandysamonte/Library/Preferences/IdeaIC2019.3/scratches/FinalOutput/Products/Beverages and Desert.txt"}
        };

        for (int i = 0; i < productType.length; i++){
            cbbProductType.addItem(productType[i]);
        }

        //All Functions
        cbbProductType.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    for (int i = 0; i < productFilePath.length; i++){
                        if(e.getItem().equals(productFilePath[i][0])){
                            try {
                                tableModel.setRowCount(0);
                                BufferedReader br = new BufferedReader(new FileReader(productFilePath[i][1]));
                                Object[] tableLines = br.lines().toArray();

                                for(int j = 0; j < tableLines.length; j++){
                                    String line = tableLines[j].toString().trim();
                                    String[] dataRow = line.split(",");
                                    tableModel.addRow(dataRow);
                                }
                            } catch (FileNotFoundException ex) {
                                ex.printStackTrace();
                            }

                            break;
                        }
                    }
                }
            }
        });


        tblProductList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

                if(e.getValueIsAdjusting()){
                    customerTableModel.addRow(tableModel.getDataVector().elementAt(tblProductList.getSelectedRow()));

                    total += Integer.parseInt((String)customerTableModel.getValueAt(customerTableModel.getRowCount() - 1, 1));
                    lblTotal.setText(Integer.toString(total));
                    System.out.println("total: " + total);
                }
            }
        });

        //All the Layout and Adding of Components
        GridBagLayout gridBagLayout = new GridBagLayout();
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        String[] tableHeader = {"Code Name", "Price"};
        String[][] data = {
                {"C1", "350"},
                {"C2", "450"}
        };
        setLayout(gridBagLayout);

        JPanel container = new JPanel();
        container.setLayout(new FlowLayout(FlowLayout.LEFT));
        container.add(new JButton("Sample"));
        container.add(lblTotal);


        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(20, 20, 20, 20);
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridx = 0;
        add(cbbProductType, gridBagConstraints);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        add(new JLabel("Sample"), gridBagConstraints);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        add(new JScrollPane(tblProductList), gridBagConstraints);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        add(new JScrollPane(tblCustomerOrder), gridBagConstraints);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        add(container, gridBagConstraints);
    }

    public static class LoginFrame extends JFrame {
        public LoginFrame(){
            setVisible(true);
            setSize(500, 300);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(EXIT_ON_CLOSE);

            //Login Basic Component
            JLabel lblLogin = new JLabel("Login");
            JComboBox cbbLoginType = new JComboBox();
            JButton btnLogin = new JButton("Login");

            //Counter Number Input
            JTextField txfCounterInput = new JTextField();

            //Admim Input
            JTextField txfAdminName = new JTextField();
            JPasswordField txfAdminPass = new JPasswordField();

            cbbLoginType.addItem("Counter");
            cbbLoginType.addItem("Admin");

            cbbLoginType.setPreferredSize(new Dimension(300, 40));
            btnLogin.setPreferredSize(new Dimension(300, 40));

            //Textfields
            txfCounterInput.setPreferredSize(new Dimension(300, 40));
            txfAdminName.setPreferredSize(new Dimension(300, 40));
            txfAdminPass.setPreferredSize(new Dimension(300, 40));

            //Separating Counter and Admin Input
            JPanel counterInputPanel = new JPanel();
            JPanel adminInputPanel = new JPanel();

            counterInputPanel.add(txfCounterInput);
            adminInputPanel.setLayout(new BoxLayout(adminInputPanel, BoxLayout.Y_AXIS));
            adminInputPanel.add(txfAdminName);
            adminInputPanel.add(txfAdminPass);

            //Setting Visibility
            counterInputPanel.setVisible(true);
            adminInputPanel.setVisible(false);

            cbbLoginType.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    if(e.getStateChange() == ItemEvent.SELECTED){
                        System.out.println(cbbLoginType.getSelectedIndex());
                        switch (cbbLoginType.getSelectedIndex()){
                            case 0:
                                counterInputPanel.setVisible(true);
                                adminInputPanel.setVisible(false);
                                break;
                            case 1:
                                counterInputPanel.setVisible(false);
                                adminInputPanel.setVisible(true);
                                break;
                        }
                    }
                }
            });


            btnLogin.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    switch (cbbLoginType.getSelectedIndex()){
                        case 0:
                            if(!txfCounterInput.getText().isEmpty()){
                                FinalOutput finalOutput = new FinalOutput();
                                finalOutput.setVisible(true);
                                setVisible(false);
                            }
                            break;
                        case 1:
                            if(!txfAdminName.getText().isEmpty() && !txfAdminPass.getText().isEmpty()){
                                try {
                                    Scanner adminList = new Scanner(new FileReader("/Users/johnmandysamonte/Library/Preferences/IdeaIC2019.3/scratches/FinalOutput/adminList.txt"));
                                    while(adminList.hasNext()){
                                        String name = adminList.next();
                                        String password = adminList.next();

                                        if(name.equals(txfAdminName.getText()) && password.equals(new String(txfAdminPass.getPassword()))){
                                            FinalOutput finalOutput = new FinalOutput();
                                            finalOutput.setVisible(true);
                                            setVisible(false);
                                            break;
                                        }
                                    }
                                } catch (FileNotFoundException ex) {
                                    ex.printStackTrace();
                                }

//                                FinalOutput finalOutput = new FinalOutput();
//                                finalOutput.setVisible(true);
//                                setVisible(false);
                            }
                            break;
                    }
                }
            });

            GridBagLayout gridBagLayout = new GridBagLayout();
            GridBagConstraints gridBagConstraints = new GridBagConstraints();

            setLayout(gridBagLayout);

            gridBagConstraints.gridy = 0;
            gridBagConstraints.gridx = 0;
            gridBagConstraints.insets = new Insets(0, 0 ,10, 0);
            add(lblLogin, gridBagConstraints);
            gridBagConstraints.gridy = 1;
            add(cbbLoginType, gridBagConstraints);
            gridBagConstraints.gridy = 2;
            add(counterInputPanel, gridBagConstraints);
            gridBagConstraints.gridy = 3;
            add(adminInputPanel, gridBagConstraints);
            gridBagConstraints.gridy = 4;
            add(btnLogin, gridBagConstraints);
        }
    }

    public static void main(String args[]){
        LoginFrame loginFrame = new LoginFrame();
    }
}