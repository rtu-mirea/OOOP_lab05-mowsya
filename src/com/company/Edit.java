package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Edit extends JFrame {
    public JLabel rooms_label = new JLabel("Кол-во аудиторий:");
    public JLabel groups_label = new JLabel("Кол-во групп:");
    public JTextField rooms_textField = new JTextField(40);
    public JTextField groups_textField = new JTextField(40);
    public JButton rooms_button = new JButton("Изменить");
    public JButton gen_button = new JButton("Ген. расписание");
    public JButton cancel_button = new JButton("Выход");
    public JLabel request_label = new JLabel("Заявка:");
    public JLabel user_label = new JLabel("Пользователь:");
    public JComboBox user_box = new JComboBox();
    public JButton user_button = new JButton("Удалить пользователя");
    public JComboBox request_box = new JComboBox();
    public JButton request_button = new JButton("Удалить заявку");
    public JPanel p = new JPanel();

    Edit() {
        super("Меню администратора");
        setResizable(false);
        p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));
        p.add(rooms_label);
        p.add(rooms_textField);
        p.add(groups_label);
        p.add(groups_textField);
        p.add(rooms_button);
        p.add(Box.createVerticalStrut(20));
        p.add(gen_button);
        p.add(Box.createVerticalStrut(20));

        p.add(request_label);
        p.add(request_box);
        p.add(request_button);
        p.add(Box.createVerticalStrut(20));
        p.add(user_label);
        p.add(user_box);
        p.add(user_button);
        p.add(Box.createVerticalStrut(20));

        p.add(cancel_button);
        add(p);
//        rooms_textField.setSize(20,17);
//        groups_textField.setSize(20,17);
        rooms_label.setAlignmentX((float)0.5);
        rooms_textField.setAlignmentX((float)0.5);
        groups_label.setAlignmentX((float)0.5);
        groups_textField.setAlignmentX((float)0.5);
        rooms_button.setAlignmentX((float)0.5);
        gen_button.setAlignmentX((float)0.5);
        cancel_button.setAlignmentX((float)0.5);
        request_label.setAlignmentX((float)0.5);
        request_button.setAlignmentX((float)0.5);
        request_box.setAlignmentX((float)0.5);
        user_label.setAlignmentX((float)0.5);
        user_button.setAlignmentX((float)0.5);
        user_box.setAlignmentX((float)0.5);


        rooms_textField.setMaximumSize(new Dimension(50,17));
        groups_textField.setMaximumSize(new Dimension(50,17));
        request_box.setMaximumSize(new Dimension(200,17));
        user_box.setMaximumSize(new Dimension(200,17));
//        Dimension size_rooms_button = rooms_button.getPreferredSize();
//        Dimension size_gen_button = gen_button.getPreferredSize();
//        Dimension size_cancel_button = cancel_button.getPreferredSize();
//
//        rooms_textField.setBounds(10, 10, 20, 17);
//        groups_textField.setBounds(110, 10, 20, 17);
//        rooms_button.setBounds(220, 10, size_rooms_button.width, 17);
//        gen_button.setBounds(230 + size_rooms_button.width, 10, size_gen_button.width, 17);
//        cancel_button.setBounds(240 + size_rooms_button.width + size_gen_button.width, 10, size_cancel_button.width, 17);

        ActionListener roomsListener = new RoomsListener();
        rooms_button.addActionListener(roomsListener);

        ActionListener groupsListener = new GroupsListener();
        rooms_button.addActionListener(groupsListener);

        ActionListener genListener = new GenListener();
        gen_button.addActionListener(genListener);

        ActionListener requestListener = new RequestListener();
        request_button.addActionListener(requestListener);

        ActionListener cancelListener = new CancelListener();
        cancel_button.addActionListener(cancelListener);

        ActionListener userListener = new UserListener();
        user_button.addActionListener(userListener);

        this.setBounds(500, 300, 460, 460);
    }

    public class RequestListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Main.requests.remove(request_box.getSelectedIndex());
            try {
                ObjectOutputStream out_requests = new ObjectOutputStream(new FileOutputStream("requests.ser"));

                out_requests.writeObject(Main.getRooms());

                out_requests.close();
            } catch (IOException e1) {
            }
            request_box.removeAllItems();
            for(Request i : Main.requests){
                request_box.addItem(i);
            }
        }
    }

    public class UserListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Main.users.remove(user_box.getSelectedIndex()+1);
            try {
                ObjectOutputStream out_users = new ObjectOutputStream(new FileOutputStream("users.ser"));

                out_users.writeObject(Main.getRooms());

                out_users.close();
            } catch (IOException e1) {
            }
            user_box.removeAllItems();
            for(Professor i : Main.users){
                if(!i.getName().equals("admin")){
                user_box.addItem(i);}
            }
        }
    }

    public class RoomsListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Main.setRooms(Integer.parseInt(rooms_textField.getText()));
            try {

                ObjectOutputStream out_rooms = new ObjectOutputStream(new FileOutputStream("rooms.ser"));

                out_rooms.writeObject(Main.getRooms());

                out_rooms.close();
            } catch (IOException e1) {
            }
        }
    }

    public class GroupsListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Main.setGroups(Integer.parseInt(groups_textField.getText()));
            try {
                ObjectOutputStream out_groups = new ObjectOutputStream(new FileOutputStream("groups.ser"));

                out_groups.writeObject(Main.getRooms());

                out_groups.close();
            } catch (IOException e1) {
            }
        }
    }

    public class GenListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                Main.generateSchedule();
                ObjectOutputStream out_pairs = new ObjectOutputStream(new FileOutputStream("pairs.ser"));
                out_pairs.writeObject(Main.pairs);
            } catch (IOException e1) {
            }
        }
    }

    public class CancelListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
            Main.log.setVisible(true);
        }
    }
}
