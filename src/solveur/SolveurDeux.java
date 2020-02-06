package solveur;

import probleme.Job;

import java.util.ArrayList;
import java.util.Comparator;

public class SolveurDeux {

    public static SolutionDeux solutionApproche(ArrayList<Job> jobs){
        ArrayList<Job> m1 = new ArrayList<>();
        ArrayList<Job> m2 = new ArrayList<>();
        ArrayList<Job> reste = new ArrayList<>(jobs);
        int dispo1 = 0;
        int dispo2 = 0;
        reste.sort(new Comparator<Job>() {
            @Override
            public int compare(Job o1, Job o2) {
                return o1.getD()-o2.getD();
            }
        });

        while(!reste.isEmpty()){
            Job j = reste.get(0);
            reste.remove(0);
            if(dispo1<=dispo2){
                m1.add(j);
                dispo1 += j.getP();
            }else{
                m2.add(j);
                dispo2 += j.getP();
            }
        }

        return new SolutionDeux(m1, m2);
    }

    /*public static SolveurDeux solve(ArrayList<Job> jobs){

    }*/

    public static int eval(SolutionDeux solution){
        int res = 0;
        int c = 0;

        for(Job j : solution.getM1()){
            c += j.getP();
            int retard = j.getD()-c;
            if(retard > res){
                res = retard;
            }
        }

        c = 0;
        for(Job j : solution.getM2()){
            c += j.getP();
            int retard = j.getD()-c;
            if(retard > res){
                res = retard;
            }
        }

        return res;
    }
}
