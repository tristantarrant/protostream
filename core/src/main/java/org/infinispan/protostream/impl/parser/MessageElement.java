package org.infinispan.protostream.impl.parser;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @since 15.0
 **/
public class MessageElement extends BaseElement<MessageElement> implements MessageContainer<MessageElement>, OptionsContainer<MessageElement>, EnumContainer<MessageElement>, FieldContainer<MessageElement> {
   private final List<EnumElement> enums = new ArrayList<>();
   private final List<ExtendElement> extend = new ArrayList<>();
   private final List<ExtensionsElement> extensions = new ArrayList<>();
   private final List<FieldElement> fields = new ArrayList<>();

   private final List<OneOfElement> oneofs = new ArrayList<>();

   private final List<MessageElement> messages = new ArrayList<>();
   private final Map<String, OptionElement> options = new LinkedHashMap<>();
   private final BitSet reservedNumbers = new BitSet();
   private final Set<String> reservedNames = new HashSet<>();

   @Override
   public MessageElement addEnum(EnumElement enumElement) {
      this.enums.add(enumElement);
      return this;
   }

   @Override
   public List<EnumElement> enums() {
      return enums;
   }

   public MessageElement addExtend(ExtendElement extend) {
      this.extend.add(extend);
      return this;
   }

   public List<ExtendElement> extend() {
      return extend;
   }

   public MessageElement addExtensions(int start, int end) {
      extensions.add(new ExtensionsElement(start, end));
      return this;
   }

   @Override
   public MessageElement addField(FieldElement fieldElement) {
      fields.add(fieldElement);
      return this;
   }

   @Override
   public List<FieldElement> fields() {
      return fields;
   }

   public void addOneOf(OneOfElement oneof) {
      oneofs.add(oneof);
   }

   public List<OneOfElement> oneofs() {
      return oneofs;
   }

   @Override
   public MessageElement addMessage(MessageElement message) {
      this.messages.add(message);
      return this;
   }

   @Override
   public List<MessageElement> messages() {
      return messages;
   }

   @Override
   public MessageElement addOption(OptionElement option) {
      this.options.put(option.name(), option);
      return this;
   }

   @Override
   public Collection<OptionElement> options() {
      return options.values();
   }

   public void addReserved(int number) {
      reservedNumbers.set(number);
   }

   public void addReserved(int from, int to) {
      reservedNumbers.set(from, to + 1);
   }

   public void addReserved(String name) {
      reservedNames.add(name);
   }

   public boolean isReserved(String name) {
      return reservedNames.contains(name);
   }

   public boolean isReserved(int number) {
      return reservedNumbers.get(number);
   }

   public int[] reservedNumbers() {
      return reservedNumbers.stream().toArray();
   }

   public String[] reservedNames() {
      return reservedNames.toArray(String[]::new);
   }

   @Override
   public String toString() {
      return "MessageElement{" +
            "name=" + name() +
            ", qualifiedName=" + qualifiedName() +
            ", enums=" + enums +
            ", extend=" + extend +
            ", extensions=" + extensions +
            ", fields=" + fields +
            ", oneofs=" + oneofs +
            ", messages=" + messages +
            ", options=" + options +
            ", reservedNumbers=" + reservedNumbers +
            ", reservedNames=" + reservedNames +
            '}';
   }
}
