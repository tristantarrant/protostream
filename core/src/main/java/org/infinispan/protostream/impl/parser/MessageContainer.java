package org.infinispan.protostream.impl.parser;

import java.util.List;

/**
 * @since 15.0
 **/
public interface MessageContainer<T extends MessageContainer<T>> {
   T addMessage(MessageElement message);

   List<MessageElement> messages();

   T addExtend(ExtendElement e);

   List<ExtendElement> extend();
}
