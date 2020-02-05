package solveur;

import probleme.Job;

import java.util.ArrayList;
import java.util.Comparator;

public class Solveur {

    //ordre decroissant des Di/Wi
    public static ArrayList<Job> heuristique(ArrayList<Job> jobs){
        ArrayList<Job> res = new ArrayList<>(jobs);
        res.sort(new Comparator<Job>() {
            @Override
            public int compare(Job job, Job t1) {
                //on multiplie par 1000 avant les divisions pour éviter les problèmes liés aux divisions entières.
                return (1000*t1.getD()/t1.getW()) - (1000*job.getD()/job.getW());
            }
        });
        return res;
    }

    public static int eval(ArrayList<Job> jobs){
        int res = 0;
        int c = 0;
        for(Job j : jobs){
            c += j.getP();
            if(c>j.getD()){
                res += (c-j.getD()) * j.getW();
            }
        }
        return res;
    }
}
