module org.infinispan.protostream.core {
   requires com.fasterxml.jackson.core;
   requires com.fasterxml.jackson.databind;
   requires java.compiler;
   requires static jcip.annotations;
   requires jdk.unsupported;
   requires org.jboss.logging;
   requires org.jboss.logging.annotations;

   exports org.infinispan.protostream;
   exports org.infinispan.protostream.annotations;
   exports org.infinispan.protostream.config;
   exports org.infinispan.protostream.containers;
   exports org.infinispan.protostream.descriptors;
   exports org.infinispan.protostream.exception;
   exports org.infinispan.protostream.schema;

   exports org.infinispan.protostream.annotations.impl to org.infinispan.protostream.processor;
   exports org.infinispan.protostream.annotations.impl.types to org.infinispan.protostream.processor;
   exports org.infinispan.protostream.impl to org.infinispan.protostream.processor;
}
