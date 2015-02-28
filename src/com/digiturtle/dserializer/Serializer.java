package com.digiturtle.dserializer;

/**
 * Represents a serializer for objects of type T
 * @author Jonathan
 */
public interface Serializer<T> {
	
	public T read(DSerializer dSerializer);
	
	public void write(DSerializer dSerializer, Object object);
	
	public Class<T> getClassSerialized();

}
