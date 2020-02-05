package solveur;

import probleme.Job;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

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

    public static ArrayList<Job> solve(ArrayList<Job> jobs){
        ArrayList<Job> best = heuristique(jobs);
        int bsup = eval(best);
        ArrayList<SolutionIntermediaire> tree = new ArrayList<>();
        tree.add(new SolutionIntermediaire(jobs));
        while (!tree.isEmpty()){
            ArrayList<SolutionIntermediaire> suppr = new ArrayList<>();//liste des solutions à supprimer


            for(SolutionIntermediaire si : tree){
                if(!si.hasNext()){
                    if(si.borneInf()<bsup){
                        bsup = si.borneInf();
                        best = si.getOrdonnes();
                    }
                    suppr.add(si);
                }else if(si.borneInf()>=bsup){
                    suppr.add(si);
                }
            }

            tree.removeAll(suppr);


            if(!tree.isEmpty()) {
                tree.sort(new Comparator<SolutionIntermediaire>() {
                    @Override
                    public int compare(SolutionIntermediaire solutionIntermediaire, SolutionIntermediaire t1) {
                        return solutionIntermediaire.borneInf() - t1.borneInf();
                    }
                });

                SolutionIntermediaire sol = tree.get(0);
                tree.remove(sol);
                tree.addAll(sol.nexts());

            }


        }
        return best;
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
