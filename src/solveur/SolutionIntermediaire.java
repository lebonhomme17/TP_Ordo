package solveur;

import probleme.Job;

import java.util.ArrayList;

public class SolutionIntermediaire{
    private ArrayList<Job> ordonnes;
    private ArrayList<Job> non_ordonnes;
    private int binf;

    public SolutionIntermediaire(ArrayList<Job> jobs){
        this.ordonnes = new ArrayList<>();
        this.non_ordonnes = new ArrayList<>(jobs);
        this.binf = -1;
    }

    public SolutionIntermediaire(SolutionIntermediaire si, Job j){
        this.ordonnes = new ArrayList<>();
        this.ordonnes.add(j);
        this.ordonnes.addAll(si.ordonnes);
        this.non_ordonnes = new ArrayList<>(si.non_ordonnes);
        this.non_ordonnes.remove(j);
        this.binf = -1;
    }

    public ArrayList<Job> getOrdonnes() {
        return ordonnes;
    }

    public boolean hasNext(){
        return !non_ordonnes.isEmpty();
    }

    public ArrayList<SolutionIntermediaire> nexts(){
        ArrayList<SolutionIntermediaire> res = new ArrayList<>();
        for(Job j : non_ordonnes){
            res.add(new SolutionIntermediaire(this, j));
        }
        return res;
    }

    public int borneInf(){
        if(binf==-1){
            binf = 0;
            int c = 0;
            for(Job j : non_ordonnes){
                c+=j.getP();
            }
            for (Job j : ordonnes){
                c+=j.getP();
                if(c>j.getD()){
                    binf += (c-j.getD()) * j.getW();
                }
            }
        }
        return binf;
    }
}
