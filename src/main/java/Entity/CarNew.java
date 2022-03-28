package Entity;

import lombok.Builder;
import lombok.ToString;

@ToString
@Builder(setterPrefix = "with", builderMethodName = "create", builderClassName = "AutoIndustry")
public class CarNew {
    private String brand;
    private String color;
    private String body;
    private Double v;

    public static class AutoIndustry {

        public AutoIndustry() { }
    }
}
