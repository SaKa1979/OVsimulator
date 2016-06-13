package controler;

/**
 * @brief Keeps track of the revision numbering
 * TODO use Maven plug in for revision numbering
 */
public class Revision {
  
  public Revision()
  {}

  public static String toSString() {
    StringBuilder sBuilder = new StringBuilder();
    sBuilder.append("v" + versionNumber);
    sBuilder.append(" build " + buildNumber + "\n");
    sBuilder.append("date " + date);
    return sBuilder.toString();
  }

  static String buildNumber = "b581b65";
  static String versionNumber = "1.0.0";
  static String date = "2016-06-10";
}
