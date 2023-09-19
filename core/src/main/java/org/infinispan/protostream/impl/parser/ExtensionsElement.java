package org.infinispan.protostream.impl.parser;

/**
 * @since 15.0
 **/
public class ExtensionsElement {
   private final int start;
   private final int end;

   public ExtensionsElement(int start, int end) {
      this.start = start;
      this.end = end;
   }

   @Override
   public String toString() {
      return "ExtensionsElement{" +
            "start=" + start +
            ", end=" + end +
            '}';
   }
}
