/**
*Абстрактиний клас амуніції рицаря
* */
abstract class Ammunition {
    private String name;
    private double weight;
    private double price;

    /**
     * Спільний конструктор для всієї амуніції
     *
     * @param name назва амуніції
     * @param weight вага амуніції
     * @param price ціна амуніції
     * @throws IllegalArgumentException якщо name порожній або weight чи price менше нуля
     */
    public Ammunition(String name, double weight, double price) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Назва амуніції не може бути порожньою");
        }
        if (weight <= 0) {
            throw new IllegalArgumentException("Вага амуніції повинна бути більшою за 0");
        }
        if (price <= 0) {
            throw new IllegalArgumentException("Цінна амуніції повинна бути більша за 0");
        }
        this.name = name;
        this.weight = weight;
        this.price = price;
    }

    public String getName() {
        return name;
    }
    public double getWeight() {
        return weight;
    }
    public double getPrice() {
        return price;
    }
}
