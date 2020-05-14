package haui.stores.services;

import haui.stores.dto.dxo.SupplyDxo;
import haui.stores.dto.rst.SupplyRst;

import java.util.List;

public interface SupplyService {
    List<SupplyRst> getListSupply(int delete);

    boolean saveSupply(SupplyDxo dxo);

    boolean deleteSupply(Long id);

    boolean existSupply(String name, String nameOld);
}
