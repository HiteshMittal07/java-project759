import javax.lang.model.util.ElementScanner14;
import java.util.*;
import java.util.ArrayList;

public class assignment8 {
    public static void prompt() {
        System.out.println("Done,press 1 to go to menu");
        Scanner sc = new Scanner(System.in);
        int h = sc.nextInt();
        if (h == 1) {
            menu();
        } else {
            System.out.println("Programm has ended thankyou for being with us");
        }
    }

    static ArrayList<Integer> arr = new ArrayList<Integer>();
    static ArrayList<Person> ppr = new ArrayList<Person>();
    static ArrayList<GovDetails> ggr = new ArrayList<GovDetails>();
    static ArrayList<WorkingDetails> wwr = new ArrayList<WorkingDetails>();

    public static void menu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter valid details to perform operations");
        System.out.println("enter 1 to create object of different classes");
        System.out.println("enter 2 to setdeails of different classes");
        System.out.println("enter 3 to showdetails of different classes");
        int t = sc.nextInt();
        switch (t) {
            case 1:
                System.out.println("enter 1 to create an object of person class");
                System.out.println("enter 2 to createobject of Govdetails class");
                System.out.println("enter 3 to create object of working details class");
                int d = sc.nextInt();
                createObject(d);
                prompt();
                break;
            case 2:
                System.out.println("enter 1 to setDetails of Person class");
                System.out.println("enter 2 to setDetails of GoVdetails class");
                System.out.println("enter 3 to setDetails of WOrkingDetail class");
                int m = sc.nextInt();
                switch (m) {
                    case 1:
                        ppr.get(ppr.size() - 1).setDetails();
                        prompt();
                        break;
                    case 2:
                        ggr.get(ggr.size() - 1).setDetails();
                        prompt();
                        break;
                    case 3:
                        wwr.get(wwr.size() - 1).setDetails();
                        prompt();
                }
            case 3:
                System.out.println("enter 1 to show details based on dateOfBirth");
                System.out.println("enter 2 to showdetails based on EmployeeId");
                int z = sc.nextInt();
                switch (z) {
                    case 1:
                        System.out.println("enter year of birth");
                        int yob = sc.nextInt();
                        for(int i=0;i<ppr.size();i++)
                        {
                            if((ppr.get(i).dateOfBirth)%10000==yob)
                            {
                                ppr.get(i).showDetails();
                            
                            }
                        }
                        for(int i=0;i<ggr.size();i++)
                        {
                            if((ggr.get(i).dateOfBirth)%10000==yob)
                            {
                                ggr.get(i).showDetails();
                                
                            }
                        }
                        for(int i=0;i<wwr.size();i++)
                        {
                            if((wwr.get(i).dateOfBirth)%10000==yob)
                            {
                                wwr.get(i).showDetails();
                            
                            }
                        }
                        prompt();
                        break;
                        case 2:
                        System.out.println("enter employeeId");
                        int d2c=sc.nextInt();
                        for(int i=0;i<wwr.size();i++)
                        {
                            if((wwr.get(i).empId==d2c))
                            {
                                wwr.get(i).showDetails();
                            }
                        }

                }
        }
    }

    public static void createObject(int g) {
        switch (g) {
            case 1:
                Person p1 = new Person();
                ppr.add(p1);
                int s = ppr.size();
                s = s - 1;
                int dob = (p1.dateOfBirth) % 10000 + s;
                arr.add(dob);
                break;
            case 2:
                GovDetails g1 = new GovDetails();
                ggr.add(g1);
                int zz = ggr.size() - 1;
                int dp = g1.dateOfBirth;
                dp = dp + zz;
                arr.add(dp);
            case 3:
                WorkingDetails w1 = new WorkingDetails();
                wwr.add(w1);
                int y = wwr.size() - 1;
                int ep = wwr.get(y).empId;
                ep = ep + y;
                arr.add(ep);
                break;

        }
    }

    public static void main(String[] args) {
        System.out.println("Hello World");
        menu();
    }
}

class Person {
    private String firstName;
    private String lastName;
    private int addressPin;
    int dateOfBirth;
    private String emailId;

    public Person() {
    }

