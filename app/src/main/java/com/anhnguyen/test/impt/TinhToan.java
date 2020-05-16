package com.anhnguyen.test.impt;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TinhToan {
    private int getPreference(char c){
        if(c=='+'|| c=='-') return 1;
        else if(c=='*' || c=='/') return 2;
        else return -1;
    }
    private String calculatePostFix(List<String> postFixList){
        Stack<Float> stack = new Stack<Float>();
        try {
            for(int i=0;i<postFixList.size();i++){
                String word = postFixList.get(i);
                if(word.length()==1 && (word.charAt(0)=='+'||word.charAt(0)=='-'||word.charAt(0)=='*'||word.charAt(0)=='/')){
                    float number2 = stack.isEmpty() ? 0 : stack.pop();
                    float number1 = stack.isEmpty() ? 0 : stack.pop();
                    if(word.charAt(0)=='+'){
                        float number = number1+number2;
                        stack.push(number);
                    }else if(word.charAt(0)=='-'){
                        float number = number1-number2;
                        stack.push(number);
                    }else if(word.charAt(0)=='*'){
                        float number = number1*number2;
                        stack.push(number);
                    }else{
                        float number = number1/number2;
                        stack.push(number);
                    }
                }else{
                    int number = Integer.parseInt(word);
                    stack.push((float) number);
                }
            }
        } catch (Exception e){
            return  "Math Error";
        }
        float result = stack.peek();
        return fmt(result);
    }

    private String fmt(float d)
    {
        if(d == (long) d)
            return String.format("%d",(long)d);
        else
            return String.format("%s",d);
    }

    private List<String> getPostFixString(String s){
        Stack<Character> stack = new Stack<Character>();
        List<String> postFixList = new ArrayList<String>();
        boolean flag = false;
        boolean flagDau = false;
        boolean flagNgoac = false;
        for(int i=0;i<s.length();i++){
            char word = s.charAt(i);
            if(word==' '){
                continue;
            }
            if(word=='('){
                stack.push(word);
                flag = false;
                flagDau = false;
                flagNgoac = true;
            }else if(word==')'){
                flag = false;
                flagDau = false;
                flagNgoac = false;
                while(!stack.isEmpty()){
                    if(stack.peek()=='('){
                        stack.pop();
                        break;
                    }else{
                        postFixList.add(stack.pop()+"");
                    }
                }
            }else if(word=='+' || word=='-' || word=='*' || word=='/'){
                flag = false;
                if(flagNgoac) {
                    postFixList.add("0");
                }
                if(stack.isEmpty()){
                    stack.push(word);
                    if(word=='*' || word == '/') {
                        flagDau = true;
                    }else {
                        flagDau = false;
                    }
                }
                else{
                    if(flagDau) {
                        if(word == '-') {
                            Character temp = stack.pop();
                            stack.push(word);
                            stack.push(temp);
                        }
                    } else {
                        while(!stack.isEmpty() && getPreference(stack.peek())>=getPreference(word)){
                            postFixList.add(stack.pop()+"");
                        }
                        stack.push(word);
                    }
                }
            }else{
                flagDau = false;
                flagNgoac = false;
                if(flag){
                    String lastNumber = postFixList.get(postFixList.size()-1);
                    lastNumber+=word;
                    postFixList.set(postFixList.size()-1, lastNumber);
                }else
                    postFixList.add(word+"");
                flag = true;
            }
        }
        while(!stack.isEmpty()){
            postFixList.add(stack.pop()+"");
        }
        return postFixList;
    }
    public String calculate(String s) {
        List<String> postFixString = getPostFixString(s);
        return calculatePostFix(postFixString);
    }
}
