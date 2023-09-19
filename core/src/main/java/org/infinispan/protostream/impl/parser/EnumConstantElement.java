package org.infinispan.protostream.impl.parser;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @since 15.0
 **/
public class EnumConstantElement extends BaseElement<EnumConstantElement> implements OptionsContainer<EnumConstantElement> {
   private final int tag;
   private final Map<String, OptionElement> options = new LinkedHashMap<>();

   public EnumConstantElement(String name, int tag) {
      name(name);
      this.tag = tag;
   }

   public int tag() {
      return tag;
   }

   @Override
   public EnumConstantElement addOption(OptionElement option) {
      options.put(option.name(), option);
      return this;
   }

   @Override
   public Collection<OptionElement> options() {
      return options.values();
   }
}
