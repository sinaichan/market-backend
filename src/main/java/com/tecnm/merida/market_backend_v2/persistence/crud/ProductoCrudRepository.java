package com.tecnm.merida.market_backend_v2.persistence.crud;
import org.springframework.data.repository.CrudRepository;
import com.tecnm.merida.market_backend_v2.persistence.entity.Producto;

public interface ProductoCrudRepository extends CrudRepository<Producto, Integer> {
}


