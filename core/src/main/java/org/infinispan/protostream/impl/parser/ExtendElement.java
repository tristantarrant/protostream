package org.infinispan.protostream.impl.parser;

import java.util.ArrayList;
import java.util.List;

/**
 * @since 15.0
 **/
public class ExtendElement extends BaseElement<ExtendElement> implements FieldContainer<ExtendElement> {
   private final List<FieldElement> fields = new ArrayList<>();

   @Override
   public ExtendElement addField(FieldElement fieldElement) {
      fields.add(fieldElement);
      return this;
   }

   @Override
   public List<FieldElement> fields() {
      return fields;
   }

   @Override
   public String toString() {
      return "ExtendElement{" +
            "name=" + name() +
            ", qualifiedName=" + qualifiedName() +
            ", fields=" + fields +
            '}';
   }
}
