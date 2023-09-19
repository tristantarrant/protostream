package org.infinispan.protostream.impl.parser;

/**
 * @since 15.0
 **/
public abstract class BaseElement<T extends BaseElement<T>> {
   private String name;
   private String qualifiedName;
   private String documentation = "";

   public String name() {
      return name;
   }

   public T name(String name) {
      this.name = name;
      return (T) this;
   }

   public String qualifiedName() {
      return qualifiedName;
   }

   public T prefix(String prefix) {
      this.qualifiedName = prefix != null ? prefix + '.' + name : name;
      return (T) this;
   }

   public String documentation() {
      return documentation;
   }

   public T documentation(String documentation) {
      this.documentation = documentation;
      return (T) this;
   }

   @Override
   public String toString() {
      return "BaseElement{" +
            "name='" + name + '\'' +
            ", qualifiedName='" + qualifiedName + '\'' +
            ", documentation='" + documentation + '\'' +
            '}';
   }
}
