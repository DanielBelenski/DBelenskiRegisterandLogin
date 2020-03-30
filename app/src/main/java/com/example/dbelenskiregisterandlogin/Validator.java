package com.example.dbelenskiregisterandlogin;

import java.util.Calendar;

public class Validator {
    public Validator(){

    }
    public Boolean isEmpty(String s){
        if (s.length() == 0)
            return false;
        else{
            return true;
        }
    }

    public Boolean uNameValidate(String s){
        //TODO: check database for username
        if (s.length() < 5){
            return false;
        }
        else{
            return true;
        }
    }
    public Boolean registerPassValidate(String s){
        if (s.length() < 5){
            return false;
        }
        Boolean num = false;
        for(int i=0; i < s.length(); i++){
            if (Character.isDigit(s.charAt(i))){
                num = true;
            }
        }
        if (num){
            return true;
        }
        else{
            return false;
        }
    }
    public Boolean loginPassValidate(String s){
        //TODO: need to query database for password
        return true;
    }
    public Boolean nameValidate(String s){
        if(s.length() < 3){
            return false;
        }
        else{
            return true;
        }
    }
    public Boolean emailValidate(String s){
        Boolean atChar = false;
        for (int i = 0; i < s.length(); i++){
            if (s.charAt(i) == '@' && i > 3 && i <= s.length()-9){
                atChar = true;
            }
        }
        return atChar;
    }
    public Boolean dateValidate(String s){
        int curYear = Calendar.getInstance().get(Calendar.YEAR);
        int curMonth = Calendar.getInstance().get(Calendar.MONTH);
        String[] date = s.split("/", 3);
        int givYear = Integer.parseInt(date[2]);
        int givMonth = Integer.parseInt(date[0]);
        if (curYear - givYear >= 18){
            return true;
        }
        else if (curYear - givYear == 17 && curMonth - givMonth >= 0){
            return true;
        }
        else {
            return false;
        }
    }
}
