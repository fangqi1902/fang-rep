package com.sxt.sys.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializUtil {

	// 序列化 对象 ---》字节数组
	public static byte[] serialization(Object object) {
		ByteArrayOutputStream byteArrayOutputStream = null;
		ObjectOutputStream objectOutputStream = null;
		byte[] obs = null;
		try {
			byteArrayOutputStream = new ByteArrayOutputStream();
			objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
			objectOutputStream.writeObject(object);
			obs = byteArrayOutputStream.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != objectOutputStream) {
				try {
					objectOutputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					// 对象失去引用，jvm回收
					objectOutputStream = null;
				}
			}
			if (null != byteArrayOutputStream) {
				try {
					byteArrayOutputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					// 对象失去引用，jvm回收
					byteArrayOutputStream = null;
				}
			}

		}
		return obs;

	}

	// 序列化 字节数组--。。对象
	public static Object deSerialization(byte[] bytes) {
		ByteArrayInputStream byteArrayInputStream =null;
		ObjectInputStream objectInputStream =null;
		Object obj = null;
		try {
			byteArrayInputStream = new ByteArrayInputStream(bytes);
			objectInputStream = new ObjectInputStream(byteArrayInputStream);
			obj = objectInputStream.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != objectInputStream) {
				try {
					objectInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					// 对象失去引用，jvm回收
					objectInputStream = null;
				}
			}
			if (null != byteArrayInputStream) {
				try {
					byteArrayInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					// 对象失去引用，jvm回收
					byteArrayInputStream = null;
				}
			}
		}
		return obj;

	}
}
