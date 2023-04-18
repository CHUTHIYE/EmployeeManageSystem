package group_homework_slot9;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

@SuppressWarnings("serial")
class Employee implements Serializable {
    int id;
    String name;
    float salary;
    long contact_no;
    String email_id;

    public Employee(int id, String name, float salary, long contact_no, String email_id){
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.contact_no = contact_no;
        this.email_id = email_id;
    }

    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", contact_no=" + contact_no +
                ", email_id='" + email_id + '\'' +
                '}';
    }

    public static class EmployeeManagement{
        static void display(ArrayList<Employee> al){
            System.out.println("**********Employee List**********");
            System.out.println(String.format("%-10s%-15s%-10s%-20s%-10s", "ID", "Name", "Salary", "contact - no", "Email - Id"));
            for (Employee e: al) {
                System.out.println(String.format("%-5s%-20s%-10s%-15s%-10s", e.id, e.name, e.salary, e.contact_no, e.email_id));
            }
        }

        @SuppressWarnings("unchecked")
        public static void main(String[] args) {
            int id;
            String name;
            float salary;
            long contact_no;
            String email_id;

            Scanner sc = new Scanner(System.in);
            ArrayList<Employee> al = new ArrayList<Employee>();

            File f = null;
            FileInputStream fis = null;
            ObjectInputStream ois = null;
            FileOutputStream fos = null;
            ObjectOutputStream oos = null;
            try {
                f = new File("src/group_homework_slot9/EmployeeDataList1.txt");
                if (f.exists()){
                    fis = new FileInputStream(f);
                    ois = new ObjectInputStream(fis);
                    al = (ArrayList<Employee>) ois.readObject();
                }
            }catch (Exception e){
                System.out.println(e);
            }
            do {
                System.out.println("\n**********Welcome to the Employee Management System**********\n");
                System.out.println("1. Add Employee");
                System.out.println("2. Search Employee");
                System.out.println("3. Edit Employee");
                System.out.println("4. Delete Employee");
                System.out.println("5. Display Employees");
                System.out.println("6. Exit");

                System.out.print("Enter your choice: ");
                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        System.out.println("\nEnter the following details to ADD list: ");
                        System.out.println("Enter ID: ");
                        id = sc.nextInt();
                        System.out.println("Enter Name: ");
                        name = sc.next();
                        System.out.println("Enter Salary: ");
                        salary = sc.nextFloat();
                        System.out.println("Enter Contact No: ");
                        contact_no = sc.nextLong();
                        System.out.println("Enter Email-ID: ");
                        email_id = sc.next();
                        al.add(new Employee(id, name, salary, contact_no, email_id));
                        display(al);
                        break;
                    case 2:
                        System.out.println("Enter the Employee ID to search: ");
                        id = sc.nextInt();
                        int i = 0;
                        for (Employee e : al){
                            if (id == e.id){
                                System.out.println(e + "\n");
                                i++;
                            }
                        }
                        if (i == 0){
                            System.out.println("\nEmployee Details are not avaliable; Please enter a valid ID!!");
                        }
                        break;
                    case 3:
                        System.out.println("\nEnter the Employee ID to EDIT the details");
                        id = sc.nextInt();
                        int j = 0;
                        for (Employee e: al) {
                            if (id == e.id) {
                                j++;
                                do {
                                    int ch1 = 0;
                                    System.out.println("\nEDIT Employee Details: \n");
                                    System.out.println("1.Employee ID: ");
                                    System.out.println("2.Name: ");
                                    System.out.println("3.Salary: ");
                                    System.out.println("4.Contact No: ");
                                    System.out.println("5.Email-ID: ");
                                    System.out.println("6.Go back.");
                                    System.out.println("Enter your choice: ");
                                    ch1 = sc.nextInt();
                                    switch (ch1) {
                                        case 1:
                                            System.out.println("\nEnter new Employee ID: ");
                                            e.id = sc.nextInt();
                                            System.out.println(e + "\n");
                                            break;
                                        case 2:
                                            System.out.println("Enter new Employee name: ");
                                            e.name = sc.nextLine();
                                            System.out.println(e + "\n");
                                            break;
                                        case 3:
                                            System.out.println("Enter new Employee Salary: ");
                                            e.salary = sc.nextFloat();
                                            System.out.println(e + "\n");
                                            break;
                                        case 4:
                                            System.out.println("Enter new Employee Contact No: ");
                                            e.contact_no = sc.nextLong();
                                            System.out.println(e + "\n");
                                            break;
                                        case 5:
                                            System.out.println("Enter new Employee Email-ID: ");
                                            e.email_id = sc.next();
                                            System.out.println(e + "\n");
                                            break;
                                        case 6:
                                            j++;
                                            break;
                                        default:
                                            System.out.println("\nEnter a correct choice from the List: ");
                                            break;
                                    }
                                }
                                while (j == 1);
                                }
                            }
                        if (j == 0){
                            System.out.println("\nEmployee Details are not available, Please enter a valid ID!!");
                        }
                        break;
                    case 4:
                        System.out.println("\nEnter Employee ID to DELETE from the Database: ");
                        id = sc.nextInt();
                        int k = 0;
                        try {
                            for (Employee e : al){
                                if (id == e.id){
                                    al.remove(e);
                                    display(al);
                                    k++;
                                }
                            }
                            if (k == 0){
                                System.out.println("\nEmployee Details are not available, Please enter a valid ID!!");
                            }
                        }catch (Exception ex){
                            System.out.println(ex);
                        }
                        break;
                    case 5:
                        try {
                            al = (ArrayList<Employee>) ois.readObject();
                        }catch (ClassNotFoundException e2){
                            System.out.println(e2);
                        }catch (Exception e2){
                            System.out.println(e2);
                        }
                        display(al);
                        break;

                    case 6:
                        try {
                            fos = new FileOutputStream(f);
                            oos = new ObjectOutputStream(fos);
                            oos.writeObject(al);
                        }catch (IOException e1){
                            e1.printStackTrace();
                        }catch (Exception e2){
                            e2.printStackTrace();
                        }finally {
                            try {
                                fis.close();
                                ois.close();
                                fos.close();
                                oos.close();
                            }catch (Exception e1){
                                e1.printStackTrace();
                            }
                        }
                        System.out.println("\nYou have chosen Exit!! Saving Files and closing the tool.");
                        sc.close();
                        System.exit(0);
                        break;

                    default:
                        System.out.println("\nEnter a correct choice from the list: ");
                        break;
                }
            }
            while (true);
        }
    }
}