package com.tecnm.merida.market_backend_v2.persistence;

import com.tecnm.merida.market_backend_v2.domain.Purchase;
import com.tecnm.merida.market_backend_v2.domain.repository.PurchaseRepository;
import com.tecnm.merida.market_backend_v2.persistence.crud.CompraCrudRepository;
import com.tecnm.merida.market_backend_v2.persistence.entity.Compra;
import com.tecnm.merida.market_backend_v2.persistence.entity.CompraProductoPK;
import com.tecnm.merida.market_backend_v2.persistence.mapper.PurchaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CompraRepository implements PurchaseRepository {

    private final CompraCrudRepository compraCrudRepository;
    private final PurchaseMapper mapper;

    public CompraRepository(CompraCrudRepository compraCrudRepository, PurchaseMapper mapper) {
        this.compraCrudRepository = compraCrudRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Purchase> getAll() {
        return mapper.toPurchases((List<Compra>) compraCrudRepository.findAll());
    }

    @Override
    public Optional<List<Purchase>> getByClient(String clientId) {
        return compraCrudRepository.findByIdCliente(clientId)
                .map(mapper::toPurchases);
    }

    @Override
    public Purchase save(Purchase purchase) {
        Compra compra = mapper.toCompra(purchase);
        compra.setEstado("P");

        if (compra.getProductos() != null) {
            compra.getProductos().forEach(product -> {
                product.setEstado(true);
                product.setCompra(compra);

                CompraProductoPK pk = product.getId() != null ? product.getId() : new CompraProductoPK();
                if (compra.getIdCompra() != null) {
                    pk.setIdCompra(compra.getIdCompra());
                }
                product.setId(pk);
            });
        }

        Compra saved = compraCrudRepository.save(compra);

        if (saved.getProductos() != null) {
            saved.getProductos().forEach(product -> {
                if (product.getId() != null && product.getId().getIdCompra() == null) {
                    product.getId().setIdCompra(saved.getIdCompra());
                }
            });
        }

        return mapper.toPurchase(saved);
    }
}
