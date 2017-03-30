package com.barexas.services.pricemodels;

import com.barexas.entities.PriceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PriceModelServiceImpl implements PriceModelService{
    @Autowired
    private PriceModelRepository priceModelRepository;

    @Override
    public List<PriceModel> getAll() {
        return priceModelRepository.findAll();
    }

    @Override
    @Transactional
    public void addPriceModel(String name, BigDecimal tax) {
        priceModelRepository.save(new PriceModel(name, tax));
    }

    @Override
    public PriceModel getById(Long id) {
        return priceModelRepository.findOne(id);
    }
}
