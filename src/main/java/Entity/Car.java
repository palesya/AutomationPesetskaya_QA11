package Entity;

public class Car {
    private String brand;
    private String color;
    private String body;
    private Double v;

    public static class AutoIndustry {
        Car car;
//конструктор
        public AutoIndustry() {
            car = new Car();
        }

        public AutoIndustry withBody(String body) {
            car.body=body;
            return this;
        }

        public AutoIndustry withBrand(String brand) {
            car.brand=brand;
            return this;
        }

        public AutoIndustry withColor(String color) {
            car.color=color;
            return this;
        }

        public AutoIndustry withV(Double v) {
            car.v=v;
            return this;
        }

        public Car create() {
            return car;
        }
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", color='" + color + '\'' +
                ", body='" + body + '\'' +
                ", v=" + v +
                '}';
    }
}
