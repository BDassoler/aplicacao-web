package br.com.senac.api.mapper;

import br.com.senac.api.entity.Pedido;
import br.com.senac.api.modelo.PedidoRequest;
import br.com.senac.api.modelo.PedidoResponse;

public class PedidoMapper {
    public static Pedido pedidoRequestParaPedido(PedidoRequest pedidoRequest){
        Pedido out = new Pedido();
        out.setId(pedidoRequest.getId());
        out.setFormaPagamento(pedidoRequest.getFormaPagamento());
        out.setValor(pedidoRequest.getValor());
        out.setNomeCliente(pedidoRequest.getFormaPagamento());

        return out;
    }

    public  static PedidoResponse pedidoParaPedidoResponse(Pedido pedido){
        PedidoResponse out = new PedidoResponse();
        out.setId(pedido.getId());
        out.setFormaPagamento(pedido.getFormaPagamento());
        out.setValor(pedido.getValor());
        out.setNomeCliente(pedido.getNomeCliente());

        return out;
    }
}
