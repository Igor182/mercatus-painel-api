package com.mercatus.painel.mercatus_painel_api.repository;


import com.mercatus.painel.mercatus_painel_api.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
