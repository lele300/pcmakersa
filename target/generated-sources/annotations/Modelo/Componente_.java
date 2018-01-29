package Modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Componente.class)
public abstract class Componente_ {

	public static volatile SingularAttribute<Componente, Double> preco;
	public static volatile SingularAttribute<Componente, String> marca;
	public static volatile SingularAttribute<Componente, TipoComponente> tipoComponente;
	public static volatile SingularAttribute<Componente, Integer> id;
	public static volatile SingularAttribute<Componente, String> modelo;
	public static volatile SingularAttribute<Componente, Integer> quantidade;
	public static volatile ListAttribute<Componente, Atributo> atributos;
	public static volatile SingularAttribute<Componente, String> descricao;

}

