package org.infinispan.protostream.impl.parser;

import java.util.List;

/**
 * @since 15.0
 **/
public interface EnumContainer<T extends EnumContainer<T>> {
   T addEnum(EnumElement element);

   List<EnumElement> enums();
}
