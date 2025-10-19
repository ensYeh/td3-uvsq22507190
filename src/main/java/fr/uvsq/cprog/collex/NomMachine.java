package fr.uvsq.cprog.collex;

public class NomMachine implements Comparable<NomMachine> {

    private String nom;

    public NomMachine(String nom) {

        // Vérifie si le nom de la machine est null avant de créer l'objet
        if (nom == null) {
            throw new IllegalArgumentException("Nom de machine invalide : null");
        }

        // Séparer par les points
        String[] parts = nom.split("\\.");
        if (parts.length < 2 || parts.length > 3) {
            throw new IllegalArgumentException(
                    "Nom de machine doit avoir au moins 2 parties (host[.subdomain].domain)");
        }

        // Vérifier que chaque partie n'est pas vide
        for (String part : parts) {
            if (part.isEmpty()) {
                throw new IllegalArgumentException("Chaque partie du nom doit être non vide");
            }
        }

        // Normaliser en minuscules
        this.nom = nom.toLowerCase();
    }

    public String getNom() {
        return nom;
    }

    @Override
    public String toString() {
        return nom;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof NomMachine))
            return false;
        NomMachine other = (NomMachine) obj;
        return nom.equals(other.nom);
    }

    @Override
    public int hashCode() {
        return nom.hashCode();
    }

    @Override
    public int compareTo(NomMachine other) {
        // On utilise la méthode compareTo() de String pour comparer les noms de
        // machines selon l’ordre alphabétique standard
        return this.nom.compareTo(other.nom);
    }
}
