package entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class User extends BaseEntity{
    String name;
    String pinCode;
    String phoneNo;
}
