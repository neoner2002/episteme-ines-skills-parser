/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inesskillsparser;

/**
 *
 * @author fet
 */
public class Company {
    String name;
    boolean isLow = true;
    boolean isMed = false;
    boolean isHigh = false;
    
    Company(String name){
        this.name = name;
    }
    
    public String getSkillLevel(){
        String response = "LOW";
        if(isMed){
            response = "MED";
        }
        if(isHigh){
            response = "HIGH";
        }
        return response;
    }
}
