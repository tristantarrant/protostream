package org.infinispan.protostream.impl.parser;

import java.util.List;

/**
 * @since 15.0
 **/
public interface FieldContainer<T extends FieldContainer<T>> {
   T addField(FieldElement fieldElement);

   List<FieldElement> fields();

   default T addMessage(MessageElement message) {
      throw new UnsupportedOperationException();
   }
}
