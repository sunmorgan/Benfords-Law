import java.io.*;
import java.util.*;
import java.util.Arrays;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.stage.Stage;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

//Morgan Start
public class App extends Application {
    
        /**
    * This method takes in value from reader() in which is an array to do further 
    * calculations. It ultimately creates an xychart bar graph structure and displays graph
    * @return      launch
    * @see         Bargraph
    */
    @Override
    public void start(Stage stage) {
        //Initializing important variables
        int j = 1;
        int[] count = reader();
        float total = 0;
        float[] percentage = new float[9];

        //Finding out the amount of first digits
        for (int i : count) {
            total += i;
        } 

        //Finding the percentage for each digit at i
        for (int i = 0; i < count.length; i++) {
            percentage[i] = ((float) count[i] / total) * 100;
        }

        // Defining the axes
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setCategories(
                FXCollections.<String>observableArrayList(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9")));
        //label
        xAxis.setLabel("Digits");

        //label
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Percent");

        // Creating the Bar chart
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("BendFord Digit Comparison");

        // Prepare XYChart.Series objects by setting data
        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        
        //Inputs the percentage into bargraph data, also printing out the numerical values of the percentage
        for(int i=0; i<count.length; ++i) {
            String sj = String.valueOf(j);
            series1.getData().add(new XYChart.Data<>(sj, percentage[i]));
            System.out.println(j+": "+percentage[i]+"%");
            j++;
        }

        //Displays the possibility of fraud occurring
        if(percentage[0]>29&&percentage[0]<32) {
            System.out.println("Fraud likely did not occur with given data");
        } else {
            System.out.println("There is a great change fraud occured within given data");
        }

        // Setting the data to bar chart
        barChart.getData().addAll(series1);

        // Creating a Group object
        Group root = new Group(barChart);

        // Creating a scene object
        Scene scene = new Scene(root, 600, 400);

        // Setting title to the Stage
        stage.setTitle("Bendford");

        // Adding scene to the stage
        stage.setScene(scene);

        // Displaying the contents of the stage
        stage.show();
    }

    public static void main(String[] args) {
        //Starts the program
        launch(args);
    }
    
    /**
     * This method uses switch case to determine the amount of times
     * a digit appears in a number. Then returns that array
     * 
     * 
     * @return int[] counter
     * @see n/a
     */
    public static int[] reader() {
        Scanner ui = new Scanner(System.in);

        //Used to find the amount of fd appearance in each digit
        int o = 0;
        int t = 0;
        int thr = 0;
        int f = 0;
        int fi = 0;
        int s = 0;
        int sev = 0;
        int ei = 0;
        int n = 0;
        int[] counter = new int[9];

        BufferedReader reader;
        try {
            System.out.println("Please select the file you want to read, also make sure its in relative path:");
            System.out.println("Example: finalBendFord/src/sales1.csv");

            String read = ui.nextLine();
            // Scans/Reads the postal_codes.csv file
            reader = new BufferedReader(new FileReader(read));
            // Checks if the user input is a null value
            String line = reader.readLine();
            while (line != null) {
                // While loop through each line
                line = reader.readLine();
                if (line == null) {
                    break;
                    // Breaks if the value is null
                }
                // Nested substring to get the first digit
                String format = line.substring(4);
                String fdigit = format.substring(0, 1);

                //Since the substring is type string, typecast to int 
                int inum = Integer.parseInt(fdigit);

                //Switch case to add up the total times a first digit appears
                switch (inum) {
                    case 1:
                        ++o;
                        counter[0] = o;
                        break;
                    case 2:
                        ++t;
                        counter[1] = t;
                        break;
                    case 3:
                        ++thr;
                        counter[2] = thr;
                        break;
                    case 4:
                        ++f;
                        counter[3] = f;
                        break;
                    case 5:
                        ++fi;
                        counter[4] = fi;
                        break;
                    case 6:
                        ++s;
                        counter[5] = s;
                        break;
                    case 7:
                        ++sev;
                        counter[6] = sev;
                        break;
                    case 8:
                        ++ei;
                        counter[7] = ei;
                        break;
                    default:
                        ++n;
                        counter[8] = n;
                }
            }
            // Catches exceptions and prints out the exception
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Returns array counter that holds the amount of appearances for all numbers
        ui.close();
        return counter;
    }
} //Morgan End
    /*
    * Nicole Padoun - Export Digit Frequency into API 
    * @param String one, two, three, four, five, six, seven, eight, nine - printed out digit values (only used for graphics)
    * @param o, t, thr, f, fi, s, sev, ei, n - values of percentage 
    */
    public static void bendford() {
        //setup of final variables
        final static String one = "1";
        final static String two = "2";
        final static String three = "3";
        final static String four = "4";
        final static String five = "5";
        final static String six = "6";
        final static String seven = "7";
        final static String eight = "8";
        final static String nine = "9";
    
        /*
        * Nicole Padoun - start of graph
        * not sure how to docstring for javafx coding but this is the set up of graphics
        */
        @Override public void start(Stage stage) {
            stage.setTitle("Bar Chart Sample"); //setting titles
            final CategoryAxis xAxis = new CategoryAxis();
            final NumberAxis yAxis = new NumberAxis();
            final BarChart<String,Number> bc = 
                new BarChart<>(xAxis,yAxis);
              //slight hard coding here just to establish proper titles (easiest way on javafx)
            bc.setTitle("Benford's law results");
            xAxis.setLabel("Digit");       
            yAxis.setLabel("Percent");
            bc.setBarGap(3);

            XYChart.Series series1 = new XYChart.Series();
            series1.setName("results");
            //set up of all bars in bar chart
            series1.getData().add(new XYChart.Data(one, o));
            series1.getData().add(new XYChart.Data(two, t));
            series1.getData().add(new XYChart.Data(three, thr));
            series1.getData().add(new XYChart.Data(four, f));
            series1.getData().add(new XYChart.Data(five, fi)); 
            series1.getData().add(new XYChart.Data(six, s));    
            series1.getData().add(new XYChart.Data(seven, sev));    
            series1.getData().add(new XYChart.Data(eight, ei));    
            series1.getData().add(new XYChart.Data(nine, n));    


            Scene scene  = new Scene(bc,800,600);
            bc.getData().addAll(series1);
            stage.setScene(scene);
            stage.show();
        }
    } // end of main
} //end of class
