import java.util.*;
import java.util.ArrayList;
import java.util.InputMismatchException;

class itemNotFound extends Exception {
    public itemNotFound(String s) {
        super(s);
    }
}
class datarange extends Exception {
    public datarange(String s) {
        super(s);
    }
}

public class assignment9 {
    static ArrayList<Item> I1 = new ArrayList<Item>();
    static ArrayList<Sell> S1 = new ArrayList<Sell>();
    static ArrayList<Return> R1 = new ArrayList<Return>();
    public static void menu() {
        System.out.println("Enter 1 to register to admin user:");
        System.out.println("Enter 2 to register to general user:");
        Scanner sc = new Scanner(System.in);
        int e;
        e = sc.nextInt();
        switch (e) {
            case 1:
                Admin temp = new Admin();
                temp.setdetails();   
                System.out.println("registered!!\n");
                System.out.println("Enter 1 to again go to register page");
                System.out.println("Enter 2 to go to operations page under users");
                int x;
                x = sc.nextInt();
                switch (x) {
                    case 1:
                        menu();
                    case 2:
                        prompt();
                }
            case 2:
                General temp1 = new General();
                temp1.setdetails();
                System.out.println("registered!!\n");
                System.out.println("Enter 1 to again go to register page");
                System.out.println("Enter 2 to go to operations page under users");
                int q;
                q = sc.nextInt();
                switch (q) {
                    case 1:
                        menu();
                    case 2:
                        prompt();
                }
        }
    }
    public static void prompt() {
        System.out.println("Select from the below options:");
        System.out.println("Enter 1 to get Operations under Admin user");
        System.out.println("Enter 2 to get Operations under General user");
        int n;
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        if (n == 1) {
            System.out.println("Enter 1 to add new stock");
            System.out.println("Enter 2 to delete the stock");
            System.out.println("Enter 3 to modify the stock");
            int d = sc.nextInt();
            switch (d) {
                case 1:
                    Item temp = addNewStock();
                    prompt();
                case 2:
                    int loop1;
                    int m=0;
                    int count;
                    do
                    {
                    loop1=0;
                    count=0;
                    try
                    {
                    System.out.println("Enter itemcode here:");
                    m = sc.nextInt();
                    int i=0;
                    while(true)
                        {
                            if(i<I1.size())
                            {
                               if(m==I1.get(i).itemCode)
                               {
                                    count++;
                               }
                            }
                            if(i>I1.size())
                            {
                                break;
                            }
                            i++;
                        }
                        if(count==0)
                        {
                            throw new itemNotFound("item of this itemcode is not available");
                        }
                    }
                    catch(itemNotFound exception)
                    {
                        System.out.println("error! item not found: "+exception.getMessage());
                        loop1=1;
                    }
                    }while(loop1==1);
                    deleteStock(m);
                    prompt();
                case 3:
                    int loop2;
                    int b=0;
                    int count2;
                    do
                    {
                    loop2=0;
                    count2=0;
                    try
                    {
                    System.out.println("Enter itemcode here:");
                    b = sc.nextInt();
                    int i=0;
                    while(true)
                        {
                            if(i<I1.size())
                            {
                               if(b==I1.get(i).itemCode)
                               {
                                    count2++;
                               }
                            }
                            if(i>I1.size())
                            {
                                break;
                            }
                            i++;
                        }
                        if(count2==0)
                        {
                            throw new itemNotFound("item of this itemcode is not available");
                        }
                    }
                    catch(itemNotFound exception)
                    {
                        System.out.println("error! item not found:"+exception.getMessage());
                        loop2=1;
                    }
                    }while(loop2==1);
                    modifyStock(b);
                    prompt();
            }
        }
        if (n == 2) {
            System.out.println("Select from the below options:");
            System.out.println("Enter 1 to sell the item");
            System.out.println("Enter 2 to return the item");
            System.out.println("Enter 3 to display the stock");
            System.out.println("Enter 4 to display the sold item");
            int c = sc.nextInt();
            switch (c) {
                case 1:
                    Sell temp = new Sell();
                    S1.add(temp);
                    temp.setdetails();
                    sellitem(temp.itemCode, temp.quantity);
                    System.out.println("your item is sold\n");
                    prompt();
                case 2:
                    Return temp1 = new Return();
                    R1.add(temp1);
                    temp1.setdetails();
                    returnItem(temp1.itemCode, temp1.quantity);
                    System.out.println("your item is returned\n");
                    prompt();
                case 3:
                    System.out.println("enter the itemcode of item of which you want to display the available stock:");
                    int z=sc.nextInt();    
                    displaystock(z);
                    prompt();
                case 4:
                    int loop3;
                    String sd=null;
                    String ed=null;
                    do
                    {
                        loop3=0;
                        try
                        {
                            System.out.println("enter start date:");
                            Scanner sc1=new Scanner(System.in);
                            sd=sc1.nextLine();    
                            System.out.println("enter end date:");
                            ed=sc1.nextLine();
                            int a1=Integer.parseInt(sd.substring(6, 10));
                            int b1=Integer.parseInt(ed.substring(6, 10));
                            int a2=Integer.parseInt(sd.substring(3, 5));
                            int b2=Integer.parseInt(ed.substring(3, 5));
                            if(a1==b1)
                            {
                                if(b2-a2>6)
                                {
                                    throw new datarange("date range exceeded:");
                                }
                            }
                            if(b1>a1)
                            {
                                int a=b2+12;
                                if(a-a2>6)
                                {
                                    throw new datarange("data range exceeded:");
                                }
                            }
                        }
                        catch(datarange exception)
                        {
                            System.out.println("error! re enter it"+exception.getMessage());
                            loop3=1;
                        }
                    }while(loop3==1);
                    displaysell(sd, ed);
                    prompt();
            }
        }
    }
    static Item addNewStock() {
        Item temp = new Item();
        I1.add(temp);
        temp.setDetails();
        System.out.println("New stock is added\n");
        return temp;
    }
    public static void deleteStock(int itemcode) {
        // if (I1.get(itemcode).availableQty == 0) {
        //     I1.remove(I1.get(itemcode));
        //     System.out.println("the stock is removed");
        // } else {
        //     System.out.println("you have the stock remaining of this item");
        // }
        Scanner sc=new Scanner(System.in);
        int i=0;
        while(true)
            {
                if(i<I1.size())
                {
                    if(I1.get(i).itemCode==itemcode && I1.get(i).availableQty==0)
                    {
                        I1.remove(i);
                        System.out.println("your stock is removed\n");
                        break;
                    }
                    if(I1.get(i).itemCode==itemcode && I1.get(i).availableQty!=0)
                    {
                        System.out.println("your have remaining stock\n");
                        break;
                    }
                }
                i++;
            }
    }
    public static void modifyStock(int itemcode) {
        // I1.get(itemCode).updateDetails();
        // // I1.set(I1.get(itemCode).itemCode,I1.get(itemCode));
        // System.out.println("The stock is updated");
        Scanner sc=new Scanner(System.in);
        int i=0;
        while(true)
            {
                if(i<I1.size())
                {
                    if(I1.get(i).itemCode==itemcode)
                    {
                        I1.get(i).updateDetails();
                        System.out.println("your stock is updated\n");
                        break;
                    }
                    if(I1.get(i).itemCode!=itemcode)
                    {
                        System.out.println("you have no item of this itemcode!! re-enter it:");
                        int itemcode1=sc.nextInt();
                        modifyStock(itemcode1);
                        break;
                    }
                }
                i++;
            }
    }
    public static void sellitem(int itemcode, int quantity) {
        int i=0;
        while(true)
            {
                if(i<I1.size())
                {
                    if(I1.get(i).itemCode==itemcode && I1.get(i).availableQty>=quantity)
                    {
                        I1.get(i).availableQty=I1.get(i).availableQty-quantity;
                        // System.out.println("your stock is updated");
                        break;
                    }
                    if(I1.get(i).availableQty<quantity)
                    {
                        System.out.println("you don't have that quantity to sell!!\n");
                        break;
                    }
                    if(I1.get(i).itemCode!=itemcode)
                    {
                        System.out.println("you have no item of this itemcode!!\n");
                        break;
                    }
                }
                i++;
            }
    }
    public static void returnItem(int itemcode, int quantity) {
        int i=0;
        while(true)
            {
                if(i<I1.size())
                {
                    if(I1.get(i).itemCode==itemcode)
                    {
                        I1.get(i).availableQty=I1.get(i).availableQty+quantity;
                        // System.out.println("your stock is updated");
                        break;
                    }
                    if(I1.get(i).itemCode!=itemcode)
                    {
                        System.out.println("you have no item of this itemcode!!\n");
                        break;
                    }
                }
                i++;
            }
    }
    public static void displaystock(int itemcode)
    {
        int i=0;
        while(true)
            {
                if(i<I1.size())
                {
                    if(I1.get(i).itemCode==itemcode)
                    {
                        I1.get(i).displayDetails(itemcode);
                        break;
                    }
                    if(I1.get(i).itemCode!=itemcode)
                    {
                        System.out.println("you have no item of this itemcode!!\n");
                        break;
                    }
                }
                i++;
            }
    }
    public static void displaysell(String startdate, String enddate)
    {
        int i=0;
        int strt=Integer.parseInt(startdate.substring(6, 10));
        int end=Integer.parseInt(enddate.substring(6, 10));
        int strt1=Integer.parseInt(startdate.substring(3, 5));
        int end1=Integer.parseInt(enddate.substring(3, 5));
        int strt2=Integer.parseInt(startdate.substring(0, 2));
        int end2=Integer.parseInt(enddate.substring(0, 2));
        
        while(true)
        {
            if(i<S1.size())
            {
                    int selld=Integer.parseInt(S1.get(i).dateOfSell.substring(6, 10));
                    int selld1=Integer.parseInt(S1.get(i).dateOfSell.substring(3, 5));
                    int selld2=Integer.parseInt(S1.get(i).dateOfSell.substring(0, 2));
                    if(selld>strt && selld<end)
                    {
                        S1.get(i).showdetails();
                        // break;
                    }
                    if(selld==strt && selld<end)
                    {
                        if(selld1>strt1)
                        {
                        S1.get(i).showdetails();
                        // break;
                        }
                    }
                    if(selld==strt && selld<end)
                    {
                        if(selld1==strt1)
                        {
                            if(selld2>=strt2)
                            {
                            S1.get(i).showdetails();
                            // break;
                            }
                        }
                    }
                    if(selld>strt && selld==end)
                    {
                        if(selld1<end1)
                        {
                        S1.get(i).showdetails();
                        // break;
                        }
                    }
                    if(selld>strt && selld==end)
                    {
                        if(selld1==end1)
                        {
                            if(selld2<=end2)
                            {
                            S1.get(i).showdetails();
                            // break;
                            }
                        }
                    }
                    if(selld==strt && selld==end)
                    {
                        if(selld1>strt1 && selld1<end1)
                        {
                            S1.get(i).showdetails();
                            // break;
                        }
                    }
                    if(selld==strt && selld==end)
                    {
                        if(selld1==strt1 && selld1==end1)
                        {
                            if(selld2>strt2 && selld2<end2)
                            {
                                S1.get(i).showdetails();
                                // break;
                            }
                        }
                    }
                    if(selld>end && selld<strt)
                    {
                        System.out.println("you have no item sold between theese dates!!\n");
                        // break;
                    }
                }
                i++;
                if(i>S1.size())
                {
                    break;
                }
            }

    }
    public static void main(String[] args) {
        System.out.println("Welcome!!");
        System.out.println("enter 1 to go to menu or press any key ");
        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();
        if (option == 1) {
            menu();
        }
    }
}
class User {
    int userID;
    String name;
    String datOfBirth;
    String address;
    int PAN;
    public void setdetails() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter user id:");
        userID = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter your name:");
        name = sc.nextLine();
        System.out.println("Enter your Date of birth");
        datOfBirth = sc.nextLine();
        System.out.println("Enter your address:");
        address = sc.nextLine();
        // sc.nextLine();
        System.out.println("Enter your PAN no:");
        PAN = sc.nextInt();
    }
}
class Admin extends User {
    String dataOfjoining;
    int salary;
    String permissibleOperations;
    public void setdetails() {
        super.setdetails();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Date of joining:");
        dataOfjoining = sc.nextLine();
        // sc.nextLine();
        System.out.println("Enter your salary:");
        salary = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter all permissible operations");
        permissibleOperations = sc.nextLine();
    }
}
class General extends User {
    String dataOfjoining;
    int salary;
    int dutyHourPerDay;
    public void setdetails() {
        super.setdetails();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Date of joining:");
        dataOfjoining = sc.nextLine();
        // sc.nextLine();
        System.out.println("Enter your salary:");
        salary = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter your duty hour per day:");
        dutyHourPerDay = sc.nextInt();
    }
}
class Item {
    int itemCode;
    int price;
    int availableQty;

