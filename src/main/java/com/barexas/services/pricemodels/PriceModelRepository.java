package com.barexas.services.pricemodels;

import com.barexas.entities.PriceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceModelRepository extends JpaRepository<PriceModel, Long>{
}
