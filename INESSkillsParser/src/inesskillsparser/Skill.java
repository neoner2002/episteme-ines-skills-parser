package inesskillsparser;

import java.util.ArrayList;

/**
 * @author fet
 */
public class Skill {
    String name;
    ArrayList<Company> companies = new ArrayList<>();
    
    Skill(String name){
        this.name = name;
    }
    
    public void addCompany(Company company){
        companies.add(company);
    }
}
