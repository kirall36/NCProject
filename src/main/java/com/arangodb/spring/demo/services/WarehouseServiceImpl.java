package com.arangodb.spring.demo.services;

import com.arangodb.spring.demo.entity.Warehouse;
import com.arangodb.spring.demo.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WarehouseServiceImpl implements WarehouseService {
    private final WarehouseRepository warehouseRepository;

    @Autowired
    public WarehouseServiceImpl(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }


    @Override
    public Optional<Warehouse> save(Warehouse warehouse) {
        return Optional.of(warehouseRepository.save(warehouse));
    }

    @Override
    public List<Warehouse> findAllWarehouses() {
        List<Warehouse> orders = new ArrayList<>();
        warehouseRepository.findAll().forEach(orders::add);
        return orders;
    }

    @Override
    public Warehouse findById(String Id) {
        return warehouseRepository.findById(Id);
    }

    @Override
    public Boolean isWarehouseExist(Warehouse warehouse) {
        return warehouseRepository.exists(warehouse.getId());
    }

    @Override
    public void updateWarehouse(Warehouse warehouse) {
        warehouseRepository.save(warehouse);
    }

    @Override
    public void deleteWarehouseById(String Id) {
        warehouseRepository.delete(Id);
    }

    @Override
    public void deleteAllWarehouses() {
        warehouseRepository.deleteAll();
    }

}
