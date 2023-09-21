package org.infinispan.protostream.impl.parser;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import org.infinispan.protostream.descriptors.Label;
import org.junit.Test;

/**
 * @since 15.0
 **/

public class ParserTest {

   @Test
   public void testParser() throws IOException, ParseException {
      try (Reader r = new InputStreamReader(ParserTest.class.getClassLoader().getResourceAsStream("sample_bank_account/bank.proto"))) {
         ProtoParser p = new ProtoParser(r);
         ProtoFile input = p.Input();

         assertEquals("sample_bank_account", input.packageName());
         assertEquals(5, input.messages().size());

         // User
         MessageElement message = assertMessage(input, 0, "User", 12, 1, 1);
         assertField(message, 0, Label.REQUIRED, DataType.ScalarType.INT32, "id", 1);
         assertField(message, 1, Label.REPEATED, DataType.ScalarType.INT32, "accountIds", 2);
         assertField(message, 2, Label.REQUIRED, DataType.ScalarType.STRING, "name", 3);
         assertField(message, 3, Label.OPTIONAL, DataType.ScalarType.STRING, "surname", 4);
         assertField(message, 4, Label.OPTIONAL, DataType.ScalarType.STRING, "salutation", 5);
         assertField(message, 5, Label.REPEATED, DataType.create("Address"), "addresses", 6);
         assertField(message, 6, Label.OPTIONAL, DataType.ScalarType.INT32, "age", 7);
         assertField(message, 7, Label.OPTIONAL, DataType.create("Gender"), "gender", 8);
         assertField(message, 8, Label.OPTIONAL, DataType.ScalarType.STRING, "notes", 9);
         assertField(message, 9, Label.OPTIONAL, DataType.ScalarType.FIXED64, "creationDate", 10);
         assertField(message, 10, Label.OPTIONAL, DataType.ScalarType.FIXED64, "passwordExpirationDate", 11);
         assertField(message, 11, Label.OPTIONAL, DataType.ScalarType.INT64, "qrCode", 12);
         assertEnum(message, 0, "Gender", "MALE", "FEMALE", "UNSPECIFIED");

         // User.Address
         message = assertMessage(message, 0, "Address", 4, 0, 0);
         assertField(message, 0, Label.REQUIRED, DataType.ScalarType.STRING, "street", 1);
         assertField(message, 1, Label.REQUIRED, DataType.ScalarType.STRING, "postCode", 2);
         assertField(message, 2, Label.REQUIRED, DataType.ScalarType.INT32, "number", 3);
         assertField(message, 3, Label.REQUIRED, DataType.ScalarType.BOOL, "isCommercial", 4);

         // Account
         message = assertMessage(input, 1, "Account", 7, 1, 1);
         assertReserved(message, "alpha", "beta", "gamma");
         assertReserved(message, 8, 10, 11, 13, 14, 15, 17, 19, 20, 22);
         assertField(message, 0, Label.REQUIRED, DataType.ScalarType.INT32, "id", 1);
         assertField(message, 1, Label.OPTIONAL, DataType.ScalarType.STRING, "description", 2);
         assertFieldOptions(message, 1, "default", "Checking account");
         assertField(message, 2, Label.REQUIRED, DataType.ScalarType.FIXED64, "creationDate", 3);
         assertField(message, 3, Label.OPTIONAL, DataType.create("Limits"), "limits", 4);
         assertField(message, 4, Label.REQUIRED, DataType.create("Limits"), "hardLimits", 5);
         assertField(message, 5, Label.REPEATED, DataType.ScalarType.BYTES, "blurb", 6);
         assertField(message, 6, Label.REPEATED, DataType.create("Currency"), "currencies", 7);
         assertEnum(message, 0, "Currency", "EUR", "GBP", "USD", "BRL");

         // Account.Limits
         message = assertMessage(message, 0, "Limits", 3, 0, 0);
         assertField(message, 0, Label.OPTIONAL, DataType.ScalarType.DOUBLE, "maxDailyLimit", 1);
         assertField(message, 1, Label.OPTIONAL, DataType.ScalarType.DOUBLE, "maxTransactionLimit", 2);
         assertField(message, 2, Label.REPEATED, DataType.ScalarType.STRING, "payees", 3);

         // Transaction
         message = assertMessage(input, 2, "Transaction", 9, 0, 0);
         assertField(message, 0, Label.REQUIRED, DataType.ScalarType.INT32, "id", 1);
         assertField(message, 1, Label.OPTIONAL, DataType.ScalarType.STRING, "description", 2);
         assertField(message, 2, Label.OPTIONAL, DataType.ScalarType.STRING, "longDescription", 3);
         assertField(message, 3, Label.OPTIONAL, DataType.ScalarType.STRING, "notes", 4);
         assertField(message, 4, Label.REQUIRED, DataType.ScalarType.INT32, "accountId", 5);
         assertField(message, 5, Label.REQUIRED, DataType.ScalarType.FIXED64, "date", 6);
         assertField(message, 6, Label.REQUIRED, DataType.ScalarType.DOUBLE, "amount", 7);
         assertField(message, 7, Label.REQUIRED, DataType.ScalarType.BOOL, "isDebit", 8);
         assertField(message, 8, Label.REQUIRED, DataType.ScalarType.BOOL, "isValid", 9);
         OneOfElement oneof = assertOneOf(message, 0, "choice");
         assertField(oneof, 0, Label.ONE_OF, DataType.ScalarType.STRING, "one", 10);
         assertField(oneof, 1, Label.ONE_OF, DataType.ScalarType.INT32, "or_the_other", 11);

         // int_array
         message = assertMessage(input, 3, "int_array", 1, 0, 0);
         assertField(message, 0, Label.REPEATED, DataType.ScalarType.INT32, "theArray", 1);

         // int_array
         message = assertMessage(input, 4, "user_list", 1, 0, 0);
         assertField(message, 0, Label.REPEATED, DataType.create("User"), "theList", 1);

      }
   }

