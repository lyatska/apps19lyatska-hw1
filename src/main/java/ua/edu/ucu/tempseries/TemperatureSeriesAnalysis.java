package ua.edu.ucu.tempseries;

import java.util.InputMismatchException;

public class TemperatureSeriesAnalysis {
    private double[] temperatureSeries;
    private int len;

    public TemperatureSeriesAnalysis() {

    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        for (int i = 0; i < len; i++){
            if (temperatureSeries[i] < -273){
                throw new InputMismatchException();
            }
        }
        this.temperatureSeries = temperatureSeries;
        this.len = temperatureSeries.length;

    }

    public double average() {
        if (len == 0){
            throw new IllegalArgumentException();
        }
        else {
            double sum = 0;
            for (int i = 0; i < len; i++){
                sum += temperatureSeries[i];
            }
            double avg = sum / len;
            return avg;
        }
    }

    public double deviation() {
        if (len == 0) {
            throw new IllegalArgumentException();
        } else {
            double avg = average();
            double dev = 0;

            for (int i = 0; i < len; i++) {
                dev += (temperatureSeries[i] - avg) * (temperatureSeries[i] - avg) / len;
            }
            dev = Math.sqrt(dev);
            return dev;
        }
    }

    public double min() {
        double min = temperatureSeries[0];
        for (int i = 0; i < len; i++){
            if (temperatureSeries[i] < min){
                min = temperatureSeries[i];
            }
        }
        return min;
    }

    public double max() {
        double max = temperatureSeries[0];
        for (int i =0; i< len; i++){
            if (temperatureSeries[i] > max){
                max = temperatureSeries[i];
            }
        }
        return max;
    }

    public double findTempClosestToZero() {
        if (len == 0){
            throw new IllegalArgumentException();
        }
        else{
            double tmp = temperatureSeries[0];
            for (int i = 1; i< len; i++){
                if (Math.abs(tmp) > Math.abs(temperatureSeries[i])){
                    tmp = temperatureSeries[i];
                }
                else if (Math.abs(tmp) == Math.abs(temperatureSeries[i])){
                    tmp = Math.abs(temperatureSeries[i]);
                }
            }
            return tmp;
        }
    }

    public double findTempClosestToValue(double tempValue) {
        if (len == 0){
            throw new IllegalArgumentException();

        }
        else{
            double tmp = temperatureSeries[0];
            for (int i = 1; i < len; i++){
                if (Math.abs(tempValue - tmp) > Math.abs(tempValue - temperatureSeries[i])){
                    tmp = temperatureSeries[i];
                }
            }
            return tmp;
        }

    }

    public double[] findTempsLessThen(double tempValue) {
        if (len == 0) {
            throw new IllegalArgumentException();
        } else {
            int amount = 0;
            for (int i = 0; i < len; i++) {
                if (tempValue > temperatureSeries[i]) {
                    amount++;
                }
            }
            double[] arr = new double[amount];
            int position = 0;
            for (int i = 0; i < len; i++) {
                if (tempValue > temperatureSeries[i]) {
                    arr[position] = temperatureSeries[i];
                    position++;
                }
            }
            return arr;


        }
    }

    public double[] findTempsGreaterThen(double tempValue) {
        if (len == 0) {
            throw new IllegalArgumentException();
        } else {
            int amount = 0;
            for (int i = 0; i < len; i++) {
                if (tempValue < temperatureSeries[i]) {
                    amount++;
                }
            }
            double[] arr = new double[amount];
            int position = 0;
            for (int i = 0; i < len; i++) {
                if (tempValue < temperatureSeries[i]) {
                    arr[position] = temperatureSeries[i];
                    position++;
                }
            }
            return arr;
        }
    }



    public TempSummaryStatistics summaryStatistics() {
        if (len == 0){
            throw new IllegalArgumentException();
        }
        else{
            TempSummaryStatistics statistics = new TempSummaryStatistics(average(), deviation(), min(), max());
            return statistics;
        }
    }

    public int addTemps(double... temps) {
        double[] arr = temperatureSeries;
        while ((arr.length + temps.length) > len) {
            temperatureSeries = new double[arr.length * 2];
        }
        int total = 0;
        int counter = 0;
        for (int i = 0; i < arr.length; i++) {
            temperatureSeries[i] = arr[i];
            counter++;
            total += arr[i];
        }
        for (int i = 0; i < temps.length; i++) {
            temperatureSeries[counter] = temps[i];
            counter++;
            total += temps[i];
        }
        return total;

    }
}
