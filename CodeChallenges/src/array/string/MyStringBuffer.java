package array.string;

public class MyStringBuffer {
	
	private char[] value;
	private int count;
	
	public MyStringBuffer() {
		this(16);
	}

	public MyStringBuffer(String str) {
		this(16 + str.length());
		append(str);
	}

	public MyStringBuffer(int capacity){
		value = new char[capacity];
	}

	public MyStringBuffer append(String str){
		if (str == null) str = "null";
        int len = str.length();
        ensureCapacityInternal(len + count);
		str.getChars(0, len, value, count);
		count += len;
		return this;
	}
	
    private void ensureCapacityInternal(int minimumCapacity) {
        // overflow-conscious code
        if (minimumCapacity - value.length > 0)
            resize(minimumCapacity * 2 + 2);
    }

    @Override
	public String toString() {
    	return new String(value);
    }

	private void resize(int newSize) {
        char[] resizedArray = new char[newSize];
        for (int i=0; i < count; i++ ) 
        	resizedArray[i] = value[i];
        value = resizedArray;
    }

	
}
