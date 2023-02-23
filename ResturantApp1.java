import java.util.*;
import java.util.Scanner;
import java.util.AbstractList;
class FoodOrder{

    int[] MenuPlan;
    double BillAmount=0;        

    int[] foodItemID = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 };
        String[] foodName = { "Water Bottel 1ltr", "ThumsUp 1ltr", "Veg Grill Sandwich", "Veg Cheese Grill Sandwich",
            "Plain Uttapam", "Chicken FriedRice", "onion Dosa", "Masala Dosa", "Chicken mugalai", "Butter Roti", "White Rice",
            "Hyderabad Biriyani", "Mixed Biriyani", "Chicken Lollipops", "Vanilla Ice Cream" };
        double[] foodItemPrice = { 25.00, 55.00, 125.00, 225.00, 150.00, 100.00, 100.00, 150.00, 225.00, 35.00, 50.00,
            225.00, 250.00, 125.00, 50.00 };


    double CalculateBillAmount(int[]MenuPlan){
        for(int i=0;i<MenuPlan.length;i+=2){
            System.out.println("menu Id: "+MenuPlan[i]+" quantity: "+MenuPlan[i+1]);
            for(int j=0;j<MenuPlan.length;j++)
            {
                if(MenuPlan[i]==this.foodItemID[j]){
                    System.out.println("Item Name: "+foodName[j] + "    Item Price: "+foodItemPrice[j]);
                    BillAmount+= MenuPlan[i+1] * foodItemPrice[j];
                }
            }
        }
        
         return this.BillAmount;
    }
}
public class ResturantApp1{
    public static int[] foodPlan;
    public static void main(String[] args) {
        FoodOrder order1= new FoodOrder();
        int menu;
        char choice = 'X';
        Scanner sc=new Scanner(System.in);
        int index=0;
        do{
            System.out.println("\n1. place order\n2. Bill Status\n3. Check Collection");
            System.out.println("\n\nEnter your Choice ");
            menu=sc.nextInt();

            switch (menu){
                case 1:
                System.out.println("Enter order Details");
                do{
                    System.out.println("Enter ItemId: ");
                    foodPlan[index]=sc.nextInt();
                    index++;

                    System.out.println("Enter Quantity\n: ");
                    foodPlan[index]=sc.nextInt();
                    index++;

                    System.out.println("\n\nDo you want to add another item? Y/N");
                    choice =sc.next().charAt(0);
                }
                while(choice =='X' || choice == 'x');
                    System.out.println("The total bill is: "+order1.CalculateBillAmount(foodPlan));
                    break;
                case 2:
                System.out.println("Enter the order Id: ");
                break;

                case 3:
                    System.out.println("Today's collection is: ");
                    break;

                case 4:
                    break;
            }
            System.out.println("\n Press 'X' to continue: ");
            choice = sc.next().charAt(0);
        }
            while(choice=='X'||choice=='x');
            sc.close();
    }
}