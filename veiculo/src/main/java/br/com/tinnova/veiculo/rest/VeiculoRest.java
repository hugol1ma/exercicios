package br.com.tinnova.veiculo.rest;

import br.com.tinnova.veiculo.dto.VeiculoVendidoDTO;
import br.com.tinnova.veiculo.entidade.Veiculo;
import br.com.tinnova.veiculo.negocio.VeiculoNegocio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/veiculos")
public class VeiculoRest {

    @Autowired
    private VeiculoNegocio veiculoNegocio;

    @GetMapping
    public ResponseEntity list(){
        return veiculoNegocio.findAll();
    }

    @GetMapping(value = "/find")
    public ResponseEntity findByKeyword(@RequestParam String palavraChave){
        return veiculoNegocio.findByKeyword(palavraChave);
    }

    @PostMapping
    public ResponseEntity save(@RequestBody Veiculo veiculo){
        return veiculoNegocio.save(veiculo);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity findById(@PathVariable Integer id){
        return veiculoNegocio.findById(id);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity updateAll(@PathVariable Integer id, @RequestBody Veiculo veiculo){
        return veiculoNegocio.updateAll(id, veiculo);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity updateStatusVenda(@PathVariable Integer id, @RequestBody VeiculoVendidoDTO veiculoVendidoDTO){
        return veiculoNegocio.updateStatusVenda(id, veiculoVendidoDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable Integer id){
        return veiculoNegocio.remove(id);
    }

}
