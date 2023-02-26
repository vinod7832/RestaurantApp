import java.util.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.*;
class ProjectTask{
    Scanner scr=new Scanner(System.in);
    ArrayList<ArrayList<String>> arr1=new ArrayList<ArrayList<String>>();
    ArrayList<ArrayList<String>> arr2=new ArrayList<ArrayList<String>>();
    ArrayList<Integer> arr3=new ArrayList<>();
    ArrayList<Integer> arr4=new ArrayList<>();
    void readfiles(){
    try {
        Scanner scr1=new Scanner(new FileReader("menuList.csv"));
        Scanner scr2=new Scanner(new FileReader("orderDetails.csv"));
        String st;
        while(scr1.hasNext()){
            st=scr1.nextLine();
            String[] str=st.split(",");
            List<String> dup=Arrays.asList(str);
            ArrayList<String> a1=new ArrayList<>(dup);
            
            arr1.add(a1);
        }
        while(scr2.hasNext()){
            String st1=scr2.nextLine();
            String[] str1=st1.split(",");
            List<String> dup1=Arrays.asList(str1);
            ArrayList<String> a2=new ArrayList<>(dup1);
            arr2.add(a2);

        }
    }
    catch (Exception e) {
        System.out.println("Running Error");
    }
    }
    void display(ArrayList<ArrayList<String>>a1){
        int n=a1.size();
        for(int i=0;i<n;i++){
            ArrayList<String> dump=a1.get(i);
            for (String string : dump) {
                System.out.print(string+" ");                
            }
            System.out.println();
        }
    }
    void menuBar(){
        String arr[]={"To palce Order","View the total collection for today","To Cancel the bill"};
        int n=arr.length;
        for(int i=0;i<n;i++){
            System.out.print(i+1+"-"+arr[i]+"\n");
        }
        System.out.print("Enter Your Choice: ");
    }
    void PlaceOrder(){
        System.out.println("To Place Order");
        display(arr1);
        ToOrder();
    }
    void Collection(){
        scr.nextLine();
        System.out.println("View the total collection for today");
        System.out.print("Enter Date: ");
        String s=scr.nextLine();
        Double collection=0.0;
        for(int i=0;i<(arr2.size());i++){
            ArrayList<String> s1=arr2.get(i);
            if((s1.get(1)).equals(s)){
                Double b=Double.parseDouble(s1.get(2));
                collection+=b;
                System.out.println(s1+"\n");
            }  
        }
        System.out.println("Total collection of day: "+collection);

    }
    void ToCancelBill(){
        System.out.println(" To Cancel the bill:: ");
        display(arr2);
        System.out.print("Enter the id in Above list:: ");
        int n=scr.nextInt();
        int v=arr2.size();
        if(n>v){
            System.out.println("Enter Valid Id");
        }
        else{
            n=n-1;
            (arr2.get(n)).set(4, "cancelled");
            try {
                FileWriter objq=new FileWriter("orderDetails.csv",false);
                for(int i=0;i<(arr2.size());i++){
                    ArrayList<String>str1=arr2.get(i);
                    String str11=String.join(",",str1);
                    str11+="\n";
                    FileWriter objq1=new FileWriter("orderDetails.csv",true);
                    objq1.write(str11);
                    objq1.close();
                }
                    objq.close();
                    display(arr2);
                    System.out.println("Cancelled order");
            } catch (Exception e) {
                System.out.println("Error");
            }
        }


    }
    void Appdetails(){
        int n=scr.nextInt();
        switch (n) {
            case 1:
                PlaceOrder();
                break;
            case 2:
                Collection();
                break;
            case 3:
                ToCancelBill();
                break;
        
            default:
                System.out.println("Enter Valid number");
                break;
        }
    }
    void ToOrder(){
        System.out.print("Enter order Id: ");
        int n=scr.nextInt();
        System.out.print("Enter Quantity: ");
        int m=scr.nextInt();
        arr3.add(n);
        arr4.add(m);
        System.out.print("If you to order again Yes->y or No->x: ");
        char ch=scr.next().charAt(0);
        if(ch=='y'){
            ToOrder();
        }
        else{
            int ordercount=arr3.size();
            Double total=0.0;
            System.out.print("check your details:: ");
            for(int i=0;i< ordercount;i++){
                int k=arr3.get(i);
                int l=arr4.get(i);
                ArrayList<String> dump2=arr1.get(k-1);
                Double a=Double.parseDouble(dump2.get(2));
                total+=a*l;
                System.out.println(arr3.get(i)+" ");
            }
            System.out.println("total:  "+total);
            System.out.print("Do you want to confirm order Yes-y or No-x: ");
            char ch1=scr.next().charAt(0);
            if(ch1=='y'){
                String s=",";
                LocalDate date1= LocalDate.now();
                DateTimeFormatter obj22=DateTimeFormatter.ofPattern("d-MMM-yy");
                String date=date1.format(obj22);
                int x=arr2.size()+1;
                String time=x+s+date+s+total+.00+s;
                for(int i=0;i<(arr3.size());i++){
                    time+=arr3.get(i)+" ";
                    time+=arr4.get(i)+" ";
                }
                time+=s+"Approved";
                try {
                    File newn=new File("orderDetails.csv");
                    FileWriter obj2=new FileWriter(newn,true);
                    obj2.write("\n"+time);
                    obj2.close();
                } catch (Exception e) {
                    System.out.println("error");
                }
                
                System.out.println("Thank You Visit Again");
            }
            else{
                menuBar();
                Appdetails();
            }      
            
        }
    }
    
}
public class ResturantApp{
 public static void main(String[] args) {
    ProjectTask obj=new ProjectTask();
    obj.readfiles();
    obj.menuBar();
    obj.Appdetails();  
    
 }   
}