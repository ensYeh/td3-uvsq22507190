package fr.uvsq.cprog.collex;

public class CommandeQuitter implements Commande {
    @Override
    public void execute() {
        System.out.println("Au revoir !");
        System.exit(0);
    }
}
