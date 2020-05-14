package haui.stores.dto.request;

import haui.stores.dto.dxo.SupplyDxo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplyRequest {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String nameOld;
    private int deleteStatus;

    public SupplyDxo toDxo() {
        SupplyDxo dxo = new SupplyDxo();
        dxo.setId(id);
        dxo.setName(StringUtils.capitalize(StringUtils.trimToEmpty(name)));
        dxo.setEmail(StringUtils.trimToEmpty(email));
        dxo.setPhone(StringUtils.trimToEmpty(phone));
        dxo.setAddress(StringUtils.trimToEmpty(address));
        dxo.setNameOld(nameOld);
        dxo.setDeleteStatus(deleteStatus);
        return dxo;
    }
}
