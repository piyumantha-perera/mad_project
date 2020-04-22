package com.example.mad_project.Employee_Details;

public class Salary {

    private String name;
    private String basSal;
    private String travAll;
    private String ot;
    private String salAdva;
    private String netSal;
    private String date;


    public Salary(String name, String basSal, String travAll, String ot, String salAdva, String netSal, String date) {

        this.name = name;
        this.basSal = basSal;
        this.travAll = travAll;
        this.ot = ot;
        this.salAdva = salAdva;
        this.netSal = netSal;
        this.date = date;

    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBasSal() {
        return basSal;
    }

    public void setBasSal(String basSal) {
        this.basSal = basSal;
    }

    public String getTravAll() {
        return travAll;
    }

    public void setTravAll(String travAll) {
        this.travAll = travAll;
    }

    public String getOt() {
        return ot;
    }

    public void setOt(String ot) {
        this.ot = ot;
    }

    public String getSalAdva() {
        return salAdva;
    }

    public void setSalAdva(String salAdva) {
        this.salAdva = salAdva;
    }

    public String getNetSal() {
        return netSal;
    }

    public void setNetSal(String netSal) {
        this.netSal = netSal;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}



