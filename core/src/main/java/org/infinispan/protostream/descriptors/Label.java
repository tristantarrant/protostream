package org.infinispan.protostream.descriptors;

/**
 * Rules associated with a field
 *
 * @author gustavonalle
 * @since 2.0
 */
public enum Label {

   REQUIRED,

   OPTIONAL,

   REPEATED,

   /**
    * Indicates a field that is a member of a {@code oneof} element. It is an implicitly optional and non-repeated
    * field.
    */
   ONE_OF;

   public static Label fromString(String label) {
      switch (label) {
         case "required":
            return REQUIRED;
         case "repeated":
            return REPEATED;
         case "optional":
            return OPTIONAL;
         case "oneof":
            return ONE_OF;
         default:
            throw new IllegalArgumentException(label);
      }
   }
}