   private void assertReserved(MessageElement message, String... names) {
      for(String name : names) {
         assertTrue(name, message.isReserved(name));
      }
   }

   private void assertReserved(MessageElement message, int... numbers) {
      assertArrayEquals(numbers, message.reservedNumbers());
   }

   private void assertEnum(MessageElement message, int index, String name, String... values) {
      EnumElement enumElement = message.enums().get(index);
      assertEquals(name, enumElement.name());
      assertEquals(values.length, enumElement.constants().size());
      int i = 0;
      for (EnumConstantElement entry : enumElement.constants()) {
         assertEquals(values[i], entry.name());
         assertEquals(i, entry.tag());
         i++;
      }
   }

   private OneOfElement assertOneOf(MessageElement message, int index, String name) {
      OneOfElement oneof = message.oneofs().get(index);
      assertEquals(name, oneof.name());
      return oneof;
   }

   private void assertField(FieldContainer<?> container, int index, Label label, DataType type, String name, int tag) {
      FieldElement field = container.fields().get(index);
      assertEquals(label, field.label());
      assertEquals(type, field.type());
      assertEquals(name, field.name());
      assertEquals(tag, field.tag());
   }

   private void assertFieldOptions(MessageElement message, int index, String... options) {
      FieldElement field = message.fields().get(index);
      assertEquals(options.length / 2, field.options().size());
      int i = 0;
      for (OptionElement option : field.options()) {
         assertEquals(options[i++], option.name());
         assertEquals(options[i++], option.value());
      }
   }

   private static MessageElement assertMessage(MessageContainer<?> container, int index, String name, int fieldCount, int messageCount, int enumCount) {
      MessageElement message = container.messages().get(index);
      assertEquals(name, message.name());
      assertEquals("Message " + name + " fields", fieldCount, message.fields().size());
      assertEquals("Message " + name + " messages", messageCount, message.messages().size());
      assertEquals("Message " + name + " enums", enumCount, message.enums().size());
      return message;
   }
}
