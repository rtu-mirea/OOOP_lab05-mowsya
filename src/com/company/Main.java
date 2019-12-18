package com.company;

import javax.swing.*;
import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

public class Main {
    static ArrayList<Professor> users = new ArrayList<>();
    static ArrayList<Request> requests = new ArrayList<>();
    static Pairs[][] pairs = new Pairs[6][6];
    static Professor currentUser;
    static int rooms, groups;
    public static Login log;
    public static Register reg;
    public static MainWindow mw;
    public static Edit edit;
    public static void main(String[] args) {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                pairs[i][j] = new Pairs();
            }
        }
        try
        {
            // Reading the object from a file
            ObjectInputStream in_requests = new ObjectInputStream(new FileInputStream("requests.ser"));
            ObjectInputStream in_users = new ObjectInputStream(new FileInputStream("users.ser"));
            ObjectInputStream in_pairs = new ObjectInputStream(new FileInputStream("pairs.ser"));
            ObjectInputStream in_rooms = new ObjectInputStream(new FileInputStream("rooms.ser"));
            ObjectInputStream in_groups = new ObjectInputStream(new FileInputStream("groups.ser"));



            // Method for deserialization of object
            requests = (ArrayList<Request>)in_requests.readObject();
            users = (ArrayList<Professor>)in_users.readObject();
            rooms = (int)in_rooms.readObject();
            groups = (int)in_groups.readObject();
            pairs = (Pairs[][])in_pairs.readObject();

            System.out.println(users);

            try {
                for (int i = 0; i < Main.groups; i++) {
                    mw.groups_box.addItem(i+1);
                }
            }catch(NullPointerException eeeee){

            }

            in_requests.close();
            in_users.close();
            in_rooms.close();
            in_groups.close();

        } catch(IOException e) { }
        catch(ClassNotFoundException e) {}
        char[] pwd = {'a','d','m','i','n'};
        users.add(new Professor("admin","admin",pwd,1));
        log = new Login();
        reg = new Register();
        mw = new MainWindow();
        edit = new Edit();
        log.setVisible(true);


    }


    public static void generateSchedule() {
        for (Request r : requests) {
            int number = r.getPairs();
            int room;
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 6; j++) {
                    room = 1;
                    if (pairs[i][j].getRooms() < rooms && number > 0) {
                        while (pairs[i][j].checkRoom(room)) {
                            room++;
                        }
                        if (!pairs[i][j].checkGroup(r.getGroup()) && !pairs[i][j].checkProf(r.getRequester())) {
                            pairs[i][j].setPair(r.getRequester(), r.getDiscipline(), room, r.getGroup(), j, i);
                            pairs[i][j].occGroup(r.getGroup());
                            pairs[i][j].occProf(r.getRequester());
                            pairs[i][j].occRoom(room);
                            number--;
                        }


                    }
                }
            }
        }
    }

    public static void getResults() {
        FileWriter out_pairs;
        try {
            out_pairs = new FileWriter("pairs.txt");

            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 6; j++) {
                    out_pairs.write(pairs[i][j].printPair(currentUser.getName()));
                    out_pairs.write(System.lineSeparator());
                }
            }
            out_pairs.close();
        }catch (IOException ee){}

    }

    public static void setUsers(ArrayList<Professor> users) {
        Main.users = users;
    }
    public static int getRooms(){
        return rooms;
    }
    public static int getGroups(){
        return groups;
    }
    public static void setCurrentUser(Professor currentUser) {
        Main.currentUser = currentUser;
    }

    public static void setRooms(int rooms){
        Main.rooms = rooms;
    }

    public static void setGroups(int groups){
        Main.groups = groups;
    }


    public static void setRequests(ArrayList<Request> requests) {
        Main.requests = requests;
    }

    public static ArrayList<Professor> getUsers() {
        return users;
    }

    public static User getCurrentUser() {
        if (currentUser != null) {
            return currentUser;
        } else
            return new User("", "", new char[0]);
    }

    public static ArrayList<Request> getRequests() {
            return requests;
    }
}
