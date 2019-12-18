package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class Login extends JFrame {
    public JTextField login_textField = new JTextField(40);
    public JPasswordField password_textField = new JPasswordField(40);
    public JLabel login_label = new JLabel("Логин:");
    public JLabel empty_login_label = new JLabel("Введите логин");
    public JLabel wrong_login_label = new JLabel("Неверный логин");
    public JLabel password_label = new JLabel("Пароль:");
    public JLabel empty_password_label = new JLabel("Введите пароль");
    public JLabel wrong_password_label = new JLabel("Неверный пароль");
    public JButton login_button = new JButton("Вход");
    public JButton register_button = new JButton("Регистрация");
    public JPanel p = new JPanel();

    Login() {
        super("Вход");
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(400, 300);
        p.setLayout(null);

        login_textField.setText("");
        password_textField.setText("");

        p.add(login_label);
        p.add(empty_login_label);
        p.add(wrong_login_label);
        p.add(password_label);
        p.add(empty_password_label);
        p.add(wrong_password_label);
        p.add(login_textField);
        p.add(password_textField);
        p.add(login_button);
        p.add(register_button);
        add(p);

//        JTextField login_textField = new JTextField(40);
//        JPasswordField password_textField = new JPasswordField(40);
//        JLabel login_label = new JLabel("Login:");
//        JLabel password_label = new JLabel("Password:");
//        JButton login_button = new JButton("Login");
//        JButton register_button = new JButton("Register");
//        JPanel p = new JPanel();


        login_label.setBounds(100, 100, 60,17);
        empty_login_label.setBounds(260, 100, 150,17);
        wrong_login_label.setBounds(260, 100, 150,17);
        password_label.setBounds(89, 117, 60,17);
        empty_password_label.setBounds(260, 117, 150,17);
        wrong_password_label.setBounds(260, 117, 150,17);
        login_textField.setBounds(140, 100, 120, 17);
        password_textField.setBounds(140, 117, 120, 17);
        login_button.setBounds(140, 134, 120, 17);
        register_button.setBounds(140, 151, 120, 17);

        ActionListener loginListener = new LoginListener();
        login_button.addActionListener(loginListener);

        ActionListener registrationListener = new RegistrationListener();
        register_button.addActionListener(registrationListener);

        empty_login_label.setVisible(false);
        wrong_login_label.setVisible(false);
        empty_password_label.setVisible(false);
        wrong_password_label.setVisible(false);

        Insets insets = this.getInsets();
        this.setBounds(500, 300, 400 + insets.left + insets.right, 300 + insets.top + insets.bottom);
    }

    public class RegistrationListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
            Main.reg.name_textField.setText("");
            Main.reg.login_textField.setText("");
            Main.reg.password_textField.setText("");
            Main.reg.repeat_password_textField.setText("");
            Main.reg.setVisible(true);
        }
    }

    public class LoginListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
//            setVisible(false);
            findUserProcess();
        }
    }

    private Professor findUser(String login, char[] password) throws Exception {
        empty_login_label.setVisible(false);
        wrong_login_label.setVisible(false);
        empty_password_label.setVisible(false);
        wrong_password_label.setVisible(false);
        if (!login.isEmpty()) {
            if (!Main.getUsers().isEmpty()) {
                for (Professor user : Main.getUsers()) {
                    if (user.login.equals(login)) {
                        if (Arrays.equals(user.password, password)) {
                            return user;
                        } else {
                            if (password.length <= 0){
                                empty_password_label.setVisible(true);
                            }else {
                                wrong_password_label.setVisible(true);
                            }
                        }
                    }
                }
            }
            if (!wrong_password_label.isVisible() && !empty_password_label.isVisible()){
                wrong_login_label.setVisible(true);
            }
        } else {
            empty_login_label.setVisible(true);
        }
        return new Professor("", "", new char[0]);
    }

    private void findUserProcess() {
        String login = login_textField.getText();
        char[] password1 = password_textField.getPassword();
        try {
            Professor user = findUser(login, password1);
            if (!user.getName().equals("")) {
                Main.setCurrentUser(user);
                Main.log.setVisible(false);
                if (Main.getCurrentUser().login.equals("admin")) {
                    Main.mw.user_label.setText(Main.getCurrentUser().getName()+ ":");
                    //Main.mw.request_button.setVisible(false);
                    for(Request i : Main.requests){
                        Main.edit.request_box.addItem(i);
                        //Main.mw.request_box.item
                    }
                    for(Professor i : Main.users){
                        if(!i.getName().equals("admin")){
                            Main.edit.user_box.addItem(i);}
                        //Main.mw.request_box.item
                    }
                    Main.edit.setVisible(true);
                } else {
                    Main.mw.user_label.setText(Main.getCurrentUser().getName() + ":");
                    Main.mw.groups_box.removeAllItems();
                    for(int i = 0; i < Main.groups; i++){
                        Main.mw.groups_box.addItem(i);
                    }
                    Main.mw.request_box.removeAllItems();
                    for(Request i : Main.requests){
                        if(Main.currentUser.getName().equals(i.getRequester()))
                        Main.mw.request_box.addItem(i);
                        //Main.mw.request_box.item
                    }
                    /*
                    Main.mw.p.add(Main.mw.user_label);
                    Main.mw.p.add(Main.mw.schedule_button);
                    Main.mw.p.add(Main.mw.request_button);
                    Main.mw.p.add(Main.mw.exit_button);
                    Main.mw.groups_box.setVisible(false);
                    Main.mw.request_button.setVisible(true);*/
                    Main.mw.setVisible(true);
                }
            } else {
                Main.log.setVisible(true);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
