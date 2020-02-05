import probleme.Job;
import solveur.Solveur;

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
        System.out.println("Exercice 1 :");
        System.out.println("1)");
        ArrayList<Job> jobs = new ArrayList<>();
        jobs.add(new Job("J1", 3,16,2));
        jobs.add(new Job("J2", 7,15,3));
        jobs.add(new Job("J3", 2,9,1));
        jobs.add(new Job("J4", 3,4,2));
        jobs.add(new Job("J5", 5,10,4));

        ArrayList h = Solveur.heuristique(jobs);
        System.out.println("Solution de l'heuristique di/wi décroissants : " + affichageSolution(h));
        System.out.println("Valeur = "+ Solveur.eval(h));


        System.out.println("2) et 3)");

        ArrayList<Job> sol = Solveur.solve(jobs);
        System.out.println("Solution optimale : " + affichageSolution(sol));
        System.out.println("Valeur = "+ Solveur.eval(sol));

        System.out.println("Exercice 2 :");
        System.out.println("1)");

    }
}
