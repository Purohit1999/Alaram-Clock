import java.time.LocalTime;
import java.util.Scanner;
/*
 * MIT License
 * Copyright (c) 2024 Purohit1999
 */


public class EnhancedAlarmClock {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Enhanced Alarm Clock ===");
        System.out.print("Enter the alarm time in HH:MM format (24-hour clock): ");
        String alarmTimeInput = scanner.nextLine();

        try {
            // Parse the alarm time input
            String[] parts = alarmTimeInput.split(":");
            int alarmHour = Integer.parseInt(parts[0]);
            int alarmMinute = Integer.parseInt(parts[1]);

            // Create the LocalTime object for the alarm time
            LocalTime alarmTime = LocalTime.of(alarmHour, alarmMinute);

            // Get the current time
            LocalTime currentTime = LocalTime.now();

            // Calculate the time difference
            long timeDifference = calculateTimeDifference(currentTime, alarmTime);

            if (timeDifference > 0) {
                System.out.println("Alarm set successfully!");

                try {
                    // Display options to snooze or cancel
                    System.out.println("Options: ");
                    System.out.println("1. Let the alarm ring");
                    System.out.println("2. Snooze for 5 minutes");
                    System.out.println("3. Cancel the alarm");
                    System.out.print("Enter your choice: ");
                    int choice = scanner.nextInt();

                    if (choice == 1) {
                        // Sleep for the remaining time
                        Thread.sleep(timeDifference * 1000); // Convert seconds to milliseconds
                        System.out.println("Wake up! It’s time!");
                    } else if (choice == 2) {
                        // Snooze for an additional 5 minutes
                        System.out.println("Snoozing for 5 minutes...");
                        Thread.sleep((timeDifference + 300) * 1000); // Add 5 minutes (300 seconds)
                        System.out.println("Wake up! It's time!");
                    } else if (choice == 3) {
                        System.out.println("Alarm canceled.");
                    } else {
                        System.out.println("Invalid choice. Alarm will proceed as scheduled.");
                        Thread.sleep(timeDifference * 1000);
                        System.out.println("Wake up! It’s time!");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Invalid alarm time. Please enter a future time.");
            }

        } catch (Exception e) {
            System.out.println("Invalid input. Please enter the time in HH:MM format.");
        }

        scanner.close();
    }

    /**
     * Calculates the time difference between the current time and the alarm time.
     * Adjusts for cases where the alarm time is on the next day.
     *
     * @param currentTime The current time.
     * @param alarmTime The alarm time.
     * @return Time difference in seconds.
     */
    public static long calculateTimeDifference(LocalTime currentTime, LocalTime alarmTime) {
        int currentHour = currentTime.getHour();
        int currentMinute = currentTime.getMinute();
        int currentSecond = currentTime.getSecond();

        int alarmHour = alarmTime.getHour();
        int alarmMinute = alarmTime.getMinute();
        int alarmSecond = alarmTime.getSecond();

        // Convert the current time and alarm time to seconds
        long currentTimeInSeconds = (currentHour * 3600) + (currentMinute * 60) + currentSecond;
        long alarmTimeInSeconds = (alarmHour * 3600) + (alarmMinute * 60) + alarmSecond;

        // Calculate the time difference
        long timeDifference = alarmTimeInSeconds - currentTimeInSeconds;

        // Adjust the time difference if it's negative (alarm time is on the next day)
        if (timeDifference < 0) {
            timeDifference += 24 * 3600; // Add 24 hours (in seconds) to the time difference
        }

        return timeDifference;
    }
}

