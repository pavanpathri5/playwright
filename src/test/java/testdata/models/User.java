package testdata.models;

import lombok.Getter;
import lombok.Setter;

public class User {
    @Getter @Setter
    private String email;
    @Getter @Setter
    private String password;
    @Getter @Setter
    private String type;
}
