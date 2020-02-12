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
        if(reste.size()!=0){
            if(reste.size()==1){

                int c1=0;
                int c2=0;
                for(Job j : solution.getM1()){
                    c1 += j.getP();
                }
                for(Job j : solution.getM2()){
                    c2 += j.getP();
                }

                if(c1<=c2 && c2-c1<=reste.get(0).getP()){
                    res.add(new SolutionIntermediaireDeux(this, 1));
                }
                else if(c2<c1 && c1-c2<=reste.get(0).getP()){
                    res.add(new SolutionIntermediaireDeux(this, 2));
                }
            }else{
                res.add(new SolutionIntermediaireDeux(this, 1));
                res.add(new SolutionIntermediaireDeux(this, 2));
            }
        }
        return res;
    }

    public int borneInf(){
        return solution.eval();
    }
}
