package ru.ncedu.java.tasks;
import java.util.Arrays;
public class ArrayVectorImpl implements ArrayVector {
    private double[] vector;

    @Override public ArrayVector clone() {
        ArrayVector cloneVector = new ArrayVectorImpl();
        cloneVector.set(get().clone());
        return cloneVector;
    }
    @Override public double get(int index) throws ArrayIndexOutOfBoundsException {
        if(index<0 && index>vector.length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return vector[index];
    }
    @Override public double getMax() {
        double max = vector[0];
        for(int i=0;i<vector.length;i++){
            if(vector[i]>max){
                max=vector[i];
            }
        }
        return max;
    }
    @Override public double getMin() {
        double min = vector[0];
        for(int i=0;i<vector.length;i++){
            if(vector[i]<min){
                min=vector[i];
            }
        }
        return min;
    }
    @Override public void mult(double factor) {
        for(int i=0;i<get().length;i++){
            get()[i]=get()[i]*factor;
        }

    }
    @Override public double getNorm() {
        double norm;
        double summ=0;
        for(int i=0;i<vector.length;i++){
            summ+=vector[i]*vector[i];
        }
        norm=Math.sqrt(summ);
        return norm;
    }
    @Override public void sortAscending() {
        double temp;
        for(int j=0;j<get().length;j++){
            for(int i=0;i<get().length-j-1;i++){
                if(get()[i]>get()[i+1]){
                    temp=get()[i];
                    get()[i]=get()[i+1];
                    get()[i+1]=temp;

                }
            }
        }


    }
    @Override public ArrayVector sum(ArrayVector anotherVector) {
        int size;
        if (anotherVector.getSize()>this.getSize()){
            size=this.getSize();
        }else size = anotherVector.getSize();

        for (int i=0;i<size;i++){
            vector[i]+=anotherVector.get()[i];
        }return this;
    }
    @Override public double scalarMult(ArrayVector anotherVector) {
        double result=0;
        int size;
        if(anotherVector.getSize()>=this.getSize()) {
            size=this.getSize();
        }else size=anotherVector.getSize();

        for (int i = 0; i <size; i++) {
            result+=vector[i]*anotherVector.get()[i];
        }
        return result;
    }
    @Override public double[] get() {
        return vector;
    }
    @Override public int getSize() {
        return vector.length;
    }
    /////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Задает все элементы вектора (определяет длину вектора).
     * Передаваемый массив не клонируется.
     * @param elements Не равен null
     */
    @Override public void set(double... elements) {
        vector=Arrays.copyOf(elements, elements.length);
    }
/**
     * Изменяет элемент по индексу. 
     * @param index В случае выхода индекса за пределы массива:<br/>
     *  а) если index<0, ничего не происходит;<br/>
     *  б) если index>=0, размер массива увеличивается так, чтобы index стал последним.
     */
    @Override public void set(int index, double value) {
        
        if((index>=0) && (index<getSize())){
            this.vector[index]=value;
        }
else
        if ((index>=getSize())&& (index>=0)){
            double [] vectorCopy= new double[index+1];
            System.arraycopy(vector,0,vectorCopy,0,vector.length);
            this.vector=vectorCopy;
            this.vector[index]=value;
            
         }
    }
}
