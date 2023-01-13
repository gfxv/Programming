package Stuff;

public class Factory {

    private String name;
    private Ad ad;

    public Factory (String name, Ad ad) {
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

    public boolean equals(Factory factory) {
        if (this == factory) return true;
        if (factory == null || getClass() != factory.getClass()) return false;
        return this.name.equals(factory.getName()) && this.ad.equals(factory.getAdInfo());
    }

    public int hashCode() {
        return (this.name == null ? 0 : this.name.hashCode() * 11 + (this.ad == null ? 0 : this.ad.hashCode()));
    }
}
