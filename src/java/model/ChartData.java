/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author 11
 */
public class ChartData {
    private String label;
    private double data;

    public ChartData(String label, double data) {
        this.label = label;
        this.data = data;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public double getData() {
        return data;
    }

    public void setData(double data) {
        this.data = data;
    }
    
}
