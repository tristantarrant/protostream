package org.infinispan.protostream.impl.parser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @since 15.0
 **/
public class EnumElement extends BaseElement<EnumElement> implements OptionsContainer<EnumElement> {

   private final List<EnumConstantElement> constants = new ArrayList<>();
   private final Map<String, OptionElement> options = new LinkedHashMap<>();

   public EnumElement addEnumConstant(EnumConstantElement constant) {
      OptionElement allowAlias = options.get("allow_alias");
      if (allowAlias == null || !"true".equals(allowAlias.value())) {
         for (EnumConstantElement c : constants) {
            if (c.tag() == constant.tag()) {
               throw new IllegalStateException("Duplicate tag " + c.tag() + " in " + qualifiedName());
            }
         }
      }
      constants.add(constant);
      return this;
   }

   public List<EnumConstantElement> constants() {
      return constants;
   }

   @Override
   public EnumElement addOption(OptionElement option) {
      options.put(option.name(), option);
      return this;
   }

   @Override
   public Collection<OptionElement> options() {
      return options.values();
   }

   @Override
   public String toString() {
      return "EnumElement{" +
            "name=" + name() +
            ", qualifiedName=" + qualifiedName() +
            ", constants=" + constants +
            ", options=" + options +
            '}';
   }
}
