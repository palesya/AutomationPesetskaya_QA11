package Entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder(setterPrefix = "with",builderMethodName = "create")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckoutData {
    private String firstName;
    private String lastName;
    private String zipPostalCode;

    public static class CheckoutDataBuilder
    {
        public CheckoutDataBuilder() { }
    }
}
