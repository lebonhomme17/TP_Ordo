import probleme.Job;
import solveur.SolutionDeux;
import solveur.SolveurDeux;
import solveur.SolveurUn;

import java.util.ArrayList;

public class Main {
    public static String affichageSolution(ArrayList<Job> jobs){
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        for(Job j: jobs){
            sb.append(j.getName() + ", ");
        }
        sb.delete(sb.length()-2, sb.length());
        sb.append(">");
        return sb.toString();
    }

    public static void main(String[] args){


        /*********************************
         * Exercice 1
         *********************************/


        System.out.println("Exercice 1 :");

        ArrayList<Job> jobs = new ArrayList<>();
        jobs.add(new Job("J1", 3,16,2));
        jobs.add(new Job("J2", 7,15,3));
        jobs.add(new Job("J3", 2,9,1));
        jobs.add(new Job("J4", 3,4,2));
        jobs.add(new Job("J5", 5,10,4));

        ArrayList h = SolveurUn.heuristique(jobs);
        System.out.println("\nSolution de l'heuristique di/wi décroissants : " + affichageSolution(h));
        System.out.println("Valeur = "+ SolveurUn.eval(h));



        ArrayList<Job> sol = SolveurUn.solve(jobs);
        System.out.println("\nSolution optimale : " + affichageSolution(sol));
        System.out.println("Valeur = "+ SolveurUn.eval(sol));


        /*********************************
         * Exercice 2
         *********************************/


        System.out.println("\n\nExercice 2 :");


        ArrayList<Job> jobs2 = new ArrayList();
        jobs2.add(new Job("T1", 3,4));
        jobs2.add(new Job("T2", 4,6));
        jobs2.add(new Job("T3", 6,8));
        jobs2.add(new Job("T4", 3,7));
        jobs2.add(new Job("T5", 2,9));
        jobs2.add(new Job("T6", 5,10));

        SolutionDeux approche = SolveurDeux.solutionApproche(jobs2);
        System.out.println("\nSolution approchée  :");
        System.out.println(approche.toString());
        System.out.println("Retard max = " + approche.eval());

        SolutionDeux sol2 = SolveurDeux.solve(jobs2);
        System.out.println("\nSolution optimale  :");
        System.out.println(sol2.toString());
        System.out.println("Retard max = " + sol2.eval());

    }
}
