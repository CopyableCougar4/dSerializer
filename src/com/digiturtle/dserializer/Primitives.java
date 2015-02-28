package com.digiturtle.dserializer;

import java.nio.ByteBuffer;

/**
 * Encloses the serializers for primitives
 * @author Jonathan
 */
public class Primitives {
	
	public static class ByteSerializer implements Serializer<Byte> {

		@Override
		public Byte read(DSerializer dSerializer) {
			return dSerializer.getScratchBuffer(true).get();
		}

		@Override
		public void write(DSerializer dSerializer, Object object) {
			dSerializer.getScratchBuffer(false).put((byte) object);
		}

		@Override
		public Class<Byte> getClassSerialized() {
			return Byte.class;
		}
		
	}
	
	public static class ShortSerializer implements Serializer<Short> {

		@Override
		public Short read(DSerializer dSerializer) {
			return dSerializer.getScratchBuffer(true).getShort();
		}

		@Override
		public void write(DSerializer dSerializer, Object object) {
			dSerializer.getScratchBuffer(false).putShort((short) object);
		}

		@Override
		public Class<Short> getClassSerialized() {
			return Short.class;
		}
		
	}
	
	public static class IntSerializer implements Serializer<Integer> {

		@Override
		public Integer read(DSerializer dSerializer) {
			return dSerializer.getScratchBuffer(true).getInt();
		}

		@Override
		public void write(DSerializer dSerializer, Object object) {
			dSerializer.getScratchBuffer(false).putInt((int) object);
		}

		@Override
		public Class<Integer> getClassSerialized() {
			return Integer.class;
		}
		
	}
	
	public static class FloatSerializer implements Serializer<Float> {

		@Override
		public Float read(DSerializer dSerializer) {
			return dSerializer.getScratchBuffer(true).getFloat();
		}

		@Override
		public void write(DSerializer dSerializer, Object object) {
			dSerializer.getScratchBuffer(false).putFloat((float) object);
		}

		@Override
		public Class<Float> getClassSerialized() {
			return Float.class;
		}
		
	}
	
	public static class LongSerializer implements Serializer<Long> {

		@Override
		public Long read(DSerializer dSerializer) {
			return dSerializer.getScratchBuffer(true).getLong();
		}

		@Override
		public void write(DSerializer dSerializer, Object object) {
			dSerializer.getScratchBuffer(false).putLong((long) object);
		}

		@Override
		public Class<Long> getClassSerialized() {
			return Long.class;
		}
		
	}
	
	public static class DoubleSerializer implements Serializer<Double> {

		@Override
		public Double read(DSerializer dSerializer) {
			return dSerializer.getScratchBuffer(true).getDouble();
		}

		@Override
		public void write(DSerializer dSerializer, Object object) {
			dSerializer.getScratchBuffer(false).putDouble((double) object);
		}

		@Override
		public Class<Double> getClassSerialized() {
			return Double.class;
		}
		
	}
	
	public static class StringSerializer implements Serializer<String> {
		
		@Override
		public String read(DSerializer dSerializer) {
			ByteBuffer buffer = dSerializer.getScratchBuffer(true);
			char[] letters = new char[buffer.getShort()];
			for (int index = 0; index < letters.length; index++) {
				letters[index] = buffer.getChar();
			}
			return new String(letters);
		}

		@Override
		public void write(DSerializer dSerializer, Object object) {
			ByteBuffer buffer = dSerializer.getScratchBuffer(false);
			String text = (String) object;
			buffer.putShort((short) text.length());
			for (char letter : text.toCharArray()) {
				buffer.putChar(letter);
			}
		}

		@Override
		public Class<String> getClassSerialized() {
			return String.class;
		}
		
	}
	
}
