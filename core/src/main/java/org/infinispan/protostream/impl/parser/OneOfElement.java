package org.infinispan.protostream.impl.parser;

import java.util.ArrayList;
import java.util.List;

/**
 * @since 15.0
 **/
public class OneOfElement extends BaseElement<OneOfElement> implements FieldContainer<OneOfElement> {
   private final List<FieldElement> fields = new ArrayList<>();

   @Override
   public OneOfElement addField(FieldElement fieldElement) {
      fields.add(fieldElement);
      return this;
   }

   public List<FieldElement> fields() {
      return fields;
   }

   @Override
   public String toString() {
      return "OneOfElement{" +
            "name=" + name() +
            ", qualifiedName=" + qualifiedName() +
            ", fields=" + fields +
            '}';
   }
}
