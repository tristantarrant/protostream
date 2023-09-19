package org.infinispan.protostream.impl.parser;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.infinispan.protostream.descriptors.Label;

/**
 * @since 15.0
 **/
public class FieldElement extends BaseElement<FieldElement> implements OptionsContainer<FieldElement> {
   private Label label;
   private DataType type;
   private int tag;
   private final Map<String, OptionElement> options = new LinkedHashMap<>();

   public FieldElement label(Label label) {
      this.label = label;
      return this;
   }

   public Label label() {
      return label;
   }

   public FieldElement tag(int tag) {
      this.tag = tag;
      return this;
   }

   public int tag() {
      return tag;
   }

   public DataType type() {
      return type;
   }

   public FieldElement type(DataType type) {
      this.type = type;
      return this;
   }

   @Override
   public FieldElement addOption(OptionElement option) {
      options.put(option.name(), option);
      return this;
   }

   @Override
   public Collection<OptionElement> options() {
      return options.values();
   }

   public OptionElement getDefault() {
      return options.get("default");
   }

   @Override
   public String toString() {
      return "FieldElement{" +
            "name=" + name() +
            ", qualifiedName=" + qualifiedName() +
            ", label=" + label +
            ", type=" + type +
            ", tag=" + tag +
            ", options=" + options +
            '}';
   }
}
