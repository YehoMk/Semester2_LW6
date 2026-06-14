import java.util.List;


public class Main {
    public static void main(String[] args) {
        CustomSet<Ammunition> ammunitionSet = new CustomSet<>();

        Ammunition sword1 = new Sword("Великий меч", 3, 1200);
        Ammunition sword2 = new Sword("Кинджал", 0.5, 300);
        Ammunition armor1 = new Armor("Кольчуга", 15, 2500);
        Ammunition shield1 = new Shield("Металевий шит", 2.5, 1000);

        ammunitionSet.add(sword1);
        ammunitionSet.add(sword2);
        ammunitionSet.add(armor1);
        ammunitionSet.add(shield1);

        displayAmmunitionSet(ammunitionSet);
        System.out.printf("size(): %d %n", ammunitionSet.size());
        System.out.printf("isEmpty(): %b %n", ammunitionSet.isEmpty());
        System.out.printf("contains(): %b %n", ammunitionSet.contains(sword1));
        ammunitionSet.clear();
        displayAmmunitionSet(ammunitionSet);
        ammunitionSet.addAll(List.of(sword1, sword2, armor1, shield1));
        displayAmmunitionSet(ammunitionSet);
        System.out.printf("containsAll(): %b %n", ammunitionSet.containsAll(List.of(sword1, armor1)));
        ammunitionSet.remove(sword1);
        displayAmmunitionSet(ammunitionSet);
        ammunitionSet.removeAll(List.of(sword2, armor1));
        displayAmmunitionSet(ammunitionSet);
        ammunitionSet.retainAll(List.of(armor1, shield1));
        displayAmmunitionSet(ammunitionSet);
    }

    public static void displayAmmunitionSet(CustomSet<? extends Ammunition> set) {
        if (set.isEmpty()) {
            System.out.println("-");
        } else {
            for (Ammunition element : set) {
                System.out.printf("%-20s ", element.getName());
            }
            System.out.printf("%n");
        }
    }
}
