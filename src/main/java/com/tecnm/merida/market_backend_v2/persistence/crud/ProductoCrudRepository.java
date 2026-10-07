package com.tecnm.merida.market_backend_v2.persistence.crud;
import org.springframework.data.repository.CrudRepository;
import com.tecnm.merida.market_backend_v2.persistence.entity.Producto;

import java.util.List;
import java.util.Optional;

//Métodos abstractos que despues se implementaran
public interface ProductoCrudRepository extends CrudRepository<Producto, Integer> {

/*SQL Query
    SELECT *
    FROM productos
    WHERE id_categoria = 10?
    ORDER BY nombre ASC
 */

    List<Producto> findByIdCategoriaOrderByNombreAsc(int idCategoria);

    //Cantidad stock
    Optional<List<Producto>> findByCantidadStockLessThanAndEstado(int cantidadStok, boolean estado);

}


