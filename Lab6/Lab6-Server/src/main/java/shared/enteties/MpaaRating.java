package shared.enteties;


import java.io.Serializable;

public enum MpaaRating implements Serializable {
    G(1),
    PG(2),
    PG_13(3),
    R(4),
    NC_17(5);

    private int value;

    MpaaRating(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    };

}
