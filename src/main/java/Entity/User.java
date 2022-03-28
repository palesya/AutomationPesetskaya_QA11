package Entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder(setterPrefix = "with",builderMethodName = "create")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String userName;
    private String password;

    public static class UserBuilder
    {
        public UserBuilder() { }
    }
}
