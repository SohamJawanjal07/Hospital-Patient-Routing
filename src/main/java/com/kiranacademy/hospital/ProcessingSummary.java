package com.kiranacademy.hospital;

public class ProcessingSummary {

    int totalPendingRecords;
    int criticalCare;
    int generalCare;
    int validationFailed;
    int skippedDuplicate;
    int successfullyProcessed;

    public void increaseTotalPendingRecords() {
        totalPendingRecords++;
    }

    public void increaseCriticalCare() {
        criticalCare++;
    }

    public void increaseGeneralCare() {
        generalCare++;
    }

    public void increaseValidationFailed() {
        validationFailed++;
    }

    public void increaseSkippedDuplicate() {
        skippedDuplicate++;
    }

    public void increaseSuccessfullyProcessed() {
        successfullyProcessed++;
    }

    public void displaySummary() {

        System.out.println();
        System.out.println("---------------------------------------");
        System.out.println("PROCESSING SUMMARY");
        System.out.println("---------------------------------------");

        System.out.println(
                "Total Pending Records : "
                + totalPendingRecords);

        System.out.println(
                "Critical Care         : "
                + criticalCare);

        System.out.println(
                "General Care          : "
                + generalCare);

        System.out.println(
                "Validation Failed     : "
                + validationFailed);

        System.out.println(
                "Skipped Duplicate     : "
                + skippedDuplicate);

        System.out.println(
                "Successfully Processed: "
                + successfullyProcessed);
    }
}