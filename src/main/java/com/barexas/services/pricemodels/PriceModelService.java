package com.barexas.services.pricemodels;

import com.barexas.entities.PriceModel;

import java.math.BigDecimal;
import java.util.List;

public interface PriceModelService {
    List<PriceModel> getAll();
    void addPriceModel(String name, BigDecimal tax);
    PriceModel getById(Long id);
}
