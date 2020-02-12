package solveur;

import probleme.Job;

import java.util.ArrayList;

public class SolutionIntermediaireDeux {
    private SolutionDeux solution;
    private ArrayList<Job> reste;
    private int binf;

    public SolutionIntermediaireDeux(ArrayList<Job> jobs){
        this.solution = new SolutionDeux(new ArrayList<>(), new ArrayList<>());
        this.reste = new ArrayList<>(jobs);
        this.binf=-1;
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
        this.binf=-1;
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
                /****** Utilisation de la propriété 1 ****/

                //Calcule des Cmax des 2 machines
                int c1=0;
                int c2=0;
                for(Job j : solution.getM1()){
                    c1 += j.getP();
                }
                for(Job j : solution.getM2()){
                    c2 += j.getP();
                }

                /*****
                 * On ajoute la solution si elle respecte les 2 propriétés suivantes :
                 * 1) la dernière tâches est placée sur la machine libre le plus tôt
                 * 2) la fin de la dernière tache se termine après ou en même temps que la dernière tâche sur l'autre machine.
                 *    autrement dit, la difference entre les dates de fin des 2 machines (avant que la dernière tache soit
                 *    placée) doit etre inférieure ou égale au P de cette tache.
                 *****/
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
        if(binf==-1){
            binf = solution.eval();
        }
        return binf;
    }
}
