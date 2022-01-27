package Homework_5;

import java.util.ArrayList;
import java.util.List;

public class CalculatedResults {
    private String area;
    private String numberOfPanels;
    private String numberOfBoxes;
    private String price;
    private String leftovers;
    private String segments;

    public CalculatedResults(String area, String numberOfPanels, String numberOfBoxes, String price, String leftovers, String segments) {
        this.area = "Площадь укладки: " + area + " м2.";
        this.numberOfPanels = "Кол-во панелей: " + numberOfPanels + " шт.";
        this.numberOfBoxes = "Кол-во упаковок: " + numberOfBoxes + " шт.";
        this.price = "Стоимость: " + price + " руб.";
        this.leftovers = "Остатки: " + leftovers + " шт.";
        this.segments = "Отрезки: " + segments + " шт.";
    }

    public List<String> getResults() {
        List<String> results = new ArrayList<>();
        results.add(area);
        results.add(numberOfPanels);
        results.add(numberOfBoxes);
        results.add(price);
        results.add(leftovers);
        results.add(segments);

        return results;
    }
}
