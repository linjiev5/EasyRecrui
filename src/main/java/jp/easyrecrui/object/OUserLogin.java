package jp.easyrecrui.object;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OUserLogin extends OUser{

    private boolean rocked;
    private String userRole;

}
