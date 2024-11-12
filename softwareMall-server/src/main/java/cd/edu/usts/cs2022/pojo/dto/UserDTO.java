package cd.edu.usts.cs2022.pojo.dto;


import cd.edu.usts.cs2022.pojo.po.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Integer userId;

    private User user;

    private String token;
}
