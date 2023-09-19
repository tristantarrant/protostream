package org.infinispan.protostream.impl.parser.mappers;

import static org.infinispan.protostream.impl.parser.mappers.Mappers.ENUM_LIST_MAPPER;
import static org.infinispan.protostream.impl.parser.mappers.Mappers.FIELD_LIST_MAPPER;
import static org.infinispan.protostream.impl.parser.mappers.Mappers.MESSAGE_LIST_MAPPER;
import static org.infinispan.protostream.impl.parser.mappers.Mappers.ONEOF_LIST_MAPPER;
import static org.infinispan.protostream.impl.parser.mappers.Mappers.OPTION_LIST_MAPPER;
import static org.infinispan.protostream.impl.parser.mappers.Mappers.filter;

import java.util.List;

import org.infinispan.protostream.descriptors.Descriptor;
import org.infinispan.protostream.impl.parser.EnumElement;
import org.infinispan.protostream.impl.parser.MessageElement;

/**
 * Mapper for Type.
 *
 * @author gustavonalle
 * @since 2.0
 */
final class MessageTypeMapper implements Mapper<MessageElement, Descriptor> {

   @Override
   public Descriptor map(MessageElement type) {
      List<MessageElement> nestedMessageTypes = filter(type.messages(), MessageElement.class);
      List<EnumElement> nestedEnumTypes = filter(type.enums(), EnumElement.class);
      return new Descriptor.Builder()
            .withFullName(type.qualifiedName())
            .withName(type.name())
            .withFields(FIELD_LIST_MAPPER.map(type.fields()))
            .withOneOfs(ONEOF_LIST_MAPPER.map(type.oneofs()))
            .withEnumTypes(ENUM_LIST_MAPPER.map(nestedEnumTypes))
            .withNestedTypes(MESSAGE_LIST_MAPPER.map(nestedMessageTypes))
            .withOptions(OPTION_LIST_MAPPER.map(type.options()))
            .withDocumentation(type.documentation())
            .build();
   }
}
