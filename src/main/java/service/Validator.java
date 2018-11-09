package service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    private static String REGEX_FOR_CHECK_CORRECT_LOGIN_AND_NAME="^[A-Za-z0-9\\._-]+$";
    private static String REGEX_FOR_CHECK_PRICE_PRODUCT="^[0-9]*$";
    private static String REGEX_FOR_CHECK_PASSWORD="[^ ]+$";

    public static boolean checkInformAboutUser(String login,String password) {
        boolean flag;
    boolean matcherCheckNullField=checkCorrectLoginAndPassword(login,password);
    boolean matcherCorrectLoginAndPassword=checkFieldsOnNull(login,password);
    if (matcherCorrectLoginAndPassword && matcherCheckNullField) {
     flag=true;

    }
    else {
        flag=false;
    }
    return flag;
    }

    public static String getNameBundle(String locale){
    String nameBundle="locale";
    if (locale==null){
        nameBundle="locale_ru";
    }else {
        nameBundle+="_"+locale;
    }
    return nameBundle;
    }


    public static boolean checkFieldsOnIsEmpty(String...fields) {
        boolean flag=true;
        for(String field:fields){
            if (field.isEmpty()){
                flag=false;
                break;
            }
        }
        return flag;
    }


    public static boolean checkFieldsOnNull(Object...fields) {
        boolean flag=true;
        for(Object field:fields){
            if (field==null){
                flag=false;
                break;
            }
        }
    return flag;
    }

    public static boolean checkPrice(String productPrice){
       return (regexMethod(productPrice,REGEX_FOR_CHECK_PRICE_PRODUCT));
    }

    private static boolean regexMethod(String parameter,String regex){
        boolean flag;
        Pattern pattern=Pattern.compile(regex);
        Matcher matcher=pattern.matcher(parameter);
        flag=matcher.find();
        return flag;
    }

private static boolean checkCorrectLoginAndPassword(String login,String password){
        boolean flag;
        if (regexMethod(login,REGEX_FOR_CHECK_CORRECT_LOGIN_AND_NAME) && regexMethod(password,REGEX_FOR_CHECK_PASSWORD)){
            flag=true;
        }
        else {
            flag=false;
        }
        return flag;
}
}



