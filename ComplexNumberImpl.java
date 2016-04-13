package com.netcracker.edu.java.tasks;

public class ComplexNumberImpl implements ComplexNumber {
    private double re, im;


    @Override public double getRe() {
        return re;
    }
    @Override public double getIm() {
        return im;
    }
    @Override public boolean isReal() {
        if (im == 0) {
            return true;
        } else return false;
    }
    @Override public void set(double re, double im) {
        this.re = re;
        this.im = im;
    }
    @Override public int compareTo(ComplexNumber other) {
        if ((getIm() * getIm() + getRe() * getRe()) < (other.getRe() * other.getRe() + other.getIm() * other.getIm())) {
            return -1;
        } else if ((getIm() * getIm() + getRe() * getRe()) > (other.getRe() * other.getRe() + other.getIm() * other.getIm())) {
            return 1;
        } else return 0;
    }
    @Override public void sort(ComplexNumber[] array) {

        for (int i = array.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if ((array[j].getIm() * array[j].getIm() + array[j].getRe() * array[j].getRe()) > (array[j + 1].getIm() * array[j + 1].getIm() + array[j + 1].getRe() * array[j + 1].getRe())) {
                    ComplexNumber tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                }
            }
        }
    }
    @Override public boolean equals(Object other){
        if (other == this)
            return true;
        if (other instanceof ComplexNumber) {
            ComplexNumber temp = (ComplexNumber) other;
            if(temp.getIm() == this.getIm() && this.getRe() == temp.getRe()){
                return true;
            } else return false;
        } else return false;
    }
    @Override public ComplexNumber copy() {
        ComplexNumber cloneComplexNumber = new ComplexNumberImpl();
        cloneComplexNumber.set(this.re, this.im);
        return cloneComplexNumber;
    }
    @Override public ComplexNumber negate() {
        re *= -1;
        im *= -1;
        return this;
    }
    @Override public ComplexNumber multiply(ComplexNumber arg2) {
        double chre= re * arg2.getRe() - im* arg2.getIm();
        double chim = im* arg2.getRe()+ re * arg2.getIm();
        re=chre;
        im=chim;
        return this;
    }
    @Override public ComplexNumber add(ComplexNumber arg2) {
        re= this.getRe() + arg2.getRe();
        im= this.getIm() + arg2.getIm();
        return this;
    }
    @Override public String toString(){
        String slovo;
        if(this.getIm()!=0&&this.getRe()!=0){
            slovo=this.getRe()+"+"+this.getIm()+"i";
        }else if(this.getIm()==0&&this.getRe()!=0){
            slovo=String.valueOf(this.getRe());
        }else slovo=this.getIm()+"i";
        return slovo;
    }
    @Override public ComplexNumber clone() throws CloneNotSupportedException {
        ComplexNumber cloneObj = new ComplexNumberImpl();
        cloneObj.set(this.re,this.im);
        if (cloneObj==null) {
            throw new CloneNotSupportedException();
        }
        return cloneObj;
    }
    /**
     * Parses the given string value and sets the real and imaginary parts of this number accordingly.<br/>
     * The string format is "re+imi", where re and im are numbers (floating point or integer) and 'i' is a special symbol
     *  denoting imaginary part (if present, it's always the last character in the string).<br/>
     * Both '+' and '-' symbols can be the first characters of re and im; but '*' symbol is NOT allowed.<br/>
     * Correct examples: "-5+2i", 1+i", "+4-i", "i", "-3i", "3". Incorrect examples: "1+2*i", "2+2", "j".<br/>
     * Note: explicit exception generation is an OPTIONAL requirement for this task,
     *   but NumberFormatException can be thrown by {@link Double#parseDouble(String)}).<br/>
     * Note: it is not reasonable to use regex while implementing this method: the parsing logic is too complicated.
     * @param value
     * @throws NumberFormatException if the given string value is incorrect
     */
    @Override public void set(String value) throws NumberFormatException {
        int max;
        int vm=value.lastIndexOf("-");
        int vp=value.lastIndexOf("+");
        if(vm>vp){
            max=vm;
        }else max=vp;



       try{
            if(value.indexOf("i")==-1){
                Double x=new Double(value);
                re=x;
                im=0.0;
                System.out.println(re);
                System.out.println(im);
            }else if (value.lastIndexOf("i")==0){
                re=0.0;
                im=1.0;
                System.out.println(re);
                System.out.println(im);
            }else

                //i существует и кроме нее ещё есть как минимум одно число
                if((max==0)&&(value.lastIndexOf("i")==1)){
                    re=0.0;
                    String help =value.charAt(0)+"1";
                    Double y=new Double(help);
                    im=y;
                    System.out.println(re);
                    System.out.println(im);
                }else if((max<=0)){
                    re=0.0;
                    Double y=new Double(value.substring(0,value.length()-1));
                    im=y;
                    System.out.println(re);
                    System.out.println(im);
                }else if(max==(value.length()-2)){
                    Double x=new Double(value.substring(0,max));
                    String help2 =value.charAt(max)+"1";
                    Double y=new Double(help2);
                    re=x;
                    im=y;
                    System.out.println(re);
                    System.out.println(im);
                }else if (max>0){

                    Double x=new Double(value.substring(0,max));
                    //String help2 =value.charAt(max)+"1";
                    Double y=new Double(value.substring(max,value.length()-1));
                    re=x;
                    im=y;
                    System.out.println(re);
                    System.out.println(im);
                }

        }catch (NumberFormatException e){
            throw new NumberFormatException();
        }
}}