// Main.java — Students version
import java.io.*;       // Used for file input operations
import java.util.*;     // Used for Scanner

public class Main {

    // Total number of months
    static final int MONTHS = 12;

    // Total number of days per month
    static final int DAYS = 28;

    // Total number of commodities
    static final int COMMS = 5;

    // Names of commodities (order is important for indexing)
    static String[] commodities = {"Gold", "Oil", "Silver", "Wheat", "Copper"};

    // Names of months (index 0 = January, index 11 = December)
    static String[] months = {
            "January","February","March","April","May","June",
            "July","August","September","October","November","December"
    };

    // 3D array to store profit data
    // Dimensions: [month][day][commodity]
    static int[][][] profitData = new int[MONTHS][DAYS][COMMS];

    // ======== REQUIRED METHOD LOAD DATA ========

    // Reads all monthly data files and stores profits into the profitData array
    public static void loadData() {

        // Loop through all months
        for (int m = 0; m < MONTHS; m++) {
            Scanner sc = null;

            try {
                // Construct file path for the current month
                String filename = "Data_Files/" + months[m] + ".txt";
                sc = new Scanner(new File(filename));

                // Skip the header line (Day,Commodity,Profit)
                if (sc.hasNextLine()) {
                    sc.nextLine();
                }

                // Read each line in the file
                while (sc.hasNextLine()) {
                    String line = sc.nextLine();

                    // Split the line by commas
                    String[] parts = line.split(",");

                    // Convert day to zero-based index
                    int day = Integer.parseInt(parts[0]) - 1;

                    // Read commodity name
                    String comm = parts[1];

                    // Read profit value
                    int profit = Integer.parseInt(parts[2]);

                    // Find the index of the commodity
                    int commIndex = -1;
                    for (int c = 0; c < COMMS; c++) {
                        if (commodities[c].equals(comm)) {
                            commIndex = c;
                            break;
                        }
                    }

                    // Store profit if indices are valid
                    if (commIndex != -1 && day >= 0 && day < DAYS) {
                        profitData[m][day][commIndex] = profit;
                    }
                }
            } catch (Exception e) {
                // Silent fail as required by the project rules
            } finally {
                // Close the scanner if it was opened
                if (sc != null) {
                    sc.close();
                }
            }
        }
    }

    // ======== 10 REQUIRED METHODS ========

    // 1
    // Returns the most profitable commodity in the given month
    public static String mostProfitableCommodityInMonth(int month) {

        // Check for invalid month
        if (month < 0 || month >= MONTHS) {
            return "INVALID_MONTH";
        }

        // Array to store total profit for each commodity
        int[] totals = new int[COMMS];

        // Sum profits for each commodity across all days of the month
        for (int d = 0; d < DAYS; d++) {
            for (int c = 0; c < COMMS; c++) {
                totals[c] += profitData[month][d][c];
            }
        }

        // Find the commodity with the maximum total profit
        int maxIndex = 0;
        for (int c = 1; c < COMMS; c++) {
            if (totals[c] > totals[maxIndex]) {
                maxIndex = c;
            }
        }

        // Return result in the required format
        return commodities[maxIndex] + " " + totals[maxIndex];
    }

    // 2
    // Returns the total profit of all commodities on a specific day of a month
    public static int totalProfitOnDay(int month, int day) {

        // Check if month or day is invalid
        if (month < 0 || month >= MONTHS || day < 1 || day > DAYS) {
            return -99999;
        }

        // Variable to store total profit
        int total = 0;

        // Convert day number to zero-based index
        int dayIndex = day - 1;

        // Sum profit of all commodities for the given day
        for (int c = 0; c < COMMS; c++) {
            total += profitData[month][dayIndex][c];
        }

        // Return the calculated total profit
        return total;
    }

    // 3
// Returns the total profit of a given commodity between two days (inclusive) across all months
    public static int commodityProfitInRange(String commodity, int from, int to) {

        // Check if the given day range is invalid
        if (from < 1 || to > DAYS || from > to) {
            // Return error value for invalid input
            return -99999;
        }

        // Find the index of the given commodity in the commodities array
        int commIndex = -1;
        for (int c = 0; c < COMMS; c++) {
            if (commodities[c].equals(commodity)) {
                commIndex = c;
                break;
            }
        }

        // If commodity is not found, return error value
        if (commIndex == -1) {
            return -99999;
        }

        // Variable to store total profit
        int total = 0;

        // Loop through all months
        for (int m = 0; m < MONTHS; m++) {

            // Loop through the given day range (convert to zero-based index)
            for (int d = from - 1; d < to; d++) {
                total += profitData[m][d][commIndex];
            }
        }

        // Return the calculated total profit
        return total;
    }

    // 4
// Returns the day number (1–28) with the highest total profit in the given month
    public static int bestDayOfMonth(int month) {

        // Check if the given month is invalid
        if (month < 0 || month >= MONTHS) {
            // Return error value for invalid month
            return -1;
        }

        // Variable to store the best day (1-based)
        int maxDay = 0;

        // Variable to store the maximum total profit found so far
        int maxProfit = Integer.MIN_VALUE;

        // Loop through all days of the month
        for (int d = 0; d < DAYS; d++) {

            // Variable to store total profit for the current day
            int dayTotal = 0;

            // Sum profit of all commodities for the current day
            for (int c = 0; c < COMMS; c++) {
                dayTotal += profitData[month][d][c];
            }

            // Update best day if a higher total profit is found
            if (dayTotal > maxProfit) {
                maxProfit = dayTotal;
                maxDay = d + 1; // Convert to 1-based day number
            }
        }

        // Return the best day of the month
        return maxDay;
    }


