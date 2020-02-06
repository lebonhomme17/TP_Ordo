package solveur;

import probleme.Job;

import java.util.ArrayList;

public class SolutionDeux {
    private ArrayList<Job> m1;
    private ArrayList<Job> m2;

    public SolutionDeux(ArrayList<Job> m1, ArrayList<Job> m2){
        this.m1 = new ArrayList<>(m1);
        this.m2 = new ArrayList<>(m2);
    }

    public ArrayList<Job> getM1() {
        return m1;
    }

    public void setM1(ArrayList<Job> m1) {
        this.m1 = m1;
    }

    public ArrayList<Job> getM2() {
        return m2;
    }

    public void setM2(ArrayList<Job> m2) {
        this.m2 = m2;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();

        sb.append("M1 <");
        if(!m1.isEmpty()) {
            for (Job j : m1) {
                sb.append(j.getName() + ", ");
            }
            sb.delete(sb.length() - 2, sb.length());
        }
        sb.append(">\n");

        sb.append("M2 <");
        if(!m2.isEmpty()) {
            for (Job j : m2) {
                sb.append(j.getName() + ", ");
            }
            sb.delete(sb.length() - 2, sb.length());
        }
        sb.append(">");

        return sb.toString();
    }

    public int eval(){
        int res = 0;
        int c = 0;

        for(Job j : m1){
            c += j.getP();
            int retard = c-j.getD();
            if(retard > res){
                res = retard;
            }
        }

        c = 0;
        for(Job j : m2){
            c += j.getP();
            int retard = c-j.getD();
            if(retard > res){
                res = retard;
            }
        }

        return res;
    }
}
