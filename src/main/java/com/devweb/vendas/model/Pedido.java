package com.devweb.vendas.model;

import java.sql.Date;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="pedido")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column private int id_pedido;
    @Column private int id_funcionario;
    @Column private int id_cliente;
    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column private Date data_pedido;
    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column private Date data_remessa;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ProdutoPedido> produtoPedidos;

    public Pedido(int id_funcionario, int id_cliente, Date data_pedido, Date data_remessa) {
        this.id_funcionario = id_funcionario;
        this.id_cliente = id_cliente;
        this.data_pedido = data_pedido;
        this.data_remessa = data_remessa;
    }
    
      // Getters
      public int getId_pedido() {
        return id_pedido;
      }
    
      public int getId_funcionario() {
        return id_funcionario;
      }
    
      public int getId_cliente() {
        return id_cliente;
      }
    
      public Date getData_pedido() {
        return data_pedido;
      }
    
      public Date getData_remessa() {
        return data_remessa;
      }
    
      // Setters
      public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
      }
    
      public void setId_funcionario(int id_funcionario) {
        this.id_funcionario = id_funcionario;
      }
    
      public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
      }
    
      public void setData_pedido(Date data_pedido) {
        this.data_pedido = data_pedido;
      }
    
      public void setData_remessa(Date data_remessa) {
        this.data_remessa = data_remessa;
      }
}
