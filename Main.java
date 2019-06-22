import java.util.*;

public class Main
{
    private static int numPastSemesters = 0;
    private static double pastGPA = 0;
    private static double totalGPA = 0;

    public static void main(String[] args)
    {
        System.out.println("This program calculates your high school GPA based on Leander ISD's grade policies.");
        System.out.println("This uses data from previous semesters and calculates the data for the new semester.");
        enterPastData();
        enterSemesterData();
        enterFinalData();
        System.out.println("Your total GPA is: " + totalGPA);
    }

    public static void enterPastData()
    {
        System.out.println();
        Scanner s = new Scanner(System.in);
        System.out.println("Enter the number of past high school semesters. For example, if you are currently ending " +
                "the first semester of sophomore year, you would enter 2.");
        numPastSemesters = s.nextInt();
        System.out.println("Enter your current GPA as recorded on Naviance.");
        pastGPA = s.nextInt();
    }

    public static void enterSemesterData()
    {
    }

    public static void enterFinalData()
    {
    }
}