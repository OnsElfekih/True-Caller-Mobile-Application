package ELFEKIHOns.truecaller;

public class Position {
    public int idPosition;
    public String numero, pseudo, latitude, longitude;

    public Position(int idPosition, String numero, String pseudo, String latitude, String longitude) {
        this.idPosition = idPosition;
        this.numero = numero;
        this.pseudo = pseudo;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "Position{" +
                "idPosition=" + idPosition +
                ", numero='" + numero + '\'' +
                ", pseudo='" + pseudo + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                '}';
    }
}