    public static int validate(int t, int adpin, int dob, String pno) {
        switch (t) {
            case 1:
                int cnt = 0;
                while (adpin > 0) {
                    adpin = adpin / 10;
                    cnt++;
                }
                if (cnt == 6) {
                    return 0;
                } else {
                    System.out.println("addressPin should contain 6 digit only please enter it correctly");
                    return 1;
                }
            case 2:
                int cnt1 = 0, m = 0, d = 0, mm = 0, dd = 0;
                while (dob > 0) {
                    dob = dob / 10;
                    int r = dob % 10;
                    cnt1++;
                    if (cnt1 == 4 || cnt1 == 5) {
                        m = m * 10 + r;
                    }
                    if (cnt1 == 6 || cnt1 == 7) {
                        d = d * 10 + r;
                    }
                }
                while (m > 0) {
                    int rr = m % 10;
                    m = m / 10;
                    mm = mm * 10 + rr;
                }
                while (d > 0) {
                    int rr = d % 10;
                    d = d / 10;
                    dd = dd * 10 + rr;
                }
                if (cnt1 == 8) {
                    if (mm <= 12) {
                        if (dd <= 31) {
                            return 0;
                        } else {
                            System.out.println("you have entered wrong day please enter dob correctly");
                            return 1;
                        }
                    } else {
                        System.out.println("you have entered wrong month plz. enter dob corretly");
                        return 1;
                    }
                } else {
                    System.out.println("please enter dateof birth in ddmmyyyy format correctly");
                    return 1;
                }
            case 3:
                char[] ch = new char[pno.length()];
                for (int i = 0; i < pno.length(); i++) {
                    ch[i] = pno.charAt(i);
                    if ((ch[i] <= 90 && ch[i] >= 65) || (ch[i] >= 48 && ch[i] <= 57)) {
                        return 0;
                    } else {
                        System.out.println("please enter correct pan no");
                        return 1;
                    }
                }

        }
        return 1;
    }

    public void setDetails() {
        System.out.println("enter firstname");
        Scanner sc = new Scanner(System.in);
        firstName = sc.nextLine();
        System.out.println("enter lastname");
        lastName = sc.nextLine();
        System.out.println("enter dateOfBirth in int ddmmyyyy format");
        dateOfBirth = sc.nextInt();
        int t = validate(2, 0, dateOfBirth, null);
        while (t != 0) {
            dateOfBirth = sc.nextInt();
            t = validate(2, 0, dateOfBirth, null);
        }
        System.out.println("enter addressPin");
        addressPin = sc.nextInt();
        int t2 = validate(1, addressPin, 0, null);
        while (t2 != 0) {
            addressPin = sc.nextInt();
            t2 = validate(1, addressPin, 0, null);
        }
        System.out.println("enter emailId");
        sc.nextLine();
        emailId = sc.nextLine();
    }

    public void showDetails() {
        System.out.println("First name= " + firstName + "\nLastname= " + lastName);
        System.out.println("dateOFBirth= " + dateOfBirth + "\nEmailId= " + emailId);
        System.out.println("AddressPin= " + addressPin);
    }
}

class GovDetails extends Person {
    private String panNo;

    public GovDetails() {
    }

    public void setDetails() {
        super.setDetails();
        System.out.println("enter pan no");
        Scanner sc = new Scanner(System.in);
        panNo = sc.nextLine();
        int t2 = Person.validate(3, 0, 0, panNo);
        while (t2 != 0) {
            panNo = sc.nextLine();
            t2 = Person.validate(3, 0, 0, panNo);
        }

    }

    public void showDetails() {
        super.showDetails();
        System.out.println("panNo= " + panNo);
    }
}

class WorkingDetails extends GovDetails {
    private String companyName;
    int empId;

    public WorkingDetails() {
    }

    public void setDetails() {
        super.setDetails();
        System.out.println("enter company name where employee works");
        Scanner sc = new Scanner(System.in);
        companyName = sc.nextLine();
        System.out.println("enter empid of employee");
        empId = sc.nextInt();
    }

    public void showDetails() {
        super.showDetails();
        System.out.println("companyName= " + companyName + "\nEmpID= " + empId);
    }
}