    // 5
    public static String bestMonthForCommodity(String comm) {
        return "DUMMY";
    }

    // 6
    public static int consecutiveLossDays(String comm) {
        return 1234;
    }

    // 7
    public static int daysAboveThreshold(String comm, int threshold) {
        return 1234;
    }

    // 8
    public static int biggestDailySwing(int month) {
        return 1234;
    }

    // 9
    public static String compareTwoCommodities(String c1, String c2) {
        return "DUMMY";
    }

    // 10
    public static String bestWeekOfMonth(int month) {
        return "DUMMY";
    }

    public static void main(String[] args) {
        loadData();
        System.out.println("Data loaded – ready for queries");
    }
}

    // 3D array to store profit data
    // Dimensions: [month][day][commodity]
    static int[][][] profitData = new int[MONTHS][DAYS][COMMS];

    // ======== REQUIRED METHOD LOAD DATA ========

    // Reads all monthly data files and stores profits into the profitData array
    public static void loadData() {

        // Loop through all months
        for (int m = 0; m < MONTHS; m++) {
            Scanner sc = null;

            try {
                // Construct file path for the current month
                String filename = "Data_Files/" + months[m] + ".txt";
                sc = new Scanner(new File(filename));

                // Skip the header line (Day,Commodity,Profit)
                if (sc.hasNextLine()) {
                    sc.nextLine();
                }

                // Read each line in the file
                while (sc.hasNextLine()) {
                    String line = sc.nextLine();

                    // Split the line by commas
                    String[] parts = line.split(",");

                    // Convert day to zero-based index
                    int day = Integer.parseInt(parts[0]) - 1;

                    // Read commodity name
                    String comm = parts[1];

                    // Read profit value
                    int profit = Integer.parseInt(parts[2]);

                    // Find the index of the commodity
                    int commIndex = -1;
                    for (int c = 0; c < COMMS; c++) {
                        if (commodities[c].equals(comm)) {
                            commIndex = c;
                            break;
                        }
                    }

                    // Store profit if indices are valid
                    if (commIndex != -1 && day >= 0 && day < DAYS) {
                        profitData[m][day][commIndex] = profit;
                    }
                }
            } catch (Exception e) {
                // Silent fail as required by the project rules
            } finally {
                // Close the scanner if it was opened
                if (sc != null) {
                    sc.close();
                }
            }
        }
    }

    // ======== 10 REQUIRED METHODS ========

    // 1
    // Returns the most profitable commodity in the given month
    public static String mostProfitableCommodityInMonth(int month) {

        // Check for invalid month
        if (month < 0 || month >= MONTHS) {
            return "INVALID_MONTH";
        }

        // Array to store total profit for each commodity
        int[] totals = new int[COMMS];

        // Sum profits for each commodity across all days of the month
        for (int d = 0; d < DAYS; d++) {
            for (int c = 0; c < COMMS; c++) {
                totals[c] += profitData[month][d][c];
            }
        }

        // Find the commodity with the maximum total profit
        int maxIndex = 0;
        for (int c = 1; c < COMMS; c++) {
            if (totals[c] > totals[maxIndex]) {
                maxIndex = c;
            }
        }

        // Return result in the required format
        return commodities[maxIndex] + " " + totals[maxIndex];
    }

    // 2
    // Returns the total profit of all commodities on a specific day of a month
    public static int totalProfitOnDay(int month, int day) {

        // Check if month or day is invalid
        if (month < 0 || month >= MONTHS || day < 1 || day > DAYS) {
            return -99999;
        }

        // Variable to store total profit
        int total = 0;

        // Convert day number to zero-based index
        int dayIndex = day - 1;

        // Sum profit of all commodities for the given day
        for (int c = 0; c < COMMS; c++) {
            total += profitData[month][dayIndex][c];
        }

        // Return the calculated total profit
        return total;
    }

    // 3
    public static int commodityProfitInRange(String commodity, int from, int to) {
        return 1234;
    }

    // 4
    public static int bestDayOfMonth(int month) {
        return 1234;
    }

    // 5
    public static String bestMonthForCommodity(String comm) {
        return "DUMMY";
    }

    // 6
    public static int consecutiveLossDays(String comm) {
        return 1234;
    }

    // 7
    public static int daysAboveThreshold(String comm, int threshold) {
        return 1234;
    }

    // 8
    public static int biggestDailySwing(int month) {
        return 1234;
    }

    // 9
    public static String compareTwoCommodities(String c1, String c2) {
        return "DUMMY";
    }

    // 10
    public static String bestWeekOfMonth(int month) {
        return "DUMMY";
    }

    public static void main(String[] args) {
        loadData();
        System.out.println("Data loaded – ready for queries");
    }
}