    public void setDetails() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter item code:");
        itemCode = sc.nextInt();
        System.out.println("Enter price of your item:");
        price = sc.nextInt();
        int loop;
        do{
        loop=0;
        try
        {
        Scanner sd=new Scanner(System.in);
        System.out.println("Enter available Quantity");
        availableQty = sd.nextInt();
        }
        catch(InputMismatchException exception)
        {
            System.out.println("available quantity can't be float "+exception);
            loop=1;
        }
        }while(loop==1);
    }
    public void updateDetails() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter updated item code:");
        itemCode = sc.nextInt();
        System.out.println("Enter updated price of your item:");
        price = sc.nextInt();
        System.out.println("Enter updated available Quantity");
        availableQty = sc.nextInt();
        // validateavq();
    }
    public void displayDetails(int itemcode)
    {
        System.out.println("Available quantity of item of itemcode-"+itemcode+" is "+availableQty);
    }
}
class FoodItem extends Item {
    int dateOfExpiry;

    public void setexpiry() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter dateOfexpiry:");
        dateOfExpiry = sc.nextInt();
    }
}
class NonFoodItem extends Item {
    int returnCount;
}
class Return {
    String dateOfReturn;
    int itemCode;
    int quantity;
    int returnAmount;
    public void setdetails() {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter the date of return:");
        dateOfReturn = sc.nextLine();
        sc.nextLine();
        System.out.println("enter itemcode:");
        itemCode = sc.nextInt();
        System.out.println("enter quantity:");
        quantity = sc.nextInt();
        System.out.println("enter return amount:");
        returnAmount = sc.nextInt();
    }
}
class Sell {
    String dateOfSell;
    int itemCode;
    int quantity;
    int totalAmount;
    public void setdetails() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter item code:");
        itemCode=sc.nextInt();
        sc.nextLine();
        System.out.println("Enter date of selling:");
        dateOfSell = sc.nextLine();
        // sc.nextInt();
        System.out.println("Enter sell quantity:");
        quantity = sc.nextInt();
        System.out.println("Enter total amount:");
        totalAmount = sc.nextInt();
    }
    public void showdetails()
    {
        System.out.println("itemcode is-"+itemCode);
        System.out.println("date of sell:-"+dateOfSell);
        System.out.println("sold quantity:-"+quantity);
        System.out.println("sold amount:-"+totalAmount+"\n");
    }
    
}