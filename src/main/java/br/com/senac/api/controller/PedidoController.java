package br.com.senac.api.controller;

import br.com.senac.api.entity.Pedido;
import br.com.senac.api.mapper.PedidoMapper;
import br.com.senac.api.modelo.DataReponse;
import br.com.senac.api.modelo.InfoRow;
import br.com.senac.api.modelo.PedidoRequest;
import br.com.senac.api.modelo.PedidoResponse;
import br.com.senac.api.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/pedidos")
public class PedidoController {
    @Autowired
    private PedidoRepository pedidoRepository;

    @PostMapping("/criar")
    public ResponseEntity<PedidoResponse> criarPedido(@RequestBody PedidoRequest pedidoRequest){
        Pedido pedido = pedidoRepository.save(PedidoMapper.pedidoRequestParaPedido(pedidoRequest));

        PedidoResponse out = PedidoMapper.pedidoParaPedidoResponse(pedido);

        return ResponseEntity.status(201).body(out);
    }

    @GetMapping("/carregar")
    public ResponseEntity<DataReponse> carregarPedidos(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "1") int size){
        Pageable paging = PageRequest.of(page, size);

        Page<Pedido> pedidoList = pedidoRepository.findAll(paging);

        List<PedidoResponse> pedidosResponseList = pedidoList.getContent()
                .stream()
                .map(PedidoMapper::pedidoParaPedidoResponse)
                .collect(Collectors.toList());

        InfoRow infoRow = new InfoRow();
        // numero da pagina atual
        infoRow.setPage(pedidoList.getNumber() + 1);
        // total de paginas a serem listadas
        infoRow.setPageCount(pedidoList.getTotalPages());
        // total de elementos
        infoRow.setTotalElements(pedidoList.getTotalElements());

        DataReponse out = new DataReponse();
        out.setInfoRows(infoRow);
        out.setRows(pedidosResponseList);

        return ResponseEntity.ok(out);
    }
}
