package Modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TipoAtributo.class)
public abstract class TipoAtributo_ {

	public static volatile SingularAttribute<TipoAtributo, String> nomeAtributo;
	public static volatile ListAttribute<TipoAtributo, TipoComponente> tipoComponentes;
	public static volatile SingularAttribute<TipoAtributo, Integer> id;
	public static volatile ListAttribute<TipoAtributo, Atributo> atributos;

}

