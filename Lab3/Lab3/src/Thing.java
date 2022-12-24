public class Thing {

    private String name;
    private Ad ad;

    public Thing (String name, Ad ad) {
        this.name = name;
        this.ad = ad;
    }

    public String getName () {
        return this.name;
    }

    public Ad getAdInfo () {
        return this.ad;
    }

    public String toString() {
        return this.name;
    }

    public boolean equals(Thing thing) {
        if (this == thing) return true;
        if (thing == null || getClass() != thing.getClass()) return false;
        return this.name.equals(thing.getName()) && this.ad.equals(thing.getAdInfo());
    }

    public int hashCode() {
        return ((this.name == null ? 0 : this.name.hashCode()) * 23 +
                (this.ad == null ? 0 : this.ad.hashCode()) * 5);
    }
}
