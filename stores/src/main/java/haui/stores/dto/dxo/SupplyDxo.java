package haui.stores.dto.dxo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplyDxo {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String nameOld;
    private int deleteStatus;
}
