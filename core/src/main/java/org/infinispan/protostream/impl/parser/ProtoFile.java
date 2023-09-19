package org.infinispan.protostream.impl.parser;

import java.util.ArrayList;
import java.util.List;

/**
 * @since 15.0
 **/
public class ProtoFile implements MessageContainer<ProtoFile>, OptionsContainer<ProtoFile>, EnumContainer<ProtoFile> {
   static ThreadLocal<StringBuilder> comments = ThreadLocal.withInitial(StringBuilder::new);
   private String fileName;
   private String documentation = "";
   private Syntax syntax;
   private String packageName;
   private List<String> privateImports = new ArrayList<>();
   private List<String> publicImports = new ArrayList<>();
   private List<EnumElement> enums = new ArrayList<>();
   private final List<ExtendElement> extend = new ArrayList<>();
   private List<MessageElement> messages = new ArrayList<>();
   private List<OptionElement> options = new ArrayList<>();


   public ProtoFile fileName(String fileName) {
      this.fileName = fileName;
      return this;
   }

   public String fileName() {
      return fileName;
   }

   public ProtoFile packageName(String packageName) {
      this.packageName = packageName;
      return this;
   }

   public String packageName() {
      return packageName;
   }

   public ProtoFile syntax(Syntax syntax) {
      this.syntax = syntax;
      return this;
   }

   public Syntax syntax() {
      return syntax;
   }

   public ProtoFile documentation(String documentation) {
      this.documentation = documentation;
      return this;
   }

   public String documentation() {
      return documentation;
   }

   public ProtoFile addPrivateImport(String importFile) {
      privateImports.add(importFile);
      return this;
   }

   public List<String> privateImports() {
      return privateImports;
   }

   public ProtoFile addPublicImport(String importFile) {
      publicImports.add(importFile);
      return this;
   }

   public List<String> publicImports() {
      return publicImports;
   }

   @Override
   public ProtoFile addEnum(EnumElement enumElement) {
      this.enums.add(enumElement);
      return this;
   }

   @Override
   public List<EnumElement> enums() {
      return enums;
   }

   @Override
   public ProtoFile addMessage(MessageElement message) {
      this.messages.add(message);
      return this;
   }

   @Override
   public List<MessageElement> messages() {
      return messages;
   }

   @Override
   public ProtoFile addExtend(ExtendElement e) {
      extend.add(e);
      return this;
   }

   @Override
   public List<ExtendElement> extend() {
      return extend;
   }

   @Override
   public ProtoFile addOption(OptionElement option) {
      this.options.add(option);
      return this;
   }

   @Override
   public List<OptionElement> options() {
      return options;
   }

   @Override
   public String toString() {
      return "ProtoFile{" +
            "syntax=" + syntax +
            ", packageName='" + packageName + '\'' +
            ", messages=" + messages +
            '}';
   }

   public enum Syntax {
      proto2,
      proto3;
   }
}
