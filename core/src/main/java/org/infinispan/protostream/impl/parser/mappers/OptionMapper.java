package org.infinispan.protostream.impl.parser.mappers;

import org.infinispan.protostream.descriptors.Option;
import org.infinispan.protostream.impl.parser.OptionElement;

/**
 * @author gustavonalle
 * @since 2.0
 */
final class OptionMapper implements Mapper<OptionElement, Option> {

   @Override
   public Option map(OptionElement input) {
      return new Option(input.name(), input.value());
   }
}
