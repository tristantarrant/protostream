package org.infinispan.protostream.impl.parser;

/**
 * @since 15.0
 **/
public class OptionElement {
   private final String name;
   private final String value;

   public OptionElement(String name, String value) {
      this.name = name;
      this.value = value;
   }

   public String name() {
      return name;
   }

   public String value() {
      return value;
   }

   @Override
   public String toString() {
      return "OptionElement{" +
            "name='" + name + '\'' +
            ", value='" + value + '\'' +
            '}';
   }
}
