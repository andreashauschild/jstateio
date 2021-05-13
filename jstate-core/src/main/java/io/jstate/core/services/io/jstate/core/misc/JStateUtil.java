package io.jstate.core.services.io.jstate.core.misc;

import io.jstate.spi.ProcessInstance;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

public class JStateUtil {

    public static String toString(Throwable e) {

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString();
    }

    public static <T> T cloneObject(T object) {

        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(object);
            oos.flush();
            oos.close();
            bos.close();
            byte[] byteData = bos.toByteArray();

            ByteArrayInputStream bais = new ByteArrayInputStream(byteData);
            return (T) new ObjectInputStream(bais).readObject();
        } catch (Exception e) {
            throw new RuntimeException("Error object could not be cloned", e);
        }

    }

    public static <T> String nullSafe(T obj, DoIfNotNullString<T> notNull) {

        if (obj == null) {
            return "null";
        } else {
            return notNull.execute(obj);
        }
    }

    public interface DoIfNotNullString<T> {

        String execute(T notNull);
    }


}
