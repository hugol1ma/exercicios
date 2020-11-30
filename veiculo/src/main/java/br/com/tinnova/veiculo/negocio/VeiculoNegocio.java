package br.com.tinnova.veiculo.negocio;

import br.com.tinnova.veiculo.dto.VeiculoVendidoDTO;
import br.com.tinnova.veiculo.entidade.Veiculo;
import br.com.tinnova.veiculo.repositorio.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class VeiculoNegocio {

    @Autowired
    private VeiculoRepository veiculoRepository;

    /**
     * Método que lista todos os veiculos cadastrados no banco de dados
     *
     * @return lista de veículos
     */
    public ResponseEntity findAll(){
        List<Veiculo> veiculos = (List<Veiculo>) veiculoRepository.findAll();
        return ResponseEntity.ok(veiculos);
    }


    /**
     * Método que lista os veiculos cadastrados que possuem ocorrência da palavra chave.
     * Baseando nos registros das colunas nome, marca e descrição de cada veículo
     *
     * @param palavraChave - Palavra chave para filtrar veículo
     * @return lista de veículos com filtro da palavraChave
     */
    public ResponseEntity findByKeyword(String palavraChave){
        List<Veiculo> veiculos = (List<Veiculo>) veiculoRepository.findByKeyword(palavraChave);
        return ResponseEntity.ok(veiculos);
    }


    /**
     * Método responsável de salvar um veículo novo
     *
     * @param veiculo
     * @return o veículo salvo com id e datas de criação e alteração
     */
    public ResponseEntity save(Veiculo veiculo){

        if (veiculo.getNome() == null || veiculo.getNome().trim().length() == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Informe o nome do veiculo");
        }

        if (veiculo.getMarca() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Informe a marca do veiculo");
        }

        if (veiculo.getAno() == null || veiculo.getAno().compareTo(0) <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Informe o ano do veiculo");
        }

        veiculo.setCreated(LocalDateTime.now());
        veiculo.setUpdated(LocalDateTime.now());

        return ResponseEntity.ok(veiculoRepository.save(veiculo));
    }

    /**
     * Método responsável por atualizar todos os dados do veículo
     *
     * @param id - id do veículo a ser atualizado
     * @param veiculo - dados do veículo para atualizar
     * @return o veículo salvo com nova data de alteração
     */
    public ResponseEntity updateAll(Integer id, Veiculo veiculo){
        veiculo.setId(id);
        veiculo.setUpdated(LocalDateTime.now());

        veiculo = veiculoRepository.save(veiculo);
        return ResponseEntity.ok(veiculo);
    }

    /**
     * Método responsável excluir o veículo do banco de dados
     *
     * @param id - Identificador do veículo para ser excluido
     */
    public ResponseEntity remove(Integer id){

        veiculoRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    /**
     * Método responsável consultar um veículo com o identificador
     *
     * @param id - Identificador do veículo para ser consultado
     * @return o veículo que possui o mesmo id do parametro
     */
    public ResponseEntity findById(Integer id) {

        Optional<Veiculo> veiculo = veiculoRepository.findById(id);
        if (veiculo.isPresent()) {
            return ResponseEntity.ok(veiculo);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        }

    }


    /**
     * Método responsável por alterar a informação de venda do veículo
     *
     * @param id - Identificador do veículo para ser consultado
     * @param veiculoVendidoDTO - Objeto que possui informações se o veiculo foi vendido ou não
     */
    @Transactional
    public ResponseEntity updateStatusVenda(Integer id, VeiculoVendidoDTO veiculoVendidoDTO) {
        veiculoRepository.updateStatusVenda(id, veiculoVendidoDTO.getVendido(), LocalDateTime.now());
        return ResponseEntity.ok().build();
    }
}
