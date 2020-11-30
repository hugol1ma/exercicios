package br.com.tinnova.veiculo.repositorio;

import br.com.tinnova.veiculo.dto.VeiculoVendidoDTO;
import br.com.tinnova.veiculo.entidade.Veiculo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VeiculoRepository extends CrudRepository<Veiculo, Integer> {


    @Query("select v from Veiculo v " +
            " where " +
            "      UPPER(v.nome) like CONCAT('%',UPPER(:palavraChave),'%') " +
            "   or UPPER(v.marca) like  CONCAT('%',UPPER(:palavraChave),'%') " +
            "   or UPPER(v.descricao) like  CONCAT('%',CONCAT('%',UPPER(:palavraChave),'%'),'%') ")
    List<Veiculo> findByKeyword(@Param("palavraChave")String palavraChave);

    @Modifying
    @Query("update Veiculo v set v.vendido = :vendido, v.updated = :updated where v.id = :id")
    void updateStatusVenda(@Param("id") Integer id, @Param("vendido") Boolean vendido, @Param("updated") LocalDateTime updated);
}
