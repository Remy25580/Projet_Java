package school.coda.remy_axel_ethan.projet_java.boat;

public enum BoatType {
    PORTE_AVION("porte-avion", 5),
    CUIRASSE("cuirassé", 4),
    DESTROYER("destroyer", 3),
    SOUS_MARIN("sous-marin",3),
    PATROUILLEUR("patrouilleur", 2);

    private final String name;
    private final int size;
    BoatType(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public String getName() { return name; }
    public int getSize() { return size; }
}
