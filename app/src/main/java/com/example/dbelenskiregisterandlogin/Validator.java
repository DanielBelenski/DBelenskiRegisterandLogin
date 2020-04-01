package com.example.dbelenskiregisterandlogin;

import java.util.Calendar;

//class to validate fields on registration page
public class Validator {

    //blank constructor
    public Validator(){

    }

    //password validation
    //@param String s, the string value of the password edittext
    //@return true/false value if parameters are valid
    public Boolean registerPassValidate(String s){
        //checks length
        if (s.length() < 5){
            return false;
        }

        //checks if password has a number
        Boolean num = false;
        for(int i=0; i < s.length(); i++){
            if (Character.isDigit(s.charAt(i))){
                num = true;
            }
        }

        //returning value of num if a number is present
        if (num){
            return true;
        }
        else{
            return false;
        }
    }


    //checks if an inputted name is valid
    //@param String s, string value of a name edittext
    //@return true/false value of name validity
    public Boolean nameValidate(String s){
        //checks length
        if(s.length() < 3){
            return false;
        }
        else{
            return true;
        }
    }

    //checks if inputted email is valid
    //@param String s, string value of inputted email address
    public Boolean emailValidate(String s){
        //checking for presence and placing of the @ character
        Boolean atChar = false;
        //iterates through string looking for @ character
        for (int i = 0; i < s.length(); i++){
            //checks placement of @ character, a valid email should be min 3 chars @ domain where the shortest domain i found is aol.com (7 characters long)
            if (s.charAt(i) == '@' && i > 3 && i <= s.length()-7){
                atChar = true;
            }
        }
        //return atChar
        return atChar;
    }

    //checks if birth date given makes the user old enough to register
    //@param: string s, string value of the date of birth field
    //@return: true/false value of if the user is 18 years old or not
    public Boolean dateValidate(String s){
        //getting current year and month
        int curYear = Calendar.getInstance().get(Calendar.YEAR);
        int curMonth = Calendar.getInstance().get(Calendar.MONTH);
        //splitting the date string on /'s to get month day and year as separate strings
        String[] date = s.split("/", 3);
        //parsing month and year strings for their integer values
        int givYear = Integer.parseInt(date[2]);
        int givMonth = Integer.parseInt(date[0]);
        //if user is definitely 18 years old or older
        if (curYear - givYear >= 18){
            return true;
        }
        //if user turned 18 this year, note they cannot sign up until their birth month is over
        else if (curYear - givYear == 17 && curMonth - givMonth > 0){
            return true;
        }
        else {
            return false;
        }
    }
}
