package fr.uvsq.cprog.collex;

public class AdresseIP {

    private String ip;

    public AdresseIP(String ip) {
        // Vérifie la validité de l'adresse IP avant de créer l'objet
        if (!isValid(ip)) {
            throw new IllegalArgumentException("Adresse IP invalide : " + ip);
        }
        this.ip = ip;
    }

    public String getIp() {
        return ip;
    }

    // Retourne l'adresse IP sous forme de chaîne
    @Override
    public String toString() {
        return ip;
    }

    // Vérifie si l'adresse IP est valide
    public static boolean isValid(String ip) {
        String[] parts = ip.split("\\.");
        if (parts.length != 4)
            return false;
        try {
            for (String part : parts) {
                int n = Integer.parseInt(part);
                if (n < 0 || n > 255)
                    return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    // Redéfinit equals pour comparer correctement deux adresses IP
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof AdresseIP))
            return false;
        AdresseIP other = (AdresseIP) obj;
        return ip.equals(other.ip);
    }

    @Override
    public int hashCode() {
        return ip.hashCode();
    }
}
