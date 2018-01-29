package Modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TipoComponente.class)
public abstract class TipoComponente_ {

	public static volatile ListAttribute<TipoComponente, Componente> componentes;
	public static volatile SingularAttribute<TipoComponente, String> nomeComponente;
	public static volatile SingularAttribute<TipoComponente, Integer> id;
	public static volatile ListAttribute<TipoComponente, TipoAtributo> tipoAtributos;

}

