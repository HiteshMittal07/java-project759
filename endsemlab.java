import java.util.*;
import java.util.ArrayList;

import javax.lang.model.util.ElementScanner14;

import java.net.Socket;
import java.io.*;
import java.net.ServerSocket;

public class endsemlab
 {
    static ArrayList<student> s1=new ArrayList<student>();
    public static void menu()
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("select from the below options");
        System.out.println("enter 1 to go to operations under admin:");
        System.out.println("enter 2 to go to operations under user:");
        int b=sc.nextInt();
        switch(b)
        {
            case 1:   
            System.out.println("enter 1 to add details:");
            System.out.println("enter 2 to delete details:");
            System.out.println("enter 3 to modify details:");
            int c=sc.nextInt();
            switch(c)
            {
                case 1:
                addStudent();
                try
                {
                    Serializable();
                }
                catch(IOException e)
                {

                }
                System.out.println("new student profile added!!\n");
                menu();
                case 2:
                if(s1.size()==0)
                {
                    System.out.println("please create a student profile first");
                }
                if(s1.size()>0)
                {
                    System.out.println("enter roll number here of student which you want to delete:");
                    int h=sc.nextInt();
                    deletestudent(h);
                }
                menu();
                case 3:
                if(s1.size()==0)
                {
                    System.out.println("please create a student profile first");
                }
                if(s1.size()>0)
                {
                    System.out.println("enter roll number here of student which you want to modify:");
                    int g=sc.nextInt();
                    modifystudent(g);
                }
                menu();
            }
            case 2:
            System.out.println("enter 1 to view details by roll number:");
            System.out.println("enter 2 to view details by name:");
            int d=sc.nextInt();
            switch(d)
            {
                case 1:
                Scanner sd=new Scanner(System.in);
                if(s1.size()==0)
                {
                    System.out.println("please create a student profile first");
                }
                if(s1.size()>0)
                {
                System.out.println("enter roll number here of which you want to view details:");
                int m=sc.nextInt();
                viewroll(m);
                }
                menu();
                case 2:
                if(s1.size()==0)
                {
                    System.out.println("please create a student profile first");
                }
                if(s1.size()>0)
                {
                    System.out.println("enter name here of which you want to view details:");
                    sc.nextLine();
                    String l=sc.nextLine();
                    viewname(l);
                }
                menu();
            }
        }
    }
    static student addStudent()
    {
        student temp=new student();
        s1.add(temp);
        temp.setdetails();
        return temp;
    }
    public static void Serializable() throws IOException
    {
        FileOutputStream out=new FileOutputStream("student1.ser");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);
        int i=0;
        while(true)
        {
            if(i<s1.size())
            {
                objectOutputStream.writeObject(s1.get(i));
            }
            if(i==s1.size())
            {
                break;
            }
        }



    }
    public static void deletestudent(int roll1)
    {
        int i=0;
        int count=0;
        while(true)
        {
            if(i<s1.size())
            {
                if(roll1==s1.get(i).RollNo)
                {
                    s1.remove(i);
                    count++;
                    System.out.println("student profile deleted!!\n");
                    break;
                }
            } 
            i++;
        }
        if(count==0)
        {
            System.out.println("you don't have student of this roll number");
        }

    }
    public static void modifystudent(int roll2)
    {
        int i=0;
        int count=0;
        while(true)
        {
            if(i<s1.size())
            {
                if(roll2==s1.get(i).RollNo)
                {
                    s1.get(i).modifydetails();
                    System.out.println("student profile modified!!\n");
                    count++;
                    break;
                }
            } 
            i++;
        }
        if(count==0)
        {
            System.out.println("you don't have student of this roll number");
        }
    }
    public static void viewroll(int roll)
    {
        int i=0;
        int count=0;
        while(true)
        {
            if(i<s1.size())
            {
                if(roll==s1.get(i).RollNo)
                {
                    s1.get(i).viewdetails();
                    count++;
                    break;
                }
            } 
            i++;
        }
        if(count==0)
        {
            System.out.println("you don't have student of this roll number");
        }
    }
    public static void viewname(String name1)
    {
        int i=0;
        while(true)
        {
            if(i<s1.size())
            {
                if(s1.get(i).name.compareTo(name1)==0)
                {
                    s1.get(i).viewdetails();
                    break;
                }
            } 
            i++;
        }
    }
    public static void main(String[] args) throws IOException
    {
        // Socket soc=new Socket("local host",9809);
        BufferedReader userinput=new BufferedReader(new InputStreamReader(System.in));
        Scanner sc=new Scanner(System.in);
        System.out.println("Welcome!!");
        System.out.println("enter 1 to go to menu");
        int a=sc.nextInt();
        if(a==1)
        {
            menu();
        }      
    }
}
class student
{
    String name;
    String DOB;
    int RollNo;
    int yearOfStudy;
    String Branch;
    public void setdetails()
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("enter your name here:");
        name=sc.nextLine();
        System.out.println("enter your date of birth here:");
        DOB=sc.nextLine();
        System.out.println("enter your roll number here:");
        RollNo=sc.nextInt();
        System.out.println("enter your year of study here:");
        yearOfStudy=sc.nextInt();
        sc.nextLine();
        System.out.println("enter your branch name here:");
        Branch=sc.nextLine();
    }
    public void viewdetails()
    {
        System.out.println("Name:"+name);
        System.out.println("Date of Birth:"+DOB);
        System.out.println("Roll Number:"+RollNo);
        System.out.println("Year of Study:"+yearOfStudy);
        System.out.println("Branch:"+Branch+"\n");
    }
    public void modifydetails()
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("enter your updated name here:");
        name=sc.nextLine();
        System.out.println("enter your updated date of birth here:");
        DOB=sc.nextLine();
        System.out.println("enter your updated roll number here:");
        RollNo=sc.nextInt();
        System.out.println("enter your updated year of study here:");
        yearOfStudy=sc.nextInt();
        sc.nextLine();
        System.out.println("enter your updated branch name here:");
        Branch=sc.nextLine();
    }
}