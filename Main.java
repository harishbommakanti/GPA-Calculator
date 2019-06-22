import java.util.*;

public class Main
{
    private static int numPastSemesters = 0;
    private static double pastGPA = 0;
    private static double totalGPA = 0;
    private static double thisSemesterGPA = 0;

    private static String[] classNames = new String[8];
    private static boolean[] weightedCheck = new boolean[8];
    private static String[] classGrades = new String[8];
    private static double[] classGPAs = new double[8];

    public static void main(String[] args)
    {
        System.out.println("This program calculates your high school GPA based on Leander ISD's grade policies.");
        System.out.println("This uses data from previous semesters and calculates the data for the new semester.");

        enterPastData();
        enterSemesterClasses();
        forEachClass();
        calcClassAverages();
        calcGPAs();

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
        pastGPA = s.nextDouble();
    }

    public static void enterSemesterClasses()
    {
        Scanner g = new Scanner(System.in);
        System.out.println();
        System.out.println("Now, enter the data for the current semester.");
        System.out.println("First, enter in the names of your classes followed by an enter. Only go up to 8.");
        for (int i = 0; i < 8; i++)
        {
            classNames[i] = g.nextLine();
        }
    }

    public static void forEachClass()
    {
        for (int i = 0; i < 8; i++)
        {
            Scanner f = new Scanner(System.in);
            String name = classNames[i];

            System.out.println("Is " + name + " a weighted class? Check out https://www.leanderisd.org/departments/teaching_learning/college_and_career_pathways/course_catalog" +
                    " if you're not sure. Enter Y for yes or N for no.");
            String ifWeighted = f.next();
            weightedCheck[i] = (ifWeighted.equals("Y") || ifWeighted.equals("yes") || ifWeighted.equals("y"))? true:false;

            System.out.println("Does " + name + " count towards your GPA? Check out \n https://www.leanderisd.org/departments/teaching_learning/college_and_career_pathways/course_catalog" +
                    " if you're not sure. Enter Y for yes or N for no.");
            String ifCounts = f.next();
            if (!(ifCounts.equals("Y") || ifCounts.equals("yes") || ifCounts.equals("y"))
                numPastSemesters--;


            f.nextLine();
            do
            {
                System.out.println("For " + name + ", enter the six weeks averages and final grade (if not exempted) " +
                        "separated by a space on one line. \n" +
                        "For example, for 'World History' I might enter: 89 91 92 85. If you are exempt from the final," +
                        " enter -1 for the fourth space. \n" +
                        "Anything over 100 rounds down to 100.");
            } while (!checkIfValidResponse(f.nextLine(), i));
        }
    }

    private static boolean checkIfValidResponse(String grade, int pos)
    {
        StringTokenizer st = new StringTokenizer(grade);
        if (st.countTokens() != 4)
            return false;
        else
        {
            classGrades[pos] = grade;
            return true;
        }
    }

    public static void calcClassAverages()
    {
        for (int i = 0; i < 8; i++)
        {
            String each = classGrades[i];
            double classAverage = 0;

            StringTokenizer tok = new StringTokenizer(each);
            int gradeSum = Integer.parseInt(tok.nextToken()) + Integer.parseInt(tok.nextToken()) + Integer.parseInt(tok.nextToken());
            int finalGrade = Integer.parseInt(tok.nextToken());
            if (finalGrade == -1)
                classAverage = gradeSum/3.0;
            else
                classAverage = (gradeSum + finalGrade)/7.0;

            classAverage = Math.round(classAverage);
            double dif = (100-classAverage)/10.0;

            if (weightedCheck[i] == true)
                classGPAs[i] = 6-dif;
            else
                classGPAs[i] = 5-dif;
        }
    }

    public static void calcGPAs()
    {
        double total = 0;
        for (double each : classGPAs)
            total += each;

        thisSemesterGPA = total/8;

        totalGPA = ((numPastSemesters*pastGPA) + thisSemesterGPA)/(numPastSemesters+1);
    }
}