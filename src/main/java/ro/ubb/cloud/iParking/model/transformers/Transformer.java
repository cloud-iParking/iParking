package ro.ubb.cloud.iParking.model.transformers;

/**
 * Interface for the transformers between entities and data transfer objects
 *
 * @param <E> entity
 * @param <D> dto
 */
public interface Transformer<E, D> {

    D toDTO(E entity);

    E toEntity(D dto);
}
