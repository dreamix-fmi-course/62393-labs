package lab2.collections.entity;

public class Dog implements Comparable<Dog> {
    private String breed;
    private int weight;

    public Dog() {
        this.breed = null;
        this.weight = 0;
    }

    public Dog(String breed, int weight) {
        this.breed = breed;
        this.weight = weight;
    }

    public String getBreed() { return this.breed; }
    public int getWeight() { return this.weight; }

    public void setBreed(String newBreed) { this.breed = newBreed; }
    public void setWeight(int newWeight) { this.weight = newWeight; }

    @Override
    public int compareTo(Dog other) {
        return Integer.compare(this.weight, other.weight);
    }

    @Override
    public String toString() {
        return "Dog{" +
            "breed='" + this.breed + "'," +
            "weight=" + this.weight + "," +
            "}";
    }
}
