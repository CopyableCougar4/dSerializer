package com.digiturtle.dserializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.HashMap;

import org.apache.commons.io.IOUtils;

/**
 * Represents the wrapper for all serialization, handling the I/O
 * @author Jonathan
 */
public class DSerializer {
	
	/** Reference for the serializers */
	private HashMap<Class<?>, Serializer<?>> serializers = new HashMap<Class<?>, Serializer<?>>();
	
	/** Accessor for input */
	private InputStream input;
	
	/** Accessor for output */
	private OutputStream output;
	
	/** Buffer holding all the data from the input stream */
	private ByteBuffer inBuffer;
	
	/** 512byte scratch buffer */
	private ByteBuffer scratch = ByteBuffer.allocateDirect(512);
	
	/** Holds all the bytes to write to the output */
	private ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
	
	/** Stream offset */
	private int offset;
	
	/**
	 * Initialize the serializer
	 */
	public DSerializer() {
		// Default Constructor
		register(new Primitives.ByteSerializer());
		register(new Primitives.ShortSerializer());
		register(new Primitives.IntSerializer());
		register(new Primitives.FloatSerializer());
		register(new Primitives.LongSerializer());
		register(new Primitives.DoubleSerializer());
		register(new Primitives.StringSerializer());
		register(new Arrays.ByteArraySerializer());
		register(new Arrays.ShortArraySerializer());
		register(new Arrays.IntArraySerializer());
		register(new Arrays.FloatArraySerializer());
		register(new Arrays.DoubleArraySerializer());
		register(new Arrays.LongArraySerializer());
		register(new Arrays.StringArraySerializer());
	}
	
	/**
	 * Register a serializer
	 * @param serializer Serializer instance
	 */
	public void register(Serializer<?> serializer) {
		serializers.put(serializer.getClassSerialized(), serializer);
	}
	
	/**
	 * Start reading from a stream
	 * @param input Stream
	 * @throws IOException 
	 */
	public void startRead(InputStream input) throws IOException {
		this.input = input;
		byte[] data = IOUtils.toByteArray(input);
		inBuffer = ByteBuffer.allocateDirect(data.length);
		inBuffer.put(data);
		inBuffer.flip();
	}
	
	/**
	 * Read an object from the active input
	 * @return Object read
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public Object readObject() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		Class<?> type = Class.forName((String) serializers.get(String.class).read(this));
		return serializers.get(type).read(this);
	}

	/**
	 * Stop reading from the active stream
	 * @throws IOException
	 */
	public void stopRead() throws IOException {
		if (input != null) {
			input.close();
		}
	}

	/**
	 * Start writing to a stream
	 * @param output Stream
	 */
	public void startWrite(OutputStream output) {
		this.output = output;
		offset = 0;
	}
	
	/**
	 * Write an object to the active output
	 * @param object Object to write
	 */
	public void writeObject(Object object) {
		serializers.get(String.class).write(this, object.getClass().getName());
		System.out.println(object.getClass().getName());
		serializers.get(object.getClass()).write(this, object);
	}
	
	/**
	 * Stop writing to the active stream
	 * @throws IOException 
	 */
	public void stopWrite() throws IOException {
		if (output != null) {
			// make sure to write leftover data
			byte[] data = new byte[scratch.position()];
			for (int index = 0; index < data.length; index++) {
				data[index] = scratch.get(index);
			}
			byteStream.write(data, offset, data.length);
			offset += data.length;
			byte[] dataChunk = byteStream.toByteArray();
			output.write(dataChunk);
			scratch.clear();
			scratch.position(0);
			output.flush();
			output.close();
		}
	}
	
	/**
	 * Get this serializer's scratch buffer
	 * @param read Whether this is the in or out buffer
	 * @return Scratch buffer
	 */
	public ByteBuffer getScratchBuffer(boolean read) {
		if (scratch.position() > 424 && !read) {
			byte[] data = new byte[scratch.position()];
			scratch.get(data);
			byteStream.write(data, offset, data.length);
			offset += data.length;
			scratch.clear();
			scratch.position(0);
		}
		return read ? inBuffer : scratch;
	}
	
	/**
	 * Get text from the input buffer
	 * @return Text read
	 */
	public String getText() {
		char[] letters = new char[inBuffer.getShort()];
		for (int index = 0; index < letters.length; index++) {
			letters[index] = inBuffer.getChar();
		}
		return new String(letters);
	}
	
	/**
	 * Write text to the output buffer
	 * @param text Text to write
	 */
	public void putText(String text) {
		getScratchBuffer(false); // flushes any overfilled data
		scratch.putShort((short) text.length());
		for (char letter : text.toCharArray()) {
			scratch.putChar(letter);
		}
		getScratchBuffer(false); // flushes any overfilled data
	}
	
}
