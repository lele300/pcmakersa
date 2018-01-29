package Modelo;

import Enum.StatusPedido;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Pedido.class)
public abstract class Pedido_ {

	public static volatile ListAttribute<Pedido, Componente> itensComponente;
	public static volatile SingularAttribute<Pedido, Double> valorTotal;
	public static volatile SingularAttribute<Pedido, String> dataPedido;
	public static volatile SingularAttribute<Pedido, StatusPedido> statusPedido;
	public static volatile SingularAttribute<Pedido, Integer> idPedido;
	public static volatile SingularAttribute<Pedido, Usuario> usuarioDoPedido;

}

