package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        final int MAX_DAYS = 28; //The max number of days the driver works
        Driver[] driverArray = new Driver[MAX_DAYS]; //An array of type Driver with a size of 28
        
        int[] morningTakingArray = new int[MAX_DAYS]; //An array for morningTaking
        int[] afternoonTakingArray = new int[MAX_DAYS]; //An array for afternoonTaking
        int[] eveningTakingArray = new int[MAX_DAYS]; //An array for eveningTakings
        
        int[] takingsArray = new int[MAX_DAYS]; //An array for the whole day of takings
        
        inputDetails(driverArray, morningTakingArray, afternoonTakingArray, eveningTakingArray, takingsArray, MAX_DAYS);
        printMonthsTaking(driverArray, MAX_DAYS); //After all the values have been assigned prints out every day of the month
    }

    public static void inputDetails(Driver[] driverArray, int[] morningTakingArray, int[] afternoonTakingArray, int[] eveningTakingArray,int[] takingsArray, int MAX_DAYS)
    {
        for (int i = 0; i < MAX_DAYS; i++) {
            driverArray[i] = createDriversDay(); //Creates a Drivers record for each day
            if (driverArray[i].morning)
            {
                addToArray(morningTakingArray, "morning", i);
            }//Adds on the array of morning taking for that day
            if (driverArray[i].afternoon)
            {
                addToArray(afternoonTakingArray, "afternoon", i);
            }//Adds on the array of afternoon taking for that day
            if (driverArray[i].evening)
            {
                addToArray(eveningTakingArray, "evening", i);
            } //Adds on the array of evening taking for that day

            takingsArray[i] = morningTakingArray[i] + afternoonTakingArray[i] + eveningTakingArray[i]; //Adds onto for the whole days taking

            driverArray[i].morningTaking = morningTakingArray[i]; //Assigns the morning taking of the record to the morningTaking of that day
            driverArray[i].afternoonTaking = afternoonTakingArray[i]; //Assigns the afternoon taking of the record to the afternoonTaking of that day
            driverArray[i].eveningTaking = eveningTakingArray[i]; //Assigns the evening taking of the record to the eveningTaking of that day

            driverArray[i].fullTaking = takingsArray[i]; //Assigns the full taking of the record to the full taking of that day

            printDaysTaking(driverArray[i]); //Prints the days taking
        }
    } //For each day it takes in the details of that day and prints out the details for that day
    public static void printMonthsTaking(Driver[] driverArray, int MAX)
    {
        for (int i = 0; i < MAX; i++) {
            print("DAY " + (i+1));
            print(getStation(driverArray[i]));

            if (getMorning(driverArray[i]))
            {
                print("In the morning earned " + getMornTaking(driverArray[i]));
            }
            if (getAfternoon(driverArray[i]))
            {
                print("In the afternoon earned " + getAfterTaking(driverArray[i]));
            }
            if (getEvening(driverArray[i]))
            {
                print("In the evening earned " + getEveningTaking(driverArray[i]));
            }

            print("In the whole day earned " + getFullTaking(driverArray[i])); //Gets full taking of each period before adding up, along with the day number and the station visited

            print("-------------------------"); //Separating each day
        }
    }
    public static void printDaysTaking(Driver d)
    {
        if (getMorning(d)){
            print("In the morning you have earned " + getMornTaking(d) + " pounds");
        }
        if (getAfternoon(d))
        {
            print("In the morning you have earned " + getAfterTaking(d) + " pounds");
        }
        if (getEvening(d))
        {
            print("In the morning you have earned " + getEveningTaking(d) + " pounds");
        }
    }

    public static void addToArray(int[] takingArray, String period, int i){
        int taking = inputInt("How much did you earn in the " + period + "? (0 to quit)");
        while (taking != 0)
        {
            if (taking < 0)
            {
                print("Choose properly");
            }
            else {
                takingArray[i] = takingArray[i] + taking;
            }
            taking = inputInt("How much more have you earned?");
        }
    }
    public static Driver createDriversDay(){
        Driver d = new Driver(); //Creates a new driver
        d.station = checkStation();
        d.morning = checkMorning();
        d.afternoon = checkAfternoon();
        d.evening =checkEvening();
        return d;
    } //Creates a new driver record based on the days work;

    public static boolean checkMorning()
    {
        boolean bool = false;
        String option = inputString("Are you working in the morning (Y/N)"); //Asks the user whether they worked in the morning
        option = checkBoolChoice(option);
        if (option.equals("Y"))
        {
            bool = true;
        }

        return bool;
    } //Returns true if the driver worked in the morning otherwise returning false

    public static boolean checkAfternoon()
    {
        boolean bool = false;
        String option = inputString("Are you working in the afternoon (Y/N)"); //Asks the user whether they worked in the morning
        option = checkBoolChoice(option);
        if (option.equals("Y"))
        {
            bool = true;
        }

        return bool;
    } //Returns true if the driver worked in the afternoon otherwise returning false

    public static boolean checkEvening()
    {
        boolean bool = false;
        String option = inputString("Are you working in the evening (Y/N)"); //Asks the user whether they worked in the morning
        option = checkBoolChoice(option);
        if (option.equals("Y"))
        {
            bool = true;
        }

        return bool;
    } //Returns true if the driver worked in the evening otherwise returning false

    public static String checkBoolChoice(String option)
    {
        while (!option.equals("Y") && !option.equals("N"))
        {
            option = inputString("Choose from the two options");
        }
        return option;
    } //Checks the choice is Y or N only
    public static String checkStation()
    {
        int stationNumber = inputInt("What station did you work at?\n1. Kings Cross\n2. Liverpool Street\n3. Paddington \n4. Euston");
        String station;
        while (stationNumber > 4 || stationNumber < 1)
        {
            stationNumber = inputInt("Choose from 1 to 4");
        }

        if (stationNumber == 1)
        {
            station = "Kings Cross";
        }
        else if (stationNumber == 2)
        {
            station = "Liverpool Street";
        }
        else if (stationNumber == 3)
        {
            station = "Paddington Street";
        }
        else {
            station = "Euston";
        }

        return station;
    } //Finds the station they worked at and returning the string station
    public static void print(String m) {
        System.out.println(m);
    } //Prints a message

    public static String inputString(String m) {
        String answer;
        Scanner scanner = new Scanner(System.in);

        print(m);
        answer = scanner.nextLine();
        return answer;
    } //Returns a string input

    public static int inputInt(String m) {
        int answer;
        Scanner scanner = new Scanner(System.in);

        print(m);
        answer = scanner.nextInt();
        return answer;
    } //Returns an integer input

    public static String getStation(Driver d) {
        return d.station;
    } //Accessor method for field station

    public static boolean getMorning(Driver d) {
        return d.morning;
    } //Accessor method for field morning

    public static boolean getAfternoon(Driver d) {
        return d.afternoon;
    } //Accessor method for field afternoon

    public static boolean getEvening(Driver d) {
        return d.evening;
    } //Accessor method for field evening

    public static int getMornTaking(Driver d){
        return d.morningTaking;
    } //Accessor method for field morningTaking

    public static int getAfterTaking(Driver d){
        return d.afternoonTaking;
    } //Accessor method for field afternoonTaking

    public static int getEveningTaking(Driver d){
        return d.eveningTaking;
    } //Accessor method for field afternoonTaking

    public static int getFullTaking(Driver d){
        return d.fullTaking;
    } //Accessor method for field fullTaking
}

class Driver{
    String station; //What station they work on
    boolean morning; //Whether they worked in the morning
    boolean afternoon; //Whether they worked in the afternoon
    boolean evening; //Whether they worked in the afternoon

    int morningTaking;
    int afternoonTaking;
    int eveningTaking;

    int fullTaking;
}
