package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class MainWindow extends JFrame {
    public JLabel user_label = new JLabel(":");
    public JLabel disc_label = new JLabel("Дисциплина:");
    public JLabel group_label = new JLabel("Группа №:");
    public JLabel pairs_label = new JLabel("Кол-во пар:");
    public JTextField disc_textField= new JTextField();
    public JComboBox groups_box = new JComboBox();
    public JTextField pairs_textField = new JTextField();
    public JLabel request_label = new JLabel("Заявка:");
    public JComboBox request_box = new JComboBox();
    public JButton schedule_button = new JButton("Получить расписание");
    public JButton request_button = new JButton("Подать заявку");
    public JButton edit_button = new JButton("Удалить заявку");
    public JButton exit_button = new JButton("Выход");
    public JPanel p = new JPanel();

    MainWindow() {
        super("Система составления расписания");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.add(user_label);
        p.add(Box.createVerticalStrut(20));
        p.add(disc_label);
        p.add(disc_textField);
        p.add(pairs_label);
        p.add(pairs_textField);
        p.add(group_label);
        p.add(groups_box);
        p.add(Box.createVerticalStrut(20));
        p.add(request_button);
        p.add(Box.createVerticalStrut(40));
        p.add(request_label);
        p.add(request_box);
        p.add(Box.createVerticalStrut(20));
        p.add(edit_button);
        p.add(Box.createVerticalStrut(40));
        p.add(schedule_button);
        p.add(Box.createVerticalStrut(20));
        p.add(exit_button);
        disc_textField.setMaximumSize(new Dimension(50,17));
        pairs_textField.setMaximumSize(new Dimension(50,17));
        groups_box.setMaximumSize(new Dimension(50,17));
        request_box.setMaximumSize(new Dimension(300,17));
        user_label.setAlignmentX((float)0.5);
        disc_label.setAlignmentX((float)0.5);
        disc_textField.setAlignmentX((float)0.5);
        pairs_label.setAlignmentX((float)0.5);
        pairs_textField.setAlignmentX((float)0.5);
        group_label.setAlignmentX((float)0.5);
        groups_box.setAlignmentX((float)0.5);
        request_label.setAlignmentX((float)0.5);
        request_button.setAlignmentX((float)0.5);
        request_box.setAlignmentX((float)0.5);
        schedule_button.setAlignmentX((float)0.5);
        exit_button.setAlignmentX((float)0.5);
        edit_button.setAlignmentX((float)0.5);

        /*
        Insets insets = p.getInsets();
        Dimension size = disc_label.getPreferredSize();
        disc_label.setBounds(25 + insets.left, 5 + insets.top, size.width, size.height);
        size = group_label.getPreferredSize();
        group_label.setBounds(25 + insets.left, 40 + insets.top, size.width, size.height);
        size = pairs_label.getPreferredSize();
        pairs_label.setBounds(25 + insets.left, 75 + insets.top, size.width , size.height );
        int l = pairs_label.getWidth()+10;
        disc_label.setBounds( 25+insets.left+l, 5 + insets.top, 100, disc_label.getHeight());
        group_label.setBounds( 25+insets.left+l, 40 + insets.top, 100, group_label.getHeight());
        pairs_label.setBounds( 25+insets.left+l, 75 + insets.top, 100, pairs_label.getHeight());
        insets = this.getInsets();
        this.setBounds(200,200, 300+insets.left + insets.right, 200+insets.top + insets.bottom);
*/
        ActionListener scheduleListener = new ScheduleListener();
        schedule_button.addActionListener(scheduleListener);

        ActionListener requestListener = new RequestListener();
        request_button.addActionListener(requestListener);

        ActionListener editListener = new EditListener();
        edit_button.addActionListener(editListener);

        ActionListener exitListener = new ExitListener();
        exit_button.addActionListener(exitListener);

        add(p);
        this.setBounds(500, 300, 460, 500);
    }




    public class ScheduleListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Main.getResults();
        }
    }

    public class RequestListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Main.requests.add(new Request(Main.currentUser, disc_textField.getText(), groups_box.getSelectedIndex(), Integer.parseInt(pairs_textField.getText())));
            try {
                ObjectOutputStream out_requests = new ObjectOutputStream(new FileOutputStream("requests.ser"));

                out_requests.writeObject(Main.getRequests());

                out_requests.close();
            } catch (IOException e1) { }
            request_box.removeAllItems();
            for(Request i : Main.requests) {
                request_box.addItem(i);
            }
        }
    }

    public class EditListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for(int i = 0; i < Main.requests.size(); i++){
                if (Main.requests.get(i) == request_box.getSelectedItem()){
                    Main.requests.remove(i);
                    break;
                }
            }
            try {
                ObjectOutputStream out_requests = new ObjectOutputStream(new FileOutputStream("requests.ser"));

                out_requests.writeObject(Main.getRequests());

                out_requests.close();
            } catch (IOException e1) { }
            request_box.removeAllItems();
            for(Request i : Main.requests) {
                request_box.addItem(i);
            }
        }
    }

    public class ExitListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
            Main.log.login_textField.setText("");
            Main.log.password_textField.setText("");
            Main.log.setVisible(true);
        }
    }


}