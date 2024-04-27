package org.camposmdev.client.entity.component;

/**
 * The component is an attribute representing of some type
 * @param <T>
 */
public interface AttributeComponent<T> {
	T max();
	T current();
}
