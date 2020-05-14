package haui.stores.dto.rst;

import haui.stores.data.Supply;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplyRst {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private int deleteStatus;

    public static SupplyRst toRST(Supply supply) {
        SupplyRst rst = new SupplyRst();
        rst.setId(supply.getId());
        rst.setName(supply.getName());
        rst.setEmail(supply.getEmail());
        rst.setPhone(supply.getPhone());
        rst.setAddress(supply.getAddress());
        rst.setDeleteStatus(supply.getDeleteStatus());
        return rst;
    }
}
