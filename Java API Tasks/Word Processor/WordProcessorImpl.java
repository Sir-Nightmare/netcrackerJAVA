package ru.ncedu.java.tasks;

import java.io.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;


public class WordProcessorImpl implements WordProcessor {

    private Set<String> setOfWords=new HashSet<>();
    private String string=null;

    @Override public String getText() {
        return string;
    }
    @Override public void setSource(FileInputStream fis) throws IOException {
        StringBuilder stringBuffer=new StringBuilder();
        int charOfByte;
        if(fis==null){
            throw new IllegalArgumentException();
        }else {
            try {

                while ((charOfByte=fis.read())!=-1){
                    stringBuffer.append((char)charOfByte);
                }
            }catch (Exception e){
                throw new IOException();
            }
            string=stringBuffer.toString();
        }

    }
    @Override public Set<String> wordsStartWith(String begin) {
        Set<String> set=new HashSet<>();
        if(string==null){
            throw new IllegalStateException();
        }else {
            String[] strArr=string.split("\\s");
            for(int i=0;i<strArr.length;i++){
                set.add(strArr[i]);
            }
            if(begin==null) return set;
            if(begin.equals(" ")) return set;

            for (String str : set) {
                if (str.startsWith(begin.toLowerCase())) {
                    setOfWords.add(str);
                }
            }
        }
        return setOfWords;
    }
    @Override public void setSource(String src) {
        if(src==null){
            throw new IllegalArgumentException();
        }else {
            string=src;

        }
    }
    @Override public void setSourceFile(String srcFile) throws IOException {
        if(srcFile==null){
            throw new IllegalArgumentException();
        }else {
            try {
                FileInputStream inStream = new FileInputStream(srcFile);
                setSource(inStream);
                inStream.close();
            }catch (Exception e){
                throw new IOException();
            }
        }
    }




}

