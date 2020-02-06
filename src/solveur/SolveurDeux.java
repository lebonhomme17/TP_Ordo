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

    public static SolutionDeux solve(ArrayList<Job> jobs){
        SolutionDeux best = solutionApproche(jobs);
        int bsup = best.eval();

        SolutionIntermediaireDeux init = new SolutionIntermediaireDeux(jobs);

        //utilisation de la propriété 2
        init.getReste().sort(new Comparator<Job>() {
            @Override
            public int compare(Job o1, Job o2) {
                return o1.getD()-o2.getD();
            }
        });

        ArrayList<SolutionIntermediaireDeux> tree = new ArrayList<>();

        //utilisation de la propriété 3
        tree.add(new SolutionIntermediaireDeux(init, 1));

        while(!tree.isEmpty()){
            tree.sort(new Comparator<SolutionIntermediaireDeux>() {
                @Override
                public int compare(SolutionIntermediaireDeux o1, SolutionIntermediaireDeux o2) {
                    return o1.borneInf() - o2.borneInf();
                }
            });

            SolutionIntermediaireDeux si = tree.get(0);
            tree.remove(0);

            for(SolutionIntermediaireDeux sol : si.nexts()){
                if(!sol.hasNext()){
                    if(sol.borneInf()<bsup){
                        bsup = sol.borneInf();
                        best = sol.getSolution();
                    }
                }else if(sol.borneInf()<bsup){
                    tree.add(sol);
                }
            }
        }

        return best;
    }


}
