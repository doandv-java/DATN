package haui.stores.services.impl;

import haui.stores.data.Supply;
import haui.stores.dto.dxo.SupplyDxo;
import haui.stores.dto.rst.SupplyRst;
import haui.stores.framework.Constants;
import haui.stores.repositories.SupplyRepository;
import haui.stores.services.SupplyService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class SupplyServiceImpl implements SupplyService {

    @Autowired
    private SupplyRepository supplyDao;

    @Override
    public List<SupplyRst> getListSupply(int delete) {
        List<Supply> supplies = supplyDao.findSuppliesByDeleteStatusIs(delete);
        List<SupplyRst> result = new ArrayList<>();
        if (supplies.isEmpty()) {
            return Collections.emptyList();
        } else {
            supplies.forEach(supply -> {
                SupplyRst rst = SupplyRst.toRST(supply);
                result.add(rst);
            });
            return result;
        }
    }

    @Override
    public boolean saveSupply(SupplyDxo dxo) {
        boolean exist = existSupply(dxo.getName(), dxo.getNameOld());
        if (exist) {
            return false;
        } else {
            Supply supply = supplyDao.findSupplyByIdIs(dxo.getId());
            if (supply == null) {
                supply = new Supply();
                supply.setDeleteStatus(Constants.DELETE.FALSE);
            } else {
                supply.setDeleteStatus(supply.getDeleteStatus());
            }
            supply.setName(dxo.getName());
            supply.setEmail(dxo.getEmail());
            supply.setAddress(dxo.getAddress());
            supply.setPhone(dxo.getPhone());
            supplyDao.save(supply);
            return true;
        }
    }

    @Override
    public boolean existSupply(String name, String nameOld) {
        name = StringUtils.trimToEmpty(name);
        if (StringUtils.isEmpty(nameOld)) {
            //Create
            Supply supply = supplyDao.findSupplyByNameAndDeleteStatus(name, Constants.DELETE.FALSE);
            return supply != null;
        } else {
            //update
            Supply supply = supplyDao.findSupplyByNameAndDeleteStatus(name, Constants.DELETE.FALSE);
            boolean check = supply == null || name.equals(nameOld);
            return !check;
        }
    }

    @Override
    public boolean deleteSupply(Long id) {
        Supply supply = supplyDao.findSupplyByIdIs(id);
        if (supply == null) {
            return false;
        } else {
            supply.setDeleteStatus(Constants.DELETE.TRUE);
            supplyDao.save(supply);
            return true;
        }
    }
}
