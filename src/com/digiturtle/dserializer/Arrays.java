package com.digiturtle.dserializer;

/**
 * Encapsulates the serializers for arrays
 * @author Jonathan
 */
public class Arrays {
	
	public static class ByteArraySerializer implements Serializer<byte[]> {

		@Override
		public byte[] read(DSerializer dSerializer) {
			byte[] data = new byte[dSerializer.getScratchBuffer(true).getShort()];
			for (int index = 0; index < data.length; index++) {
				data[index] = dSerializer.getScratchBuffer(true).get();
			}
			return data;
		}

		@Override
		public void write(DSerializer dSerializer, Object object) {
			byte[] data = (byte[]) object;
			dSerializer.getScratchBuffer(false).putShort((short) data.length);
			for (int index = 0; index < data.length; index++) {
				dSerializer.getScratchBuffer(false).put(data[index]);
			}
		}

		@Override
		public Class<byte[]> getClassSerialized() {
			return byte[].class;
		}
		
	}
	
	public static class ShortArraySerializer implements Serializer<short[]> {

		@Override
		public short[] read(DSerializer dSerializer) {
			short[] data = new short[dSerializer.getScratchBuffer(true).getShort()];
			for (int index = 0; index < data.length; index++) {
				data[index] = dSerializer.getScratchBuffer(true).getShort();
			}
			return data;
		}

		@Override
		public void write(DSerializer dSerializer, Object object) {
			short[] data = (short[]) object;
			dSerializer.getScratchBuffer(false).putShort((short) data.length);
			for (int index = 0; index < data.length; index++) {
				dSerializer.getScratchBuffer(false).putShort(data[index]);
			}
		}

		@Override
		public Class<short[]> getClassSerialized() {
			return short[].class;
		}
		
	}
	
	public static class IntArraySerializer implements Serializer<int[]> {

		@Override
		public int[] read(DSerializer dSerializer) {
			int[] data = new int[dSerializer.getScratchBuffer(true).getShort()];
			for (int index = 0; index < data.length; index++) {
				data[index] = dSerializer.getScratchBuffer(true).getInt();
			}
			return data;
		}

		@Override
		public void write(DSerializer dSerializer, Object object) {
			int[] data = (int[]) object;
			dSerializer.getScratchBuffer(false).putShort((short) data.length);
			for (int index = 0; index < data.length; index++) {
				dSerializer.getScratchBuffer(false).putInt(data[index]);
			}
		}

		@Override
		public Class<int[]> getClassSerialized() {
			return int[].class;
		}
		
	}
	
	public static class FloatArraySerializer implements Serializer<float[]> {

		@Override
		public float[] read(DSerializer dSerializer) {
			float[] data = new float[dSerializer.getScratchBuffer(true).getShort()];
			for (int index = 0; index < data.length; index++) {
				data[index] = dSerializer.getScratchBuffer(true).getFloat();
			}
			return data;
		}

		@Override
		public void write(DSerializer dSerializer, Object object) {
			float[] data = (float[]) object;
			dSerializer.getScratchBuffer(false).putShort((short) data.length);
			for (int index = 0; index < data.length; index++) {
				dSerializer.getScratchBuffer(false).putFloat(data[index]);
			}
		}

		@Override
		public Class<float[]> getClassSerialized() {
			return float[].class;
		}
		
	}
	
	public static class DoubleArraySerializer implements Serializer<double[]> {

		@Override
		public double[] read(DSerializer dSerializer) {
			double[] data = new double[dSerializer.getScratchBuffer(true).getShort()];
			for (int index = 0; index < data.length; index++) {
				data[index] = dSerializer.getScratchBuffer(true).getDouble();
			}
			return data;
		}

		@Override
		public void write(DSerializer dSerializer, Object object) {
			double[] data = (double[]) object;
			dSerializer.getScratchBuffer(false).putShort((short) data.length);
			for (int index = 0; index < data.length; index++) {
				dSerializer.getScratchBuffer(false).putDouble(data[index]);
			}
		}

		@Override
		public Class<double[]> getClassSerialized() {
			return double[].class;
		}
		
	}
	
	public static class LongArraySerializer implements Serializer<long[]> {

		@Override
		public long[] read(DSerializer dSerializer) {
			long[] data = new long[dSerializer.getScratchBuffer(true).getShort()];
			for (int index = 0; index < data.length; index++) {
				data[index] = dSerializer.getScratchBuffer(true).getLong();
			}
			return data;
		}

		@Override
		public void write(DSerializer dSerializer, Object object) {
			long[] data = (long[]) object;
			dSerializer.getScratchBuffer(false).putShort((short) data.length);
			for (int index = 0; index < data.length; index++) {
				dSerializer.getScratchBuffer(false).putLong(data[index]);
			}
		}

		@Override
		public Class<long[]> getClassSerialized() {
			return long[].class;
		}
		
	}
	
	public static class StringArraySerializer implements Serializer<String[]> {

		@Override
		public String[] read(DSerializer dSerializer) {
			String[] data = new String[dSerializer.getScratchBuffer(true).getShort()];
			for (int index = 0; index < data.length; index++) {
				data[index] = dSerializer.getText();
			}
			return data;
		}

		@Override
		public void write(DSerializer dSerializer, Object object) {
			String[] data = (String[]) object;
			dSerializer.getScratchBuffer(false).putShort((short) data.length);
			for (int index = 0; index < data.length; index++) {
				dSerializer.putText(data[index]);
			}
		}

		@Override
		public Class<String[]> getClassSerialized() {
			return String[].class;
		}
		
	}

}
