package org.after90.sometest;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.junit.Test;


public class PeopleTest {

  @Test
  public void SerializableTest() {
    try {
      serializeFlyPig();
      People people = deserializeFlyPig();
      System.out.println(people.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 序列化
   */
  private static void serializeFlyPig() throws IOException {
    People people = new People();
    people.setColor("black");
    people.setName("naruto");
    people.setCar("0000");

    // ObjectOutputStream 对象输出流，将 flyPig 对象存储到E盘的 flyPig.txt 文件中，完成对 flyPig 对象的序列化操作
    ObjectOutputStream oos = new ObjectOutputStream(
        new FileOutputStream(new File("./data/people.txt")));
    oos.writeObject(people);
    System.out.println("FlyPig 对象序列化成功！");
    oos.close();
  }

  /**
   * 反序列化
   */
  private static People deserializeFlyPig() throws Exception {
    ObjectInputStream ois = new ObjectInputStream(
        new FileInputStream(new File("./data/people.txt")));
    People people = (People) ois.readObject();
    System.out.println("FlyPig 对象反序列化成功！");
    return people;
  }
}