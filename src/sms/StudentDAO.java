package sms;

import java.sql.*;
import java.util.Scanner;

public class StudentDAO {

    Scanner sc = new Scanner(System.in);

    public void addStudent() {
        try {
            Connection con = DBConnection.getConnection();

            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Age: ");
            int age = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Course: ");
            String course = sc.nextLine();

            System.out.print("Enter Email: ");
            String email = sc.nextLine();

            String query = "insert into students(name,age,course,email) values(?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setString(3, course);
            ps.setString(4, email);

            ps.executeUpdate();

            System.out.println("Student Added Successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viewStudents() {
        try {
            Connection con = DBConnection.getConnection();

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("select * from students");

            while (rs.next()) {
                System.out.println(
                        rs.getInt(1) + " | " +
                        rs.getString(2) + " | " +
                        rs.getInt(3) + " | " +
                        rs.getString(4) + " | " +
                        rs.getString(5));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateStudent() {
        try {
            Connection con = DBConnection.getConnection();

            System.out.print("Enter ID to Update: ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter New Name: ");
            String name = sc.nextLine();

            PreparedStatement ps = con.prepareStatement(
                    "update students set name=? where id=?");

            ps.setString(1, name);
            ps.setInt(2, id);

            ps.executeUpdate();

            System.out.println("Updated Successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteStudent() {
        try {
            Connection con = DBConnection.getConnection();

            System.out.print("Enter ID to Delete: ");
            int id = sc.nextInt();

            PreparedStatement ps = con.prepareStatement(
                    "delete from students where id=?");

            ps.setInt(1, id);

            ps.executeUpdate();

            System.out.println("Deleted Successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}