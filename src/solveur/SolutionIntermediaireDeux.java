package solveur;

import probleme.Job;

import java.util.ArrayList;

public class SolutionIntermediaireDeux {
    private SolutionDeux solution;
    private ArrayList<Job> reste;

    public SolutionIntermediaireDeux(ArrayList<Job> jobs){
        this.solution = new SolutionDeux(new ArrayList<>(), new ArrayList<>());
        this.reste = new ArrayList<>(jobs);
    }

    public SolutionIntermediaireDeux(SolutionIntermediaireDeux pred, int machine){
        this.solution = new SolutionDeux(new ArrayList<>(pred.getSolution().getM1()), new ArrayList<>(pred.getSolution().getM2()));
        this.reste = new ArrayList<>(pred.getReste());
        if(machine == 1) {
            this.solution.getM1().add(reste.get(0));
        }else if(machine == 2){
            this.solution.getM2().add(reste.get(0));
        }
        this.reste.remove(0);

    }

    public boolean hasNext(){
        return !reste.isEmpty();
    }

    public SolutionDeux getSolution(){
        return solution;
    }

    public ArrayList<Job> getReste(){
        return reste;
    }

    public ArrayList<SolutionIntermediaireDeux> nexts(){
        ArrayList<SolutionIntermediaireDeux> res = new ArrayList<>();
        res.add(new SolutionIntermediaireDeux(this, 1));
        res.add(new SolutionIntermediaireDeux(this, 2));
        return res;
    }

    public int borneInf(){
        return solution.eval();
    }
}
