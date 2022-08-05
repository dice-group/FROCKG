package org.dice.frockg.utilities;

import java.net.URI;

public class Validator {
  public static boolean IsUriValid(String uri) {
    // TODO should improve
    final URI u;
    try {
      u = new URI(uri);
      u.toURL();
    } catch (Exception e1) {
      return false;
    }
    return true;
  }
}
