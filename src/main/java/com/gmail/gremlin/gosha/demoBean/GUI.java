package com.gmail.gremlin.gosha.demoBean;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI {
    JFrame frame;
    JLabel labelClient;
    JLabel labelEmail;
    JLabel labelNumber;
    JLabel labelIdClient;
    JButton addClient;
    JButton showAllClients;
    JButton showClient;
    JButton editClient;
    JButton deleteClient;
    JTextField nameClientTextField;
    JTextField emailTextField;
    JTextField numberTextField;
    JTextField idTextField;
    JTextArea textPaneOutPut;
    JPanel textPanel;
    Box buttonBox;
    Box textFieldBox;

    DemoBeanApplication master;
    public void go() {
        fillButtonBox();
        fillTextFieldBox();
        fillTextPanel();

        frame = new JFrame("JUST FOR LULS");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.getContentPane().add(BorderLayout.SOUTH,buttonBox);
        frame.getContentPane().add(BorderLayout.CENTER,textFieldBox);
        frame.getContentPane().add(BorderLayout.NORTH,textPanel);
        frame.setSize(1200,800);
        frame.setVisible(true);
    }

    private void fillButtonBox(){
        buttonBox = new Box(BoxLayout.X_AXIS);
        addClient = new JButton("Добавить клиента");
        addClient.addActionListener(new MyAddClientListener());
        buttonBox.add(addClient);

        showAllClients = new JButton("Показать всех клиентов");
        showAllClients.addActionListener(new MyShowAllClientsListener());
        buttonBox.add(showAllClients);

        showClient = new JButton("Показать клиента");
        showClient.addActionListener(new MyShowClientListener());
        buttonBox.add(showClient);

        editClient = new JButton("Изменить данные клиента");
        editClient.addActionListener(new MyEditClientListener());
        buttonBox.add(editClient);

        deleteClient = new JButton("Удалить клиента");
        deleteClient.addActionListener(new MyDeleteClientListener());
        buttonBox.add(deleteClient);
    }

    private void fillTextFieldBox() {
        textFieldBox = new Box(BoxLayout.Y_AXIS);
        textFieldBox.setMinimumSize(new Dimension(600,300));

        //label2 = new JLabel("Поля ввода");
        nameClientTextField = new JTextField();
        nameClientTextField.setSize(90,40);
        emailTextField = new JTextField();
        numberTextField = new JTextField();
        idTextField = new JTextField();

        labelClient = new JLabel("Имя клиента");
        labelEmail = new JLabel("Электронная почта");
        labelNumber = new JLabel("Телефон клиента");
        labelIdClient = new JLabel("Id клиента");

        textFieldBox.add(labelClient);
        textFieldBox.add(nameClientTextField);
        textFieldBox.add(labelEmail);
        textFieldBox.add(emailTextField);
        textFieldBox.add(labelNumber);
        textFieldBox.add(numberTextField);
        textFieldBox.add(labelIdClient);
        textFieldBox.add(idTextField);
    }

    private void fillTextPanel() {
        textPanel = new JPanel();
        textPanel.setSize(600,600);

        textPaneOutPut = new JTextArea();
        textPaneOutPut.setColumns(100);
        textPaneOutPut.setRows(30);
        textPanel.add(textPaneOutPut);
    }

    void outputClient(String name,String email,String phone){
        textPaneOutPut.setText("");
        textPaneOutPut.append(name+" "+email+" "+phone+"\n");
    }

    void outputAllClient(String msg){
        textPaneOutPut.setText("");
        textPaneOutPut.append(msg);
    }
    void setMaster(DemoBeanApplication master){
        this.master = master;
    }
    public class MyAddClientListener implements ActionListener {
        public void actionPerformed(ActionEvent a) {
            master.addNewClient(nameClientTextField.getText(),emailTextField.getText(),numberTextField.getText());
            nameClientTextField.setText("");
            emailTextField.setText("");
            numberTextField.setText("");
        }
    }

    public class MyShowAllClientsListener implements ActionListener {
        public void actionPerformed(ActionEvent a) {
            master.showAllClient();
        }
    }

    public class MyShowClientListener implements ActionListener {
        public void actionPerformed(ActionEvent a) {
            System.out.println("MyShowClientListener idTextField.getText() = " + idTextField.getText());
            int id = Integer.parseInt(idTextField.getText());
            System.out.println("id = " + id);
            master.showClient(id);

            idTextField.setText("");
        }
    }

    public class MyEditClientListener implements ActionListener {
        public void actionPerformed(ActionEvent a) {
            System.out.println("MyEditClientListener idTextField.getText() = " + idTextField.getText());
            int id = Integer.parseInt(idTextField.getText());
            System.out.println("id = " + id);
            master.editClient(id,nameClientTextField.getText(),emailTextField.getText(),numberTextField.getText());

            nameClientTextField.setText("");
            emailTextField.setText("");
            numberTextField.setText("");
            idTextField.setText("");
        }
    }

    public class MyDeleteClientListener implements ActionListener {
        public void actionPerformed(ActionEvent a) {
            System.out.println("MyDeleteClientListener idTextField.getText() = " + idTextField.getText());
            int id = Integer.parseInt(idTextField.getText());
            System.out.println("id = " + id);
            master.deleteClient(id);

            idTextField.setText("");
        }
    }
}
