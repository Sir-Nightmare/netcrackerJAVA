package ru.ncedu.java.tasks;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Created by tumas on 27.04.2016.
 */
public class BusinessCardImpl implements BusinessCard{
    private String name;
    private String lastName;
    private String deparment;
    private String birthDate;
    private String gender;
    private Integer salary;
    private String phone;
    private Date date;


    /**
     * This method obtains (via Scanner) information from an input stream
     * that contains the following information about an Employee:<br/>
     * Name - String<br/>
     * Last name - String<br/>
     * Department - String <br/>
     * Birth date - String in format: "DD-MM-YYYY", where DD - two-digits birth date,
     * MM - two-digits month of birth, YYYY - year of birth<br/>
     * Gender : F or M - Character<br/>
     * Salary : number from 100 to 100000<br/>
     * Phone number : 10-digits number<br/>
     * All entries are separated with ";" sign<br/>
     * The format of input is the following:<br/>
     * Name;Last name;Department;Birth date;Gender;Salary;Phone number
     *
     * @param scanner Data source
     * @return Business Card
     * @throws InputMismatchException Thrown if input value
     *                                does not correspond to the data format given above (for example,
     *                                if phone number is like "AAA", or date format is incorrect, or salary is too high)
     * @throws NoSuchElementException Thrown if input stream hasn't enough values
     *                                to construct a BusinessCard
     */

    @Override
    public BusinessCard getBusinessCard(Scanner scanner) {

        String[] split=new String[0];
        if (scanner.hasNext())
            split = scanner.nextLine().split(";");
        if(split.length<7){
            throw new NoSuchElementException();
        }
        this.name=split[0];
        this.lastName=split[1];
        this.deparment=split[2];
            this.birthDate=split[3];

        try {
            date = new SimpleDateFormat("dd-MM-yyyy").parse(birthDate);
            this.gender = split[4];
            this.salary = new Integer(split[5]);
            this.phone = split[6];
            if (salary < 100 || salary > 100000 || split[6].length() != 10) {
                throw new InputMismatchException();
            }
            if (!gender.equals("F") && !gender.equals("M")) {
                throw new InputMismatchException();
            }
        }  catch (ParseException|NumberFormatException e){
            throw new InputMismatchException();
        }
        return this;
    }


    @Override public String getEmployee() {
        return this.name+" "+this.lastName;
    }
    @Override public String getDepartment() {
        return this.deparment;
    }
    @Override public int getSalary() {
        return salary;
    }
    @Override public String getGender() {
        if(this.gender.equals("F")){
            return "Female";
        }else return "Male";
    }
    /**
     * @return Employee Age in years, like 69
     */
    @Override
    public int getAge() {
        long age;
        age = (System.currentTimeMillis() - date.getTime())
                / 1000 / 60 / 60 / 24 / 365;
        return (int)age;
    }
    /**
     * @return Employee Phone Number in the following format: "+7 123-456-78-90"
     */ @Override public String getPhoneNumber() {
        return "+7 "+phone.substring(0,3)+"-"+
                phone.substring(3,6)+ "-"+
                phone.substring(6,8)+"-"+
                phone.substring(8,10);
    }

    public static void main (String[] args){
        Scanner sc=new Scanner("Alexander;Kharichkin;DSI;07-09-1988;M;1000;9032606540");
        System.out.println(new BusinessCardImpl().getBusinessCard(sc).getSalary());
    }
}
