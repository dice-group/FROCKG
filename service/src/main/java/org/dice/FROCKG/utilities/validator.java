package org.dice.FROCKG.utilities;

import java.net.URI;

public class validator {
  public static boolean isUriValid(String uri) {
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
