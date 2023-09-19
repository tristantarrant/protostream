package org.infinispan.protostream.impl.parser;

import java.util.Collection;

/**
 * @since 15.0
 **/
public interface OptionsContainer<T extends OptionsContainer<T>> {
   T addOption(OptionElement option);

   Collection<OptionElement> options();
}
