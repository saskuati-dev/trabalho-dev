package com.devweb.vendas.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="produto_pedido")
public class ProdutoPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column private Produto idProduto;
    @Column private int quantidade;
    @Column private float precoUnitario;
    @Column private float desconto;

     @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_produto")
    private Produto produto;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_pedido")
    private Pedido pedido;

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
      }
    
      public float getPrecoUnitario() {
        return precoUnitario;
      }
    
      public float getDesconto() {
        return desconto;
      }
         
    
      public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
      }
    
      public void setPrecoUnitario(float precoUnitario) {
        this.precoUnitario = precoUnitario;
      }
    
      public void setDesconto(float desconto) {
        this.desconto = desconto;
      }
}
